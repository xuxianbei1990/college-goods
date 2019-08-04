package order.constant;

/**  订单支付枚举
 *  个人建议把所有订单枚举定义在一起方便找。
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 15:54
 * Version:V1.0
 */
public enum OrderPayEnum {

    OFF_LINE_PAY(1, "线下支付"),
    ON_LINE_PAY(0, "线上支付");

    private Integer key;
    private String value;

    OrderPayEnum(Integer key, String value) {
         this.key = key;
         this.value = value;
    }
}
