package pay.service.impl;

import org.springframework.stereotype.Component;
import pay.model.*;

/**
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 11:51
 * Version:V1.0
 */
@Component
public class QFPayService extends AbstractPayService {

    //暂时以这种方式实现。后续可能优化为注解或者泛型或者数据库配置
    @Override
    public int getPayTypeId() {
        return 1;
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
