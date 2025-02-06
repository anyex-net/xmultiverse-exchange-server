/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.mapper;


import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.model.*;
import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.account.entity.AccountInviteRewards;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 账户邀请奖励表 持久层接口
 * <p>File：AccountInviteRewardsMapper.java </p>
 * <p>Title: AccountInviteRewardsMapper </p>
 * <p>Description:AccountInviteRewardsMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountInviteRewardsMapper extends GenericMapper<AccountInviteRewards>
{
    AccountRewardsStatisticsModel getStatisticsModel();
    List<AccountRewardsItemModel> getStatisticsItems(Account entity);
    AccountRewardsItemDetailModel getStatisticsItemsDetail(@Param("accountId") Long accountId);
    AccountInvitedStatisticsTotalForAppModel getRewardsForAppTotal(@Param("tag") String tag,@Param("rewardsAccountId") Long rewardsAccountId, @Param("levelAccountId") Long levelAccountId);
    List<AccountInvitedStatisticsItemForAppModel> getRewardsItemsForAppTotal(@Param("tag") String tag, @Param("rewardsAccountId") Long rewardsAccountId, @Param("levelAccountId") Long levelAccountId);

    /**
     * 查询二级邀请用户的返佣统计
     * @param rewardsAccountId 当前登录用户
     * @param firstLevelAccountId 当前登录用户的一级邀请用户id
     * @return
     */
    AccountInvitedRewardsEveryLevelDetailModel getSecondLevelStatisticsForApp(@Param("rewardsAccountId") Long rewardsAccountId, @Param("firstLevelAccountId") Long firstLevelAccountId);

    /**
     * 查询三级邀请用户的返佣统计
     * @param rewardsAccountId 当前登录用户
     * @param firstLevelAccountId 当前登录用户的一级邀请用户id
     * @return
     */
    AccountInvitedRewardsEveryLevelDetailModel getThirdLevelStatisticsForApp(@Param("rewardsAccountId") Long rewardsAccountId, @Param("firstLevelAccountId") Long firstLevelAccountId);
}
