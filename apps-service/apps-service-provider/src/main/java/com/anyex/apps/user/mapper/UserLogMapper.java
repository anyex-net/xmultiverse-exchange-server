/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.exception.BusinessException;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.user.entity.UserLog;

import java.util.List;

/**
 * 用户日志 持久层接口
 * <p>File：UserLogMapper.java </p>
 * <p>Title: UserLogMapper </p>
 * <p>Description:UserLogMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UserLogMapper extends GenericMapper<UserLog>
{
    List<UserLog> findTopTenUserLog(UserLog userLog);
}
