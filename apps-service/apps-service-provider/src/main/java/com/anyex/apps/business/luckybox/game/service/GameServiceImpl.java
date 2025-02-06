/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.game.service;

import com.anyex.apps.asset.AssetUtil;
import com.anyex.apps.asset.entity.WalletAsset;
import com.anyex.apps.asset.entity.WalletAssetFlows;
import com.anyex.apps.asset.service.WalletAssetFlowsService;
import com.anyex.apps.asset.service.WalletAssetService;
import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.game.entity.Game;
import com.anyex.apps.business.luckybox.game.entity.GamePrize;
import com.anyex.apps.business.luckybox.order.entity.Order4Game;
import com.anyex.apps.business.luckybox.order.service.Order4GameService;
import com.anyex.apps.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.business.luckybox.game.mapper.GameMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 游戏信息表 服务实现类
 * <p>File：GameServiceImpl.java </p>
 * <p>Title: GameServiceImpl </p>
 * <p>Description:GameServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class GameServiceImpl extends GenericServiceImpl<Game> implements GameService
{
    protected GameMapper gameMapper;

    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private GamePrizeService gamePrizeService;

    @Autowired(required = false)
    private WalletAssetService walletAssetService;

    @Autowired(required = false)
    private WalletAssetFlowsService walletAssetFlowsService;

    @Autowired(required = false)
    private Order4GameService order4GameService;

    @Autowired(required = false)
    public GameServiceImpl(GameMapper gameMapper)
    {
        super(gameMapper);
        this.gameMapper = gameMapper;
    }

    /**
     * 参与游戏即可开奖
     *
     * @param accountId
     * @param gameId
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public GamePrize playGame(Long accountId, Long gameId) throws BusinessException
    {
        // 判断游戏是否存在
        Game gameDB = gameMapper.selectByPrimaryKey(gameId);
        if(null == gameDB || !gameDB.getStatus()) {
            log.error("判断游戏是否存在异常");
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        // 判断游戏对应奖品是否存在
        GamePrize gamePrizeSearch = new GamePrize();
        gamePrizeSearch.setGameId(gameId);
        gamePrizeSearch.setStatus(true);
        List<GamePrize> listGamePrize = gamePrizeService.findList(gamePrizeSearch);
        if(null == listGamePrize) {
            log.error("判断游戏对应奖品是否存在异常");
            throw new BusinessException(CommonEnums.ERROR_DATA_NO_FOUND_ERR);
        }
        //
        // 参与游戏核心逻辑
        //
        // 判断账户资产是否足够
        WalletAsset walletAssetDB = walletAssetService.findByAccountIdAndCurrency(accountId, GlobalConst.CURRENCY_PKR);
        log.info("判断账户资产是否足够walletAssetDB:{}", walletAssetDB.toString());
        if(null == walletAssetDB || (walletAssetDB.getBalance().subtract(walletAssetDB.getFrozenBal())).compareTo(gameDB.getGameChips()) < 0){
            log.error("判断账户资产是否足够异常,可用资金余额不足");
            throw new BusinessException(CommonEnums.RISK_ENABLE_BALANCE_NOTAVAILABLE);
        }

        // 抽奖开始
        String key = CacheConst.GAME_LOTTERY_GAME_PREFIX + gameDB.getId();
        redisTemplate.delete(key);
        // 获得抽奖结果
        Long gamePrizeIdInRedis = (Long)redisTemplate.opsForList().leftPop(key);
        log.info("直接抽奖 gamePrizeIdInRedis:{}", gamePrizeIdInRedis);
        // 如果没有抽奖奖励，则按比例生成
        if(null == gamePrizeIdInRedis)
        {
            //
            List<Long> listGamePrizeId = new ArrayList<>();
            for(GamePrize gamePrize : listGamePrize){
                for(int i = 0; i < gamePrize.getPercentWinningAmount(); i++){
                    listGamePrizeId.add(gamePrize.getId());
                }
            }
            Collections.shuffle(listGamePrizeId);
            gamePrizeIdInRedis = listGamePrizeId.remove(0); // 拿出一个当作随机中奖的奖品ID
            log.info("拿出一个当作随机中奖的奖品ID gamePrizeIdInRedis:{}", gamePrizeIdInRedis);
            //
            for(Long gamePrizeId : listGamePrizeId){
                this.redisTemplate.opsForList().rightPush(key, gamePrizeId);
            }
        }
        // 抽奖结束

        //
        // 抽中奖品ID对应的奖品信息
        GamePrize gamePrizeDB = gamePrizeService.selectByPrimaryKey(gamePrizeIdInRedis);
        log.info("gamePrizeDB:{}", gamePrizeDB);
        // 是否中奖逻辑 根据奖励金额判断
        Boolean isWinningFlag = gamePrizeDB.getRewardBalance() > 0 ? true:false;
        // 插入游戏订单记录
        Order4Game order4GameNew = new Order4Game();
        order4GameNew.setId(SerialnoUtils.buildPrimaryKey());
        order4GameNew.setOrderTxNo(SerialnoUtils.getOrderNum()); // 订单编号
        order4GameNew.setGameId(gameId);
        order4GameNew.setAccountId(accountId);
        order4GameNew.setGameExpendBalance(gameDB.getGameChips());
        order4GameNew.setGamePrizeId(gamePrizeIdInRedis);
        order4GameNew.setGamePrizeName(gamePrizeDB.getPrizeName());
        order4GameNew.setGameRewardBalance(gamePrizeDB.getRewardBalance());
        order4GameNew.setOrderStatus(true);
        order4GameNew.setIsWinning(isWinningFlag);
        order4GameNew.setRemark("参与游戏已开奖");
        order4GameNew.setCreateTime(System.currentTimeMillis());
        order4GameNew.setUpdateTime(System.currentTimeMillis());
        log.info("order4Game:{}", order4GameNew);
        order4GameService.insert(order4GameNew);

        // 扣减账户资产流水
        WalletAssetFlows walletAssetFlowsSub = new WalletAssetFlows();
        walletAssetFlowsSub.setAccountId(accountId);
        walletAssetFlowsSub.setCurrency(GlobalConst.CURRENCY_PKR);
        walletAssetFlowsSub.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_EXPEND);
        walletAssetFlowsSub.setBusinessType(GlobalConst.BUSINESS_TYPE_GAME_SPIN);
        walletAssetFlowsSub.setBeforeBalance(walletAssetDB.getBalance());
        walletAssetFlowsSub.setBalance(gameDB.getGameChips());
        walletAssetFlowsSub.setFee(BigDecimal.ZERO);
        walletAssetFlowsSub.setDirection("-");
        walletAssetFlowsSub.setAfterBalance(walletAssetDB.getBalance().subtract(gameDB.getGameChips()));
        walletAssetFlowsSub.setOrgBusinessId(order4GameNew.getId());
        walletAssetFlowsSub.setOrgBusinessNo(order4GameNew.getOrderTxNo());
        walletAssetFlowsSub.setStatus(true);
        walletAssetFlowsSub.setCreateTime(System.currentTimeMillis());
        walletAssetFlowsSub.setRemark("Spin");
        log.info("扣减账户资产流水 walletAssetFlowsSub:{}", walletAssetFlowsSub);
        walletAssetFlowsService.insert(walletAssetFlowsSub);

        // 扣减账户资产
        walletAssetDB.setBalance(walletAssetDB.getBalance().subtract(gameDB.getGameChips()));
        walletAssetDB.setUpdateTime(System.currentTimeMillis());
        log.info("扣减账户资产 walletAssetDB:{}", walletAssetDB);
        walletAssetService.updateByPrimaryKeySelective(walletAssetDB);

        // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
        AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(walletAssetDB.getBalance(), walletAssetFlowsSub.getAfterBalance());

        // 根据游戏奖品信息 判断 否则需要增加账户资产
        if(isWinningFlag) {
            log.info("根据游戏奖品信息 中奖 需要增加账户资产walletAssetDB:{}", walletAssetDB.toString());
            // 增加账户资产流水
            WalletAssetFlows walletAssetFlowsAdd = new WalletAssetFlows();
            walletAssetFlowsAdd.setAccountId(accountId);
            walletAssetFlowsAdd.setCurrency(GlobalConst.CURRENCY_PKR);
            walletAssetFlowsAdd.setBusinessCategory(GlobalConst.BUSINESS_CATEGORY_REVENUE);
            walletAssetFlowsAdd.setBusinessType(GlobalConst.BUSINESS_TYPE_GAME_SPIN_REWARD);
            walletAssetFlowsAdd.setBeforeBalance(walletAssetDB.getBalance());
            walletAssetFlowsAdd.setBalance(BigDecimal.valueOf(gamePrizeDB.getRewardBalance()));
            walletAssetFlowsAdd.setFee(BigDecimal.ZERO);
            walletAssetFlowsAdd.setDirection("+");
            walletAssetFlowsAdd.setAfterBalance(walletAssetDB.getBalance().add(BigDecimal.valueOf(gamePrizeDB.getRewardBalance())));
            walletAssetFlowsAdd.setOrgBusinessId(order4GameNew.getId());
            walletAssetFlowsAdd.setOrgBusinessNo(order4GameNew.getOrderTxNo());
            walletAssetFlowsAdd.setStatus(true);
            walletAssetFlowsAdd.setCreateTime(System.currentTimeMillis());
            walletAssetFlowsAdd.setRemark("Bonus-Spin");
            log.info("增加账户资产流水 walletAssetFlowsAdd:{}", walletAssetFlowsAdd);
            walletAssetFlowsService.insert(walletAssetFlowsAdd);

            // 增加账户资产
            walletAssetDB.setBalance(walletAssetDB.getBalance().add(BigDecimal.valueOf(gamePrizeDB.getRewardBalance())));
            walletAssetDB.setUpdateTime(System.currentTimeMillis());
            log.info("增加账户资产 walletAssetDB:{}", walletAssetDB);
            walletAssetService.updateByPrimaryKeySelective(walletAssetDB);

            // 检查钱包资产余额 与 钱包资产流水发生后余额 是否相等
            AssetUtil.checkWalletAssetBalanceAndWalletAssetFlowsAfterBalance(walletAssetDB.getBalance(), walletAssetFlowsAdd.getAfterBalance());
        }
        //
        // 返回游戏中奖奖品信息
        return gamePrizeDB;
    }
}
