/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.user.entity.UserApi;

/**
 * 用户API 持久层接口
 * <p>File：UserApiMapper.java </p>
 * <p>Title: UserApiMapper </p>
 * <p>Description:UserApiMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UserApiMapper extends GenericMapper<UserApi>
{

}
