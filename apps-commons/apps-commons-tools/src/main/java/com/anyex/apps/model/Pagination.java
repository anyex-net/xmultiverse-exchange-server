/*
 * @(#)Pagination.java 2014-1-8 下午1:30:30
 * Copyright 2014 Playguy, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.model;

import com.anyex.apps.consts.GlobalConst;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * <p>File：Pagination.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午1:30:30</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "分页对象")
public class Pagination implements Serializable
{
    private static final long serialVersionUID = -4312266410100339520L;
    
    // 是否有上一页
    @ApiModelProperty(value = "是否有上一页", hidden = true)
    protected Boolean         hasPreviousPage;
    
    // 是否有下一页
    @ApiModelProperty(value = "是否有下一页", hidden = true)
    protected Boolean         hasNextPage      = false;
    
    // 每页的记录数
    @Min(value = 1, message = "分页大小最小值为1")
    @Max(value = 50, message = "分页大小最大值为50")
    @ApiModelProperty(value = "分页大小")
    protected Integer         size             = GlobalConst.DEFAULT_PAGE_SIZE;
    
    // 当前是第几页
    @ApiModelProperty(value = "当前页数")
    protected Integer         current          = GlobalConst.DEFAULT_CURRENT_PAGE;
    
    // 记录开始位置
    @ApiModelProperty(value = "记录开始位置", hidden = true)
    protected Integer         startIndex       = GlobalConst.DEFAULT_START_INDEX;
    
    // 记录结束位置
    @ApiModelProperty(value = "记录结束位置", hidden = true)
    protected Integer         endIndex         = GlobalConst.DEFAULT_START_INDEX;
    
    // 记录的总数量
    @ApiModelProperty(value = "记录的总数量", hidden = true)
    protected Long            total            = 0L;
    
    // 记录的总页数
    @ApiModelProperty(value = "记录的总页数", hidden = true)
    protected Integer         pages;
    
    @JsonIgnore
    @ApiModelProperty(value = "排序字段", hidden = true)
    private String            sort             = "id";
    
    @JsonIgnore
    @ApiModelProperty(value = "排序方式", hidden = true)
    private String            order            = "desc";
    
    /**
     * 构造器一
     */
    public Pagination()
    {
        super();
    }
    
    public Pagination(Integer size)
    {
        this.size = size;
    }
    
    /**
     * 构造器二
     */
    public Pagination(Integer current, Integer size)
    {
        this.size = size;
        this.current = current;
        this.startIndex = (this.current - 1) * this.size;
    }
    
    /**
     * 构造器三
     */
    public Pagination(Boolean hasPreviousPage, Boolean hasNextPage, Integer size, Integer pages, Integer current, Integer startIndex, Long total)
    {
        this.hasPreviousPage = hasPreviousPage;
        this.hasNextPage = hasNextPage;
        this.pages = pages;
        this.startIndex = startIndex;
        this.total = total;
        this.size = size;
        this.current = current;
    }
    
    /**
     * 取得是否有上页的标记
     *
     * @return boolean 是否有上页的标记(true：有,false：无)
     */
    public Boolean getHasPreviousPage()
    {
        return hasPreviousPage;
    }
    
    /**
     * 设置是否有上页的标记
     *
     * @param hasPreviousPage 是否有上页的标记(true：有,false：无)
     */
    public void setHasPreviousPage(Boolean hasPreviousPage)
    {
        this.hasPreviousPage = hasPreviousPage;
    }
    
    /**
     * 取得是否有下页的标记
     *
     * @return boolean 是否有下页的标记(true：有,false：无)
     */
    public Boolean getHasNextPage()
    {
        return hasNextPage;
    }
    
    /**
     * 设置是否有下页的标记
     *
     * @param hasNextPage 是否有下页的标记(true：有,false：无)
     */
    public void setHasNextPage(Boolean hasNextPage)
    {
        this.hasNextPage = hasNextPage;
    }
    
    /**
     * 取得每页显示的资料笔数
     *
     * @return int 每页显示的资料笔数(默认为20)
     */
    public Integer getSize()
    {
        if (size > 100)
        { return 100; }
        return size;
    }
    
    /**
     * 设置每页显示的资料笔数
     *
     * @param size 每页显示的资料笔数(默认为20)
     */
    public void setSize(Integer size)
    {
        if (size < 1)
        {
            this.size = GlobalConst.DEFAULT_PAGE_SIZE;
        }
        else
        {
            this.size = size;
        }
    }
    
    /**
     * 取得当前显示的页标
     *
     * @return int 当前显示的页标
     */
    public Integer getCurrent()
    {
        return current;
    }
    
    /**
     * 设置当前显示的页标
     *
     * @param current 当前显示的页标
     */
    public void setCurrent(Integer current)
    {
        if (current < 1)
        {
            this.current = GlobalConst.DEFAULT_CURRENT_PAGE;
        }
        else
        {
            this.current = current;
        }
    }
    
    /**
     * 取得记录开始位置
     *
     * @return int 记录开始位置
     */
    public Integer getStartIndex()
    {
        if (startIndex == 0)
        { return (this.current - 1) * this.size; }
        return startIndex;
    }
    
    /**
     * 设置记录开始位置
     *
     * @param startIndex 记录开始位置
     */
    public void setStartIndex(Integer startIndex)
    {
        this.startIndex = startIndex;
    }
    
    /**
     * 取分页结束位置
     *
     * @return
     */
    public Integer getEndIndex()
    {
        return getStartIndex() + size;
    }
    
    /**
     * 设置分页结束位置
     *
     * @return
     */
    public void setEndIndex(Integer endIndex)
    {
        this.endIndex = endIndex;
    }
    
    /**
     * 取得资料总笔数
     *
     * @return int 资料总笔数
     */
    public Long getTotal()
    {
        return total;
    }
    
    /**
     * 设置资料总笔数
     *
     * @param total 资料总笔数
     */
    public void setTotal(Long total)
    {
        this.total = total;
        this.pages = new Double(Math.ceil(1.0 * total / this.getSize())).intValue();
        this.hasNextPage = this.getPages() > this.getCurrent();
        this.hasPreviousPage = this.getCurrent() > 1;
    }
    
    /**
     * 取得资料总页数
     *
     * @return int 资料总页数
     */
    public Integer getPages()
    {
        return pages;
    }
    
    /**
     * 设置资料总页数
     *
     * @param pages 资料总页数
     */
    public void setPages(Integer pages)
    {
        this.pages = pages;
    }
    
    public String getSort()
    {
        return sort;
    }
    
    public void setSort(String sort)
    {
        this.sort = sort;
    }
    
    public String getOrder()
    {
        return order;
    }
    
    public void setOrder(String order)
    {
        this.order = order;
    }
}
