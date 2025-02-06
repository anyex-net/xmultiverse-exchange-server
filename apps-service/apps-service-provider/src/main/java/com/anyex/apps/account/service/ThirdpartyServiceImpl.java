///*
// * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
// * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.anyex.fhsc.account.service;
//
//import com.anyex.fhsc.account.mapper.ThirdpartyMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.anyex.fhsc.account.entity.Thirdparty;
//import com.anyex.fhsc.bean.GenericServiceImpl;
//import com.anyex.fhsc.exception.BusinessException;
//
///**
// * 第三方登录 服务实现类
// * <p>File：ThirdpartyServiceImpl.java </p>
// * <p>Title: ThirdpartyServiceImpl </p>
// * <p>Description:ThirdpartyServiceImpl </p>
// * <p>Copyright: Copyright (c) May 26, 2015</p>
// * <p>Company: AnyEx</p>
// * @author Playguy
// * @version 1.0
// */
//@Service
//public class ThirdpartyServiceImpl extends GenericServiceImpl<Thirdparty> implements ThirdpartyService
//{
//    protected ThirdpartyMapper thirdpartyMapper;
//
//    @Autowired(required = false)
//    public ThirdpartyServiceImpl(ThirdpartyMapper thirdpartyMapper)
//    {
//        super(thirdpartyMapper);
//        this.thirdpartyMapper = thirdpartyMapper;
//    }
//
//    @Override
//    public Thirdparty findByOpenId(String openId) throws BusinessException
//    {
//        return thirdpartyMapper.findByOpenId(openId);
//    }
//}
