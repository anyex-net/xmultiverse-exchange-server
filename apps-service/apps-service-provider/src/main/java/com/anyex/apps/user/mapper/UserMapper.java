/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.user.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息 持久层接口
 * <p>File：UserMapper.java </p>
 * <p>Title: UserMapper </p>
 * <p>Description:UserMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UserMapper extends GenericMapper<User>
{
    /**
     * 根据UNID得到唯一的用户数据
     * @param unid
     * @return
     */
    User findByUnid(Long unid);

    /**
     * 根据用户名取用户数据
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 通过手机号获取用户数据
     * @param mobileNo
     * @return
     */
    User findByMobileNoAndCountry(String mobileNo, String country);

    /**
     * 根据邮件地址和手机号获取用户数据
     * @param email
     * @param mobileNo
     * @return
     */
    User findByEmailAndMobileNo(@Param("email") String email, @Param("mobileNo") String mobileNo);

    /**
     * 根据用户名与状态获取用户数据
     * @param userName
     * @param state
     * @return
     */
    User findByUserNameAndState(@Param("userName") String userName, @Param("state") Integer state, @Param("country") String country);

    /**
     * 取最大的UNID
     * @return
     */
    Long getMaxUNID();
}
