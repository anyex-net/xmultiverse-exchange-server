package com.anyex.apps.aop;

import java.lang.annotation.*;

/**
 * 访问日志注解
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface AccessLog {
    String module() default ""; // 访问模块
    String type() default "";  // 访问类型
    String desc() default "";  // 访问说明
}
