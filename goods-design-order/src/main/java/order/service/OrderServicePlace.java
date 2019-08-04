package order.service;

import order.model.BaseOrderDTO;

/**  下单接口
 * User: xuxianbei
 * Date: 2019/7/30
 * Time: 9:44
 * Version:V1.0
 */
public interface OrderServicePlace {

    String place(BaseOrderDTO orderDTO);
}
