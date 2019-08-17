package pay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pay.model.*;
import pay.service.PayCenterService;

/**支付中心
 * 预期：支付统一管理类
 * 只提供基础支付接口。
 * User: xuxianbei
 * Date: 2019/8/12
 * Time: 9:34
 * Version:V1.0
 * 未实现：所有参数校验问题
 */
@RestController
@RequestMapping("/payCenter")
public class PayCenterController {

    @Autowired
    PayCenterService payCenterService;


    /**
     * 支付
     * @return
     *  未使用 PayResult 封装结果
     */
    @PostMapping("/pay")
    public PayResult pay(@Validated @RequestBody PayDTO payDTO) {
        return payCenterService.pay(payDTO);
    }

    /**
     * 退款
     * @return
     */
    @PostMapping("/refund")
    public RefundResult refund(@Validated @RequestBody RefundDTO refundDTO) {
        return payCenterService.refund(refundDTO);
    }

    /**
     * 支付查询
     * @param thirdTradeNum
     * @return
     * 这个暂时不做成工具方式实现。
     */
    @GetMapping("/pay/query")
    public PayQueryResult payQuery(String thirdTradeNum) {
        return null;
    }

    /**
     * 退款查询
     * @param thirdTradeNum
     * @return
     * 这个暂时不做成工具方式实现。
     */
    @GetMapping("/refund/query")
    public RefundQueryResult refundQuery(String thirdTradeNum) {
        return null;
    }

}
