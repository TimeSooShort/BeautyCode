package DesignPattern.ProducerConsumer.Exchanger;

import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
        Exchanger<char[]> exchanger = new Exchanger<>();
        char[] buffer1 = new char[5];
        char[] buffer2 = new char[5];
        ProducerThread producerThread = new ProducerThread(exchanger, buffer1, 654987);
        ConsumerThread consumerThread = new ConsumerThread(exchanger, buffer2, 123654);
        producerThread.start();
        consumerThread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producerThread.interrupt();
        consumerThread.interrupt();
    }
}
