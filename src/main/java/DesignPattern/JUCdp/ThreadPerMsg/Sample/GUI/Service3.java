package DesignPattern.JUCdp.ThreadPerMsg.Sample.GUI;

/**
 * 线程立即从service方法返回，保证执行doService方法的只有一个线程
 */
public class Service3 {
    private static volatile boolean working = false;
    public static synchronized void service() {
        System.out.print("service");
        if (working){
            System.out.println(" is balked,");
            return;
        }
        working = true;
        new Thread(Service3::doService).start();
    }

    private static void doService() {
        try {
            for (int i = 0; i < 50; i++) {
                System.out.print(".");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
            System.out.println("done.");
        } finally {
            working = false;
        }
    }
}
