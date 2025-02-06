/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.account.entity.Attribute;
import com.anyex.apps.account.mapper.AttributeMapper;

import java.util.List;

/**
 * 用户属性表 服务实现类
 * <p>File：AttributeServiceImpl.java </p>
 * <p>Title: AttributeServiceImpl </p>
 * <p>Description:AttributeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AttributeServiceImpl extends GenericServiceImpl<Attribute> implements AttributeService
{
    protected AttributeMapper attributeMapper;

    @Autowired(required = false)
    public AttributeServiceImpl(AttributeMapper attributeMapper)
    {
        super(attributeMapper);
        this.attributeMapper = attributeMapper;
    }

    @Override
    public Attribute findByUserId(String userId) {
        return attributeMapper.findByUserId(userId);
    }

    @Override
    public List<Attribute> findByUserIds(List<String> userIds) {
        return attributeMapper.findByUserIds(userIds);
    }
}
