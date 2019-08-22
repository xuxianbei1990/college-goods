package pay.constrant;

/**
 * User: xuxianbei
 * Date: 2019/8/21
 * Time: 15:21
 * Version:V1.0
 */
public enum PayCenterEnum {

    WECHAT_PAY(0, "微信"),
    QF_PAY(1, "QFPay"),
    REFUND_TYPE_SYNC(0, "退款同步"),
    REFUND_TYPE_ASYN(1, "退款异步");

    private Integer key;

    private String desc;

    PayCenterEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
