package com.anyex.apps.account;

import com.alibaba.fastjson.JSON;
import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.account.entity.Account;
import com.anyex.apps.account.entity.AccountInviteRewards;
import com.anyex.apps.account.entity.AccountInviteRewardsDetail;
import com.anyex.apps.account.model.AccountInvitedModel;
import com.anyex.apps.account.model.AccountInvitedStatisticsForAppModel;
import com.anyex.apps.account.service.AccountInviteRewardsDetailService;
import com.anyex.apps.account.service.AccountInviteRewardsService;
import com.anyex.apps.account.service.AccountService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@Slf4j
public class AccountInviteRewardsServiceImplTest extends BaseServiceImplTest
{
    @Autowired
    private AccountInviteRewardsService accountInviteRewardsService;

    @Autowired
    private AccountInviteRewardsDetailService accountInviteRewardsDetailService;

    @Autowired
    private AccountService accountService;

    @Test
    public void add()
    {
        Account account = accountService.selectByPrimaryKey(864029534813556736L);
        account.setReferralCode("800001");
        accountService.updateByPrimaryKey(account);


        Account account2 = new Account();
        BeanUtils.copyProperties(account, account2);
        account2.setId(SerialnoUtils.buildPrimaryKey());
        account2.setReferralCode("90001");
        account2.setInvitationCode(account.getReferralCode());
        accountService.insert(account2);

        account = account2;
        account2 = new Account();
        BeanUtils.copyProperties(account, account2);
        account2.setReferralCode("90002");
        account2.setId(SerialnoUtils.buildPrimaryKey());
        account2.setInvitationCode(account.getReferralCode());
        accountService.insert(account2);

        account = account2;
        account2 = new Account();
        BeanUtils.copyProperties(account, account2);
        account2.setId(SerialnoUtils.buildPrimaryKey());
        account2.setReferralCode("90003");
        account2.setInvitationCode(account.getReferralCode());
        accountService.insert(account2);
        System.out.println(account2.getId());

    }

    @Test
    public void doInviteRewardsNew() throws BusinessException {
        AccountInviteRewardsDetail search = new AccountInviteRewardsDetail();
        search.setStatus(0);
        List<AccountInviteRewardsDetail> list = accountInviteRewardsDetailService.findList(search);
        for (AccountInviteRewardsDetail entity : list)
        {
            accountInviteRewardsDetailService.doInviteRewardsAsset(entity);
        }
    }

    @Test
    public void doInviteRewardsOld() throws BusinessException {


        Account account = accountService.selectByPrimaryKey(876700271248543744L);
        accountService.updateByPrimaryKey(account);

        Account account2 = new Account();
        BeanUtils.copyProperties(account, account2);
        account2.setId(SerialnoUtils.buildPrimaryKey());
        account2.setReferralCode("800007");
        account2.setEmail("800007@qq.com");
        account2.setUnid(800007L);
        account2.setInvitationCode(account.getReferralCode());
        System.out.println(account2.getId());
        account2.setDeviceId(UUID.randomUUID().toString());
        accountService.insert(account2);
        accountInviteRewardsService.doInviteRewards(account2);

        System.out.println(JSON.toJSONString(accountInviteRewardsService.getStatisticsItemsDetailForApp(1,864029534813556736L,null,null)));


    }

    @Test
    public void doInviteRewardsAsset() throws BusinessException {
        AccountInviteRewards searcg = new AccountInviteRewards();
        searcg.setRewardsStatus(0);
        List<AccountInviteRewards> list = accountInviteRewardsService.findList(searcg);
        list.forEach(entity->
        {
            accountInviteRewardsService.doInviteRewardsAsset(entity);
        });

    }

    @Test
    public void getInvitedStatistics() throws BusinessException {
        //System.out.println(JSON.toJSONString(accountService.getInvitedStatistics()));
        Pagination pagination = new Pagination();
        pagination.setCurrent(1);
        pagination.setSize(10);
        AccountInvitedModel account =new AccountInvitedModel();
        account.setUnid(4L);
        System.out.println(JSON.toJSONString(accountService.getInvitedAccount(pagination,account)));
    }

    @Test
    public void getInvitedRewardsStatistics() throws BusinessException {
        System.out.println(JSON.toJSONString(accountInviteRewardsService.getStatisticsModel()));
        System.out.println("=======================================");
        Pagination pagination = new Pagination();
        pagination.setCurrent(1);
        pagination.setSize(10);
        Account account =new Account();
        // account.setUnid(4L);
        System.out.println(JSON.toJSONString(accountInviteRewardsService.getStatisticsItems(pagination,account)));
        System.out.println("=======================================");
        System.out.println(JSON.toJSONString(accountInviteRewardsService.getStatisticsItemsDetail(1L)));
    }

    @Test
    public void getAppTest() throws BusinessException {

        // 877070093849333760
 /*       insert into im.Account (id)
        values  (877070712353984512),
                (877072629025083392),
                (877072842284470272),
                (877074212488089600);*/

        AccountInvitedStatisticsForAppModel model1 = accountInviteRewardsService.getStatisticsItemsDetailForApp(1,877070093849333760L,null,null);
        AccountInvitedStatisticsForAppModel model2 = accountInviteRewardsService.getStatisticsItemsDetailForApp(2,877070093849333760L,877070712353984512L,null);
        AccountInvitedStatisticsForAppModel model3 = accountInviteRewardsService.getStatisticsItemsDetailForApp(3,877070093849333760L,877070712353984512L,877072629025083392L);
        System.out.println(JSON.toJSONString(model1));
        System.out.println(JSON.toJSONString(model2));
       System.out.println(JSON.toJSONString(model3));

       /* AccountInvitedStatisticsForAppModel model1 = accountInviteRewardsService.getStatisticsItemsDetailForApp(1,877070712353984512L,null,null);
        AccountInvitedStatisticsForAppModel model2 = accountInviteRewardsService.getStatisticsItemsDetailForApp(2,877070712353984512L,877072629025083392L,null);
        AccountInvitedStatisticsForAppModel model3 = accountInviteRewardsService.getStatisticsItemsDetailForApp(3,877070712353984512L,877072629025083392L,877072842284470272L);
        System.out.println(JSON.toJSONString(model1));
       System.out.println(JSON.toJSONString(model2));
        System.out.println(JSON.toJSONString(model3));*/

      /*  AccountInvitedStatisticsForAppModel model1 = accountInviteRewardsService.getStatisticsItemsDetailForApp(1,877072629025083392L,null,null);
        AccountInvitedStatisticsForAppModel model2 = accountInviteRewardsService.getStatisticsItemsDetailForApp(2,877072629025083392L,877072842284470272L,null);
       // AccountInvitedStatisticsForAppModel model3 = accountInviteRewardsService.getStatisticsItemsDetailForApp(3,877070712353984512L,877072629025083392L,877072842284470272L);
        System.out.println(JSON.toJSONString(model1));
        System.out.println(JSON.toJSONString(model2));*/
       // System.out.println(JSON.toJSONString(model3));


        System.out.println("=======================================");
       /* System.out.println(JSON.toJSONString(accountInviteRewardsService.getStatisticsItemsDetailForApp(2,1L,2L,null)));
        System.out.println("=======================================");
        System.out.println(JSON.toJSONString(accountInviteRewardsService.getStatisticsItemsDetailForApp(3,1L,2L,3L)));
        System.out.println("=======================================");*/


    }



}
