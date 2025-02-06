/*
 * @(#)JsonMessage.java 2014-4-17 下午4:04:43
 * Copyright 2014 Playguy, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.anyex.apps.bean.EnumDescribable;
import com.anyex.apps.enums.CommonEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>File：JsonMessage.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-4-17 下午4:04:43</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "返回结果对象")
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class JsonMessage implements Serializable
public class JsonMessage<T> implements Serializable
{
    private static final long serialVersionUID = 714679657596837388L;

    @ApiModelProperty(value = "编码")
    private Integer           code;

    @ApiModelProperty(value = "消息")
    private String            message;

    @ApiModelProperty(value = "对象")
    //private Object            data;
    private T                 data;

    @ApiModelProperty(value = "时间戳")
    private Long              timestamp;


//    @ApiModelProperty(value = "数据集")
//    //private List<?>           rows;
//    private List<T>           rows;
//
//    @ApiModelProperty(value = "总记录数")
//    private Long              total;
//
//    // 分页查询时, 当前页码
//    @ApiModelProperty(value = "当前页码")
//    private Integer           currentPage;
//
//    // 分页查询时，总共页数
//    @ApiModelProperty(value = "总页数")
//    private Integer           totalPage;
//
//    // 是否有下一页
//    @ApiModelProperty(value = "是否有下一页")
//    private Boolean           hasNext;
//
//    // 是否有上一页
//    @ApiModelProperty(value = "是否有上一页")
//    private Boolean           hasPrevious;

    public JsonMessage()
    {
    }

    public JsonMessage(EnumDescribable enumDescribable)
    {
        this.code = enumDescribable.getCode();
        this.message = enumDescribable.getMessage();
        this.timestamp = System.currentTimeMillis();
    }

//    public JsonMessage(EnumDescribable enumDescribable, Object object)
//    {
//        this.data = object;
//        this.code = enumDescribable.getCode();
//        this.message = enumDescribable.getMessage();
//    }
    public JsonMessage(EnumDescribable enumDescribable, T object)
    {
        this.code = enumDescribable.getCode();
        this.message = enumDescribable.getMessage();
        this.data = object;
        this.timestamp = System.currentTimeMillis();
    }

    public JsonMessage(Integer code, String message)
    {
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }


    /**
     * 返回JsonMessage
     *
     * @param data 数据
     * @param <T>  T 泛型标记
     * @return JsonMessage
     */
    public static <T> JsonMessage<T> data(T data) {
        return new JsonMessage(CommonEnums.SUCCESS, data);
    }
}