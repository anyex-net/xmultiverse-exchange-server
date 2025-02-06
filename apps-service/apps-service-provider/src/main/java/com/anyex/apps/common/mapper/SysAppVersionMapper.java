/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.common.entity.SysAppVersion;
import org.apache.ibatis.annotations.Mapper;

/**
 * 手机端版本表 持久层接口
 * <p>File：AppVersionMapper.java </p>
 * <p>Title: AppVersionMapper </p>
 * <p>Description:AppVersionMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 *
 * @author yukai
 * @version 1.0
 */
@Mapper
public interface SysAppVersionMapper extends GenericMapper<SysAppVersion>
{
}
