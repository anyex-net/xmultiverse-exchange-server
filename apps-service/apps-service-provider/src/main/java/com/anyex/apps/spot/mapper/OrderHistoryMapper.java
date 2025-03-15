/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.spot.entity.OrderHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * order_history_example 持久层接口
 * <p>File：OrderHistoryExampleMapper.java </p>
 * <p>Title: OrderHistoryExampleMapper </p>
 * <p>Description:OrderHistoryExampleMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface OrderHistoryMapper extends GenericMapper<OrderHistory>
{

}
