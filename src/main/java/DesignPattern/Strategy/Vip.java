package DesignPattern.Strategy;

/**
 * vip用户9折
 */
@PriceRegion(min = 1000, max = 2000)
public class Vip implements CalPrice{

    public Double calPrice(Double price) {
        return price*0.9;
    }
}
