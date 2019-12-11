package pay.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import pay.limiting.LeakyBucket;
import pay.model.*;
import pay.service.DispatcherResult;
import pay.service.PayCenterService;
import pay.service.PayDispatchDTO;
import pay.service.PayService;

import java.util.Iterator;
import java.util.Map;

/**
 * 支付调度中心
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 11:43
 * Version:V1.0
 * 整体依赖spring
 * 第一版本只是根据数据库进行动态匹配；问题：pay， refund。流程差不多造成冗余代码
 * 第二版相同的流程合并。把变化点提炼成一个接口 @FunctionalInterface。这时候代码写完了。有一个新接口添加，会更改原有的接口
 * 第三版参数合并。参数接口化。
 */
@Component
public class PayCenterDispatchServiceImpl implements PayCenterService {


    @Autowired
    ApplicationContext applicationContext;

//    @Resource
//    PayTypeMapper payTypeMapper;

//    @Resource
//    SellerPayBindMapper sellerPayBindMapper;

    //主要为了合并冗余代码
    @FunctionalInterface
    interface Pay {
        PayResult pay(PayService payService, PayType payType, SellerPayBind sellerPayBind, PayDTO payDTO);
    }


    @FunctionalInterface
    interface DispatchFunc {
        DispatcherResult dispatch(PayService payService, PayType payType, SellerPayBind sellerPayBind, PayDispatchDTO payDispatchDTO);
    }

    @Override
    public PayResult pay(PayDTO payDTO) {
        //这样引入一个新的问题，如果我增加的支付查询，需要修改原来的参数接口，明显违背了开闭原则
//        Pay pay = PayService::pay;
        // Pay 升级到 DispatchFunc
        DispatchFunc dispatchFunc = PayService::pay;
        return (PayResult) templateFunc(payDTO, dispatchFunc);
    }

    //优化点
    //1.每个商户的支付类型实际基本不变的，所以没有必要每次查数据库
    private Object templateFunc(PayDispatchDTO payDispatchDTO, DispatchFunc dispatchFunc) {
        //从数据库（或者其他）拿到商户关联的支付id
        SellerPayBind sellerPayBind = null;
        Assert.isTrue(sellerPayBind != null, "参数异常商户id：" + payDispatchDTO.getSellerId());
        //通过支付id拿到支付信息
        PayType payType = null;
        Assert.isTrue(payType != null, "参数异常支付类型id：" + sellerPayBind.getFpayTypeId());
        //打包组织信息转发给对应的实现

        //如果配置错误直接报错
        Map<String, PayService> map = applicationContext.getBeansOfType(PayService.class);
        Iterator<Map.Entry<String, PayService>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, PayService> entry = iterator.next();
            PayService payService = entry.getValue();
            if (payService.getPayTypeId() == payType.getFpayTypeId()) {
                // 这里流程一般都是固定的，所以这样写没有关系
                if (dispatchFunc != null) {
                    if (!LeakyBucket.getInstance().append()) {
                        throw new RuntimeException("调用太频繁了");
                    }
                    DispatcherResult dispatcherResult = dispatchFunc.dispatch(payService, payType, sellerPayBind, payDispatchDTO);
                    dispatcherResult.setPayTypeId(payService.getPayTypeId());
                    return dispatcherResult;
                }
            }
        }
        throw new RuntimeException("未实现或者未配置支付类型");
    }

    @FunctionalInterface
    interface Refund {
        RefundResult refund(PayService payService, PayType payType, SellerPayBind sellerPayBind, RefundDTO refundDTO);
    }

    @Override
    public RefundResult refund(RefundDTO refundDTO) {
        DispatchFunc dispatchFunc = PayService::refund;
        return (RefundResult) templateFunc(refundDTO, dispatchFunc);
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
