/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.fund.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.fund.entity.WithdrawalHistory;

/**
 * 提现历史 持久层接口
 * <p>File：WithdrawalHistoryMapper.java </p>
 * <p>Title: WithdrawalHistoryMapper </p>
 * <p>Description:WithdrawalHistoryMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface WithdrawalHistoryMapper extends GenericMapper<WithdrawalHistory>
{

}
