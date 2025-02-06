/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.business.luckybox.game;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.business.luckybox.game.req.ReqGame;
import com.anyex.apps.controller.business.luckybox.game.req.ReqGamePagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.business.luckybox.game.service.GameService;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.business.luckybox.game.entity.Game;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 游戏信息表 控制器
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
    private GameService gameService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("game:game:data")
    @ApiOperation(value = "根据ID取游戏信息", httpMethod = "GET")
    public JsonMessage<Game> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, gameService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("game:game:operator")
    @ApiOperation(value = "保存游戏信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqGame info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            Game game = new Game();
            BeanUtils.copyProperties(info, game);
            game.setCreateTime(System.currentTimeMillis());
            game.setUpdateTime(System.currentTimeMillis());
            //
            log.info("game:{}", game);
            if(null == game.getId()){
                gameService.insert(game);
            } else {
                gameService.updateByPrimaryKey(game);
            }
        }
        return json;
    }

    @GetMapping(value = "/gameList")
    @RequiresPermissions("game:game:data")
    @ApiOperation(value = "查询游戏信息所有数据", httpMethod = "GET")
    public JsonMessage<List<Game>> gameList() throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, gameService.selectAll());
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("game:game:data")
    @ApiOperation(value = "查询游戏信息分页列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<Game>> data(@Validated @ModelAttribute ReqGamePagination reqGamePagination) throws BusinessException
    {
        //
        Game game = new Game();
        BeanUtils.copyProperties(reqGamePagination, game);
        //
        PaginateResult<Game> result = gameService.search(reqGamePagination, game);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("game:game:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form", required = true)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        gameService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
