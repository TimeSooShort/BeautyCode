package DesignPattern.JUCdp.ProducerConsumer.Exchanger;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ProducerThread extends Thread {

    private final Exchanger<char[]> exchanger;
    private char[] buffer;
    private char index;
    private final Random random;

    public ProducerThread(Exchanger<char[]> exchanger, char[] buffer, long seed) {
        super("ProducerThread");
        this.exchanger = exchanger;
        this.buffer = buffer;
        this.random = new Random(seed);
        this.index = 0;
    }

    @Override
    public void run() {
        try {
            while(true) {
                for (int i = 0; i < buffer.length; i++) {
                    // 向缓冲区添加数据
                    buffer[i] = nextChar();
                    System.out.println(Thread.currentThread().getName() + ": " +
                            buffer[i] + " -> ");
                }
                System.out.println(Thread.currentThread().getName() + ": BEFORE exchange");
                buffer = exchanger.exchange(buffer);
                System.out.println(Thread.currentThread().getName() + ": AFTER exchange");
            }
        } catch (InterruptedException e) {
        }
    }

    // 生成字符
    private char nextChar() throws InterruptedException {
        char c = (char) ('A' + index%26);
        index++;
        Thread.sleep(random.nextInt(1000));
        return c;
    }
}
