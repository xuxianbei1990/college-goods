package pay.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pay.v1.enums.CertificateEnum;
import pay.v1.enums.PaymentTypeEnum;

import java.util.List;

/**
 * @author: xuxianbei
 * Date: 2020/8/1
 * Time: 14:52
 * Version:V1.0
 */
@Service
public class PayService {
    @Autowired
    private List<Certifacate> certifacates;

    /**
     * 伪代码
     */
    public String getCertifacates(Integer id, Integer payTypeId) {
        certifacates.stream().forEach(certifacate -> certifacate.getCertifacate(CertificateEnum.getById(id),
                PaymentTypeEnum.getById(payTypeId)));
        return "";
    }
}
