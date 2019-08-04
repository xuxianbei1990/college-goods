package order.service.impl;

import order.service.AfterPayEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/** 监听者
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 20:31
 * Version:V1.0
 */
@Component
public class DelayQueueApplicationListener implements ApplicationListener<AfterPayEvent> {
    @Override
    public void onApplicationEvent(AfterPayEvent afterPayEvent) {

    }
}
