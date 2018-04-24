package DesignPattern.memoizer;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 为了解决2的重复计算问题，这里采用FutureTask；
 * 但是仍未完全避免重复计算问题，不过重复概率更小；
 * 原因和2相同，符合操作没能保证原子性
 * @param <A>
 * @param <V>
 */
public class Memorizer3<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> computable;

    public Memorizer3(Computable<A, V> computable) {
        this.computable = computable;
    }

    public V compute(final A args) throws InterruptedException {
        Future<V> future = cache.get(args);
        if (future == null){
            FutureTask<V> futureTask = new FutureTask<V>(new Callable<V>() {
                public V call() throws Exception {
                    return computable.compute(args);
                }
            });
            future = futureTask;
            cache.put(args, futureTask);
            futureTask.run();
        }
        try {
            return future.get(); //阻塞直到结果计算出来
        } catch (ExecutionException e) {
            throw Throwable2RunEx.launderThrowable(e.getCause());
        }
    }
}
