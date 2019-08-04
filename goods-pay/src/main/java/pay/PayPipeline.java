package pay;

/**  支付链
 * User: xuxianbei
 * Date: 2019/8/2
 * Time: 18:04
 * Version:V1.0
 * 规定了支付主要流程
 *
 * 作废：理由，这个不是一个链
 */
public interface PayPipeline {

    void pay();

    void payCallBack();

    void payQuery();

    void refund();

    void refundCallBack();

    void refundQuery();

}
