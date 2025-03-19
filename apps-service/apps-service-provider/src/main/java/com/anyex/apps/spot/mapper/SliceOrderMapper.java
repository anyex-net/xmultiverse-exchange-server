/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.spot.entity.SliceOrder;

/**
 * slice_order_example 持久层接口
 * <p>File：SliceOrderExampleMapper.java </p>
 * <p>Title: SliceOrderExampleMapper </p>
 * <p>Description:SliceOrderExampleMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SliceOrderMapper extends GenericMapper<SliceOrder>
{

}
