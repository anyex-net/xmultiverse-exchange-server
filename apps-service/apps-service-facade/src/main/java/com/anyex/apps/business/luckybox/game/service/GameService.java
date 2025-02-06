/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.game.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.business.luckybox.game.entity.Game;
import com.anyex.apps.business.luckybox.game.entity.GamePrize;

/**
 * 游戏信息表 服务接口
 * <p>File：GameService.java </p>
 * <p>Title: GameService </p>
 * <p>Description:GameService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface GameService extends GenericService<Game>
{

    /**
     * 参与游戏即可开奖
     *
     * @param accountId
     * @param gameId
     */
    GamePrize playGame(Long accountId, Long gameId);
}
