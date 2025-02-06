package com.anyex.apps.account.service;
import com.anyex.apps.bean.GenericService;
import com.anyex.apps.account.entity.AccountInviteRewardsDetail;

/**
 * 账户邀请奖励 服务接口
 * <p>File：AccountInviteRewardsDetailService.java </p>
 * <p>Title: AccountInviteRewardsDetailService </p>
 * <p>Description:AccountInviteRewardsDetailService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountInviteRewardsDetailService extends GenericService<AccountInviteRewardsDetail> {

    /**
     * 返佣操作
     * @param rewards 返佣记录
     */
    void doInviteRewardsAsset(AccountInviteRewardsDetail rewards);

}
