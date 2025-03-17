/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.spot.entity.DealHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * deal_history_example 持久层接口
 * <p>File：DealHistoryExampleMapper.java </p>
 * <p>Title: DealHistoryExampleMapper </p>
 * <p>Description:DealHistoryExampleMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface DealHistoryMapper extends GenericMapper<DealHistory>
{
    List<DealHistory> selectList(DealHistory dealHistory, @Param("tableName") String tableName);
}
