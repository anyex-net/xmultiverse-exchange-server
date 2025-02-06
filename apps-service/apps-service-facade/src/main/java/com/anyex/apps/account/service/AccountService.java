/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.service;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.model.AccountInvitedModel;
import com.anyex.apps.account.model.AccountInvitedStatisticsModel;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.model.AccountInfoModel;

/**
 * 账户表 服务接口
 * <p>File：AccountService.java </p>
 * <p>Title: AccountService </p>
 * <p>Description:AccountService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountService extends GenericService<Account>
{
    /**
     * 取最大的UNID
     *
     * @return
     */
    Long getMaxUNID() throws BusinessException;

    /**
     * 根据账户UNID查账户对象
     *
     * @param unid
     * @return
     */
    Account findByUnid(Long unid) throws BusinessException;

    Account findByReferralCode(String referralCode) throws BusinessException;

    Account findByInvitationCode(String invitationCode) throws BusinessException;

    /**
     * 根据账户名取帐户对象
     *
     * @param username
     * @return
     */
    Account findByAccountName(String username) throws BusinessException;

    /**
     * 根据手机号查帐户对象
     *
     * @param mobile
     * @return {@link Account}
     * @throws BusinessException
     */
    Account findByMobile(String mobile) throws BusinessException;

    /**
     * 根据邮箱查帐户对象
     *
     * @param email
     * @return
     */
    Account findByEmail(String email) throws BusinessException;

    Account findByUserId(String userId) throws BusinessException;

    /**
     * 根据邮箱查帐户对象
     *
     * @param email
     * @return
     */
    Account findByEmail4Register(String email) throws BusinessException;

    /**
     * 帐户注册
     * @param account
     * @param walletAsset
     * @throws BusinessException
     */
    void register(Account account, WalletAsset walletAsset) throws BusinessException;

    /**
     * 邀请统计
     * @return
     */
    AccountInvitedStatisticsModel getInvitedStatistics();

    /**
     * 邀请查询
     * @param pagin
     * @param model
     * @return
     * @throws BusinessException
     */
    PaginateResult<AccountInvitedModel> getInvitedAccount(Pagination pagin, AccountInvitedModel model) throws BusinessException;

    PaginateResult<AccountInfoModel> findSocialList(Pagination pagin, String keywords, String viewerUserId) throws BusinessException;
}
