/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.base.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.base.entity.InstrumentsCurrency;

/**
 * 平台交易产品币种信息 持久层接口
 * <p>File：InstrumentsCurrencyMapper.java </p>
 * <p>Title: InstrumentsCurrencyMapper </p>
 * <p>Description:InstrumentsCurrencyMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface InstrumentsCurrencyMapper extends GenericMapper<InstrumentsCurrency>
{

}
