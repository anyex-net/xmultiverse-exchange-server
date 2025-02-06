package com.anyex.apps.task.account;

import com.anyex.apps.account.entity.AccountInviteRewards;
import com.anyex.apps.account.entity.AccountInviteRewardsDetail;
import com.anyex.apps.account.service.AccountInviteRewardsDetailService;
import com.anyex.apps.account.service.AccountInviteRewardsService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.utils.DateUtils;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 邀请返佣奖励发放
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class AccountInviteRewardAssetTask
{
    @Autowired
    RedisTemplate redisTemplate;


    @Autowired(required = false)
    AccountInviteRewardsDetailService accountInviteRewardsDetailService;


    /**
     * 邀请返佣奖励发放（最新）
     * @throws RuntimeException
     */
      @Scheduled(cron = "0 1/2 * * * ?")
    public void inviteAwardTask() throws RuntimeException
    {
        log.info("邀请返佣奖励发放 开始任务");
        StringBuilder redisLockName = null;
        AccountInviteRewardsDetail search = new AccountInviteRewardsDetail();
        search.setStatus(0);
        List<AccountInviteRewardsDetail> list = accountInviteRewardsDetailService.findList(search);
        for (AccountInviteRewardsDetail entity : list) {
            redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX).append(entity.getAccountId());
            RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
            if (redisLock.lock()) {
                try {
                    accountInviteRewardsDetailService.doInviteRewardsAsset(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("邀请返佣奖励发放调度异常：id={} error={}", entity.getId(), e.getMessage());
                } finally {
                    redisLock.unlock();
                }
            } else {
                log.error("邀请返佣奖励发放调度异常：id={} error={}", entity.getId(), "分布式锁获取失败");
            }

        }
        log.info("邀请返佣奖励发放 结束任务");
    }

    /**
     * 邀请返佣奖励发放
     * 这个是老的邀请返佣 已停掉
     * 分钟尾巴为1，6 秒数尾巴为30
     * @throws RuntimeException
     */
   //  @Scheduled(cron = "30 1/5 * * * ?")
    public void depostQueryTask() throws RuntimeException
    {
        /*log.info("邀请返佣奖励发放 开始任务");
        StringBuilder redisLockName = null;
        AccountInviteRewards search = new AccountInviteRewards();
        search.setRewardsStatus(0);
        List<AccountInviteRewards> list = accountInviteRewardsService.findList(search);
        for (AccountInviteRewards entity : list) {
            if(entity.getCreateTime().longValue()>DateUtils.getCurrentDateFirstSec().longValue())
            {
                // 当天的返佣跳过，次日返佣
                continue;
            }
            redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX).append(entity.getRewardsAccountId());
            RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
            if (redisLock.lock()) {
                try {
                    accountInviteRewardsService.doInviteRewardsAsset(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("邀请返佣奖励发放调度异常：id={} error={}", entity.getId(), e.getMessage());
                } finally {
                    redisLock.unlock();
                }
            } else {
                log.error("邀请返佣奖励发放调度异常：id={} error={}", entity.getId(), "分布式锁获取失败");
            }

        }
        log.info("邀请返佣奖励发放 结束任务");*/
    }
}
