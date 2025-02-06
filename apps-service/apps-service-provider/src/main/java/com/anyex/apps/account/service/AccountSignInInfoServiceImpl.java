
package com.anyex.apps.account.service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.anyex.apps.account.entity.AccountSignInDetail;
import com.anyex.apps.asset.AssetUtil;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetFlows;
import com.anyex.apps.asset.service.WalletAssetFlowsService;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.consts.DateConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.utils.DateUtils;
import com.anyex.apps.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.account.entity.AccountSignInInfo;
import com.anyex.apps.account.mapper.AccountSignInInfoMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * 账户签到信息 服务实现类
 * <p>File：AccountSignInInfoServiceImpl.java </p>
 * <p>Title: AccountSignInInfoServiceImpl </p>
 * <p>Description:AccountSignInInfoServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class AccountSignInInfoServiceImpl extends GenericServiceImpl<AccountSignInInfo> implements AccountSignInInfoService
{
    protected AccountSignInInfoMapper accountsignininfoMapper;

    @Autowired
    AccountSignInDetailService accountSignInDetailService;

    @Autowired
    AccountSignInInfoService accountSignInInfoService;

    @Autowired
    WalletAssetFlowsService walletAssetFlowsService;

    @Autowired
    WalletAssetService walletAssetService;

    @Autowired(required = false)
    public AccountSignInInfoServiceImpl(AccountSignInInfoMapper accountsignininfoMapper)
    {
        super(accountsignininfoMapper);
        this.accountsignininfoMapper = accountsignininfoMapper;
    }


    @Override
    public AccountSignInInfo findByAccountId(Long accountId) {
        return accountsignininfoMapper.findByAccountId(accountId);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void doSignIn(Long accountId, String dateStr) throws BusinessException {
        AccountSignInDetail detail  = accountSignInDetailService.findByAccountIdAndSignInDate(accountId, dateStr);
        if(null != detail){
            throw new BusinessException(CommonEnums.ERROR_HAS_BEAN_SIGNIN);
        }
        AccountSignInInfo info = accountSignInInfoService.findByAccountId(accountId);
        if(null == info){
            info = new AccountSignInInfo();
            info.setAccountId(accountId);
            info.setPointsLevel(1);

            try {
                info.setLastSigninDate(DateUtils.parseDate(dateStr, DateConst.DATE_FORMAT_YMD));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            info.setCurrentSigninTimes(1);
            info.setTotalPoints(1);
            info.setRemainingPoints(1);
            info.setTotalSigninTimes(1);
            info.setMaxsSigninTimes(1);
            info.setCreateTime(System.currentTimeMillis());
            info.setUpdateTime(System.currentTimeMillis());
            info.setId(SerialnoUtils.buildPrimaryKey());
            accountSignInInfoService.insert(info);
        }
        else
        {
            try {
                info.setLastSigninDate(DateUtils.parseDate(dateStr, DateConst.DATE_FORMAT_YMD));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            info.setCurrentSigninTimes(info.getCurrentSigninTimes()+1);
            info.setCurrentSigninTimes(info.getCurrentSigninTimes()>7?1:info.getCurrentSigninTimes());

            info.setTotalPoints(info.getTotalPoints()+(info.getCurrentSigninTimes()==7?14:info.getCurrentSigninTimes()));
            info.setRemainingPoints(info.getRemainingPoints()+(info.getCurrentSigninTimes()==7?14:info.getCurrentSigninTimes()));
            info.setTotalSigninTimes(info.getTotalSigninTimes()+1);
            info.setMaxsSigninTimes(info.getMaxsSigninTimes()>info.getCurrentSigninTimes()?info.getMaxsSigninTimes():info.getCurrentSigninTimes());
            accountSignInInfoService.updateByPrimaryKey(info);
        }

        detail = new AccountSignInDetail();
        detail.setAccountId(accountId);
        try {
            detail.setSigninDate(DateUtils.parseDate(dateStr, DateConst.DATE_FORMAT_YMD));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        detail.setIsContinuous(info.getCurrentSigninTimes()>1?1:0);
        detail.setCurrentSigninTimes(info.getCurrentSigninTimes());
        detail.setPointsAwarded(info.getCurrentSigninTimes()==7?14:info.getCurrentSigninTimes());
        detail.setSigninTime(new Date());
        detail.setStatus(0);
        detail.setCreateTime(System.currentTimeMillis());
        detail.setUpdateTime(System.currentTimeMillis());
        detail.setId(SerialnoUtils.buildPrimaryKey());
        accountSignInDetailService.insert(detail);
    }

    @Override
    public void doCheckCutOffSignIn() throws BusinessException {
        List<AccountSignInInfo> list = accountSignInInfoService.findList(new AccountSignInInfo());
        // 昨日日期
        String dateStr = (LocalDate.now().plusDays(-1)).format(DateTimeFormatter.ofPattern(DateConst.DATE_FORMAT_YMD));
        for(AccountSignInInfo info:list){
            AccountSignInDetail detail  = accountSignInDetailService.findByAccountIdAndSignInDate(info.getAccountId(), dateStr);
            if(null == detail){
                info.setCurrentSigninTimes(0);
                accountSignInInfoService.updateByPrimaryKey(info);
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void doAwardSignIn(AccountSignInDetail detail) throws BusinessException {
        if(detail == null)
        {
            return;
        }
        detail = accountSignInDetailService.selectByPrimaryKey(detail.getId());
        if( detail.getStatus() != 0)
        {
            log.error("签到奖励发放，ID={},状态无效 {}",detail.getId(),detail.getStatus());
            return;
        }

        AccountSignInInfo info = accountSignInInfoService.findByAccountId(detail.getAccountId());

        String trxNo = SerialnoUtils.getOrderNum();

        // 资产处理 新增资产
        WalletAsset asset = walletAssetService.findByAccountIdAndCurrency(detail.getAccountId(), GlobalConst.CURRENCY_PKR);
        if (null == asset) {
            log.error("账户{} PKR 资产不存在", detail.getAccountId());
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }

        // 更新资产
        BigDecimal oldBalance = asset.getBalance();
        // 资产新增奖励金额
        asset.setBalance(asset.getBalance().add(BigDecimal.valueOf(detail.getPointsAwarded())));
        asset.setUpdateTime(System.currentTimeMillis());
        log.info("签到奖励查询：asset ={}", asset);
        walletAssetService.updateByPrimaryKey(asset);
        // 资金流水
        // 类型：充值 发生方向+ 业务类型：充值
        WalletAssetFlows flows = new WalletAssetFlows();
        flows.setAccountId(detail.getAccountId());
        flows.setCurrency(GlobalConst.CURRENCY_PKR);
        flows.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
        flows.setBusinessType(GlobalConst.BUSINESS_TYPE_SIGNIN_REWARD);
        flows.setBeforeBalance(oldBalance);
        flows.setDirection("+");
        flows.setBalance(BigDecimal.valueOf(detail.getPointsAwarded()));
        flows.setFee(BigDecimal.ZERO);
        flows.setAfterBalance(asset.getBalance());
        flows.setOrgBusinessId(detail.getId());
        flows.setOrgBusinessNo(trxNo);
        flows.setStatus(true);
        flows.setCreateTime(System.currentTimeMillis());
        flows.setUpdateTime(System.currentTimeMillis());
        flows.setRemark("SignIn Rewards");
        log.info("签到奖励查询：flows ={}", flows);
        walletAssetFlowsService.insert(flows);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(asset.getBalance(), flows.getAfterBalance());

        detail.setStatus(1);
        log.info("签到奖励：rewards ={}", detail);
        accountSignInDetailService.updateByPrimaryKey(detail);

        // 积分已换币 扣减可用积分
        info.setRemainingPoints(info.getRemainingPoints()-detail.getPointsAwarded());
        accountSignInInfoService.updateByPrimaryKey(info);

    }


}
