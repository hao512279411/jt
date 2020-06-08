package com.jt.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheUpdate {
    //指定的前缀为多少  后动态添加 后缀 item_cat_id::
    String parKey();
    // 设置超时时间
    int seconds() default 0;
}
