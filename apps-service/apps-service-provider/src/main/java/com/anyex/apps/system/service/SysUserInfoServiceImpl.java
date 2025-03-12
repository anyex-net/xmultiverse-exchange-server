/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.annotation.SlaveDataSource;
import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.config.GlobalProperies;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.utils.EncryptUtils;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.anyex.apps.system.entity.SysUserData;
import com.anyex.apps.system.entity.SysUserInfo;
import com.anyex.apps.system.entity.SysUserRole;
import com.anyex.apps.system.mapper.SysRoleInfoMapper;
import com.anyex.apps.system.mapper.SysUserDataMapper;
import com.anyex.apps.system.mapper.SysUserInfoMapper;
import com.anyex.apps.system.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户信息表 服务实现类
 * <p>File：UserInfo.java </p>
 * <p>Title: UserInfo </p>
 * <p>Description:UserInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysUserInfoServiceImpl extends GenericServiceImpl<SysUserInfo> implements SysUserInfoService {
    private SysUserInfoMapper userInfoMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserDataMapper userDataMapper;

    @Autowired
    private SysRoleInfoMapper roleInfoMapper;

    @Autowired
    private GlobalProperies properies;

    @Autowired
    public SysUserInfoServiceImpl(SysUserInfoMapper userInfoMapper) {
        super(userInfoMapper);
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(SysUserInfo entity) throws BusinessException {
        int flag;
        this.beanValidator(entity);
        if (null == entity.getId()) {
            if (StringUtils.isBlank(entity.getPassWord())) {// 如果用户没有设置密码，初始化一个
                entity.setPassWord(properies.getPassWord());
            } else {
//                if (!PasswordUtil.checkPassword(entity.getPassWord()))
//                    throw new BusinessException(2002, "密码长度大于等于8且包含至少包含3种字符");
            }
            entity.setId(SerialnoUtils.buildPrimaryKey());
            entity.setPassWord(EncryptUtils.entryptPassword(entity.getPassWord()));
            flag = userInfoMapper.insert(entity);
            saveUserRole(entity);
        } else {
            if (StringUtils.isNotBlank(entity.getPassWord())) {// 修改密码
//                if (!PasswordUtil.checkPassword(entity.getPassWord()))
//                    throw new BusinessException(2002, "密码长度大于等于8且包含至少包含3种字符");
                entity.setPassWord(EncryptUtils.entryptPassword(entity.getPassWord()));
            }
            flag = userInfoMapper.updateByPrimaryKeySelective(entity);
            saveUserRole(entity);
        }
        return flag;
    }

    @Override
    @SlaveDataSource()
    public PaginateResult<SysUserInfo> search(Pagination pagin, SysUserInfo entity) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<SysUserInfo> pageInfo = PageInfo.of(findList(entity));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        List<SysUserInfo> result = pageInfo.getList();
        for (SysUserInfo info : result) {
            info.setRoleList(roleInfoMapper.findByUserId(info.getId()));
        }
        return new PaginateResult<>(pagin, result);
    }

    /**
     * 保存用户角色
     *
     * @param info
     */
    private void saveUserRole(SysUserInfo info) {
        userRoleMapper.removeByUser(info.getId());
        if (StringUtils.isNotBlank(info.getRoleIds())) {
            String[] roleIds = info.getRoleIds().split(",");
            List<SysUserRole> userRoleList = Lists.newArrayList();
            SysUserRole userRole;
            for (String roleId : roleIds) {
                userRole = new SysUserRole(Long.parseLong(roleId), info.getId());
                userRole.setId(SerialnoUtils.buildPrimaryKey());
                userRoleList.add(userRole);
            }
            userRoleMapper.insertBatch(userRoleList);
        }
    }

    @Override
    public SysUserInfo selectByPrimaryKey(Long userId) throws BusinessException {
        SysUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        userInfo.setRoleList(roleInfoMapper.findByUserId(userInfo.getId()));
        return userInfo;
    }

    @Override
    public SysUserInfo findByUserName(String userName) {
        return userInfoMapper.findByUserName(userName);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void changePassword(Long userId, String oldPwd, String newPwd) {
        SysUserInfo entity = userInfoMapper.selectByPrimaryKey(userId);
        if (null == entity) {
            throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST);
        }
        if (!EncryptUtils.validatePassword(String.valueOf(oldPwd), entity.getPassWord())) {// 验证帐户密码
            throw new BusinessException(CommonEnums.ERROR_LOGIN_PASSWORD);
        }
//        if (!PasswordUtil.checkPassword(newPwd))
//            throw new BusinessException(2002, "密码长度大于等于8且包含至少包含3种字符");
        entity.setPassWord(EncryptUtils.entryptPassword(newPwd));
        userInfoMapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void resetPassword(Long userId, String newPwd) {
        SysUserInfo entity = userInfoMapper.selectByPrimaryKey(userId);
        if (null == entity) {
            throw new BusinessException(CommonEnums.ERROR_USER_NOT_EXIST);
        }
//        if (!PasswordUtil.checkPassword(newPwd))
//            throw new BusinessException(2002, "密码长度大于等于8且包含至少包含3种字符");
        entity.setPassWord(EncryptUtils.entryptPassword(newPwd));
        userInfoMapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveUserData(Long userId, String orgIds) throws BusinessException {
        if (null == userId) {
            throw new BusinessException("用户编码ID不可为空");
        }
        if (StringUtils.isBlank(orgIds)) {
            throw new BusinessException("机构编码授权资源不可为空");
        }
        userDataMapper.removeByUserId(userId);// 先删除原有的机构编码授权数据
        String[] orgIdArray = orgIds.split(",");
        List<SysUserData> userDataList = Lists.newArrayList();
        SysUserData userData;
        for (String orgId : orgIdArray) {
            userData = new SysUserData(userId, Long.parseLong(orgId));
            userData.setId(SerialnoUtils.buildPrimaryKey());
            userDataList.add(userData);
        }
        userDataMapper.insertBatch(userDataList);
    }

    @Override
    public SysUserInfo findByZZDOpenId(String ZZDOpenId) {
        return userInfoMapper.findByZZDOpenId(ZZDOpenId);
    }

    @Override
    public boolean freezeLogin(Long userId) {
        SysUserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        userInfo.setActive(false);
        return userInfoMapper.updateByPrimaryKey(userInfo) > 0;
    }

}
