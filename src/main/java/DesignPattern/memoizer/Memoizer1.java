package DesignPattern.memoizer;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个结果缓存器的演变
 * Memoizer1使用锁保护，并发行差
 */
public class Memoizer1<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> computable;

    public Memoizer1(Computable<A, V> computable) {
        this.computable = computable;
    }

    public synchronized V compute(A args) throws InterruptedException {
        V result = cache.get(args);
        if (result == null){
            result = computable.compute(args);
            cache.put(args, result);
        }
        return result;
    }
}
