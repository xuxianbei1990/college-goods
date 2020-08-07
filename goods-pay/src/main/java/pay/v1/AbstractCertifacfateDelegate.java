package pay.v1;

import pay.v1.enums.CertificateEnum;
import pay.v1.enums.PaymentTypeEnum;

/**
 * @author: xuxianbei
 * Date: 2020/8/5
 * Time: 21:36
 * Version:V1.0
 */
public abstract class AbstractCertifacfateDelegate implements Certifacate, UrlCallBackNotify {

    private final static Integer CERTIFACATE_FUNCTION = 1;
    private final static Integer CALLBACK_FUNCTION = 2;

    private Integer function;

    protected abstract boolean support(PaymentTypeEnum paymentTypeEnum);

    /**
     * 派发
     * 遗留：幂等问题，风控（调用频繁问题），最大通知问题，回调幂等问题
     *
     * @param certificateEnum
     * @return
     */
    @Override
    public Object getCertifacate(CertificateEnum certificateEnum, PaymentTypeEnum paymentTypeEnum) {
        function = CERTIFACATE_FUNCTION;
        doDispatch(certificateEnum, paymentTypeEnum, function);
        return null;
    }

    //第二版
    private void doDispatch(CertificateEnum certificateEnum, PaymentTypeEnum paymentTypeEnum, Integer function) {
        //未解决问题：如果支付流程发生变更，这里将无法适应
        if (certificateEnum.equals(CertificateEnum.Pre_Pay)) {
            //解决如果有第三种支付厂商出现； 即支付厂商水平扩容问题
            if (support(paymentTypeEnum)) {
                if (function == CERTIFACATE_FUNCTION) {
                    doPrePay();
                } else if (function == CALLBACK_FUNCTION) {
                    doPrepayCallBack();
                }
            }
        } else if (certificateEnum.equals(CertificateEnum.Call_Third)) {
            if (support(paymentTypeEnum)) {
                if (function == CERTIFACATE_FUNCTION) {
                    doCallThird();
                } else if (function == CALLBACK_FUNCTION) {
                    doCallThirdCallBack();
                }
            }
        } else {
            throw new RuntimeException("不支持");
        }
    }

    /**
     * 如何解决 executeNotify  getCertifacate 代码类似问题
     *
     * @param value
     * @return
     */
    @Override
    public Object doCallBackNotify(String value) {
        function = CALLBACK_FUNCTION;
        //解析分发
        CertificateEnum certificateEnum = CertificateEnum.Pre_Pay;
        PaymentTypeEnum paymentTypeEnum = PaymentTypeEnum.ALI_PAY;
        doDispatch(certificateEnum, paymentTypeEnum, function);
        return null;
    }
    //这样写的好处在于：如果日后需要监听什么的，都放在这里就行了
    //不用关心具体实现，同时兼容个性化监听
    protected void doCallThird() {
    }

    protected void doCallThirdCallBack() {
    }

    protected void doPrePay() {
    }

    protected void doPrepayCallBack() {
    }
}
