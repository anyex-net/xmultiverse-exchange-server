package com.anyex.apps.task.asset;

import com.anyex.apps.asset.entity.WalletAssetTransactions;
import com.anyex.apps.asset.service.WalletAssetTransactionsService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 充提记录 未到账调度查询
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class WalletAssetTransactionsTask
{
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired(required = false)
    WalletAssetTransactionsService walletAssetTransactionsService;

    /**
     * 充值状态查询
     * @throws RuntimeException
     */
    @Scheduled(cron = "0 1/5 * * * ?")
    public void depostQueryTask() throws RuntimeException
    {
        log.info("充值状态查询 开始任务");
        StringBuilder redisLockName = new StringBuilder();
        WalletAssetTransactions search = new WalletAssetTransactions();
        search.setTrxStatus(GlobalConst.STATUS_PENDING);
        search.setTrxType("deposit");
        List<WalletAssetTransactions> list = walletAssetTransactionsService.findList(search);
        for (WalletAssetTransactions entity : list) {
            redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX).append(entity.getAccountId());
            RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
            if (redisLock.lock()) {
                try {
                    walletAssetTransactionsService.depositQueryAndUpdate(entity.getId(), entity.getAccountId());
                    Thread.sleep(500);// 暂停0.5秒 降频
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("充值状态查询调度异常：id={} trxNo={}  error={}", entity.getId(), entity.getTrxNo(), e.getMessage());
                } finally {
                    redisLock.unlock();
                }
            } else {
                log.error("充值状态查询调度异常：id={} trxNo={}  error={}", entity.getId(), entity.getTrxNo(), "分布式锁获取失败");
            }

        }
        log.info("充值状态查询 结束任务");
    }

    /**
     * 提现状态查询
     * @throws RuntimeException
     */
    @Scheduled(cron = "0 4/5 * * * ?")
    public void withdrawQueryTask() throws RuntimeException
    {
        log.info("提现状态查询 开始任务");
        StringBuilder redisLockName = new StringBuilder();
        WalletAssetTransactions search = new WalletAssetTransactions();
        search.setTrxStatus(GlobalConst.STATUS_PENDING);
        search.setTrxType("withDraw");
        List<WalletAssetTransactions> list = walletAssetTransactionsService.findList(search);
        for (WalletAssetTransactions entity : list) {
            redisLockName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX).append(entity.getAccountId());
            RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
            // 分布式锁
            if (redisLock.lock()) {
                try {
                    walletAssetTransactionsService.withdrawQueryAndUpdate(entity.getId(), entity.getAccountId());
                    Thread.sleep(500);// 暂停0.5秒 降频
                } catch (Exception e) {
                    // 业务出现异常
                    e.printStackTrace();
                    log.error("提现状态查询调度异常：id={} trxNo={}  error={}", entity.getId(), entity.getTrxNo(), e.getMessage());
                } finally {
                    redisLock.unlock();
                }
            } else {
                log.error("提现状态查询调度异常：id={} trxNo={}  error={}", entity.getId(), entity.getTrxNo(), "获取分布式锁失败");
            }
        }
        log.info("提现状态查询 结束任务");
    }
}
