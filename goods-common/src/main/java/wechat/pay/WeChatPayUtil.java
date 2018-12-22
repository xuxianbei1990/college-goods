package wechat.pay;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 微信支付工具
 * User: xuxb
 * Date: 2018-10-19
 * Time: 9:43
 * Version:V1.0
 */
public class WeChatPayUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatPayUtil.class);

    private static final AtomicInteger id = new AtomicInteger(0);

    private static final WeChatPayUtil weChatPayUtil = new WeChatPayUtil();

    private static final ThreadLocal<PayCommonParam> THREAD_LOCAL = new ThreadLocal();

    private WeChatPayUtil() {
    }

    public static WeChatPayUtil getInstance() {
        return weChatPayUtil;
    }


    // 一毫秒 + 最大流水号 2147483647; 分布式 的话，需要是使用分布式id
    // 该id无法满足分布式需求; 简单扩展思路就是在加一位作为分布式id即可满足9台集群。
    // 最好还是用分布式id
    // 线程安全，测试用例2000线程
    private String tradeNo() {
        final int i = id.incrementAndGet();
        return System.currentTimeMillis() + "_" + String.format("%010d", i);
    }

    /***
     * 微信支付后异步回调
     * */
    public boolean callback(HttpServletRequest request, String tradeType, PayCommonParam payCommonParam) {
        THREAD_LOCAL.set(payCommonParam);
        Map<String, String> params = new HashMap<>();
        params.put("body", getBody(request));
        //读取xml内容转为map
        params = readStringXmlOut(params.getOrDefault("body", ""));

        LOGGER.info("微信支付回调参数 [ {} ]", params);

        //商户订单号
        String out_trade_no = params.getOrDefault("out_trade_no", "");
//        wechatPayDTO.setOutTradeNo(out_trade_no);
//        wechatPayDTO.setTransactionId(params.getOrDefault("transaction_id", ""));
        //业务结果SUCCESS或者FAIL
        String result_code = params.getOrDefault("result_code", "");
        if ("SUCCESS".equals(result_code)) {
//            wechatPayDTO.setResultCode((byte) 1);
        }
        //支付金额 (分)
        String total_fee = params.getOrDefault("total_fee", "");
//        wechatPayDTO.setTotalFee(new BigDecimal(total_fee));
        //支付完成时间
        String time_end = params.getOrDefault("time_end", "");
//        wechatPayDTO.setTimeEnd(time_end);
        //签名
        String sign = params.getOrDefault("sign", "");

        //商家数据包，原样返回
        String attach = params.getOrDefault("attach", "");

        //排除sign参数的所有参数，参与签名 , 验证微信回调的签名
        Map<String, String> map = new HashMap<>();
        for (String s : params.keySet()) {
            String value = params.get(s);
            if (StringUtils.isNotBlank(value) && !StringUtils.equals(s, "sign")) {
                map.put(s, value);
            }
        }

        String comSign = getSign(map, THREAD_LOCAL.get().secret);

        if (null != result_code && "SUCCESS".equals(result_code)) {
            if (StringUtils.equals(sign, comSign)) {
                //TODO  通过out_trade_no或者attach查询到数据库里的订单,再进行验证等
                return true;
            } else {
                LOGGER.info("验证签名失败 [ {} ]", params);
            }
        } else {
            LOGGER.info("支付失败 [ {} ]", params);
        }
        THREAD_LOCAL.remove();
        return false;
    }

    private String getBody(HttpServletRequest request) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            CharArrayWriter data = new CharArrayWriter();
            char[] buf = new char[8192];
            int ret;
            while ((ret = in.read(buf, 0, 8192)) != -1) {
                data.write(buf, 0, ret);
            }
            return data.toString();
        } catch (Exception e) {
            System.err.println("接收BODY内容失败：" + e);
        }
        return null;
    }

    class WeChatConstant {
        // 交易号
        final static String OUT_TRADE_NO = "out_trade_no";
        // 付款ip
        final static String SPBILL_CREATE_IP = "spbill_create_ip";
        //
        final static String PREPAY_ID = "prepay_id";


    }

    /**
     * 统一下单
     *
     * @param money    金额
     * @param ip       客户端ip
     * @param saveKeys 需要保存的关键数据
     * @return
     */
    public Map<String, String> payUnifiedorder(int money, String ip, Map<String, String> saveKeys, String tradeType,
                                               String openid, PayCommonParam payCommonParam) {
        THREAD_LOCAL.set(payCommonParam);
        Map<String, String> map = new LinkedHashMap<>();

        // 填充参数
        fillParam(money, ip, map);
        saveKeys.put(WeChatConstant.OUT_TRADE_NO, map.get(WeChatConstant.OUT_TRADE_NO));
        saveKeys.put(WeChatConstant.SPBILL_CREATE_IP, map.get(WeChatConstant.SPBILL_CREATE_IP));
        String sign = getSignString(map, tradeType, payCommonParam);

        // 获取xml
        StringBuffer xml = getXMLStringBuffer(map, sign);
        LOGGER.info("xml \n {} ", xml.toString());

        //请求接口返回prepay_id等等数据
        String responseBosy = HttpUtil.sentPost("PAYURL", xml.toString(), "UTF-8");
        LOGGER.info("responseBosy [ {} ]", responseBosy);
        //将返回的xml转为map
        Map<String, String> resultMap = readStringXmlOut(responseBosy);
        String prepay_id = resultMap.getOrDefault("prepay_id", "");

        String return_code = resultMap.getOrDefault("return_code", "");
        if (return_code != null && "SUCCESS".equals(return_code) && prepay_id != null && !"".equals(prepay_id)) {
            LOGGER.info(" prepay_id  [ {} ]", prepay_id);
            // 组织返回app
            Map<String, String> paraMapApp = getAPPParam(prepay_id);
            LOGGER.info("返回给app的参数 [ {} ]", paraMapApp);
            saveKeys.put(WeChatConstant.PREPAY_ID, resultMap.get(WeChatConstant.PREPAY_ID));
            saveKeys.put("trade_type", resultMap.get("trade_type"));
            return paraMapApp;
        } else {
            LOGGER.info("获取prepay_id失败 [ {} ]", resultMap.getOrDefault("err_code_des", ""));
            saveKeys.put("err_code", resultMap.getOrDefault("err_code", ""));
            saveKeys.put("err_code_des", resultMap.getOrDefault("err_code_des", ""));
            saveKeys.put(WeChatConstant.PREPAY_ID, "");
            saveKeys.put("trade_type", resultMap.get("trade_type"));
        }
        return new HashMap<>();
    }

    /**
     * 统一下单
     *
     * @param money    金额
     * @param ip       客户端ip
     * @param saveKeys 需要保存的关键数据
     * @return
     */
    public Map<String, String> payUnifiedorder(int money, String ip, Map<String, String> saveKeys, String tradeType) {
        return payUnifiedorder(money, ip, saveKeys, tradeType, null, null);
    }

    /**
     * 获取签名信息
     *
     * @param map
     * @return
     */
    public String getSignString(Map<String, String> map, String tradeType, PayCommonParam payCommonParam) {
        THREAD_LOCAL.set(payCommonParam);
        //将参数字典序列排序
        String stringSignTemp = formatUrlMap(map, false, false);
        stringSignTemp = stringSignTemp + "&key=" + THREAD_LOCAL.get().secret;

        //得到签名
        return MD5Utils.md5Encoding(stringSignTemp).toUpperCase();
    }

    /**
     * 查询支付装填
     *
     * @param transactionId
     * @return
     */
    public int payOrderQuery(String transactionId, String tradeType, PayCommonParam payCommonParam) {
        THREAD_LOCAL.set(payCommonParam);
        Map<String, String> map = new HashMap<>();
        map.put("appid", THREAD_LOCAL.get().appId);
        map.put("mch_id", THREAD_LOCAL.get().mchId);
        map.put("transaction_id", transactionId);
        map.put("nonce_str", "");
        String sign = getSignString(map, tradeType, payCommonParam);

        // 获取xml
        StringBuffer xml = getXMLStringBuffer(map, sign);
        String responseBosy = HttpUtil.sentPost("WechatCommonData.ORDERQUERY", xml.toString(), "UTF-8");
        LOGGER.info("responseBosy [ {} ]", responseBosy);
        Map<String, String> resultMap = readStringXmlOut(responseBosy);
        return "SUCCESS".equals(resultMap.get("result_code")) ? 1 : 0;
    }

    private Map<String, String> getAPPParam(String prepay_id) {
        //要返回给app端的支付参数
        //APP端调起支付的参数列表
        Map<String, String> paraMapApp = new HashMap<>();
        //微信开放平台审核通过的应用APPID
        paraMapApp.put("appid", THREAD_LOCAL.get().appId);
        //	微信支付分配的商户号
        paraMapApp.put("partnerid", THREAD_LOCAL.get().mchId);
        paraMapApp.put("prepayid", prepay_id);
        paraMapApp.put("package", "Sign=WXPay");
        paraMapApp.put("noncestr", "IdUtils.getUUID()");
        String timeStamp = String.valueOf(getSecondTimestamp(new Date()));
        paraMapApp.put("timestamp", timeStamp);

        String stringSignTempApp = formatUrlMap(paraMapApp, false, false);
        stringSignTempApp = stringSignTempApp + "&key=" + THREAD_LOCAL.get().mchId;
        LOGGER.info("stringSignTempApp [ {} ]", stringSignTempApp);
        //得到app支付签名
        String signApp = MD5Utils.md5Encoding(stringSignTempApp).toUpperCase();
        paraMapApp.put("sign", signApp);
        return paraMapApp;
    }

    /**
     * 获取精确到秒的时间戳 10 位数
     *
     * @return
     */
    private static int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0, length - 3));
        } else {
            return 0;
        }
    }

    private StringBuffer getXMLStringBuffer(Map<String, String> map, String sign) {
        //拼接xml
        StringBuffer xml = new StringBuffer();
        xml.append("<xml>");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            xml.append("<" + entry.getKey() + ">");
            xml.append(entry.getValue());
            xml.append("</" + entry.getKey() + ">" + "\n");
        }
        xml.append("<sign>");
        xml.append(sign);
        xml.append("</sign>");
        xml.append("</xml>");
        return xml;
    }

    private void fillParam(int money, String ip, Map<String, String> map) {
        map.put("appid", THREAD_LOCAL.get().appId);
        map.put("body", "-投一票");
        map.put("mch_id", THREAD_LOCAL.get().mchId);
        map.put("nonce_str", "IdUtils.getUUID()");
        map.put("notify_url", THREAD_LOCAL.get().notifyUrl);
        map.put(WeChatConstant.OUT_TRADE_NO, tradeNo());
        map.put(WeChatConstant.SPBILL_CREATE_IP, ip);
        map.put("total_fee", String.valueOf(money));
        map.put("trade_type", THREAD_LOCAL.get().tradeType);
    }

    /**
     * @param xml
     * @return Map
     * @description 将xml字符串转换成map
     */
    private static Map<String, String> readStringXmlOut(String xml) {
        Map<String, String> map = new HashMap<String, String>();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement(); // 获取根节点
            @SuppressWarnings("unchecked")
            List<Element> list = rootElt.elements();// 获取根节点下所有节点
            for (Element element : list) { // 遍历节点
                map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 方法用途: 对所有传入参数按照字段名的Unicode码从小到大排序（字典序），并且生成url参数串<br>
     * 实现步骤: <br>
     *
     * @param paraMap    要排序的Map对象
     * @param urlEncode  是否需要URLENCODE
     * @param keyToLower 是否需要将Key转换为全小写
     *                   true:key转化成小写，false:不转化
     * @return
     */
    private static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, (o1, o2) -> (o1.getKey()).toString().compareTo(o2.getKey()));
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (StringUtils.isNotBlank(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }

    /**
     * 获取参数签名
     *
     * @param paramMap  签名参数
     * @param paySecret 签名密钥
     * @return
     */
    private static String getSign(Map<String, String> paramMap, String paySecret) {
        SortedMap<String, String> smap = new TreeMap<String, String>(paramMap);

        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> m : smap.entrySet()) {
            Object value = m.getValue();
            if (value != null && StringUtils.isNotBlank(String.valueOf(value)) && !"sign".equals(m.getKey())) {
                stringBuffer.append(m.getKey()).append("=").append(value).append("&");
            }
        }

        String argPreSign = stringBuffer.append("key=").append(paySecret).toString();
        return MD5Utils.md5Encoding(argPreSign).toUpperCase();
    }
}

class MD5Utils {
    private MD5Utils() {
    }

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /*
     * 先转为utf-8
     * */
    public static String md5Encoding(String s) {
        byte[] btInput = null;
        try {
            btInput = s.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5(btInput, 32);
    }


    public static String md5(String s) {
        byte[] btInput = s.getBytes();
        return md5(btInput, 32);
    }

    public static String md516(String str) {
        byte[] btInput = str.getBytes();
        return md5(btInput, 16);
    }

    private static String md5(byte[] btInput, int length) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("md5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            String result = new String(str);
            return length == 16 ? result.substring(8, 24) : result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}