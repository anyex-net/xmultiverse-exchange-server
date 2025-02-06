/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.game;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.game.entity.GamePrize;
import com.anyex.apps.business.luckybox.game.service.GamePrizeService;
import com.anyex.apps.model.JsonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 游戏奖品表 控制器
 * <p>File：GamePrizeController.java </p>
 * <p>Title: GamePrizeController </p>
 * <p>Description:GamePrizeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/business/luckybox/game/gamePrize")
@Api(tags = "游戏奖品")
public class GamePrizeController extends GenericController
{
    @Autowired(required = false)
    private GamePrizeService gamePrizeService;

    @GetMapping(value = "/gamePrizeList")
    @ApiOperation(value = "根据游戏ID查询对应游戏奖品所有数据", httpMethod = "GET")
    @ApiImplicitParam(name = "gameId", value = "游戏ID", paramType = "query", required = true, dataType = "Long")
    public JsonMessage<List<GamePrize>> gamePrizeList(@RequestParam("gameId") Long gameId) throws BusinessException
    {
        //
        GamePrize gamePrize = new GamePrize();
        gamePrize.setGameId(gameId); // 游戏ID
        gamePrize.setStatus(true); // 游戏是否启用
        //
        log.info("gamePrize:{}", gamePrize);
        return getJsonMessage(CommonEnums.SUCCESS, gamePrizeService.findList(gamePrize));
    }
}
