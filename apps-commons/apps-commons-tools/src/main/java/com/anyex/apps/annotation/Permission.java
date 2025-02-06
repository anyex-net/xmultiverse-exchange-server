//package com.anyex.apps.annotation;
//
//import com.anyex.apps.enums.LogicTypeEnum;
//
//import java.lang.annotation.*;
//
///**
// *
// * 权限注解，用于检查权限
// * 使用方式：@Permission表示检查是否有权限访问该资源
// *
// */
//@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
//@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
//@Documented
//public @interface Permission {
//
//    /**
//     * 加上此注解表示需要有该资源url的才可以访问, 默认值为空，即该url，如果设置了值，则表示有该角色才可以访问
//     */
//    String[] value() default {};
//
//    /**
//     * 逻辑枚举，默认或
//     */
//    LogicTypeEnum logicType() default LogicTypeEnum.OR;
//}
