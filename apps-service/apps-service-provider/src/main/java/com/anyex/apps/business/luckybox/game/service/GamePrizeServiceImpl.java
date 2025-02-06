/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.game.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.game.entity.GamePrize;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.business.luckybox.game.mapper.GamePrizeMapper;

/**
 * 游戏奖品表 服务实现类
 * <p>File：GamePrizeServiceImpl.java </p>
 * <p>Title: GamePrizeServiceImpl </p>
 * <p>Description:GamePrizeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class GamePrizeServiceImpl extends GenericServiceImpl<GamePrize> implements GamePrizeService
{
    protected GamePrizeMapper gamePrizeMapper;

    @Autowired(required = false)
    public GamePrizeServiceImpl(GamePrizeMapper gamePrizeMapper)
    {
        super(gamePrizeMapper);
        this.gamePrizeMapper = gamePrizeMapper;
    }
}
