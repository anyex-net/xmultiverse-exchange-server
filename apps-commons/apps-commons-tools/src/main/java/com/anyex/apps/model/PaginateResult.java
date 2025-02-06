/*
 * @(#)PaginateResult.java 2014-1-8 下午1:32:17
 * Copyright 2014 Playguy, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>File：PaginateResult.java</p>
 * <p>Title: 分页查询结果对象</p>
 * <p>Description:封装Pagination及List对象，供前端调用及显示分页结果</p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午1:32:17</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "分页查询结果对象")
public class PaginateResult<T> implements Serializable
{
    private static final long serialVersionUID = 3612116988761318827L;
    
//    public PaginateResult()
//    {
//        super();
//    }
    
    public PaginateResult(Pagination page, List<T> records)
    {
        this.page = page;
        this.records = records;
        // 初始化
        this.size = page.size;
        this.total = page.total.intValue();
        this.current = page.current;
        this.pages = page.pages;
    }

    @JsonIgnore
    @ApiModelProperty(value = "分页对象")
    private Pagination page;
    
    @ApiModelProperty(value = "数据列表")
    private List<T>    records;

    @ApiModelProperty(value = "分页大小")
    private Integer    size;

    @ApiModelProperty(value = "总记录数")
    private Integer    total;

    @ApiModelProperty(value = "当前页码")
    private Integer    current;

    @ApiModelProperty(value = "总页数")
    private Integer    pages;
    
    public Pagination getPage()
    {
        return page;
    }
    
    public void setPage(Pagination page)
    {
        this.page = page;
    }
    
    public List<T> getRecords()
    {
        return records;
    }
    
    public void setRecords(List<T> records)
    {
        this.records = records;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total.intValue();
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
