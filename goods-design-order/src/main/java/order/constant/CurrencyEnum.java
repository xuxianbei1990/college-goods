package order.constant;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @description:币种枚举 代码--名称
 * @author:lchm
 * @date:2019/5/19 15:53
 * @company:版权所有 深圳市天行云供应链有限公司
 * @version:1.0.0
 */
public enum CurrencyEnum {

    /**
     * currencyNameEn:币种代码
     * currencyName:币种名称
     * scale:保留的小数位
     * roundingMode:进位
     */
    HKD("HKD", "港币", 2, BigDecimal.ROUND_UP),

    USD("USD", "美元", 2, BigDecimal.ROUND_UP),

    EUR("EUR", "欧元", 2, BigDecimal.ROUND_UP),

    JPY("JPY", "日元", 0, BigDecimal.ROUND_UP),

    AUD("AUD", "澳元", 2, BigDecimal.ROUND_UP),

    CAD("CAD", "加拿大元", 2, BigDecimal.ROUND_UP),

    THB("THB", "泰铢", 2, BigDecimal.ROUND_UP),

    SGD("SGD", "新加坡元", 2, BigDecimal.ROUND_UP),

    NOK("NOK", "挪威克朗", 2, BigDecimal.ROUND_UP),

    NZD("NZD", "新西兰元", 2, BigDecimal.ROUND_UP),

    DKK("DKK", "丹麦克朗", 2, BigDecimal.ROUND_UP),

    SEK("SEK", "瑞典克朗", 2, BigDecimal.ROUND_UP),

    PHP("PHP", "菲律宾比索", 2, BigDecimal.ROUND_UP),

    RUB("RUB", "俄罗斯卢布", 2, BigDecimal.ROUND_UP),

    CHF("CHF", "瑞士法郎", 2, BigDecimal.ROUND_UP),

    TWD("TWD", "新台币", 2, BigDecimal.ROUND_UP),

    MOP("MOP", "澳门币", 2, BigDecimal.ROUND_UP),

    MYR("MYR", "马来西亚林吉特", 2, BigDecimal.ROUND_UP),

    ZAR("ZAR", "南非兰特", 2, BigDecimal.ROUND_UP),

    KRW("KRW", "韩元", 0, BigDecimal.ROUND_UP),

    GBP("GBP", "英镑", 2, BigDecimal.ROUND_UP),

    CNY("CNY", "人民币", 2, BigDecimal.ROUND_UP);

    private final String currencyNameEn;

    private final String currencyName;

    /**
     * 需要保留的小数位
     */
    private final int scale;

    /**
     * 进位
     */
    private final int roundingMode;

    CurrencyEnum(String currencyNameEn, String currencyName, int scale, int roundingMode) {
        this.currencyNameEn = currencyNameEn;
        this.currencyName = currencyName;
        this.scale = scale;
        this.roundingMode = roundingMode;
    }

    public static CurrencyEnum getByCurrencyNameEn(String currencyNameEn) {
        if (StringUtils.isEmpty(currencyNameEn)) {
            return null;
        }
        for (CurrencyEnum em : CurrencyEnum.values()) {
            if (StringUtils.equals(em.currencyNameEn, currencyNameEn)) {
                return em;
            }
        }
        return null;
    }

    public static String getCurrencyNameByCurrencyNameEn(String currencyNameEn) {
        if (StringUtils.isEmpty(currencyNameEn)) {
            return "";
        }
        for (CurrencyEnum em : CurrencyEnum.values()) {
            if (StringUtils.equals(em.currencyNameEn, currencyNameEn)) {
                return em.currencyName;
            }
        }
        return "";
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyNameEn() {
        return currencyNameEn;
    }

    public int getScale() {
        return scale;
    }

    public int getRoundingMode() {
        return roundingMode;
    }
}
