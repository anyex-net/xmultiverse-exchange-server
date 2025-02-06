
package com.anyex.apps.account.service;

import com.anyex.apps.asset.AssetUtil;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetFlows;
import com.anyex.apps.asset.service.WalletAssetFlowsService;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.account.entity.AccountInviteRewardsDetail;
import com.anyex.apps.account.mapper.AccountInviteRewardsDetailMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * 账户邀请奖励 服务实现类
 * <p>File：AccountInviteRewardsDetailServiceImpl.java </p>
 * <p>Title: AccountInviteRewardsDetailServiceImpl </p>
 * <p>Description:AccountInviteRewardsDetailServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class AccountInviteRewardsDetailServiceImpl extends GenericServiceImpl<AccountInviteRewardsDetail> implements AccountInviteRewardsDetailService
{
    protected AccountInviteRewardsDetailMapper accountinviterewardsdetailMapper;

    @Autowired
    WalletAssetFlowsService walletAssetFlowsService;

    @Autowired
    WalletAssetService walletAssetService;

    @Autowired
    AccountInviteRewardsDetailService accountInviteRewardsDetailService;

    @Autowired(required = false)
    public AccountInviteRewardsDetailServiceImpl(AccountInviteRewardsDetailMapper accountinviterewardsdetailMapper)
    {
        super(accountinviterewardsdetailMapper);
        this.accountinviterewardsdetailMapper = accountinviterewardsdetailMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void doInviteRewardsAsset(AccountInviteRewardsDetail rewards) {
        if(rewards == null)
        {
            return;
        }
        rewards = accountinviterewardsdetailMapper.selectByPrimaryKey(rewards.getId());
        if( rewards.getStatus() != 0)
        {
            log.error("奖励发放，ID={},状态无效 {}",rewards.getId(),rewards.getStatus());
            return;
        }

        String trxNo = SerialnoUtils.getOrderNum();

        // 资产处理 新增资产
        WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(rewards.getAccountId(), GlobalConst.CURRENCY_PKR);
        if (null == asset) {
            log.error("账户{} PKR 资产不存在", rewards.getAccountId());
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }

        // 更新资产
        BigDecimal oldBalance = asset.getBalance();
        // 资产新增奖励金额
        asset.setBalance(asset.getBalance().add(rewards.getInviteAward()));
        asset.setUpdateTime(System.currentTimeMillis());
        log.info("邀请返佣查询：asset ={}", asset);
        walletAssetService.updateByPrimaryKey(asset);
        // 资金流水
        // 类型：充值 发生方向+ 业务类型：充值
        WalletAssetFlows flows = new WalletAssetFlows();
        flows.setAccountId(rewards.getAccountId());
        flows.setCurrency(GlobalConst.CURRENCY_PKR);
        flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
        flows.setBusinessType(GlobalConst.BUSINESS_TYPE_INVITE_REWARD);
        flows.setBeforeBalance(oldBalance);
        flows.setDirection("+");
        flows.setBalance(rewards.getInviteAward());
        flows.setFee(BigDecimal.ZERO);
        flows.setAfterBalance(asset.getBalance());
        flows.setOrgBusinessId(rewards.getId());
        flows.setOrgBusinessNo(trxNo);
        flows.setStatus(true);
        flows.setCreateTime(System.currentTimeMillis());
        flows.setUpdateTime(System.currentTimeMillis());
        flows.setRemark("Invite Rewards");
        log.info("邀请返佣查询：flows ={}", flows);
        walletAssetFlowsService.insert(flows);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());

        rewards.setStatus(1);
        rewards.setRemark(trxNo);
        log.info("邀请返佣查询：rewards ={}", rewards);
        accountInviteRewardsDetailService.updateByPrimaryKey(rewards);

    }
}
