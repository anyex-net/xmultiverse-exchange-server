/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.user.entity.UserRebate;

/**
 * 用户返佣记录 持久层接口
 * <p>File：UserRebateMapper.java </p>
 * <p>Title: UserRebateMapper </p>
 * <p>Description:UserRebateMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UserRebateMapper extends GenericMapper<UserRebate>
{

}
