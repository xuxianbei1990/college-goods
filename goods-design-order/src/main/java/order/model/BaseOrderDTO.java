package order.model;

import lombok.Data;
import lombok.ToString;
import order.constant.OrderPayEnum;

import java.io.Serializable;

/** 订单基类
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 14:50
 * Version:V1.0
 *
 */
@Data
@ToString
public class BaseOrderDTO implements Serializable {
    private static final long serialVersionUID = 3912472862130704936L;

    private OrderPayEnum orderPayEnum;
}
