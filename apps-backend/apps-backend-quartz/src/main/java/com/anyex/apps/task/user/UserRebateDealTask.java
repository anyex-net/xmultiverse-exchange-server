package com.anyex.apps.task.user;

import com.anyex.apps.user.service.UserInviteRewardConfigService;
import com.anyex.apps.user.service.UserRebateService;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 用户返佣记录每个周期返现处理调度
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class UserRebateDealTask
{
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    UserRebateService userRebateService;

    @Autowired(required = false)
    UserInviteRewardConfigService userInviteRewardConfigService;

    /**
     * 用户返佣记录每个周期返现处理调度
     * @throws RuntimeException
     */
    @Scheduled(cron = "0 */11 * * * ?")
    public void userRebateDealTask() throws RuntimeException
    {
        log.info("用户返佣记录每个周期返现处理调度 开始任务");
        StringBuilder redisLockName = new StringBuilder("redislock:task:userRebateDealTask");
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock())
        {
            try
            {

            } catch (Exception e) {
                e.printStackTrace();
                log.error("用户返佣记录每个周期返现处理调度 异常：error={}",e.getMessage());
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("用户返佣记录每个周期返现处理调度 异常: error={}", "分布式锁获取失败");
        }
        log.info("用户返佣记录每个周期返现处理调度 结束任务");
    }
}
