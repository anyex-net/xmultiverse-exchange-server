/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import com.google.common.collect.Lists;
import com.anyex.apps.system.entity.SysFrontRoleRes;
import com.anyex.apps.system.mapper.SysFrontRoleResMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 前端角色权限信息表 服务实现类
 * <p>File：FrontRoleResServiceImpl.java </p>
 * <p>Title: FrontRoleResServiceImpl </p>
 * <p>Description:FrontRoleResServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class SysFrontRoleResServiceImpl extends GenericServiceImpl<SysFrontRoleRes> implements SysFrontRoleResService
{
    protected SysFrontRoleResMapper frontRoleResMapper;

    @Autowired(required = false)
    public SysFrontRoleResServiceImpl(SysFrontRoleResMapper frontRoleResMapper)
    {
        super(frontRoleResMapper);
        this.frontRoleResMapper = frontRoleResMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveGrant(Long roleId, String moduleIds) throws BusinessException {
        log.info("添加的权限："+moduleIds);
        if(roleId == null){
            throw new BusinessException("角色id不可为空");
        }
        if(StringUtils.isBlank(moduleIds)){
            throw new BusinessException("角色授权前端功能不可为空");
        }
        //先根据角色把数据清空，再赋权限
        frontRoleResMapper.removeByRoleId(roleId);
        String[] mduIds = moduleIds.split(",");
        List<SysFrontRoleRes> resList = Lists.newArrayList();
        SysFrontRoleRes fres;
        for (String mduId : mduIds) {
            fres=new SysFrontRoleRes();
            fres.setId(SerialnoUtils.buildPrimaryKey());
            fres.setRoleId(roleId);
            fres.setModuleId(Long.parseLong(mduId));
            resList.add(fres);
        }
        frontRoleResMapper.insertBatch(resList);
        log.info("角色:"+ roleId + " 添加授权： "+moduleIds );

    }
}
