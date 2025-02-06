/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.game.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.business.luckybox.game.entity.Game;

/**
 * 游戏信息表 持久层接口
 * <p>File：GameMapper.java </p>
 * <p>Title: GameMapper </p>
 * <p>Description:GameMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface GameMapper extends GenericMapper<Game>
{

}
