package pay.service;


import pay.model.*;

/**
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 11:42
 * Version:V1.0
 */
public interface PayCenterService {

    /**
     * 支付
     * @return
     */
    PayResult pay(PayDTO payDTO);

    /**
     * 退款
     * @return
     */
    RefundResult refund(RefundDTO refundDTO) ;

    /**
     * 支付查询
     * @param thirdTradeNum
     * @return
     * 这个暂时不做成工具方式实现。
     * 定时器方案：缺点空轮询；优点：实现快
     */
     PayQueryResult payQuery(String thirdTradeNum);

    /**
     * 退款查询
     * @param thirdTradeNum
     * @return
     * 这个暂时不做成工具方式实现。
     */
     RefundQueryResult refundQuery(String thirdTradeNum) ;

}
