package pay.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pay.model.PayCallBackResult;
import pay.model.RefundCallBackResult;

/**
 * User: xuxianbei
 * Date: 2019/8/2
 * Time: 18:26
 * Version:V1.0
 */
@RestController
@RequestMapping("/callBack")
public class PayCenterCallBackController {

    public PayCallBackResult pay(){
        return null;
    }

    public RefundCallBackResult refund() {
        return null;
    }
}
