/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.user.entity.User;

/**
 * 用户信息 服务接口
 * <p>File：UserService.java </p>
 * <p>Title: UserService </p>
 * <p>Description:UserService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface UserService extends GenericService<User>
{
    /**
     * 根据UNID得到唯一的用户数据
     * @param unid
     * @return {@link User}
     * @throws BusinessException
     */
    User findByUnid(Long unid) throws BusinessException;

    /**
     * 根据用户名取用户数据
     * @param userName
     * @return {@link User}
     */
    User findByUserName(String userName) throws BusinessException;

    /**
     * 根据用户名取正常状态的用户数据
     * @param userName
     * @return {@link User}
     */
    User findByUserNameAndNormal(String userName) throws BusinessException;

    /**
     * 根据用户名和区域代码取正常状态的数据
     * @param userName
     * @param country
     * @return {@link User}
     */
    User findByUserNameAndNormal(String userName, String country) throws BusinessException;

    /**
     * 通过手机号获取用户数据
     * @param mobileNo
     * @return
     */
    User findByMobileNoAndCountry(String mobileNo, String country) throws BusinessException;

    /**
     * 根据邮件地址和手机号获取用户数据
     * @param email
     * @param mobileNo
     * @return {@link User}
     * @throws BusinessException
     */
    User findByEmailAndMobileNo(String email, String mobileNo) throws BusinessException;

    /**
     * 根据用户ID查找用户数据(不进行数据验签)
     * @param id
     * @return
     * @throws BusinessException
     */
    User selectByPrimaryKeyNoCheck(Long id) throws BusinessException;
}
