///*
// * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
// * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.anyex.fhsc.account.service;
//
//import com.anyex.fhsc.account.entity.AccountCertification;
//import com.anyex.fhsc.bean.GenericService;
//import com.anyex.fhsc.exception.BusinessException;
//
///**
// * 帐户认证 服务接口
// * <p>File：AccountCertificationService.java </p>
// * <p>Title: AccountCertificationService </p>
// * <p>Description:AccountCertificationService </p>
// * <p>Copyright: Copyright (c) May 26, 2015</p>
// * <p>Company: AnyEx</p>
// * @author Playguy
// * @version 1.0
// */
//public interface AccountCertificationService extends GenericService<AccountCertification>
//{
//    /**
//     * 根据帐户ID取认证信息
//     * @param id
//     * @return {@link AccountCertification}
//     * @throws BusinessException
//     */
//    AccountCertification findByAccountId(Long id) throws BusinessException;
//
//    /**
//     * 认证审核
//     * @param entity
//     * @throws BusinessException
//     */
//    void approve(AccountCertification entity) throws BusinessException;
//
//    /**
//     * 判断用户是否开通哔哔号
//     * @param accountId
//     * @return
//     * @throws BusinessException
//     */
//    Boolean isBibiH(Long accountId) throws BusinessException;
//}
