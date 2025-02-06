///*
// * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
// * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.anyex.fhsc.account.mapper;
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Mapper;
//
//import com.anyex.fhsc.account.entity.AccountDevice;
//import com.anyex.fhsc.bean.GenericMapper;
//
///**
// * 账户设备表 持久层接口
// * <p>File：AccountDeviceMapper.java </p>
// * <p>Title: AccountDeviceMapper </p>
// * <p>Description:AccountDeviceMapper </p>
// * <p>Copyright: Copyright (c) May 26, 2015</p>
// * <p>Company: AnyEx</p>
// * @author Playguy
// * @version 1.0
// */
//@Mapper
//public interface AccountDeviceMapper extends GenericMapper<AccountDevice>
//{
//    /**
//     * 根据账户id 查询用户信任设备
//     * @param accountId
//     * @return
//     */
//    List<String> accountDeviceNums(Long accountId);
//
//    /**
//     * 根据账户id 删除用户信任设备
//     */
//    void deleteDeviceNum(AccountDevice accountDevice);
//}
