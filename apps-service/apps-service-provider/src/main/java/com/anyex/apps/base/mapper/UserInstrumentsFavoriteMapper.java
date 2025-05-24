/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.base.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.base.entity.UserInstrumentsFavorite;

/**
 * 用户交易产品收藏 持久层接口
 * <p>File：UserInstrumentsFavoriteMapper.java </p>
 * <p>Title: UserInstrumentsFavoriteMapper </p>
 * <p>Description:UserInstrumentsFavoriteMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UserInstrumentsFavoriteMapper extends GenericMapper<UserInstrumentsFavorite>
{

}
