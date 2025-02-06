/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.game.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.business.luckybox.game.entity.GamePrize;

/**
 * 游戏奖品表 持久层接口
 * <p>File：GamePrizeMapper.java </p>
 * <p>Title: GamePrizeMapper </p>
 * <p>Description:GamePrizeMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface GamePrizeMapper extends GenericMapper<GamePrize>
{

}
