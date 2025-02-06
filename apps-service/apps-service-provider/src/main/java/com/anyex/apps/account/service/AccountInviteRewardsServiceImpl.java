/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.service;

import com.alibaba.fastjson.JSON;
import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.model.*;
import com.anyex.apps.asset.AssetUtil;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetFlows;
import com.anyex.apps.asset.service.WalletAssetFlowsService;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.account.entity.AccountInviteRewards;
import com.anyex.apps.account.mapper.AccountInviteRewardsMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * 账户邀请奖励表 服务实现类
 * <p>File：AccountInviteRewardsServiceImpl.java </p>
 * <p>Title: AccountInviteRewardsServiceImpl </p>
 * <p>Description:AccountInviteRewardsServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class AccountInviteRewardsServiceImpl extends GenericServiceImpl<AccountInviteRewards> implements AccountInviteRewardsService
{
    protected AccountInviteRewardsMapper accountInviteRewardsMapper;

    @Autowired
    AccountService accountService;

    @Autowired
    WalletAssetFlowsService walletAssetFlowsService;

    @Autowired
    WalletAssetService walletAssetService;

    @Autowired
    AccountInviteRewardsService accountInviteRewardsService;

    @Autowired(required = false)
    public AccountInviteRewardsServiceImpl(AccountInviteRewardsMapper accountInviteRewardsMapper)
    {
        super(accountInviteRewardsMapper);
        this.accountInviteRewardsMapper = accountInviteRewardsMapper;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void doInviteRewards(Account registerAccount) {
        if(null == registerAccount || StringUtils.isBlank(registerAccount.getReferralCode()))
        {
            log.info("新注冊用戶{},邀请码为空，不进行返佣。", (registerAccount==null?"未传入":registerAccount.getEmail()));
            return ;
        }
        // 获取有效邀请用户
        AccountInviteRewardsAccountsModel model = getEffectiveInviteAccounts(registerAccount);
        log.info("邀请返佣，获得有效的返佣用户如下：{}", JSON.toJSONString(model));
        StringBuilder tag = new StringBuilder(registerAccount.getId().toString());
        // 一级邀请无效 直接结束 日志已在查找时打印
        if(model.getEffectiveFirstInviteAccount() == null)
        {
            return;
        }
        else {
            // 往前面追加ID和逗号
            tag.insert(0,model.getEffectiveFirstInviteAccount().getId().toString()+",");
        }
        if(model.getEffectiveSecondInviteAccount() != null)
        {
            // 往前面追加ID和逗号
            tag.insert(0,model.getEffectiveSecondInviteAccount().getId().toString()+",");
        }
        if(model.getEffectiveThirdInviteAccount() != null)
        {
            // 往前面追加ID和逗号
            tag.insert(0,model.getEffectiveThirdInviteAccount().getId().toString()+",");
        }
        log.info("邀请返佣，TAG组装如下：{}", tag.toString());

        // 1. 给一级邀请用户添加返佣记录
        Account firstAct = model.getEffectiveFirstInviteAccount();
        AccountInviteRewards accountInviteRewards = toEntity(registerAccount,firstAct,firstAct,registerAccount,tag.toString(),1,"0.7","14");
        log.info("新注冊用戶{} 邀请码{} 设备 {},一级返佣用户{} UID {} 设备 {}，对象：{}。",
                registerAccount.getEmail(),
                registerAccount.getReferralCode(),
                registerAccount.getDeviceId(),
                firstAct.getUnid(),
                firstAct.getEmail(),
                firstAct.getDeviceId(),
                JSON.toJSONString(accountInviteRewards)
        );
        accountInviteRewardsService.insert(accountInviteRewards);

        // 2. 给二级邀请用户添加返佣记录
        Account secondAct = model.getEffectiveSecondInviteAccount();
        if(secondAct != null)
        {
            accountInviteRewards = toEntity(registerAccount,firstAct,secondAct,firstAct,tag.toString(),2,"0.2","4");
            log.info("新注冊用戶{} 邀请码{} 设备 {},二级返佣用户{} UID {} 设备 {}，对象：{}。",
                    registerAccount.getEmail(),
                    registerAccount.getInvitationCode(),
                    registerAccount.getDeviceId(),
                    secondAct.getUnid(),
                    secondAct.getEmail(),
                    secondAct.getDeviceId(),
                    JSON.toJSONString(accountInviteRewards)
            );
            accountInviteRewardsService.insert(accountInviteRewards);
        }

        // 3. 给三级邀请用户添加返佣记录
        Account thirdAct = model.getEffectiveThirdInviteAccount();
        if(thirdAct != null)
        {
            accountInviteRewards = toEntity(registerAccount,firstAct,thirdAct,secondAct,tag.toString(),3,"0.1","2");
            log.info("新注冊用戶{} 邀请码{} 设备 {},三级返佣用户{} UID {} 设备 {}，对象：{}。",
                    registerAccount.getEmail(),
                    registerAccount.getInvitationCode(),
                    registerAccount.getDeviceId(),
                    thirdAct.getUnid(),
                    thirdAct.getEmail(),
                    thirdAct.getDeviceId(),
                    JSON.toJSONString(accountInviteRewards)
            );
            accountInviteRewardsService.insert(accountInviteRewards);
        }

    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void doInviteRewardsAsset(AccountInviteRewards rewards) {
        if(rewards == null)
        {
            return;
        }
        rewards = accountInviteRewardsService.selectByPrimaryKey(rewards.getId());
        if( rewards.getRewardsStatus() != 0)
        {
            log.error("奖励发放，ID={},状态无效 {}",rewards.getId(),rewards.getRewardsStatus());
            return;
        }

        String trxNo = SerialnoUtils.getOrderNum();

        // 资产处理 新增资产
        WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(rewards.getRewardsAccountId(), GlobalConst.CURRENCY_PKR);
        if (null == asset) {
            log.error("账户{} PKR 资产不存在", rewards.getRewardsAccountId());
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }

        // 更新资产
        BigDecimal oldBalance = asset.getBalance();
        // 资产新增奖励金额
        asset.setBalance(asset.getBalance().add(rewards.getRewardsBalance()));
        asset.setUpdateTime(System.currentTimeMillis());
        log.info("邀请返佣查询：asset ={}", asset);
        walletAssetService.updateByPrimaryKey(asset);
        // 资金流水
        // 类型：充值 发生方向+ 业务类型：充值
        WalletAssetFlows flows = new WalletAssetFlows();
        flows.setAccountId(rewards.getRewardsAccountId());
        flows.setCurrency(GlobalConst.CURRENCY_PKR);
        flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
        flows.setBusinessType(GlobalConst.BUSINESS_TYPE_INVITE_REWARD);
        flows.setBeforeBalance(oldBalance);
        flows.setDirection("+");
        flows.setBalance(rewards.getRewardsBalance());
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

        rewards.setRewardsStatus(1);
        rewards.setRemark(trxNo);
        log.info("邀请返佣查询：rewards ={}", rewards);
        accountInviteRewardsService.updateByPrimaryKey(rewards);

    }

    @Override
    public AccountRewardsStatisticsModel getStatisticsModel() {
        return accountInviteRewardsMapper.getStatisticsModel();
    }

    @Override
    public PaginateResult<AccountRewardsItemModel> getStatisticsItems(Pagination pagin, Account entity) throws BusinessException
    {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<AccountRewardsItemModel> pageInfo = PageInfo.of(accountInviteRewardsMapper.getStatisticsItems(entity));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    public AccountRewardsItemDetailModel getStatisticsItemsDetail(Long accountId) {
        return accountInviteRewardsMapper.getStatisticsItemsDetail(accountId);
    }

    @Override
    public AccountInvitedStatisticsForAppModel getStatisticsItemsDetailForApp(Integer level, Long firstAccountId, Long secondAccountId, Long thirdAccountId) {
        AccountInvitedStatisticsForAppModel model = new AccountInvitedStatisticsForAppModel();
        StringBuilder tag = new StringBuilder(firstAccountId.toString());
        if(level == 1)
        {
            model.setTotalModel(accountInviteRewardsMapper.getRewardsForAppTotal(tag.toString(),firstAccountId,firstAccountId));
            model.setItems(accountInviteRewardsMapper.getRewardsItemsForAppTotal(tag.toString(),firstAccountId,firstAccountId));
        }
        else if(level == 2)
        {
            tag.append(",").append(secondAccountId.toString());
            model.setTotalModel(accountInviteRewardsMapper.getRewardsForAppTotal(tag.toString(),firstAccountId,secondAccountId));
            model.setItems(accountInviteRewardsMapper.getRewardsItemsForAppTotal(tag.toString(),firstAccountId,secondAccountId));
        }
        else if(level == 3)
        {
            tag.append(",").append(secondAccountId.toString());
            tag.append(",").append(thirdAccountId.toString());
            model.setTotalModel(accountInviteRewardsMapper.getRewardsForAppTotal(tag.toString(),firstAccountId,thirdAccountId));
            model.setItems(accountInviteRewardsMapper.getRewardsItemsForAppTotal(tag.toString(),firstAccountId,thirdAccountId));
        }

        return model;
    }

    @Override
    public AccountInvitedRewardsDetailModel getFirstLevelDetailsForApp(Long rewardsAccountId, Long firstAccountId) {
        AccountInvitedRewardsEveryLevelDetailModel secondModel = accountInviteRewardsMapper.getSecondLevelStatisticsForApp(rewardsAccountId,firstAccountId);
        AccountInvitedRewardsEveryLevelDetailModel thirdModel = accountInviteRewardsMapper.getThirdLevelStatisticsForApp(rewardsAccountId,firstAccountId);
        AccountInvitedRewardsDetailModel model = new AccountInvitedRewardsDetailModel();
        model.setTotalDirectSum(secondModel.getTotalSum());
        model.setTotalDirectPendingSum(secondModel.getTodaySum());
        model.setTotalDirectCnt(secondModel.getTotalCnt());
        model.setTotalIndirectSum(thirdModel.getTotalSum());
        model.setTotalIndirectPendingSum(thirdModel.getTodaySum());
        model.setTotalIndirectCnt(thirdModel.getTotalCnt());
        model.setTodayDirectSum(secondModel.getTodaySum());
        model.setTodayDirectCnt(secondModel.getTodayCnt());
        model.setTodayIndirectSum(thirdModel.getTodaySum());
        model.setTodayIndirectCnt(thirdModel.getTodayCnt());
        return model;
    }

    protected AccountInviteRewards toEntity(Account registerAccount,Account inviteAccount,Account rewardAccount ,Account fromAccount,String tag,int level,String rate,String balance )
    {
        AccountInviteRewards accountInviteRewards = new AccountInviteRewards();
        accountInviteRewards.setId(SerialnoUtils.buildPrimaryKey());
        accountInviteRewards.setRegisterAccountId(registerAccount.getId());
        accountInviteRewards.setRegisterEmail(registerAccount.getEmail());
        accountInviteRewards.setInviteAccountId(inviteAccount.getId());
        accountInviteRewards.setInviteEmail(inviteAccount.getEmail());
        accountInviteRewards.setRewardsAccountId(rewardAccount.getId());
        accountInviteRewards.setRewardsEmail(rewardAccount.getEmail());
        accountInviteRewards.setRewardsSubAccountId(fromAccount.getId());
        accountInviteRewards.setRewardsSubEmail(fromAccount.getEmail());
        accountInviteRewards.setRewardsRate(rate);
        accountInviteRewards.setRewardsBalance(new BigDecimal(balance));
        accountInviteRewards.setRewardsLevel(level);
        accountInviteRewards.setRewardsTag(tag.toString());
        accountInviteRewards.setRewardsStatus(0);
        accountInviteRewards.setCreateTime(System.currentTimeMillis());
        accountInviteRewards.setUpdateTime(System.currentTimeMillis());
        return accountInviteRewards;
    }

    protected AccountInviteRewardsAccountsModel getEffectiveInviteAccounts(Account registerAccount)
    {
        AccountInviteRewardsAccountsModel model = new AccountInviteRewardsAccountsModel();
        model.setRegisterAccount(registerAccount);

        Account search = new Account();
        search.setDeviceId(registerAccount.getDeviceId());
        List<Account> accountsOfDevices = accountService.findList(search);
        if(accountsOfDevices.size()>1)
        {
            log.info("新注冊用戶{} 推荐码{} 设备 {}，存在与已存在用户设备编号一致情况，所有人不返佣。",
                    registerAccount.getEmail(),
                    registerAccount.getReferralCode(),
                    registerAccount.getDeviceId()
            );
            model.setEffectiveFirstInviteAccount(null);
            model.setEffectiveSecondInviteAccount(null);
            model.setEffectiveThirdInviteAccount(null);
            return model;
        }

        Account firstAct = accountService.findByInvitationCode(registerAccount.getReferralCode());
        if(null == firstAct || StringUtils.equalsIgnoreCase(registerAccount.getDeviceId(),firstAct.getDeviceId()))
        {
            log.info("新注冊用戶{} 推荐码{} 设备 {},一级返佣用户{} 邀请码{} 推荐码 {} 设备 {}，{} 不进行返佣，后续用户不再返佣。",
                    registerAccount.getEmail(),
                    registerAccount.getReferralCode(),
                    registerAccount.getDeviceId(),
                    (null == firstAct?"不存在":firstAct.getEmail()),
                    (null == firstAct?"不存在":firstAct.getInvitationCode()),
                    (null == firstAct?"不存在":firstAct.getReferralCode()),
                    (null == firstAct?"不存在":firstAct.getDeviceId()),
                    (null == firstAct?"返佣账户不存在":"新注册用户与一级返佣用户设备相同")
            );
            if(null != firstAct &&  StringUtils.equalsIgnoreCase(registerAccount.getDeviceId(),firstAct.getDeviceId())) {
                model.setEffectiveFirstInviteAccount(null);
                model.setEffectiveSecondInviteAccount(null);
                model.setEffectiveThirdInviteAccount(null);
                log.error("新注册用户与一级返佣用户设备相同,取消这条邀请链路上所有的返佣。");
            }
            return  model;
        }

        if(StringUtils.isNotEmpty(firstAct.getDeviceId()))
        {
            search.setDeviceId(firstAct.getDeviceId());
            accountsOfDevices = accountService.findList(search);
            if(accountsOfDevices.size()>1)
            {
                log.info("一级邀请用戶{} 推荐码{} 设备 {}，存在与已存在用户设备编号一致情况，所有人不返佣。",
                        firstAct.getEmail(),
                        firstAct.getReferralCode(),
                        firstAct.getDeviceId()
                );
                model.setEffectiveFirstInviteAccount(null);
                model.setEffectiveSecondInviteAccount(null);
                model.setEffectiveThirdInviteAccount(null);
                return model;
            }
        }

        log.info("新注冊用戶{} 推荐{} 设备 {},一级返佣用户{} 邀请码{} 推荐码 {} 设备 {}，返佣金额：{}。",
                registerAccount.getEmail(),
                registerAccount.getReferralCode(),
                registerAccount.getDeviceId(),
                firstAct.getEmail(),
                firstAct.getInvitationCode(),
                firstAct.getReferralCode(),
                firstAct.getDeviceId(),
                "14"
        );
        model.setEffectiveFirstInviteAccount(firstAct);

        if(StringUtils.isEmpty(firstAct.getReferralCode()))
        {
            return model;
        }

        Account secondAct = accountService.findByInvitationCode(firstAct.getReferralCode());
        if(null == secondAct || StringUtils.equalsIgnoreCase(registerAccount.getDeviceId(),secondAct.getDeviceId()))
        {
            log.info("新注冊用戶{} 推荐码{} 设备 {},二级返佣用户{} 邀请码{} 推荐码 {} 设备 {}，{} 不进行返佣，后续用户不再返佣。",
                    registerAccount.getEmail(),
                    registerAccount.getReferralCode(),
                    registerAccount.getDeviceId(),
                    (null == secondAct?"不存在":secondAct.getEmail()),
                    (null == secondAct?"不存在":secondAct.getInvitationCode()),
                    (null == secondAct?"不存在":secondAct.getReferralCode()),
                    (null == secondAct?"不存在":secondAct.getDeviceId()),
                    (null == secondAct?"返佣账户不存在":"新注册用户与二级返佣用户设备相同")
            );
            if(null != secondAct && StringUtils.equalsIgnoreCase(registerAccount.getDeviceId(),secondAct.getDeviceId())) {
                model.setEffectiveFirstInviteAccount(null);
                model.setEffectiveSecondInviteAccount(null);
                model.setEffectiveThirdInviteAccount(null);
                log.error("新注册用户与二级返佣用户设备相同,取消这条邀请链路上所有的返佣。");
            }
            return  model;
        }
        if(StringUtils.isNotEmpty(secondAct.getDeviceId()))
        {
            search.setDeviceId(secondAct.getDeviceId());
            accountsOfDevices = accountService.findList(search);
            if(accountsOfDevices.size()>1)
            {
                log.info("二级邀请用戶{} 推荐码{} 设备 {}，存在与已存在用户设备编号一致情况，后面所有人不返佣。",
                        secondAct.getEmail(),
                        secondAct.getReferralCode(),
                        secondAct.getDeviceId()
                );
                model.setEffectiveSecondInviteAccount(null);
                model.setEffectiveThirdInviteAccount(null);
                return model;
            }
        }
        log.info("新注冊用戶{} 推荐{} 设备 {},二级返佣用户{} 邀请码{} 推荐码 {} 设备 {}，返佣金额：{}。",
                registerAccount.getEmail(),
                registerAccount.getReferralCode(),
                registerAccount.getDeviceId(),
                secondAct.getEmail(),
                secondAct.getInvitationCode(),
                secondAct.getReferralCode(),
                secondAct.getDeviceId(),
                "4"
        );
        model.setEffectiveSecondInviteAccount(secondAct);

        if(StringUtils.isEmpty(secondAct.getReferralCode()))
        {
            return model;
        }
        Account thirdAct = accountService.findByInvitationCode(secondAct.getReferralCode());
        if(null == thirdAct || StringUtils.equalsIgnoreCase(registerAccount.getDeviceId(),thirdAct.getDeviceId()))
        {
            log.info("新注冊用戶{} 推荐码{} 设备 {},三级返佣用户{} 邀请码{} 推荐码 {} 设备 {}，{} 不进行返佣，后续用户不再返佣。",
                    registerAccount.getEmail(),
                    registerAccount.getReferralCode(),
                    registerAccount.getDeviceId(),
                    (null == thirdAct?"不存在":thirdAct.getEmail()),
                    (null == thirdAct?"不存在":thirdAct.getInvitationCode()),
                    (null == thirdAct?"不存在":thirdAct.getReferralCode()),
                    (null == thirdAct?"不存在":thirdAct.getDeviceId()),
                    (null == thirdAct?"返佣账户不存在":"新注册用户与三级返佣用户设备相同")
            );
            if(null != thirdAct &&  StringUtils.equalsIgnoreCase(registerAccount.getDeviceId(),thirdAct.getDeviceId())) {
                model.setEffectiveFirstInviteAccount(null);
                model.setEffectiveSecondInviteAccount(null);
                model.setEffectiveThirdInviteAccount(null);
                log.error("新注册用户与三级返佣用户设备相同,取消这条邀请链路上所有的返佣。");
            }
            return  model;
        }
        if(StringUtils.isNotEmpty(thirdAct.getDeviceId()))
        {
            search.setDeviceId(thirdAct.getDeviceId());
            accountsOfDevices = accountService.findList(search);
            if(accountsOfDevices.size()>1)
            {
                log.info("三级邀请用戶{} 推荐码{} 设备 {}，存在与已存在用户设备编号一致情况，后面所有人不返佣。",
                        thirdAct.getEmail(),
                        thirdAct.getReferralCode(),
                        thirdAct.getDeviceId()
                );
                model.setEffectiveThirdInviteAccount(null);
                return model;
            }
        }
        log.info("新注冊用戶{} 推荐{} 设备 {},三级返佣用户{} 邀请码{} 推荐码 {} 设备 {}，返佣金额：{}。",
                registerAccount.getEmail(),
                registerAccount.getReferralCode(),
                registerAccount.getDeviceId(),
                thirdAct.getEmail(),
                thirdAct.getInvitationCode(),
                thirdAct.getReferralCode(),
                thirdAct.getDeviceId(),
                "2"
        );
        model.setEffectiveThirdInviteAccount(thirdAct);
        return  model;
    }
}
