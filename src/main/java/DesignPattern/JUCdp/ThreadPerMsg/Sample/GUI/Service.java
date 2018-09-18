package DesignPattern.JUCdp.ThreadPerMsg.Sample.GUI;

/**
 * Swing框架会调用actionPerformed方法，该方法会调用Service.service()，由于service()比较耗时
 * 导致按钮的反应，以及应用程序对用户的响应都会变得非常慢.
 */
public class Service {
    public static void service() {
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
