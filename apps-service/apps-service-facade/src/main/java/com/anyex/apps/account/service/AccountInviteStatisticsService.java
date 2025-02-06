package com.anyex.apps.account.service;
import com.anyex.apps.account.entity.Account;
import com.anyex.apps.bean.GenericService;
import com.anyex.apps.account.entity.AccountInviteStatistics;

/**
 * 账户邀请统计 服务接口
 * <p>File：AccountInviteStatisticsService.java </p>
 * <p>Title: AccountInviteStatisticsService </p>
 * <p>Description:AccountInviteStatisticsService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountInviteStatisticsService extends GenericService<AccountInviteStatistics> {

    void award(Account account);
}
