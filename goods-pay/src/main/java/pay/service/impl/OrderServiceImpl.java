package pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pay.constrant.PayCenterEnum;
import pay.controller.PayCenterController;
import pay.model.RefundResult;

/**
 * User: xuxianbei
 * Date: 2019/8/21
 * Time: 15:14
 * Version:V1.0
 */
@Service
public class OrderServiceImpl {

    // 因为是书写逻辑，这里直接注入了，正常的的话，这里是fegin
    @Autowired
    PayCenterController payCenterController;

    public String cancel() {

        //线上支付，需要退款时候，不同支付方式，退款方式不一样；
        // 有同步，异步的。
        RefundResult refundResult = payCenterController.refund(null);
        if (refundResult.getRefundType().equals(PayCenterEnum.REFUND_TYPE_SYNC.getKey())) {

        } else if /*异步处理*/ (refundResult.getRefundType().equals(PayCenterEnum.REFUND_TYPE_ASYN.getKey())) {
            //代码应该是这么写的 balabal 然后return；
            // 这里分成两个函数即可

        } else {
            //抛出异常
        }
        return null;
    }
}
