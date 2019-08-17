package pay.model;

import lombok.Data;
import pay.service.DispatcherResult;

/**
 * User: xuxianbei
 * Date: 2019/8/4
 * Time: 10:09
 * Version:V1.0
 */
@Data
public class PayResult implements DispatcherResult {

    private Integer payTypeId;

    private Object data;
}
