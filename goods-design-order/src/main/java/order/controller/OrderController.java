package order.controller;

import order.model.BaseOrderDTO;
import order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: xuxianbei
 * Date: 2019/7/29
 * Time: 14:27
 * Version:V1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public String place(BaseOrderDTO orderDTO) {
        return orderService.place(orderDTO);
    }

}
