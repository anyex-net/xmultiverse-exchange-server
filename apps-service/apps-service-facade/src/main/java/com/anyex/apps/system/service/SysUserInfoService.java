/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.system.entity.SysUserInfo;

/**
 * 用户信息表 服务接口
 * <p>File：UserInfoService.java </p>
 * <p>Title: UserInfoService </p>
 * <p>Description:UserInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SysUserInfoService extends GenericService<SysUserInfo>
{
    /**
     * 根据用户名找用户
     * @param userName
     * @return
     */
    SysUserInfo findByUserName(String userName);
    
    /**
     * 修改用户密码
     * @param userId
     * @param oldPwd
     * @param newPwd
     */
    void changePassword(Long userId, String oldPwd, String newPwd);

    /**
     * 重置用户密码
     * @param userId
     */
    void resetPassword(Long userId, String newPwd);

    /**
     * 保存用户数据
     * @param userId
     * @param orgIds
     * @throws BusinessException
     */
    void saveUserData(Long userId, String orgIds) throws BusinessException;

    /**
     * 根据浙政钉OpenId找用户
     *
     * @param ZZDOpenId 浙政钉OpenId
     * @return UserInfo
     */
    SysUserInfo findByZZDOpenId(String ZZDOpenId);

    /**
     * 根据账号ID冻结账号
     */
    boolean freezeLogin(Long userId);
}
