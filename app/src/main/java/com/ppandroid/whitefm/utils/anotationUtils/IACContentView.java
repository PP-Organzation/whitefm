package com.ppandroid.whitefm.utils.anotationUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yeqinfu on 2016/4/7.
 * ACTIVITY 内容注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface IACContentView {
    int value();
}