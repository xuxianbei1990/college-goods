package order.service;

import order.constant.OrderPayEnum;
import order.model.BaseOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 订单模板
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 14:48
 * Version:V1.0
 * 用于规定订单需要走的主流程。
 */
public abstract class AbstractOrderServicePlaceTemplate implements OrderServicePlace {

    @Autowired
    OffLineOrder offLineOrder;

    @Autowired
    OnLineOrder onLineOrder;

    @Autowired
    OrderStore orderStrore;

    @Autowired
    GoodsStore goodsStore;

    @Override
    public String place(BaseOrderDTO orderDTO) {

        //下单之前
        beforePlace();

        // 校验
        verify(orderDTO);

        //重新计算RBM  因为是做海外交易，一定涉及计算RBM
        //所以把这个流程加上
        reCalcRmb(orderDTO);


        if (orderDTO.getOrderPayEnum().equals(OrderPayEnum.OFF_LINE_PAY)) {
            // 线下支付
            offLineOrder.prePay();
            // 预减库存
            goodsStore.preDecrease();
            orderStrore.insert();
            //支付
            offLineOrder.pay();
            //减库存
            goodsStore.decrease();
            //购物车流程
        } else if (orderDTO.getOrderPayEnum().equals(OrderPayEnum.ON_LINE_PAY)) {
            //线上支付
            onLineOrder.prePay();
            goodsStore.preDecrease();
            orderStrore.insert();
            onLineOrder.pay();
            goodsStore.decrease();
            onLineOrder.afterPay();
        } else {
            //抛出异常
        }

        return null;
    }

    protected abstract void reCalcRmb(BaseOrderDTO orderDTO);

    protected abstract void beforePlace();

    protected abstract boolean verify(BaseOrderDTO orderDTO);
}
