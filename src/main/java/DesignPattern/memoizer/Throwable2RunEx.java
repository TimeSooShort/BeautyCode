package DesignPattern.memoizer;

/**
 * 任务代码抛出的异常都被统一封装成ExecutionException
 * 这里进行拆分处理,主要是将其转化成RuntimeException
 */
public class Throwable2RunEx {

    public static RuntimeException launderThrowable(Throwable t){
        if (t instanceof RuntimeException){
            return (RuntimeException) t;
        }else if(t instanceof Error){
            throw (Error)t;
        }else{
            throw new IllegalStateException("Not checked", t);
        }
    }
}
