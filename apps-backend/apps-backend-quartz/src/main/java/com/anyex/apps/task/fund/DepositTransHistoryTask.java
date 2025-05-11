package com.anyex.apps.task.fund;

import com.anyex.apps.fund.service.DepositAddressService;
import com.anyex.apps.user.service.UserService;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 充值交易历史调度
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class DepositTransHistoryTask
{
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    UserService userService;

    @Autowired(required = false)
    DepositAddressService depositAddressService;

    /**
     * 充值交易历史调度
     * @throws RuntimeException
     */
    @Scheduled(cron = "0 5/5 * * * ?")
    public void depositAddressTask() throws RuntimeException
    {
        log.info("充值地址调度 开始任务");
        StringBuilder redisLockName = new StringBuilder("redislock:task:depositAddressTask");
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                // monitorWalletAssetFlowsService.monitorWalletAssetFlowsTask();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("充值地址调度异常：error={}",e.getMessage());
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("充值地址调度异常: error={}", "分布式锁获取失败");
        }
        log.info("充值地址调度 结束任务");
    }
}
