package DesignPattern.JUCdp.ThreadPerMsg.Sample.GUI;

/**
 * 线程会立即从service方法返回，同时执行doService方法的线程只有一个
 * 用户按几次，doService就会执行几次，但输出不会混在一起
 */
public class Service2 {
    public static void service() {
        new Thread(Service2::doService).start();
    }

    private static synchronized void doService() {
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
