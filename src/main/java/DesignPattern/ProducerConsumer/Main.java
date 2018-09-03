package DesignPattern.ProducerConsumer;

public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);
        Thread[] threads = {
                new MakerThread("MakerThread-1", table, 31415),
                new MakerThread("MakerThread-2", table, 92653),
                new MakerThread("MakerThread-3", table, 45862),
                new EaterThread("EaterThread-1", table, 75128),
                new EaterThread("EaterThread-2", table, 56928),
                new EaterThread("EaterThread-3", table, 15648),
        };
        for (Thread thread : threads){
            thread.start();
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }

        System.out.println("*******interrupt*******");

        for (Thread thread : threads){
            thread.interrupt();
        }
    }
}
