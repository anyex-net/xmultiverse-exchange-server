/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.user.consts.UserConsts;
import com.anyex.apps.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.user.entity.User;
import com.anyex.apps.user.mapper.UserMapper;

/**
 * 用户信息 服务实现类
 * <p>File：UserServiceImpl.java </p>
 * <p>Title: UserServiceImpl </p>
 * <p>Description:UserServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService
{
    protected UserMapper userMapper;

    @Autowired(required = false)
    public UserServiceImpl(UserMapper userMapper)
    {
        super(userMapper);
        this.userMapper = userMapper;
    }

    @Override
    public User findByUnid(Long unid) throws BusinessException {
        User user = userMapper.findByUnid(unid);
        if (null != user && !user.verifySignature())
        { // 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        return user;
    }

    @Override
    public User findByUserName(String userName) throws BusinessException {
        if (StringUtils.isBlank(userName)) return null;
        return userMapper.findByUserName(userName);
    }

    @Override
    public User findByUserNameAndNormal(String userName) throws BusinessException {
        return findByUserNameAndNormal(userName, null);
    }

    @Override
    public User findByUserNameAndNormal(String userName, String country) throws BusinessException {
        if (StringUtils.isBlank(userName)) return null;
        return userMapper.findByUserNameAndState(userName, country, UserConsts.USER_STATUS_NORMAL);
    }

    @Override
    public User findByMobileNo(String mobileNo) throws BusinessException {
        return userMapper.findByMobileNo(mobileNo);
    }

    @Override
    public User findByEmailAndMobileNo(String email, String mobileNo) throws BusinessException {
        if (StringUtils.isBlank(email) || StringUtils.isBlank(mobileNo)) return null;
        User user = userMapper.findByEmailAndMobileNo(email, mobileNo);
        if (null != user && !user.verifySignature())
        { // 校验数据
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        return user;
    }

    @Override
    public User selectByPrimaryKeyNoCheck(Long id) throws BusinessException {
        return super.selectByPrimaryKey(id);
    }
}
