package order.service.impl;

import order.service.AfterPayEvent;
import order.service.OnLineOrder;
import order.util.observe.WatchPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 线上支付
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 18:05
 * Version:V1.0
 */
@Component
public class OnLineOrderImpl implements OnLineOrder {

    @Autowired
    private ApplicationContext applicationContext;

    WatchPublish watchPublish = null;

    @PostConstruct
    void registryObserve() {
        watchPublish  = new WatchPublish();
        //方案缺点：只支持ONLine监听
        //这里使用spring的获取接口所有类，注入到watchPublish
        // 解决方案打标记  但是还是不够完美
        // 预先知道哪些监听这个事件可以使用此方法（pass掉）

    }


    @Override
    public void prePay() {

    }

    @Override
    public void pay() {

    }

    //我认为支付之后会加很多动作，除了订单超时逻辑
    //所以我觉得应该用观察者模式,好处是什么？解耦
    @Override
    public void afterPay() {
        //手工实现观察者
        watchPublish.notifyObserves();
        //基于springEvent（原理也是观察者）
        applicationContext.publishEvent(new AfterPayEvent("这里可以放订单"));

    }

}
