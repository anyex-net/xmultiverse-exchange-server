///*
// * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
// * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.anyex.fhsc.account.service;
//
//import com.anyex.fhsc.account.entity.AccountDevice;
//import com.anyex.fhsc.bean.GenericService;
//
//import java.util.List;
//
///**
// * 账户设备表 服务接口
// * <p>File：AccountDeviceService.java </p>
// * <p>Title: AccountDeviceService </p>
// * <p>Description:AccountDeviceService </p>
// * <p>Copyright: Copyright (c) May 26, 2015</p>
// * <p>Company: AnyEx</p>
// * @author Playguy
// * @version 1.0
// */
//public interface AccountDeviceService extends GenericService<AccountDevice>
//{
//    /**
//     * 根据账户id 查询用户信任设备
//     * @param accountId
//     * @return
//     */
//    List<String> accountDeviceNums(Long accountId);
//
//    /**
//     * 记录登录设备
//     * @param device
//     */
//    void addAccountDevice(AccountDevice device);
//
//    /**
//     * 根据账户id 删除用户信任设备
//     */
//    void deleteDeviceNum(AccountDevice accountDevice);
//}
