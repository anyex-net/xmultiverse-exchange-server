/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.spot.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * order_detail_example 持久层接口
 * <p>File：OrderDetailExampleMapper.java </p>
 * <p>Title: OrderDetailExampleMapper </p>
 * <p>Description:OrderDetailExampleMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface OrderDetailMapper extends GenericMapper<OrderDetail>
{
    List<OrderDetail> selectList(OrderDetail orderDetail, @Param("tableName") String tableName);

}
