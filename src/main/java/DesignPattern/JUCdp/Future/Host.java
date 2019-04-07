package DesignPattern.JUCdp.Future;

public class Host {

    public Data request(final int count, final char c) {
        System.out.println("    request("+count+", "+c+") BEGIN");
        final FutureData futureData = new FutureData(); // 创建FutureData的实例
        // 启动一个线程执行耗时任务
        new Thread(() -> {
            RealData realData = new RealData(count, c);
            futureData.setRealData(realData);
        }).start();
        System.out.println("    request("+count+", "+c+") END");
        return futureData; // 返回FutureData
    }
}
