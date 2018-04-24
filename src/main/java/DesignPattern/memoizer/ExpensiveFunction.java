package DesignPattern.memoizer;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {

    public BigInteger compute(String args) throws InterruptedException {
        //在经过长时间计算后
        return new BigInteger(args);
    }
}
