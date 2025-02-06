package com.anyex.apps.task.account;

import com.anyex.apps.account.entity.AccountSignInDetail;
import com.anyex.apps.account.service.AccountSignInDetailService;
import com.anyex.apps.account.service.AccountSignInInfoService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 签到奖励发放和断签
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class AccountSignInTask
{
    @Autowired
    RedisTemplate redisTemplate;


    @Autowired(required = false)
    AccountSignInInfoService accountSignInInfoService;

    @Autowired(required = false)
    AccountSignInDetailService accountSignInDetailService;


    /**
     * 签到奖励发放（最新）
     * @throws RuntimeException
     */
    @Scheduled(cron = "5 0/1 * * * ?")
    public void signInAwardTask() throws RuntimeException
    {
        log.info("签到奖励发放 开始任务");
        StringBuilder redisLockName = null;
        AccountSignInDetail search = new AccountSignInDetail();
        search.setStatus(0);
        List<AccountSignInDetail> list = accountSignInDetailService.findList(search);
        for (AccountSignInDetail entity : list) {
            redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX).append(entity.getAccountId());
            RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
            if (redisLock.lock()) {
                try {
                    accountSignInInfoService.doAwardSignIn(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("签到奖励发放调度异常：id={} error={}", entity.getId(), e.getMessage());
                } finally {
                    redisLock.unlock();
                }
            } else {
                log.error("签到奖励发放调度异常：id={} error={}", entity.getId(), "分布式锁获取失败");
            }

        }
        log.info("签到奖励发放 结束任务");
    }

    /**
     * 检查断签
     * @throws RuntimeException
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkCutOffSignIn() throws RuntimeException
    {
        log.info("检测断签 开始任务");
        try {
            accountSignInInfoService.doCheckCutOffSignIn();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("检测断签：error={}", e.getMessage());
        }
        log.info("检测断签 结束任务");
    }


}
