/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.fund.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.fund.entity.Balances;

/**
 * 资金账户余额 持久层接口
 * <p>File：BalancesMapper.java </p>
 * <p>Title: BalancesMapper </p>
 * <p>Description:BalancesMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface BalancesMapper extends GenericMapper<Balances>
{

}
