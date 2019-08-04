package pay.service.impl;

import pay.model.*;
import pay.service.PayService;

/**
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 11:48
 * Version:V1.0
 */
public class WechatPayService implements PayService {

    @Override
    public boolean verify() {
        return false;
    }

    @Override
    public PayResult pay(PayType payType, SellerPayBind sellerPayBind) {
        return null;
    }

    @Override
    public RefundResult refund(Integer cash, String thirdTradeNum) {
        return null;
    }

    @Override
    public PayQueryResult payQuery(String thirdTradeNum) {
        return null;
    }

    @Override
    public RefundQueryResult refundQuery(String thirdTradeNum) {
        return null;
    }
}
