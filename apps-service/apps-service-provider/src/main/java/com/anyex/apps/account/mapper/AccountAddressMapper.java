/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.account.entity.AccountAddress;

/**
 * 账户地址表 持久层接口
 * <p>File：AccountAddressMapper.java </p>
 * <p>Title: AccountAddressMapper </p>
 * <p>Description:AccountAddressMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountAddressMapper extends GenericMapper<AccountAddress>
{

}
