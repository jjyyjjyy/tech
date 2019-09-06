package me.jy.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author jy
 */
@Aspect
@Component
public class DemoAspect {

    @Before("execution(* me.jy.service..*.*(..))")
    public void before() {
        System.out.println("Aspect before ...");
    }
}
