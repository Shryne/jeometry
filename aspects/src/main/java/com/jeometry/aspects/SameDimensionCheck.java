package com.jeometry.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SameDimensionCheck {

    @Before("execution(@com.jeometry.aspects.DimensionsEqual * *(..))")
    public void check(JoinPoint joinPoint) {
        System.out.println("aspect before");
    }
}
