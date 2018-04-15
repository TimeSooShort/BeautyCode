package DesignPattern.Strategy;

/**
 * 黄金vip，7折
 */
@PriceRegion(min = 3000)
public class GoldVip implements CalPrice {
    public Double calPrice(Double price) {
        return price * 0.7;
    }
}
