package DesignPattern.JUCdp.ThreadPerMsg.Sample.GUI;

/**
 * 启动一个线程，由该线程来执行实际处理操作，这样线程便可以立刻从service方法返回
 * 不过在用户多次点击按钮时，多个线程同时执行doService
 */
public class Service1 {
    public static void service() {
        new Thread(Service1::doService).start();
    }

    private static void doService() {
        System.out.print("service");
        for (int i = 0; i < 50; i++) {
            System.out.print(".");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        System.out.println("done.");
    }
}
