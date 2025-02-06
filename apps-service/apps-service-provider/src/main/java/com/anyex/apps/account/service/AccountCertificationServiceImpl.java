///*
// * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
// * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.anyex.fhsc.account.service;
//
//import com.anyex.fhsc.account.mapper.AccountCertificationMapper;
//import com.anyex.fhsc.account.mapper.AccountMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.anyex.fhsc.account.entity.Account;
//import com.anyex.fhsc.account.entity.AccountCertification;
//import com.anyex.fhsc.bean.GenericServiceImpl;
//import com.anyex.fhsc.exception.BusinessException;
//
///**
// * AccountCertification 服务实现类
// * <p>File：AccountCertificationServiceImpl.java </p>
// * <p>Title: AccountCertificationServiceImpl </p>
// * <p>Description:AccountCertificationServiceImpl </p>
// * <p>Copyright: Copyright (c) May 26, 2015</p>
// * <p>Company: AnyEx</p>
// * @author Playguy
// * @version 1.0
// */
//@Service
//public class AccountCertificationServiceImpl extends GenericServiceImpl<AccountCertification> implements AccountCertificationService
//{
//    protected AccountCertificationMapper accountCertificationMapper;
//
//    @Autowired(required = false)
//    private AccountMapper accountMapper;
//
//    @Autowired(required = false)
//    public AccountCertificationServiceImpl(AccountCertificationMapper accountCertificationMapper)
//    {
//        super(accountCertificationMapper);
//        this.accountCertificationMapper = accountCertificationMapper;
//    }
//
//    @Override
//    public AccountCertification findByAccountId(Long id) throws BusinessException
//    {
//        if (null == id) return null;
//        return accountCertificationMapper.findByAccountId(id);
//    }
//
//    @Override
//    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public void approve(AccountCertification entity) throws BusinessException
//    {
//        AccountCertification certification = accountCertificationMapper.selectByPrimaryKey(entity.getId());
//        certification.setStatus(entity.getStatus());
//        certification.setRecommend(entity.isRecommend());
//        accountCertificationMapper.updateByPrimaryKey(certification);
//        Account account = accountMapper.selectByPrimaryKey(certification.getAccountId());
//        //
////        if (ApproveEnums.STATUS_APPROVE.getCode().equals(certification.getStatus()))
////        {// 实名认证通过后开通哔哔号
////            account.setBibiH(true);
////            accountMapper.updateByPrimaryKey(account);
////        } else {
////            // 实名认证通过后，发现有问题，再次接受审核或拒绝
////            account.setBibiH(false);
////            accountMapper.updateByPrimaryKey(account);
////        }
//    }
//
//    @Override
//    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public Boolean isBibiH(Long accountId) throws BusinessException {
//        AccountCertification certification = accountCertificationMapper.findByAccountId(accountId);
////        if(certification != null){
////            if(ApproveEnums.STATUS_APPROVE.getCode().equals(certification.getStatus())){
////                return true;
////            }
////        }
//        return false;
//    }
//}
