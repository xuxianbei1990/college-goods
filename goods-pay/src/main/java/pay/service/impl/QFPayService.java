package pay.service.impl;

import pay.model.*;
import pay.service.PayService;

/**
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 11:51
 * Version:V1.0
 */
public class QFPayService implements PayService {


    //暂时以这种方式实现。后续可能优化为注解或者泛型或者数据库配置
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
