
package com.anyex.apps.account.service;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.entity.AccountInviteRewardsDetail;
import com.anyex.apps.utils.SerialnoUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.account.entity.AccountInviteStatistics;
import com.anyex.apps.account.mapper.AccountInviteStatisticsMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 账户邀请统计 服务实现类
 * <p>File：AccountInviteStatisticsServiceImpl.java </p>
 * <p>Title: AccountInviteStatisticsServiceImpl </p>
 * <p>Description:AccountInviteStatisticsServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccountInviteStatisticsServiceImpl extends GenericServiceImpl<AccountInviteStatistics> implements AccountInviteStatisticsService
{
    protected AccountInviteStatisticsMapper accountinvitestatisticsMapper;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountInviteStatisticsService accountInviteStatisticsService;

    @Autowired
    private AccountInviteRewardsDetailService accountInviteRewardsDetailService;

    @Autowired(required = false)
    public AccountInviteStatisticsServiceImpl(AccountInviteStatisticsMapper accountinvitestatisticsMapper)
    {
        super(accountinvitestatisticsMapper);
        this.accountinvitestatisticsMapper = accountinvitestatisticsMapper;
    }

    int getLevel(int cnt)
    {
        return cnt<3?1:cnt<5?3:cnt<10?4:cnt<20?5:6;
    }
    int getmoney(int cnt)
    {
        return cnt==1?10:cnt<3?10:cnt<5?40:cnt<10?60:cnt<20?120:cnt==20?250:(cnt-20)*15+250;
    }


    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void award(Account account) {

        account.getReferralCode();// 邀请人的码
        if(account.getReferralCode() != null)
        {
            Account accountReferral = accountService.findByUnid(Long.valueOf(account.getReferralCode()));
            if (null != accountReferral) {
                // 规则 累计1人 10  3人40 5人 60 10人120 20人250 >20人每+1人+15
                AccountInviteStatistics statistics = new AccountInviteStatistics();
                statistics.setAccountId(accountReferral.getId());
                statistics = accountInviteStatisticsService.selectOne(statistics);
                if (null == statistics) {
                    statistics = new AccountInviteStatistics();
                    statistics.setAccountId(accountReferral.getId());
                    statistics.setInviteCnt(1);
                    statistics.setRewardsLevel(getLevel(statistics.getInviteCnt())) ;
                    // 累计奖励金额
                    statistics.setInviteAwardedTotal(BigDecimal.valueOf(getmoney(statistics.getInviteCnt())));

                    statistics.setCreateTime(System.currentTimeMillis());
                    statistics.setUpdateTime(System.currentTimeMillis());
                    statistics.setId(SerialnoUtils.buildPrimaryKey());
                    accountInviteStatisticsService.insert(statistics);
                }
                else
                {
                    statistics.setInviteCnt(statistics.getInviteCnt() + 1);
                    statistics.setRewardsLevel(getLevel(statistics.getInviteCnt())) ;
                    // 累计奖励金额
                    statistics.setInviteAwardedTotal(BigDecimal.valueOf(getmoney(statistics.getInviteCnt())));
                    accountInviteStatisticsService.updateByPrimaryKey(statistics);
                }

                AccountInviteRewardsDetail detail = new AccountInviteRewardsDetail();
                detail.setAccountId(statistics.getAccountId());
                detail.setInviteActId(account.getId());
                detail.setInviteActNick(account.getAccountName());
                detail.setInviteActHead(account.getHeadUrl());
                detail.setInviteActSeq(statistics.getInviteCnt());
                detail.setInviteCnt(statistics.getInviteCnt());
                detail.setRewardsLevel(statistics.getRewardsLevel());
                detail.setStatus(0);
                if(detail.getInviteActSeq()>20)
                {
                    detail.setInviteAward(BigDecimal.valueOf(15));
                }
                else
                {
                    switch (detail.getInviteActSeq())
                    {
                        case 1:
                            detail.setInviteAward(BigDecimal.valueOf(10));
                            break;
                        case 3:
                            detail.setInviteAward(BigDecimal.valueOf(30));
                            break;
                        case 5:
                            detail.setInviteAward(BigDecimal.valueOf(20));
                            break;
                        case 10:
                            detail.setInviteAward(BigDecimal.valueOf(60));
                            break;
                        case 20:
                            detail.setInviteAward(BigDecimal.valueOf(130));
                            break;
                       default:
                           detail.setInviteAward(BigDecimal.ZERO);
                           detail.setStatus(-1);
                        break;
                    }
                }
                if(detail.getStatus() != -1)
                {
                    detail.setRemark(statistics.getInviteAwardedTotal().intValue()+"");
                }
                detail.setCreateTime(System.currentTimeMillis());
                detail.setUpdateTime(System.currentTimeMillis());
                detail.setId(SerialnoUtils.buildPrimaryKey());
                accountInviteRewardsDetailService.insert(detail);
            }
        }

    }
}
