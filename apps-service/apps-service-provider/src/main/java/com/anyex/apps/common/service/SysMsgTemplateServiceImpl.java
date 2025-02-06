/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.common.mapper.SysMsgTemplateMapper;
import com.anyex.apps.common.entity.SysMsgTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息模版 服务实现类
 * <p>File：MsgTemplate.java </p>
 * <p>Title: MsgTemplate </p>
 * <p>Description:MsgTemplate </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysMsgTemplateServiceImpl extends GenericServiceImpl<SysMsgTemplate> implements SysMsgTemplateService
{
    SysMsgTemplateMapper msgTemplateMapper;
    
    @Autowired
    public SysMsgTemplateServiceImpl(SysMsgTemplateMapper msgTemplateMapper)
    {
        super(msgTemplateMapper);
        this.msgTemplateMapper = msgTemplateMapper;
    }
}
