package pay.v1.impl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xuxianbei
 * Date: 2020/8/7
 * Time: 9:13
 * Version:V1.0
 */
@ConfigurationProperties("alipay.sdk")
@Configuration
@Data
public class AliPayParamConfig {

    /**
     * 通知
     */
    private String notifyUrl;

    /**
     * 超时时间
     */
    private String timeoutExpress = "5m";
}
