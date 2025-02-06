/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.openim.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.openim.entity.NoticeAccount;
import com.anyex.apps.openim.mapper.NoticeAccountMapper;

/**
 * 通知账号 服务实现类
 * <p>File：NoticeAccountServiceImpl.java </p>
 * <p>Title: NoticeAccountServiceImpl </p>
 * <p>Description:NoticeAccountServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class NoticeAccountServiceImpl extends GenericServiceImpl<NoticeAccount> implements NoticeAccountService
{
    protected NoticeAccountMapper noticeAccountMapper;

    @Autowired(required = false)
    public NoticeAccountServiceImpl(NoticeAccountMapper noticeAccountMapper)
    {
        super(noticeAccountMapper);
        this.noticeAccountMapper = noticeAccountMapper;
    }
}
