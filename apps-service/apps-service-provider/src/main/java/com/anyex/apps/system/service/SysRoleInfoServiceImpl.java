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
import com.anyex.apps.system.entity.SysRoleData;
import com.anyex.apps.system.entity.SysRoleInfo;
import com.anyex.apps.system.entity.SysRoleRes;
import com.anyex.apps.system.mapper.SysRoleDataMapper;
import com.anyex.apps.system.mapper.SysRoleInfoMapper;
import com.anyex.apps.system.mapper.SysRoleResMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色信息表 服务实现类
 * <p>File：RoleInfo.java </p>
 * <p>Title: RoleInfo </p>
 * <p>Description:RoleInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysRoleInfoServiceImpl extends GenericServiceImpl<SysRoleInfo> implements SysRoleInfoService
{
    private SysRoleInfoMapper roleInfoMapper;
    
    @Autowired
    private SysRoleResMapper roleResMapper;

    @Autowired
    private SysRoleDataMapper roleDataMapper;
    
    @Autowired
    public SysRoleInfoServiceImpl(SysRoleInfoMapper roleInfoMapper)
    {
        super(roleInfoMapper);
        this.roleInfoMapper = roleInfoMapper;
    }
    
    @Override
    public List<SysRoleInfo> findByUserId(Long userId)
    {
        return roleInfoMapper.findByUserId(userId);
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveGrant(Long id, String resourceIds) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException("角色编码不可为空"); }
        if (StringUtils.isBlank(resourceIds))
        { throw new BusinessException("角色授权资源不可为空"); }
        roleResMapper.removeByRoleId(id);// 先删除原有的授权数据
        String[] resIds = resourceIds.split(",");
        List<SysRoleRes> resList = Lists.newArrayList();
        SysRoleRes res;
        for (String resId : resIds)
        {
            res = new SysRoleRes(id, Long.parseLong(resId));
            res.setId(SerialnoUtils.buildPrimaryKey());
            resList.add(res);
        }
        roleResMapper.insertBatch(resList);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveRoleData(Long id, String orgIds) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException("角色编码不可为空"); }
        if (StringUtils.isBlank(orgIds))
        { throw new BusinessException("机构编码授权资源不可为空"); }
        roleDataMapper.removeByRoleId(id);// 先删除原有的机构编码授权数据
        String[] orgIdArray = orgIds.split(",");
        List<SysRoleData> roleDataList = Lists.newArrayList();
        SysRoleData roleData;
        for (String orgId : orgIdArray)
        {
            roleData = new SysRoleData(id, Long.parseLong(orgId));
            roleData.setId(SerialnoUtils.buildPrimaryKey());
            roleDataList.add(roleData);
        }
        roleDataMapper.insertBatch(roleDataList);
    }

    @Override
    public SysRoleInfo findByRoleId(Long id) {
        SysRoleInfo byRoleId = roleInfoMapper.findByRoleId(id);
        return byRoleId;
    }

}
