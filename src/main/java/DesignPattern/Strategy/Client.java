package DesignPattern.Strategy;

public class Client {

    public static void main(String[] args) {
        Player player = new Player();
        player.buy(500D);
        System.out.println("玩家应付：" + player.lastPrice());
        player.buy(600D);
        System.out.println("玩家应付：" + player.lastPrice());
        player.buy(1000D);
        System.out.println("玩家应付：" + player.lastPrice());
        player.buy(1500D);
        System.out.println("玩家应付：" + player.lastPrice());
    }
}
