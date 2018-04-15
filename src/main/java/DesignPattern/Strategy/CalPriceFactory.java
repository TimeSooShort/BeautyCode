package DesignPattern.Strategy;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * 生产CalPrice的工厂
 */
public class CalPriceFactory {

//    private CalPriceFactory(){}
//
//    public static CalPrice createCalPrice(Player player){
//        if (player.getTotalAmount() > 3000){
//            return new GoldVip();
//        } else if (player.getTotalAmount() > 2000){
//            return new SuperVip();
//        } else if (player.getTotalAmount() > 1000) {
//            return new Vip();
//        } else {
//            return new Normal();
//        }
//    }

    private static final String CAL_PRICE_PACKAGE = "DesignPattern.Strategy";
    private ClassLoader loader = getClass().getClassLoader();
    private List<Class<? extends CalPrice>> calPriceList; //策略列表

    //单例
    private CalPriceFactory() {
        init();
    }

    //在工厂初始化时初始化策略列表
    private void init(){
        calPriceList = new ArrayList<Class<? extends CalPrice>>();
        File[] resources = getResources();
        Class<CalPrice> calPriceClass = null;
        try {
            calPriceClass = (Class<CalPrice>) loader.loadClass(CalPrice.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("未找到策略接口");
        }
        for (int i = 0; i < resources.length; i++){
            try {
                Class<?> clazz = loader.loadClass(CAL_PRICE_PACKAGE + "." + resources[i].getName().replace(".class", ""));
                //判断是否是CalPrice的实现类并且不是CalPrice本身，符合就加到calPriceList
                if (CalPrice.class.isAssignableFrom(clazz) && calPriceClass != clazz) {
                    calPriceList.add((Class<? extends CalPrice>) clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //获取扫描的包下的所有.class文件
    private File[] getResources(){
        File file = null;
        try {
            file = new File(loader.getResource(CAL_PRICE_PACKAGE.replace(".", "/")).toURI());
            return file.listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    if (pathname.getName().endsWith(".class")){
                        return true;
                    }
                    return false;
                }
            });
        } catch (URISyntaxException e) {
            throw new RuntimeException("未找到资源");
        }
    }

    //根据玩家消费的总金额产生对应的策略
    public CalPrice createCalPrice(Player player){
        //在策略列表查找策略
        for (Class<? extends CalPrice> clazz: calPriceList){
            PriceRegion region = handleAnnotation(clazz); //返回策略的注解
            //判断金额是否在注解的区间
            if (player.getTotalAmount() > region.min() && player.getTotalAmount() < region.max()){
                try {
                    //是的话，返回当前策略的实例
                    return clazz.newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("策略实例获得失败");
                }
            }
        }
        throw new RuntimeException("策略实例获得失败");
    }

    //传入一个策略类，返回它的注解
    private PriceRegion handleAnnotation(Class<? extends CalPrice> clazz){
        Annotation[] annotations = clazz.getDeclaredAnnotations();
        if (annotations == null || annotations.length == 0){
            return null;
        }
        for (int i = 0; i < annotations.length; i++){
            if (annotations[i] instanceof PriceRegion){
                return (PriceRegion) annotations[i];
            }
        }
        return null;
    }

    public static CalPriceFactory getInstance(){
        return CalPriceInstance.instance;
    }

    private static class CalPriceInstance{
        private static CalPriceFactory instance = new CalPriceFactory();
    }
}
