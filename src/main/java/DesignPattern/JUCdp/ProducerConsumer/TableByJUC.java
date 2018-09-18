package DesignPattern.JUCdp.ProducerConsumer;

import java.util.concurrent.ArrayBlockingQueue;

public class TableByJUC extends ArrayBlockingQueue<String> {

    public TableByJUC(int capacity) {
        super(capacity);
    }

    public void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " puts " + cake);
        super.put(cake);
    }

    public String take() throws InterruptedException {
        String cake = super.take();
        System.out.println(Thread.currentThread().getName() + " take " + cake);
        return cake;
    }
}
