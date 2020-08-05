package pay.v1;

import pay.v1.enums.CertificateEnum;
import pay.v1.enums.PaymentTypeEnum;

/**
 * @author: xuxianbei
 * Date: 2020/8/1
 * Time: 12:10
 * Version:V1.0
 */
public abstract class AbstractCertifacfateDelegate implements Certifacate, UrlCallBackNotify {

    /**
     * 如何解决 executeNotify  getCertifacate 代码类似问题
     *
     * @param value
     * @return
     */
    @Override
    public Object doCallBackNotify(String value) {
        //解析分发
        CertificateEnum certificateEnum = CertificateEnum.Pre_Pay;
        PaymentTypeEnum paymentTypeEnum = PaymentTypeEnum.ALI_PAY;

        if (certificateEnum.equals(CertificateEnum.Pre_Pay)) {
            if (support(paymentTypeEnum)) {
                doPrepayCallBack();
            }
        } else if (certificateEnum.equals(CertificateEnum.Call_Third)) {
            if (support(paymentTypeEnum)) {
                doCallThirdCallBack();
            }
        } else {
            throw new RuntimeException("不支持");
        }

        return null;
    }

    protected void doCallThirdCallBack() {
    }


    protected void doPrepayCallBack() {
    }


    /**
     * 派发
     * 遗留：幂等问题，风控（调用频繁问题），最大通知问题，回调幂等问题
     *
     * @param certificateEnum
     * @return
     */
    @Override
    public Object getCertifacate(CertificateEnum certificateEnum, PaymentTypeEnum paymentTypeEnum) {
        //未解决问题：如果支付流程发生变更，这里将无法适应
        if (certificateEnum.equals(CertificateEnum.Pre_Pay)) {
            //解决如果有第三种支付厂商出现；
            if (support(paymentTypeEnum)) {
                doPrePay();
            }
        } else if (certificateEnum.equals(CertificateEnum.Call_Third)) {
            if (support(paymentTypeEnum)) {
                doCallThird();
            }
        } else {
            throw new RuntimeException("不支持");
        }
        return null;
    }

    protected abstract boolean support(PaymentTypeEnum paymentTypeEnum);

    protected void doPrePay() {
    }


    protected void doCallThird() {
    }

}
