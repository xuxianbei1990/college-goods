package pay.controller;

import com.alipay.easysdk.factory.Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pay.v1.PayService;
import pay.v1.enums.CertificateEnum;
import pay.v1.enums.PaymentTypeEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author: xuxianbei
 * Date: 2020/8/3
 * Time: 17:17
 * Version:V1.0
 */
@RestController
public class SimpleController {

    @Autowired
    PayService payService;

    @GetMapping("/simple")
    public String simple() {
        return "simple";
    }

    @GetMapping("call")
    public String call() {
        payService.getCertifacates(CertificateEnum.Call_Third.getId(), PaymentTypeEnum.ALI_PAY.getId());
        return "success";
    }

    @RequestMapping("callback")
    public void callback(HttpServletRequest request) {
        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        System.out.println(params);
//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        try {
            Factory.Payment.Common().verifyNotify(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
