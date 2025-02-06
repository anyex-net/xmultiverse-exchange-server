/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.mapper;

import com.anyex.apps.account.model.AccountInvitedModel;
import com.anyex.apps.account.model.AccountInvitedStatisticsModel;
import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.social.model.AccountInfoModel;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.account.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 账户表 持久层接口
 * <p>File：AccountMapper.java </p>
 * <p>Title: AccountMapper </p>
 * <p>Description:AccountMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountMapper extends GenericMapper<Account>
{
    /**
     * 取最大的UNID
     * @return
     */
    Long getMaxUNID();

    /**
     * 根据账户UNID查账户对象
     * @param unid
     * @return
     */
    Account findByUnid(Long unid);

    Account findByUserId(@Param("userId") String userId);

    Account findByReferralCode(@Param("referralCode") String referralCode);

    Account findByInvitationCode(@Param("invitationCode") String invitationCode) throws BusinessException;

    /**
     * 根据帐户名查帐户对象
     *
     * @param accountName
     * @return
     */
    Account findByAccountName(String accountName);

    /**
     * 根据手机号查帐户对象
     *
     * @param mobile
     * @return
     */
    Account findByMobile(String mobile);

    /**
     * 根据邮箱查帐户对象
     *
     * @param email
     * @return
     */
    Account findByEmail(String email);

    /**
     * 根据邮箱查帐户对象
     *
     * @param email
     * @return
     */
    Account findByEmail4Register(String email);

    AccountInvitedStatisticsModel getInvitedStatistics();

    List<AccountInvitedModel> getInvitedAccount(@Param("entity") AccountInvitedModel entity);

    List<AccountInfoModel> findSocialList(@Param("keywords") String keywords, @Param("viewerUserId") String viewerUserId);
}
