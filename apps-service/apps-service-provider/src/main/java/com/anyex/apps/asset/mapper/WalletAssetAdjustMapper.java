/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.asset.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.asset.entity.WalletAssetAdjust;

/**
 * 钱包资产调整记录表 持久层接口
 * <p>File：WalletAssetAdjustMapper.java </p>
 * <p>Title: WalletAssetAdjustMapper </p>
 * <p>Description:WalletAssetAdjustMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface WalletAssetAdjustMapper extends GenericMapper<WalletAssetAdjust>
{

}
