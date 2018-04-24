package DesignPattern.memoizer;

import java.util.Map;
import java.util.concurrent.*;

public class Memorizer<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> computable;

    public Memorizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    public V compute(final A args) throws InterruptedException {
        while (true){  //这里采用while循环，为了防止阻塞线程被意外唤醒
            Future<V> future = cache.get(args);
            if (future == null){
                FutureTask<V> futureTask = new FutureTask<V>(new Callable<V>() {
                    public V call() throws Exception {
                        return computable.compute(args);
                    }
                });
                future = cache.putIfAbsent(args, futureTask);
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
