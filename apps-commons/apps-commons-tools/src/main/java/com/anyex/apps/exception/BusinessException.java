/*
 * @(#)BusinessException.java 2014年3月12日 上午9:31:59
 * Copyright 2014 Playguy, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.exception;

import com.anyex.apps.bean.EnumDescribable;
import com.anyex.apps.bean.ErrorInfo;
import lombok.Data;

/**
 * 自定义业务异常
 * <p>File：BusinessException.java</p>
 * <p>Title: BusinessException</p>
 * <p>Description: BusinessException</p>
 * <p>Copyright: Copyright (c) 2014 2014-2-27 下午9:05:01</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class BusinessException extends RuntimeException
{
    private static final long serialVersionUID = -3267019434607947850L;

    /**
     * 错误代码
     */
    private Integer           code;

    /**
     * 消息对象
     */
    private Object            object;

    /**
     * 错误描述
     */
    private EnumDescribable   error;

    /**
     * 目前业务异常非常多，业务异常代码未进行整理，
     * 考虑到后期统一对业务异常代码进行规范编号的问题，
     * 目前开放此接口解决困扰程序员找不到特定的错误代码的问题
     *
     * @param message
     */
    public BusinessException(String message)
    {
        super(message);
        this.code = 9999;
        this.error = new ErrorInfo(this.code, message);
    }

    public BusinessException(Integer code, String message)
    {
        super(message);
        this.code = code;
        this.error = new ErrorInfo(code, message);
    }

    public BusinessException(Integer code, String message, Object object)
    {
        this(code, message);
        this.object = object;
    }

    /**
     * @author Playguy
     */
    public BusinessException(EnumDescribable describable)
    {
        super(new StringBuilder("error code: ").append(describable.getCode()).append(", description: ").append(describable.getMessage()).toString());
        this.error = describable;
    }

    /**
     * @param describable
     * @param object
     * @author Playguy
     */
    public BusinessException(EnumDescribable describable, Object object)
    {
        super(new StringBuilder("error code: ").append(describable.getCode()).append(", description: ").append(describable.getMessage()).toString());
        this.error = describable;
        this.object = object;
    }
}
