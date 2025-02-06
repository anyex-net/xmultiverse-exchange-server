/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.common.entity.SysNotice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 平台公告表 持久层接口
 * <p>File：NoticeMapper.java </p>
 * <p>Title: NoticeMapper </p>
 * <p>Description:NoticeMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2021</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysNoticeMapper extends GenericMapper<SysNotice>
{

}
