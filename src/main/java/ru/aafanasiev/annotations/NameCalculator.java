/**
 *
 */
package ru.aafanasiev.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marker for Name Calculator in calculators packages.
 *
 * @author <a href="mailto:aafanasyev@umail.ru">Andrey Afanasiev</a>
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface NameCalculator {
    /**
     * Priority of name calculators <br>
     * Name calculators will be sorted from high to low value of priority<br>
     * Default: {@code 1}
     *
     * @return Name calculator priority
     */
    int value() default 1;
}
