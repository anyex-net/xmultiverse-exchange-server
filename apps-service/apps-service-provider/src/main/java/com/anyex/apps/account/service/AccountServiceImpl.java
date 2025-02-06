/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.account.service;

import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.entity.Attribute;
import com.anyex.apps.account.model.AccountInvitedModel;
import com.anyex.apps.account.model.AccountInvitedStatisticsModel;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.openim.entity.RegisterDefaultFriend;
import com.anyex.apps.openim.entity.RegisterDefaultGroup;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.apps.openim.service.RegisterDefaultFriendService;
import com.anyex.apps.openim.service.RegisterDefaultGroupService;
import com.anyex.apps.social.model.AccountInfoModel;
import com.anyex.apps.utils.DateUtils;
import com.anyex.apps.utils.SerialnoUtils;
import com.anyex.apps.utils.StringUtils;
import com.anyex.openim.api.vo.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.account.mapper.AccountMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 账户表 服务实现类
 * <p>File：AccountServiceImpl.java </p>
 * <p>Title: AccountServiceImpl </p>
 * <p>Description:AccountServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class AccountServiceImpl extends GenericServiceImpl<Account> implements AccountService
{
    protected AccountMapper accountMapper;

    @Autowired(required = false)
    private WalletAssetService walletAssetService;

    @Autowired(required = false)
    private AccountInviteRewardsService accountInviteRewardsService;

    @Autowired(required = false)
    AccountInviteStatisticsService accountInviteStatisticsService;

    @Autowired(required = false)
    private OpenImApiService openImApiService;

    @Autowired(required = false)
    private AttributeService attributeService;

    @Autowired(required = false)
    private RegisterDefaultGroupService registerDefaultGroupService;

    @Autowired(required = false)
    private RegisterDefaultFriendService registerDefaultFriendService;

    @Autowired(required = false)
    public AccountServiceImpl(AccountMapper accountMapper)
    {
        super(accountMapper);
        this.accountMapper = accountMapper;
    }

    @Override
    public Long getMaxUNID() throws BusinessException
    {
        return accountMapper.getMaxUNID();
    }

    @Override
    public Account findByUnid(Long unid) throws BusinessException
    {
        return accountMapper.findByUnid(unid);
    }

    @Override
    public Account findByReferralCode(String referralCode)
    {
        return accountMapper.findByReferralCode(referralCode);
    }

    @Override
    public Account findByInvitationCode(String invitationCode) throws BusinessException {
        return accountMapper.findByInvitationCode(invitationCode);
    }

    @Override
    public Account findByAccountName(String username) throws BusinessException
    {
        return accountMapper.findByAccountName(username);
    }

    @Override
    public Account findByMobile(String mobile) throws BusinessException
    {
        log.info("mobile:{}", mobile);
        if (StringUtils.isBlank(mobile))
        {
            return null;
        }
        return accountMapper.findByMobile(mobile);
    }

    @Override
    public Account findByEmail(String email) throws BusinessException
    {
        log.info("email:{}", email);
        if (StringUtils.isBlank(email))
        {
            return null;
        }
        return accountMapper.findByEmail(email);
    }

    @Override
    public Account findByUserId(String userId) throws BusinessException
    {
        log.info("userId:{}", userId);
        if (StringUtils.isBlank(userId))
        {
            return null;
        }
        return accountMapper.findByUserId(userId);
    }


    @Override
    public Account findByEmail4Register(String email) throws BusinessException
    {
        log.info("email:{}", email);
        if (StringUtils.isBlank(email)){
            return null;
        }
        return accountMapper.findByEmail4Register(email);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void register(Account account, WalletAsset walletAsset) throws BusinessException
    {

        UserInfo info = new UserInfo();
        info.setUserID(account.getUserId());
        info.setNickname(account.getAccountName());
        info.setFaceURL(account.getHeadUrl());
        info.setEx(null);
        info.setCreateTime(null);
        info.setAppMangerLevel(null);
        info.setGlobalRecvMsgOpt(null);

        Attribute attribute = attributeService.findByUserId(account.getUserId());
        if (null == attribute) {
            attribute = new Attribute();
            attribute.setUserId(account.getUserId());
            attribute.setAccount(account.getAccountName());
            attribute.setPhoneNumber(account.getMobile());
            attribute.setAreaCode(account.getCountry());
            attribute.setEmail(account.getEmail());
            attribute.setNickname(account.getAccountName());
            attribute.setFaceUrl(account.getHeadUrl());
           // attribute.setGender(account.getGender()?1:0);
            attribute.setCreateTime(new Date());
            attribute.setChangeTime(new Date());
            //Calendar calendar = Calendar.getInstance();
            //calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)-18);
            //account.setBirth(DateUtils.formatDate(calendar.getTime(), "yyyy-MM-dd"));
            try {
                Date bir = DateUtils.parseDate(account.getBirth(), "yyyy-MM-dd");
                attribute.setBirthTime(bir);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            attribute.setLevel(20);
            attribute.setAllowVibration(0);
            attribute.setAllowBeep(0);
            attribute.setAllowAddFriend(1);
            attribute.setGlobalRecvMsgOpt(0);
            attribute.setRegisterType(0);
            attribute.setId(SerialnoUtils.buildPrimaryKey());
            attributeService.insert(attribute);
        }

        String r = openImApiService.registerUser(info);
        // 1102 已注册 0成功
        log.error(r);

        log.info("register account:{}:{}", account);
        log.info("register walletAsset:{}", walletAsset);
        //
        if(account.getCountry() == null)
        {
            account.setCountry(GlobalConst.DEFAULT_COUNTRY);
        }
        accountMapper.insert(account);
        walletAssetService.insert(walletAsset);
        accountInviteStatisticsService.award(account);
        // 导入默认好友
        List<RegisterDefaultFriend> list = registerDefaultFriendService.findList(new RegisterDefaultFriend());
        if(list.size()>0)
        {
            List<String> userIds = list.stream()
                    .map(RegisterDefaultFriend::getUserId)
                    .collect(Collectors.toList());
            openImApiService.importFriends(account.getUserId(),userIds);
        }

        // 导入默认群
        List<RegisterDefaultGroup> ls = registerDefaultGroupService.findList(new RegisterDefaultGroup());
        List<String> userIds = new ArrayList<>();
        for(RegisterDefaultGroup g:ls)
        {
            userIds.clear();
            userIds.add(account.getUserId());
            openImApiService.inviteUserToGroup(g.getGroupId(),userIds);
        }
    }

    @Override
    public AccountInvitedStatisticsModel getInvitedStatistics() {
        return accountMapper.getInvitedStatistics();
    }

    @Override
    public PaginateResult<AccountInvitedModel> getInvitedAccount(Pagination pagin, AccountInvitedModel model) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<AccountInvitedModel> pageInfo = PageInfo.of(accountMapper.getInvitedAccount(model));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    public PaginateResult<AccountInfoModel> findSocialList(Pagination pagin, String keywords, String viewerUserId) throws BusinessException {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<AccountInfoModel> pageInfo = PageInfo.of(accountMapper.findSocialList(keywords, viewerUserId));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }


}
