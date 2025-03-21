/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.spot.entity.SliceHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * slice_history 持久层接口
 * <p>File：SliceHistoryMapper.java </p>
 * <p>Title: SliceHistoryMapper </p>
 * <p>Description:SliceHistoryMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SliceHistoryMapper extends GenericMapper<SliceHistory>
{
//    List<SliceHistory> selectList(SliceHistory history, @Param("tableName") String tableName);
}
