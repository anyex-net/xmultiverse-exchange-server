/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.exception.BusinessException;
import com.google.common.collect.Lists;
import com.anyex.apps.system.entity.SysOrganization;
import com.anyex.apps.system.mapper.SysOrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机构信息表 服务实现类
 * <p>File：Organization.java </p>
 * <p>Title: Organization </p>
 * <p>Description:Organization </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysOrganizationServiceImpl extends GenericServiceImpl<SysOrganization> implements SysOrganizationService {

    private SysOrganizationMapper organizationMapper;

    @Autowired
    public SysOrganizationServiceImpl(SysOrganizationMapper organizationMapper) {
        super(organizationMapper);
        this.organizationMapper = organizationMapper;
    }

    @Override
    public List<SysOrganization> treeData() throws BusinessException {
        List<SysOrganization> data = Lists.newArrayList();
        List<SysOrganization> entitys = organizationMapper.selectAll();
        for (SysOrganization parent : entitys)
        {
            if (null == parent.getParentId() || 0L == parent.getParentId())
            {
                data.add(parent);
            }
            for (SysOrganization child : entitys)
            {
                if (parent.getId().equals(child.getParentId()))
                {
                    if (parent.getChildren() == null)
                    {
                        parent.setChildren(Lists.newArrayList(child));
                    }
                    else
                    {
                        parent.getChildren().add(child);
                    }
                }
            }
        }
        //
        return data;
    }
}
