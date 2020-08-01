package pay.v1.enums;

import lombok.Getter;

import java.util.Objects;

/**支付方式
 * @author: xuxianbei
 * Date: 2020/8/1
 * Time: 14:26
 * Version:V1.0
 */
@Getter
public enum PaymentTypeEnum {

    /**
     * 微信支付
     */
    WECHAT_PAY("微信", "wechat_pay", 0),

    /**
     * 支付宝二维码
     */
    ALI_PAY("支付宝", "ali_pay", 1);

    private Integer id;
    private String code;
    private String message;

    PaymentTypeEnum(String message, String code, Integer id) {
        this.code = code;
        this.message = message;
        this.id = id;
    }

    public static PaymentTypeEnum getById(Integer id) {
        for (PaymentTypeEnum value : values()) {
            if (Objects.equals(value.getId(), id)) {
                return value;
            }
        }
        throw new RuntimeException();
    }
}
