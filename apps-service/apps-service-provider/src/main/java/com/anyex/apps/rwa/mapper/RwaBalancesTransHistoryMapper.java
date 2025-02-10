/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.rwa.entity.RwaBalancesTransHistory;

/**
 * RWA账户交易历史 持久层接口
 * <p>File：RwaBalancesTransHistoryMapper.java </p>
 * <p>Title: RwaBalancesTransHistoryMapper </p>
 * <p>Description:RwaBalancesTransHistoryMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface RwaBalancesTransHistoryMapper extends GenericMapper<RwaBalancesTransHistory>
{

}
