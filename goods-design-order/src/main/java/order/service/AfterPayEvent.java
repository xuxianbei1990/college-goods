package order.service;

import org.springframework.context.ApplicationEvent;

/** 支付之后事件
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 20:27
 * Version:V1.0
 */
public class AfterPayEvent extends ApplicationEvent {
    public AfterPayEvent(Object source) {
        super(source);
    }
}
