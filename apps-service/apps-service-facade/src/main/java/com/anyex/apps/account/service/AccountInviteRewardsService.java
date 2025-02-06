/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.service;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.model.*;
import com.anyex.apps.bean.GenericService;
import com.anyex.apps.account.entity.AccountInviteRewards;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;

/**
 * 账户邀请奖励表 服务接口
 * <p>File：AccountInviteRewardsService.java </p>
 * <p>Title: AccountInviteRewardsService </p>
 * <p>Description:AccountInviteRewardsService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountInviteRewardsService extends GenericService<AccountInviteRewards>
{
    /**
     * 返佣操作
     * @param registerAccount 注册用户
     */
    void doInviteRewards(Account registerAccount);

    /**
     * 返佣操作
     * @param rewards 返佣记录
     */
    void doInviteRewardsAsset(AccountInviteRewards rewards);

    /**
     * 获取返佣统计-管理端统计
     * @return
     */
    AccountRewardsStatisticsModel getStatisticsModel();

    /**
     * 佣金统计列表
     * @param pagin
     * @param entity
     * @return
     * @throws BusinessException
     */
    PaginateResult<AccountRewardsItemModel> getStatisticsItems(Pagination pagin, Account entity) throws BusinessException;

    /**
     * 佣金统计-明细
     * @param accountId
     * @return
     */
    AccountRewardsItemDetailModel getStatisticsItemsDetail(Long accountId);

    /**
     * APP端 佣金统计 1 2 3 级统计查询
     * @param level 返佣级别
     * @param firstAccountId 一级返佣账户id
     * @param secondAccountId 二级返佣账户id
     * @param thirdAccountId 三级返佣账户id
     * @return
     */
    AccountInvitedStatisticsForAppModel getStatisticsItemsDetailForApp(Integer level,Long firstAccountId,Long secondAccountId,Long thirdAccountId);


    /**
     * 给一级邀请返佣的统计明细
     * @param rewardsAccountId 被奖励用户
     * @param firstAccountId 被奖励用户的一级邀请用户
     * @return
     */
    AccountInvitedRewardsDetailModel getFirstLevelDetailsForApp(Long rewardsAccountId, Long firstAccountId);
}
