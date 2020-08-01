package pay.v1.impl;

import org.springframework.stereotype.Component;
import pay.v1.DispatchCertifacfate;
import pay.v1.enums.PaymentTypeEnum;

/**
 * @author: xuxianbei
 * Date: 2020/8/1
 * Time: 14:15
 * Version:V1.0
 */
@Component
public class WeiXinCertifacfate extends DispatchCertifacfate {
    @Override
    protected boolean support(PaymentTypeEnum paymentTypeEnum) {
        return paymentTypeEnum.equals(PaymentTypeEnum.WECHAT_PAY);
    }

    @Override
    protected void doPrePay() {
        super.doPrePay();
    }

    @Override
    protected void doCallThird() {
        super.doCallThird();
    }
}
