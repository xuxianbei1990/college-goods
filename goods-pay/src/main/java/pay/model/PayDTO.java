package pay.model;

import lombok.Data;
import pay.service.PayDispatchDTO;

import javax.validation.constraints.NotNull;

/**
 * User: xuxianbei
 * Date: 2019/8/12
 * Time: 15:43
 * Version:V1.0
 */
@Data
public class PayDTO implements PayDispatchDTO {

    @NotNull(groups = {pay.class, refund.class})
    //金额
    private Long cash;
    //微信就是openid 用户支付对象
    @NotNull(groups = pay.class)
    private String userKey;
    @NotNull(groups = {pay.class, refund.class})
    //商户id
    private Integer sellerId;

    //本来下面参数需要再包一层实现，就这样吧，有时间在优化
    //本来是用来处理各个支付参数差异化的
    @NotNull(groups = refund.class)
    private String orderNo;

    @NotNull(groups = pay.class)
    private String ip;

    //如果是退款，这里需要放微信支付的流水单号
    @NotNull(groups = refund.class)
    private String thirdTradeNum;

    /**
     * 订单总金额（用户实际支付金额），单位：分（RMB）
     */
    @NotNull(groups = refund.class)
    private Long forderTotalAmount;

    interface pay {

    }

    interface refund {

    }
}
