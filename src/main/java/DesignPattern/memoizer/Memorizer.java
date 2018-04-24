package DesignPattern.memoizer;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 这里采用ConcurrentHashMap.putIfAbsent来解决重复计算问题
 * @param <A>
 * @param <V>
 */
public class Memorizer<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memorizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    public V compute(final A args) throws InterruptedException {
        while (true){  //这里采用while循环，为了防止阻塞线程被意外唤醒
            Future<V> future = cache.get(args);
            if (future == null){
                FutureTask<V> futureTask = new FutureTask<>(new Callable<V>() {
                    public V call() throws Exception {
                        return computable.compute(args);
                    }
                });
                future = cache.putIfAbsent(args, futureTask); //原子性的实现了“如果没有则添加”的符合操作
                if (future == null){
                    future = futureTask;
                    futureTask.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e){
                cache.remove(args, future); //计算被取消或失败时，删除Future
            } catch (ExecutionException e) {
                throw Throwable2RunEx.launderThrowable(e.getCause());
            }
        }
    }
}
