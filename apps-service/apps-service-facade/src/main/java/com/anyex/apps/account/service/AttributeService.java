/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.account.entity.Attribute;

import java.util.List;

/**
 * 用户属性表 服务接口
 * <p>File：AttributeService.java </p>
 * <p>Title: AttributeService </p>
 * <p>Description:AttributeService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface AttributeService extends GenericService<Attribute>
{
    Attribute findByUserId(String userId);

    List<Attribute> findByUserIds(List<String> userIds);

}
