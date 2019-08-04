package order.util.calculate;

import order.constant.CurrencyEnum;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * 订单计算工具类
 * User: xuxianbei
 * Date: 2019/7/31
 * Time: 21:06
 * Version:V1.0
 * 提供基础计算逻辑
 */
@Component
public class OrderCalculateUtil {

    private final static BigDecimal BIG_DECIMAL_100 = new BigDecimal(100);

    private final static BigDecimal BIG_DECIMAL_1000000 = new BigDecimal(1000000);
    /**
     * 获取汇率
     *
     * @param currencyNameEn
     * @return
     */
    public BigDecimal getExchangeRate(String currencyNameEn) {
         // 从缓存中获取
        // 失败从数据库中获取
        BigDecimal rate = new BigDecimal(0);
        return rate;
    }

    /**
     * 计算rmb
     * @param num            数量
     * @param price          单价
     * @param currencyNameEn 外币编码
     * @return
     */
    public BigDecimal calculateRmb(Integer num, BigDecimal price, String currencyNameEn) {
        Assert.isTrue((num > 0 && price != null && price.longValue() > 0), "数量必须大于0且价格大于0");
        BigDecimal rate = getExchangeRate(currencyNameEn);
        rate = getCalcRate(rate);
        return price.multiply(new BigDecimal(num)).multiply(rate);
    }

    /**
     * 计算rmb
     * @param price
     * @param currencyNameEn
     * @return
     */
    public BigDecimal calculateRmb(BigDecimal price, String currencyNameEn) {
        BigDecimal rate = getExchangeRate(currencyNameEn);
        return price.multiply(getCalcRate(rate));
    }

    //计算汇率
    private BigDecimal getCalcRate(BigDecimal rate) {
        return rate.divide(BIG_DECIMAL_1000000);
    }

    /**
     * 外币界面展示
     * @param price
     * @return
     */
    public BigDecimal priceToVO(BigDecimal price, CurrencyEnum currencyEnum) {
        return price.divide(BIG_DECIMAL_100).setScale(currencyEnum.getScale(), currencyEnum.getRoundingMode());
    }

    /**
     * 外币界面展示
     * @param price
     * @return
     */
    public BigDecimal priceToVO(BigDecimal price, String currencyEnumEn) {
        CurrencyEnum currencyEnum = CurrencyEnum.getByCurrencyNameEn(currencyEnumEn);
        return price.divide(BIG_DECIMAL_100).setScale(currencyEnum.getScale(), currencyEnum.getRoundingMode());
    }

    /**
     * 外币 存储数据库
     * @param price
     * @return
     */
    public BigDecimal priceToDB(BigDecimal price, CurrencyEnum currencyEnum) {
        return price.setScale(currencyEnum.getScale(), currencyEnum.getRoundingMode()).multiply(BIG_DECIMAL_100);
    }

    /**
     * 外币 存储数据库
     * @param price
     * @return
     */
    public BigDecimal priceToDB(BigDecimal price, String currencyEnumEn) {
        CurrencyEnum currencyEnum = CurrencyEnum.getByCurrencyNameEn(currencyEnumEn);
        return price.setScale(currencyEnum.getScale(), currencyEnum.getRoundingMode()).multiply(BIG_DECIMAL_100);
    }


    /**
     * rmb 存储数据库
     * @param price
     * @return
     */
    public BigDecimal priceToRMBDB(BigDecimal price) {
        return priceToDB(price, CurrencyEnum.CNY);
    }


    /**
     * rmb 界面展示
     * @param price
     * @return
     */
    public BigDecimal priceToRMBVO(BigDecimal price) {
        return priceToVO(price, CurrencyEnum.CNY);
    }

}
