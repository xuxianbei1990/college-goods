package pay.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pay.model.PayQueryResult;
import pay.model.PayResult;
import pay.model.RefundQueryResult;
import pay.model.RefundResult;
import pay.service.PayCenterService;

/**
 * User: xuxianbei
 * Date: 2019/8/2
 * Time: 18:18
 * Version:V1.0
 */
@RestController
@RequestMapping("/payCenter")
public class PayCenterController {

    PayCenterService payCenterService;


    /**
     * 支付
     * @param cash
     * @param userKey
     * @param sellerId
     * @return
     */
    @GetMapping("/pay")
    public PayResult pay(Integer cash, String userKey, Integer sellerId) {
        return null;
    }

    /**
     * 退款
     * @param cash
     * @param thirdTradeNum
     * @return
     */
    @GetMapping("/refund")
    public RefundResult refund(Integer cash, String thirdTradeNum) {
        return null;
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
