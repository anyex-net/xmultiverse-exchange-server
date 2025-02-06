/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.game;

import cn.hutool.core.util.DesensitizedUtil;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.service.SysParameterService;
import com.anyex.apps.consts.CacheConst;
import com.anyex.apps.controller.business.luckybox.game.req.ReqPlayGame;
import com.anyex.apps.controller.business.luckybox.game.resp.RespGamePlayerData;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.game.entity.Game;
import com.anyex.apps.business.luckybox.game.entity.GamePrize;
import com.anyex.apps.business.luckybox.game.service.GameService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.business.luckybox.order.entity.Order4Game;
import com.anyex.apps.business.luckybox.order.service.Order4GameService;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.utils.OnLineUserUtils;
import com.anyex.apps.utils.RedisLock;
import com.anyex.apps.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 游戏信息 控制器
 * <p>File：GameController.java </p>
 * <p>Title: GameController </p>
 * <p>Description:GameController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/game/game")
@Api(tags = "游戏信息")
public class GameController extends GenericController
{
    @Autowired(required = false)
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private SysParameterService sysParameterService;

    @Autowired(required = false)
    private GameService gameService;

    @Autowired(required = false)
    private Order4GameService order4GameService;

    @GetMapping(value = "/gameList")
    @ApiOperation(value = "查询游戏信息所有数据", httpMethod = "GET")
    public JsonMessage<List<Game>> gameList() throws BusinessException
    {
        Game game = new Game();
        game.setStatus(true); // 游戏是否启用
        //
        return getJsonMessage(CommonEnums.SUCCESS, gameService.findList(game));
    }

    @GetMapping(value = "/gamePlayerData")
    @ApiOperation(value = "查询游戏玩家数据", httpMethod = "GET")
    public JsonMessage<RespGamePlayerData> gamePlayerData() throws BusinessException
    {
        //
        RespGamePlayerData respGamePlayerData = new RespGamePlayerData();
        //
        Long accountNum = order4GameService.getOrder4GameAccountNum();
        Long accountIsWinningNum = order4GameService.getOrder4GameIsWinningAccountNum();
        //
        Pagination pagination = new Pagination();
        Order4Game order4Game = new Order4Game();
        order4Game.setIsWinning(true);
        List<Order4Game> listWinningOrder4Game = order4GameService.search(pagination, order4Game).getRecords();
        listWinningOrder4Game.stream().forEach(entity->
        {
            entity.setEmail(DesensitizedUtil.email(entity.getEmail())); // 邮箱模糊
        });
        //
        respGamePlayerData.setAccountNum(accountNum);
        respGamePlayerData.setIsWinningAccountNum(accountIsWinningNum);
        respGamePlayerData.setListWinningOrder4Game(listWinningOrder4Game);
        log.info("respGamePlayerData:{}", respGamePlayerData);
        return getJsonMessage(CommonEnums.SUCCESS, respGamePlayerData);
    }

    @PostMapping(value = "/playGame")
    @ApiOperation(value = "参与游戏", httpMethod = "POST")
    public JsonMessage<GamePrize> playGame(@RequestBody ReqPlayGame reqPlayGame) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == principal) throw new BusinessException(CommonEnums.USER_NOT_LOGIN);
        //
        if(StringUtils.equalsIgnoreCase(sysParameterService.getParameterByName("SystemTradeSwitch").getValue(), "OFF"))
        {
            log.error("系统开关已关闭");
            throw new BusinessException(CommonEnums.RISK_TRADE_OFF);
        }
        //
        log.info("reqPlayGame:{}", reqPlayGame);
        //
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, reqPlayGame))
        {
            StringBuilder redisLockName = new StringBuilder(CacheConst.REDISLOCK_GAME_GAME_PREFIX);
            redisLockName.append(reqPlayGame.getGameId());
            log.info("redisLockName:{}", redisLockName.toString());
            // 分布式redis锁判断
            RedisLock redisLock = new RedisLock(redisTemplate, redisLockName.toString(), 3);
            if (redisLock.lock())
            {
                log.info("拿到分布式redis锁:{}, ts:{}", redisLockName.toString(), System.currentTimeMillis());
                try {
                    //
                    //
                    StringBuilder redisLockAssetName = new StringBuilder(CacheConst.REDISLOCK_WALLETASSET_ACCOUNT_PREFIX);
                    redisLockAssetName.append(principal.getId());
                    log.info("redisLockAssetName:{}", redisLockAssetName.toString());
                    // 分布式redis锁判断
                    RedisLock redisLockAsset = new RedisLock(redisTemplate, redisLockAssetName.toString(), 3);
                    if (redisLockAsset.lock())
                    {
                        log.info("拿到分布式redis锁:{}, ts:{}", redisLockAssetName.toString(), System.currentTimeMillis());
                        try {
                            //
                            // 参与游戏核心逻辑
                            GamePrize gamePrize = gameService.playGame(principal.getId(), reqPlayGame.getGameId());
                            log.info("gamePrize:{}", gamePrize);
                            //
                            json.setData(gamePrize);
                            //
                        } catch (BusinessException e) {
                            log.error("playGame参与游戏异常:{}", e.getLocalizedMessage());
                            throw e;
                        } finally {
                            log.info("释放分布式redis锁:{}, ts:{}", redisLockAssetName.toString(), System.currentTimeMillis());
                            redisLockAsset.unlock();
                        }
                    } else {
                        log.error(CommonEnums.SERVICE_BUSY_ERROR.getMessage());
                        throw new BusinessException(CommonEnums.SERVICE_BUSY_ERROR);
                    }
                    //
                    //
                } catch (BusinessException e) {
                    log.error("playGame参与游戏异常:{}", e.getLocalizedMessage());
                    throw e;
                } finally {
                    log.info("释放分布式redis锁:{}, ts:{}", redisLockName.toString(), System.currentTimeMillis());
                    redisLock.unlock();
                }
            } else {
                log.error(CommonEnums.SERVICE_BUSY_ERROR.getMessage());
                throw new BusinessException(CommonEnums.SERVICE_BUSY_ERROR);
            }
        }
        //
        return json;
    }
}
