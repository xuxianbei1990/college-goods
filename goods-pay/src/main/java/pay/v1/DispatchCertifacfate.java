package pay.v1;

import pay.v1.enums.CertificateEnum;
import pay.v1.enums.PaymentTypeEnum;

/**
 * @author: xuxianbei
 * Date: 2020/8/1
 * Time: 12:10
 * Version:V1.0
 */
public abstract class DispatchCertifacfate implements Certifacate {


    /**
     * 派发
     *
     * @param certificateEnum
     * @return
     */
    @Override
    public Object getCertifacate(CertificateEnum certificateEnum, PaymentTypeEnum paymentTypeEnum) {
        //解决如果有第三种支付厂商出现；
        //未解决问题：如果支付流程发生变更，这里将无法适应  解决方法，采用注册的方式实现
        if (certificateEnum.equals(CertificateEnum.Pre_Pay)) {
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
