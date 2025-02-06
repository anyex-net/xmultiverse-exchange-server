/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.common.entity.SysMsgRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信记录表 持久层接口
 * <p>File：MsgRecordMapper.java </p>
 * <p>Title: MsgRecordMapper </p>
 * <p>Description:MsgRecordMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysMsgRecordMapper extends GenericMapper<SysMsgRecord>
{
}
