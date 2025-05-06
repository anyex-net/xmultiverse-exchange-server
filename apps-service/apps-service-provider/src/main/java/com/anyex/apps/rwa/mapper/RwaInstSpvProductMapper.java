/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.rwa.mapper;

import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.rwa.entity.RwaInstSpvProduct;

import java.util.List;

/**
 * RWA机构SPV产品 持久层接口
 * <p>File：RwaInstSpvProductMapper.java </p>
 * <p>Title: RwaInstSpvProductMapper </p>
 * <p>Description:RwaInstSpvProductMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface RwaInstSpvProductMapper extends GenericMapper<RwaInstSpvProduct>
{
    List<RwaInstSpvProduct> findListByState(RwaInstSpvProduct entity);

    RwaInstSpvProduct selectOneForUpdate(RwaInstSpvProduct rwaInstSpvProduct);
}
