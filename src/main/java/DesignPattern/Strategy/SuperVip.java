package DesignPattern.Strategy;

/**
 * 超级vip，8折
 */
@PriceRegion(min = 2000, max = 3000)
public class SuperVip implements CalPrice {
    public Double calPrice(Double price) {
        return price * 0.8;
    }
}
