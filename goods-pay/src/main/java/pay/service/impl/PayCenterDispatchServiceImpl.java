package pay.service.impl;

import pay.model.PayQueryResult;
import pay.model.PayResult;
import pay.model.RefundQueryResult;
import pay.model.RefundResult;
import pay.service.PayCenterService;
import pay.service.PayService;

/**  支付调度中心
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 11:43
 * Version:V1.0
 * 整体依赖spring
 */
public class PayCenterDispatchServiceImpl implements PayCenterService {


    PayService payService;

    @Override
    public PayResult pay(Integer cash, String userKey, Integer sellerId) {
        //从数据库（或者其他）拿到商户关联的支付id
        //通过支付id拿到支付信息
        //打包组织信息转发给对应的实现

        //如果配置错误直接报错

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
