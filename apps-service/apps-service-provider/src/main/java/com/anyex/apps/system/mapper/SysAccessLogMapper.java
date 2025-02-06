/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.system.entity.SysAccessLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * AccessLog 持久层接口
 * <p>File：AccessLogMapper.java </p>
 * <p>Title: AccessLogMapper </p>
 * <p>Description:AccessLogMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysAccessLogMapper extends GenericMapper<SysAccessLog>
{

}
