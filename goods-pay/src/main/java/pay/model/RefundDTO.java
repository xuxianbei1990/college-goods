package pay.model;

import lombok.Data;
import pay.service.PayDispatchDTO;

/**
 * User: xuxianbei
 * Date: 2019/8/14
 * Time: 10:47
 * Version:V1.0
 */
@Data
public class RefundDTO implements PayDispatchDTO {

    //金额
    private Long cash;

    //商户id
    private Integer sellerId;

    //本来下面参数需要再包一层实现，就这样吧，有时间在优化
    private String orderNo;

    //如果是退款，这里需要放微信支付的流水单号
    private String thirdTradeNum;

    /**
     * 订单总金额（用户实际支付金额），单位：分（RMB）
     */
    private Long forderTotalAmount;
}
