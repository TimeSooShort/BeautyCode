package DesignPattern.Strategy;

/**
 * 计算最终价格，分为普通用户，vip，superVip，goldVip
 */
public interface CalPrice {

    //返回一个最终的价格
    Double calPrice(Double price);
}
