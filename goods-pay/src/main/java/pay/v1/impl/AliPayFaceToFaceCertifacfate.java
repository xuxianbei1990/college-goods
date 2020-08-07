package pay.v1.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.TradeStatus;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: xuxianbei
 * Date: 2020/8/7
 * Time: 11:21
 * Version:V1.0
 */
@Slf4j
@Component
public class AliPayFaceToFaceCertifacfate {

    /**
     * 支付宝当面付2.0服务
     */
    private static AlipayTradeService tradeService;

    @Autowired
    private AliPayParamConfig aliPayParamConfig;

    static {
        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
    }

    public String PrePay(String orderNum) {
        AlipayF2FPrecreateResult result;
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setOutTradeNo(orderNum)
                .setSubject("商品交易")
//                .setTotalAmount(order.getTotalPrice().setScale(2).toPlainString())
                .setTotalAmount("0.01")
                .setBody("")
//                .setGoodsDetailList(goodsDetailList)
                .setNotifyUrl(aliPayParamConfig.getNotifyUrl())
                .setStoreId("杭州饰的网络科技有限公司")
                .setTimeoutExpress(aliPayParamConfig.getTimeoutExpress());
        log.info("支付宝预下单请求参数:{}", JSONObject.toJSONString(builder));
        result = tradeService.tradePrecreate(builder);
        log.info("支付宝预下单响应结果:{}", JSONObject.toJSONString(result));
        if (result.getTradeStatus() != TradeStatus.SUCCESS) {
            throw new RuntimeException("获取付款二维码失败");
        }
        AlipayTradePrecreateResponse response = result.getResponse();
        return response.getQrCode();
    }
}
