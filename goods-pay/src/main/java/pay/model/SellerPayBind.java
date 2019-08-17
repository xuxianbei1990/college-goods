package pay.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SellerPayBind implements Serializable {
    /**
     * 主键id
     */
    private Integer fsellerPayBindId;

    /**
     * 商户id
     */
    private Integer fsellerId;

    /**
     * 支付方式表主键
     */
    private Integer fpayTypeId;

    /**
     * merchantId
     */
    private String fmerchantId;

    /**
     * 私钥
     */
    private String fprimaryKey;

    /**
     * 证书
     */
    private String fcertificatePath;

    /**
     * appid
     */
    private String fappid;

    /**
     * 备注
     */
    private String fremark;

    /**
     * 创建时间
     */
    private Date fcreateTime;

    /**
     * 修改时间
     */
    private Date fmodifyTime;

    private static final long serialVersionUID = 1L;


}