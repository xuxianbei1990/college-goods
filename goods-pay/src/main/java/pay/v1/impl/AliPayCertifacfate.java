package pay.v1.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.app.models.AlipayTradeAppPayResponse;
import org.springframework.stereotype.Component;
import pay.v1.AbstractCertifacfateDelegate;
import pay.v1.enums.PaymentTypeEnum;

/**
 * @author: xuxianbei
 * Date: 2020/8/1
 * Time: 14:18
 * Version:V1.0
 */
@Component
public class AliPayCertifacfate extends AbstractCertifacfateDelegate {

//    @Autowired
//    private AliPayFaceToFaceCertifacfate aliPayFaceToFaceCertifacfate;

    @Override
    protected boolean support(PaymentTypeEnum paymentTypeEnum) {
        return paymentTypeEnum.equals(PaymentTypeEnum.ALI_PAY);
    }

    @Override
    protected void doPrePay() {
        super.doPrePay();
//        aliPayFaceToFaceCertifacfate.PrePay("skdjfksj");
    }

    @Override
    protected void doCallThird() {
        super.doCallThird();
        Factory.setOptions(getOptions());
        try {
            Order order = new Order();
            order.setId("李磊");
            String subObject = JSONObject.toJSONString(order);
            AlipayTradeAppPayResponse response = Factory.Payment.App()
                    .pay(subObject, "lileiXX1", "10");
            if (ResponseChecker.success(response)) {
                System.out.println(response.body);
            } else {
                System.out.println("调用失败:" + response.body);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";
//<-- 请填写您的AppId，例如：2019091767145019 -->
        config.appId = "2021000117650097";

        // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中 <-- 请填写您的应用私钥，例如：MIIEvQIBADANB ... ... -->
        config.merchantPrivateKey =
                "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCY2FrUEW2IX+cV4tbXuCtqtXmLhl6yfSxFD96YUnRZOLm" +
                        "1qr4WhXucxVp8+dEUGyC5FrKe9NG8UND/73YCoh7Fgby3YpfPCaYPe2DNCaMbSR0Rdto7iVOHTFFpgQxXAZbKewRT" +
                        "dIyI4yRnkBvMY/tk09DD3vIIXGDGcSh97PflNry2j3ZjHKP4e1eUtvxz9wNSWDYq7gy+AzFB164sdHgNO7wIDg9KW" +
                        "Bjqp61Pk+cpSqiaxpqFFSbAKp/3YJMvoc86XsycxTkNyYQCACbsZfCte0lxRpthFNUzgFP4Z3l9wj7PKllAioAmfHy" +
                        "xDeilwRVkv77XfEMdNtiXvebbyYWtAgMBAAECggEAXfppnos4temBxMs9HlZ3G52JpmhwQX8FgVFIpI/OSi2+whzB" +
                        "1Ecuwi32fXF9RMeJT0TNfz841nk6iKNMBEJ6eSZUSs2sWfisykgqNOgACf7iiKJzfLfrS/148U4PsE+hz83j0wyOfH" +
                        "xtHeBVt06ifFRgwI6McueZ+WoKY6MYqS4RfETguvoH1HJkHrrdptp2M3++Ulq1TC/Jze1yMN1uqBxLoe2kRAdRCOrL" +
                        "eycd06Rqa5LJYYb0w1yvjol2NNzW+IzlL9g33dhagfCrJoOMYH4hDQSvuwueGLJFk9QhQVDxXe2Ra2/OcgC28p2J8/w7" +
                        "gQu3nDFeB55BLpopARLwYQKBgQDhO3+LyRCo0vK+PBOqL3Lc5f7g4Zs5cM8czI0NxiYle4N8XXbfanRNgA4hoB3YtbLcTJ" +
                        "YULwkHYVgZrX1/HhdpEQlAYgQgG6xnlH0AGMGHG0DzvH3kl0YbfDQ2v/8K72mMIXq1dwbwOqJ91dVxMCLULnRBR6ugM4" +
                        "LO+4ZdmETkGwKBgQCtuW755pHvXXoCSlznBK5AQTZn0uW1bJSOczZl3HlSlbdPYdp9RM2nbn0ocHlNjqr4krfJWG8WaSF" +
                        "ifPVlS7bygLoKfy4D0LiUQYeKf2JsslUo9g5qC8F0Ak+hx/mAdlf4KXL1z4DbSBouGfWnroBsogvn1csVG6LNm27vzks" +
                        "J1wKBgQCYIiMt3Trmfc4+1r+FpZUVRjkZ1umNK/iJgFUKZ7wed0ZB+7Ouhu5V5tgkuE1QsrJEgWqh6X9Uv9jNR1VMRpwT" +
                        "eNFh7pLQyUBzmmWwaghWZP9DYx86Bz/wkr2F+bugvEPzxi1nYyWJoGFfU89fK99qqJIYAfwYMpL5kgWZNJ/b3wKBgQCj" +
                        "yADiRQgsnPocErOHcRg+p7x9IY1C/70Gl4QfgD45J8EMa/qqwyUHxtjkOTWDcw+pDdmLT35bqH269of8Gee+tLGwl7pwC" +
                        "mUaELWSUwod4/r5hRCN1ctqbYtZwMhu3iONaLZjHGAlW9KiyAKbNZ8Jy7Rhu2Oc4NMZ+IIcP2brPQKBgHqsSKL/nnjRx1" +
                        "qkRo3o8b22Ef2ySX+VsAWwpZhzw/3wGBlNuCsBXHBsa7Qe771HDt79DWc8cNqftZXEyW1WbmdtSWTMoc3JPCBDOr41b6osu" +
                        "njVwaatcsE3c/EmY7XjZT+zl5KOjsAMeHku0Cxd+vAcswye8drJrPm1psnn62jw";

        //注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先
        //
        //
        // 从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
//        config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
//        config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
//        config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";

        //注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
        //<-- 请填写您的支付宝公钥，例如：MIIBIjANBg... -->
         config.alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgCQ/JIJ0a1hIg4HZP9x7n+GD4RhjT7wMVteYuvJaN" +
                 "CkypOICZA4oMpIGR0AyigpFeJWOXESMVWYgWOHK0+1THwLH4v+Tcw39c4+kAyJl89V/X6jjLa/F3Kq9BnGN03Yoxv+hywNjx7mdfIr+" +
                 "3jwKzHA2dF4K+nF2bVzzRRFX0noJi6zpnY/Hdf+anegq26Mu1qB57Qx+UDAkf1nK1gwBam2kDq04pZ2+2y3SktP6NaROycfqK5i1WZH1h" +
                 "gD7oxNsljHhCcuQpJLTHy9vL2lV+GN5P7lZVQ4fwRlg3i1gf2mUxm181V17jr48b+n0WhfHHioVHCaYb0bEAzIRC6rZ+QIDAQAB";

        //可设置异步通知接收服务地址（可选）  <-- 请填写您的支付类接口异步通知接收服务地址，例如：https://www.test.com/callback -->
        config.notifyUrl = "http://jb85x3.natappfree.cc/callback";

        //可设置AES密钥，调用AES加解密相关接口时需要（可选）
//        config.encryptKey = "<-- 请填写您的AES密钥，例如：aa4BtZ4tspm2wnXLb1ThQA== -->";

        return config;
    }
}
