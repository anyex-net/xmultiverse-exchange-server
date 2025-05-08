/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.user.entity.UserLog;

import java.util.List;

/**
 * 用户日志 服务接口
 * <p>File：UserLogService.java </p>
 * <p>Title: UserLogService </p>
 * <p>Description:UserLogService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface UserLogService extends GenericService<UserLog>
{
    /**
     * 查询数据列表，如果需要分页，请设置分页对象
     *
     * @param userLog
     * @return {@link List}
     * @throws BusinessException
     */
    List<UserLog> findTopTenUserLog(UserLog userLog) throws BusinessException;
}
