package pay.service;

import pay.model.*;

/**
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 13:56
 * Version:V1.0
 */
public interface PayService {

    /**
     * 校验
     * @return
     */
    boolean verify();


    PayResult pay(PayType payType, SellerPayBind sellerPayBind);

    /**
     * 退款
     * @param cash
     * @param thirdTradeNum
     * @return
     */
    RefundResult refund(Integer cash, String thirdTradeNum) ;

    /**
     * 支付查询
     * @param thirdTradeNum
     * @return
     * 这个暂时不做成工具方式实现。
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
