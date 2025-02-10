/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.base.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.base.entity.InstTradeFee;
import com.anyex.apps.base.mapper.InstTradeFeeMapper;

/**
 * 平台交易手续费费率 服务实现类
 * <p>File：InstTradeFeeServiceImpl.java </p>
 * <p>Title: InstTradeFeeServiceImpl </p>
 * <p>Description:InstTradeFeeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class InstTradeFeeServiceImpl extends GenericServiceImpl<InstTradeFee> implements InstTradeFeeService
{
    protected InstTradeFeeMapper instTradeFeeMapper;

    @Autowired(required = false)
    public InstTradeFeeServiceImpl(InstTradeFeeMapper instTradeFeeMapper)
    {
        super(instTradeFeeMapper);
        this.instTradeFeeMapper = instTradeFeeMapper;
    }
}
