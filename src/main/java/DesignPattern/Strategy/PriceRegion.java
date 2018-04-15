package DesignPattern.Strategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义了一个接口，用来限定价格范围
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PriceRegion {
    int max() default Integer.MAX_VALUE;
    int min() default Integer.MIN_VALUE;
}
