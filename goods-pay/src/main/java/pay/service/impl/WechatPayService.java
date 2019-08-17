package pay.service.impl;

import org.springframework.stereotype.Component;
import pay.model.*;

/**
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 11:48
 * Version:V1.0
 */
@Component
public class WechatPayService extends AbstractPayService {


    @Override
    public int getPayTypeId() {
        return 0;
    }

    @Override
    public PayResult doPay(PayType payType, SellerPayBind sellerPayBind, PayDTO payDTO) {
        return null;
    }

    @Override
    public RefundResult doRefund(PayType payType, SellerPayBind sellerPayBind, RefundDTO payDTO) {
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
