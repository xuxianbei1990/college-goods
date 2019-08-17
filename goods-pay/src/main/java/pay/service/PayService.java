package pay.service;


import pay.model.*;

/**  支付服务
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
    int getPayTypeId();


    //PayDTO 改为 PayDispatchDTO
    // PayResult 升级为 DispatcherResult
    DispatcherResult pay(PayType payType, SellerPayBind sellerPayBind, PayDispatchDTO payDispatchDTO);

    /**
     * 退款
     * @return
     */
    DispatcherResult refund(PayType payType, SellerPayBind sellerPayBind, PayDispatchDTO payDispatchDTO) ;

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
