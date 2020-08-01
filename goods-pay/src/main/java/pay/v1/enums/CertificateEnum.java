package pay.v1.enums;

import lombok.Getter;

import java.util.Objects;

/**
 * 凭证
 *
 * @author: xuxianbei
 * Date: 2020/8/1
 * Time: 11:55
 * Version:V1.0
 */
@Getter
public enum CertificateEnum {

    /**
     * 预支付-二维码方式
     */
    Pre_Pay(1, "预支付"),
    /**
     * 调用第三方程序方式
     */
    Call_Third(2, "调用第三方");

    private int id;
    private String desc;

    CertificateEnum(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public static CertificateEnum getById(Integer id) {
        for (CertificateEnum value : values()) {
            if (Objects.equals(value.getId(), id)) {
                return value;
            }
        }
        throw new RuntimeException();
    }

}
