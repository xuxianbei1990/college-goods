package pay.v1.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author: xuxianbei
 * Date: 2020/8/7
 * Time: 13:30
 * Version:V1.0
 */
@Slf4j
@Component
public class WXPayFaceToFaceCertifacfate {


   @Autowired
   private WxPayProperties wxPayProperties;

   @Autowired
   private WxPayService wxPayService;

   public String PrePay(String orderNum) {
      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
      String ip = getIpAddress(request);
      WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = WxPayUnifiedOrderRequest.newBuilder()
              .outTradeNo(orderNum)
              .totalFee(1)
              .body("杭州饰的网络科技有限公司")
              .notifyUrl(wxPayProperties.getNotifyUrl())
              .timeExpire(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").withZone(ZoneId.systemDefault())
                      .format(Instant.now().plusSeconds(300)))
              .spbillCreateIp(ip)
              .tradeType("NATIVE")
              // TODO
              .productId(orderNum)
              .build();
      WxPayUnifiedOrderResult wxPayUnifiedOrderResult = null;
      try {
         log.info("微信预支付请求参数:{}", JSONObject.toJSONString(wxPayUnifiedOrderRequest));
         wxPayUnifiedOrderResult = this.wxPayService.unifiedOrder(wxPayUnifiedOrderRequest);
         log.info("微信预支付响应结果:{}", JSONObject.toJSONString(wxPayUnifiedOrderRequest));
      } catch (WxPayException e) {
         log.info("微信预支付失败:{}", e.getCustomErrorMsg() + "\n" + e.getReturnMsg() + "\n" + e.getErrCodeDes());
         throw new RuntimeException(e.getReturnMsg());
      }
      return wxPayUnifiedOrderResult.getCodeURL();
   }

   public static String getIpAddress(HttpServletRequest request) {
      String ip = request.getHeader("x-forwarded-for");
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("WL-Proxy-Client-IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("HTTP_CLIENT_IP");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("HTTP_X_FORWARDED_FOR");
      }
      if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getRemoteAddr();
      }
      return ip;
   }
}
