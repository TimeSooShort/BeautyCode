package DesignPattern.Strategy;

/**
 * 玩家
 */
public class Player {

    private Double totalAmount = 0D;
    private Double amount = 0D;
    private CalPrice calPrice = new Normal();

    public void buy(Double amount){
        this.amount = amount;
        this.totalAmount += amount;

//        if (totalAmount > 3000){
//            calPrice = new GoldVip();
//        }else if (totalAmount > 2000){
//            calPrice = new SuperVip();
//        } else if (totalAmount > 1000){
//            calPrice = new Vip();
//        }

//        calPrice = CalPriceFactory.createCalPrice(this);

        calPrice = CalPriceFactory.getInstance().createCalPrice(this);
    }

    public Double lastPrice(){
        return calPrice.calPrice(amount);
    }

    public Double getTotalAmount(){
        return totalAmount;
    }
}
