package com.anyex.apps.task.operation;

import com.anyex.apps.operation.service.MonitorWalletAssetFlowsService;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 资金流水监控
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class MonitorWalletAssetFlowsTask
{
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    MonitorWalletAssetFlowsService monitorWalletAssetFlowsService;

    /**
     * 资金流水监控
     * @throws RuntimeException
     */
    @Scheduled(cron = "0 3/3 * * * ?")
    public void monitorWalletAssetFlowsTask() throws RuntimeException
    {
        log.info("资金流水监控 开始任务");
        StringBuilder redisLockName = new StringBuilder("redislock:monitor:walletassetflows");
        RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                monitorWalletAssetFlowsService.monitorWalletAssetFlowsTask();
            } catch (Exception e) {
                e.printStackTrace();
                log.error("资金流水监控调度异常：error={}",e.getMessage());
            } finally {
                redisLock.unlock();
            }
        } else {
            log.error("资金流水监控调度异常： error={}",  "分布式锁获取失败");
        }
        log.info("资金流水监控 结束任务");
    }
}
