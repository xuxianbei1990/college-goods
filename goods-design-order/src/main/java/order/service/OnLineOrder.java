package order.service;

/** 线上订单
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 17:46
 * Version:V1.0
 */
public interface OnLineOrder {
    void prePay();

    void pay();

    void afterPay();
}
