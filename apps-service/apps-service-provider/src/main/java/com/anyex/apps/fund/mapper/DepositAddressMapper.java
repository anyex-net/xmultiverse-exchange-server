/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.fund.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.fund.entity.DepositAddress;

/**
 * 充值地址 持久层接口
 * <p>File：DepositAddressMapper.java </p>
 * <p>Title: DepositAddressMapper </p>
 * <p>Description:DepositAddressMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface DepositAddressMapper extends GenericMapper<DepositAddress>
{

}
