package pay.service;

import pay.model.PayQueryResult;
import pay.model.PayResult;
import pay.model.RefundQueryResult;
import pay.model.RefundResult;

/**
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 11:42
 * Version:V1.0
 */
public interface PayCenterService {

    /**
     * 支付
     * @param cash
     * @param userKey
     * @param sellerId
     * @return
     */
     PayResult pay(Integer cash, String userKey, Integer sellerId);

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
