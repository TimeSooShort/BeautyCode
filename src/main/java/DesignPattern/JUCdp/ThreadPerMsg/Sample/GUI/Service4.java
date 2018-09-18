package DesignPattern.JUCdp.ThreadPerMsg.Sample.GUI;

/**
 * 线程立即从service方法返回，当用户连续点击时，取消任务重新开始
 */
public class Service4 {
    private static Thread worker = null;
    public static synchronized void service() {
        if (worker != null && worker.isAlive()) {
            worker.interrupt();
            try {
                worker.join();
            } catch (InterruptedException e) {
            }
            worker = null;
        }
        System.out.print("service");
        worker  = new Thread(Service4::doService);
        worker.start();
    }

    private static void doService() {
        try {
            for (int i = 0; i < 50; i++) {
                System.out.print(".");
                Thread.sleep(100);
            }
            System.out.println("done.");
        } catch (InterruptedException e){
            System.out.println("cancelled.");
        }
    }
}
