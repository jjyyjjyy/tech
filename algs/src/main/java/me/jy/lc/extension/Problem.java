package me.jy.lc.extension;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Problem {

    int value();

    Difficulty difficulty() default Difficulty.EASY;

    enum Difficulty {
        EASY, MEDIUM, HARD
    }
}
