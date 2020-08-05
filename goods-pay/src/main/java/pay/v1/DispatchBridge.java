package pay.v1;

/**
 * @author: xuxianbei
 * Date: 2020/8/3
 * Time: 14:06
 * Version:V1.0
 */
public class DispatchBridge {

    public void doDispatch() {
        /**
         * 是不是可以这么做定义一个类
         * 参数 CertificateEnum
         * Comsumer<>: 把两个作为一个方法 support() doprePay()
         *
         */

//        if (certificateEnum.equals(CertificateEnum.Pre_Pay)) {
//            //解决如果有第三种支付厂商出现；
//            if (support(paymentTypeEnum)) {
//                doPrePay();
//            }
//        } else if (certificateEnum.equals(CertificateEnum.Call_Third)) {
//            if (support(paymentTypeEnum)) {
//                doCallThird();
//            }
//        } else {
//            throw new RuntimeException("不支持");
//        }
    }
}
