/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.game;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.game.req.ReqGamePrize;
import com.anyex.apps.controller.business.luckybox.game.req.ReqGamePrizePagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.game.service.GamePrizeService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.game.entity.GamePrize;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

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

    @GetMapping(value = "/findBy")
    @RequiresPermissions("game:gamePrize:data")
    @ApiOperation(value = "根据ID取游戏奖品", httpMethod = "GET")
    public JsonMessage<GamePrize> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, gamePrizeService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("game:gamePrize:operator")
    @ApiOperation(value = "保存游戏奖品", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqGamePrize info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            GamePrize gamePrize = new GamePrize();
            BeanUtils.copyProperties(info, gamePrize);
            gamePrize.setCreateTime(System.currentTimeMillis());
            gamePrize.setUpdateTime(System.currentTimeMillis());
            //
            log.info("gamePrize:{}", gamePrize);
            if(null == gamePrize.getId()){
                gamePrizeService.insert(gamePrize);
            } else {
                gamePrizeService.updateByPrimaryKey(gamePrize);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("game:gamePrize:data")
    @ApiOperation(value = "查询游戏奖品分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<GamePrize>> data(@Validated @ModelAttribute ReqGamePrizePagination reqGamePrizePagination) throws BusinessException
    {
        //
        GamePrize gamePrize = new GamePrize();
        BeanUtils.copyProperties(reqGamePrizePagination, gamePrize);
        //
        PaginateResult<GamePrize> result = gamePrizeService.search(reqGamePrizePagination, gamePrize);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("game:gamePrize:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form", required = true)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        gamePrizeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
