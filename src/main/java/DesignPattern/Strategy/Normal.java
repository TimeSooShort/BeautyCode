package DesignPattern.Strategy;

/**
 * 普通用户
 */
@PriceRegion(min = 0, max = 1000)
public class Normal implements CalPrice {

    public Double calPrice(Double price) {
        return price;
    }
}
