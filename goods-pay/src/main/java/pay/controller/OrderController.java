package pay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pay.service.impl.OrderServiceImpl;

/**
 * User: xuxianbei
 * Date: 2019/8/21
 * Time: 15:13
 * Version:V1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    @PostMapping("/cancel")
    public String cancel() {
        return orderService.cancel();
    }

    /**
     * 下单；
     * 背景：重复下单。
     * 问题：同样的数据，完全由你发送过来的正常数据。发送过来；这时候通过摘要算法已经不能搞定了
     * 如果大量这样恶意订单下来，会造成服务器无法处理正常订单。
     * 解决思路：下单之前，到后台拿到一个订单号下单；这样只要校验改订单是否存在就可以了。
     *   那么拿订单号，限制用户，限制ip即可。
     *   校验订单方式：采用redis缓存解决。拿订单时候，放入缓存。下单时候只要判断是否存在缓存中即可。
     *   如果是那么把订单放入到处理缓存当中。
     *
     * @return
     */
    @PostMapping("/place")
    public String place() {
        return "";
    }
}
