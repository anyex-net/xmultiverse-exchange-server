/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.common.entity.SysNotice;
import com.anyex.apps.common.mapper.SysNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 平台公告表 服务实现类
 * <p>File：NoticeServiceImpl.java </p>
 * <p>Title: NoticeServiceImpl </p>
 * <p>Description:NoticeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2021</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysNoticeServiceImpl extends GenericServiceImpl<SysNotice> implements SysNoticeService
{
    protected SysNoticeMapper noticeMapper;

    @Autowired(required = false)
    public SysNoticeServiceImpl(SysNoticeMapper noticeMapper)
    {
        super(noticeMapper);
        this.noticeMapper = noticeMapper;
    }
}
