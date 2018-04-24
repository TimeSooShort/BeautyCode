package DesignPattern.memoizer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 相较于Memorizer1,2采用ConcurrentHashMap
 * 无法避免重复值多次计算问题，因为复合操作“没有则添加”没有锁保护，无原子性
 */
public class Memorizer2<A, V> implements Computable<A, V>{

    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> computable;

    public Memorizer2(Computable<A, V> computable) {
        this.computable = computable;
    }

    public V compute(A args) throws InterruptedException {
        V result = cache.get(args);
        if (result == null){
            result = computable.compute(args);
            cache.put(args, result);
        }
        return result;
    }
}
