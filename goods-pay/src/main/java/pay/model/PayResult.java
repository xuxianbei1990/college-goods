package pay.model;

import lombok.Data;
import pay.service.DispatcherResult;

/**
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 10:09
 * Version:V1.0
 */
@Data
public class PayResult implements DispatcherResult {

    private Integer payTypeId;

//    //收款金额
//    private Integer amount;
//    //支付渠道类型
//    private String payType;
//    //小程序支付参数
//    private Object pay_params;
//    //支付交易订单号
//    private String orderNum;

    private Object data;
}
