package pay.service.impl;

import org.springframework.util.Assert;
import pay.model.*;
import pay.service.DispatcherResult;
import pay.service.PayDispatchDTO;
import pay.service.PayService;

/**
 * User: xuxianbei
 * Date: 2019/8/14
 * Time: 11:51
 * Version:V1.0
 */
public abstract class AbstractPayService implements PayService {


    //是不是需要进一步接口隔离
    protected abstract PayResult doPay(PayType payType, SellerPayBind sellerPayBind, PayDTO payDTO);

    protected abstract RefundResult doRefund(PayType payType, SellerPayBind sellerPayBind, RefundDTO refundDTO);

    @Override
    public DispatcherResult pay(PayType payType, SellerPayBind sellerPayBind, PayDispatchDTO payDispatchDTO) {
        Assert.isTrue(payDispatchDTO instanceof PayDTO, "参数 payDispatchDTO 实例错误");
        return doPay(payType, sellerPayBind, (PayDTO) payDispatchDTO);
    }

    @Override
    public DispatcherResult refund(PayType payType, SellerPayBind sellerPayBind, PayDispatchDTO payDispatchDTO) {
        Assert.isTrue(payDispatchDTO instanceof RefundDTO, "参数 payDispatchDTO 实例错误");
        return doRefund(payType, sellerPayBind, (RefundDTO) payDispatchDTO);
    }
}
