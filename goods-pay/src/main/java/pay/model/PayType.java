package pay.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PayType implements Serializable {
    /**
     * 主键id
     */
    private Integer fpayTypeId;

    /**
     * 支付方式名称（例如微信，QFPay）
     */
    private String fpayTypeName;

    /**
     * 支付url
     */
    private String fpayUrl;

    /**
     * 支付查询url
     */
    private String fpayQueryUrl;

    /**
     * 支付回调url
     */
    private String fpayCallbackUrl;

    /**
     * 退款url
     */
    private String frefundUrl;

    /**
     * 退款查询url
     */
    private String frefundQueryUrl;

    /**
     * 退款回调url
     */
    private String frefundCallbackUrl;

    /**
     * 输入编码格式默认UTF-8
     */
    private String finputCharset;

    /**
     * 摘要算法默认md5
     */
    private String fsignType;

    /**
     * 备注
     */
    private String fremark;

    /**
     * 创建时间
     */
    private Long fcreateTime;

    /**
     * 修改时间
     */
    private Date fmodifyTime;

    private static final long serialVersionUID = 1L;


}