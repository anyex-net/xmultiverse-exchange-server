package com.anyex.apps.interceptor;

import java.lang.annotation.*;

/**
 * AccessLimit Introduce
 * <p>File：AccessLimit.java</p>
 * <p>Title: AccessLimit</p>
 * <p>Description: AccessLimit</p>
 * <p>Copyright: Copyright (c) 2021/5/25</p>
 * <p>Company: AnyEx</p>
 *
 * @author Sun
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit
{
    /**
     * 请求限制数
     */
    int limit();

    /**
     * 时间范围单位秒
     */
    int timeScope();

    /**
     * 是否登录
     */
    boolean isLogin();
}
