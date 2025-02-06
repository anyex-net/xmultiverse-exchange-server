package com.anyex.apps.task.asset;

import com.anyex.apps.asset.entity.WalletAssetTipGift;
import com.anyex.apps.asset.service.WalletAssetTipGiftService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 礼物自动接收
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class WalletAssetTipGiftTask
{
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    WalletAssetTipGiftService walletAssetTipGiftService;

    /**
     * 礼物自动接收
     * @throws RuntimeException
     */
    @Scheduled(cron = "30 0/1 * * * ?")
    public void depostQueryTask() throws RuntimeException
    {
        log.info("礼物自动接收 开始任务");
        StringBuilder redisLockName = new StringBuilder();
        WalletAssetTipGift search = new WalletAssetTipGift();
        search.setStatus(1);
        List<WalletAssetTipGift> list = walletAssetTipGiftService.findList(search);
        for (WalletAssetTipGift entity : list) {
            redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX).append(entity.getToAccountId());
            RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
            if (redisLock.lock()) {
                try {
                    walletAssetTipGiftService.getGift(entity);
                    Thread.sleep(500);// 暂停0.5秒 降频
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("礼物自动接收调度异常：id={} trxNo={}  error={}", entity.getId(), entity.getTrxNo(), e.getMessage());
                } finally {
                    redisLock.unlock();
                }
            } else {
                log.error("礼物自动接收调度异常：id={} trxNo={}  error={}", entity.getId(), entity.getTrxNo(), "分布式锁获取失败");
            }

        }
        log.info("礼物自动接收 结束任务");
    }
}
