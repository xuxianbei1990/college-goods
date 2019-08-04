package order.service.impl;

import order.model.BaseOrderDTO;
import order.service.OrderService;
import order.service.OrderServicePlace;
import order.service.Pay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 14:43
 * Version:V1.0
 *
 * 问题：重复下单？每次下单都要从后台拿到一个下单号。
 *       防止刷单？限制ip，请求安全
 *       延迟队列问题？rabbitMq;
 *       服务调用问题？黑白名单。
 *       下单数据量大的问题；如果每日1000万订单数据？这个问题可以仔细思考下。我觉得不单单只是分表这么简单
 *       金额计算问题？需要springMVC和mybatis统一拦截下处理
 *       错误信息统一拦截处理。spirngMVC 错误信息
 *
 */
@Service
public class OrderServiceImpl  implements OrderService {

    //第一问题  OrderServiceImpl也实现了OrderServicePlace接口，那么怎么区分;然后spring和mybatis怎么处理的
    @Autowired
    OrderServicePlace orderServicePlace;

    // 同第一个问题
    @Autowired
    Pay pay;

    @Override
    public String place(BaseOrderDTO orderDTO) {
        return orderServicePlace.place(orderDTO);
    }


    @Override
    public void payOrder() {
        pay.payOrder();
    }

    @Override
    public void list() {

    }

    @Override
    public void listDetail() {

    }

    @Override
    public void goods() {

    }

    @Override
    public void statisticsCount() {

    }

    @Override
    public void cancel() {

    }
}
