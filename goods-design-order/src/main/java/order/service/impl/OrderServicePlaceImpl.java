package order.service.impl;

import order.model.BaseOrderDTO;
import order.service.AbstractOrderServicePlaceTemplate;
import org.springframework.stereotype.Component;

/**
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 21:01
 * Version:V1.0
 */
@Component
public class OrderServicePlaceImpl extends AbstractOrderServicePlaceTemplate {
    @Override
    protected void reCalcRmb(BaseOrderDTO orderDTO) {
        
    }

    @Override
    protected void beforePlace() {

    }

    @Override
    protected boolean verify(BaseOrderDTO orderDTO) {
        return false;
    }
}
