///*
// * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
// * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.anyex.fhsc.account.service;
//
//import java.util.List;
//
//import com.anyex.fhsc.account.mapper.AccountDeviceMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.anyex.fhsc.account.entity.AccountDevice;
//import com.anyex.fhsc.bean.GenericServiceImpl;
//
///**
// * 账户设备表 服务实现类
// * <p>File：AccountDeviceServiceImpl.java </p>
// * <p>Title: AccountDeviceServiceImpl </p>
// * <p>Description:AccountDeviceServiceImpl </p>
// * <p>Copyright: Copyright (c) May 26, 2015</p>
// * <p>Company: AnyEx</p>
// * @author Playguy
// * @version 1.0
// */
//@Service
//public class AccountDeviceServiceImpl extends GenericServiceImpl<AccountDevice> implements AccountDeviceService
//{
//    protected AccountDeviceMapper accountDeviceMapper;
//
//    @Autowired
//    public AccountDeviceServiceImpl(AccountDeviceMapper accountDeviceMapper)
//    {
//        super(accountDeviceMapper);
//        this.accountDeviceMapper = accountDeviceMapper;
//    }
//
//    @Override
//    public List<String> accountDeviceNums(Long accountId)
//    {
//        return accountDeviceMapper.accountDeviceNums(accountId);
//    }
//
//    @Override
//    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public void addAccountDevice(AccountDevice device)
//    {
//        AccountDevice accountDevice = new AccountDevice();
//        List<AccountDevice> accountDeviceList = accountDeviceMapper.findList(device);
//        if (accountDeviceList.size() != 0)
//        {
//            accountDevice = accountDeviceList.get(0);
//        }else {
//            accountDevice.setCreateDate(System.currentTimeMillis());
//            accountDevice.setLatestLoginDate(System.currentTimeMillis());
//        }
//        accountDevice.setAccountId(device.getAccountId());
//        accountDevice.setAppVersion(device.getAppVersion());
//        accountDevice.setDeviceName(device.getDeviceName());
//        accountDevice.setDeviceNum(device.getDeviceNum());
//        accountDevice.setDeviceType(device.getDeviceType());
//        accountDevice.setIpAddress(device.getIpAddress());
//        this.save(accountDevice);
//    }
//
//    @Override
//    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public void deleteDeviceNum(AccountDevice accountDevice)
//    {
//        accountDeviceMapper.deleteDeviceNum(accountDevice);
//    }
//}
