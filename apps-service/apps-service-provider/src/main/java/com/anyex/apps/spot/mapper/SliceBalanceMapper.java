/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.spot.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.spot.entity.Operlog;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.spot.entity.SliceBalance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * slice_balance_example 持久层接口
 * <p>File：SliceBalanceExampleMapper.java </p>
 * <p>Title: SliceBalanceExampleMapper </p>
 * <p>Description:SliceBalanceExampleMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SliceBalanceMapper extends GenericMapper<SliceBalance>
{
    List<SliceBalance> selectList(SliceBalance sliceBalance, @Param("tableName") String tableName);
}
