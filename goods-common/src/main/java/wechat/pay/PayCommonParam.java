package wechat.pay;

import lombok.Data;

/**
 * Name 支付公共参数
 *
 * @author xuxb
 * Date 2018-11-17
 * VersionV1.0
 * @description
 */
@Data
public abstract class PayCommonParam {
    protected String appId;
    protected String secret;
    protected String mchId;
    protected String tradeType;
    protected String notifyUrl;

    // 填充数据
    public abstract void fillData();

    // 是否支持
    public boolean support(String type){
        return type.equals(this.tradeType);
    }

    @Override
    public String toString() {
        return "PayCommonParam{" +
                "appId='" + appId + '\'' +
                ", secret='" + secret + '\'' +
                ", mchId='" + mchId + '\'' +
                ", tradeType='" + tradeType + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                '}';
    }
}
