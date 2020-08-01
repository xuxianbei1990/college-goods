package pay.v1;

import pay.v1.enums.CertificateEnum;
import pay.v1.enums.PaymentTypeEnum;

/** 凭证
 * @author: xuxianbei
 * Date: 2020/8/1
 * Time: 12:04
 * Version:V1.0
 *
 */
public interface Certifacate {

    Object getCertifacate(CertificateEnum certificateEnum, PaymentTypeEnum paymentTypeEnum);

}
