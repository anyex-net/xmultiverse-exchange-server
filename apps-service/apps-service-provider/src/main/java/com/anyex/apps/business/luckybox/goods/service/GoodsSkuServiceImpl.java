/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.business.luckybox.goods.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.business.luckybox.goods.entity.GoodsSku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.anyex.apps.business.luckybox.goods.mapper.GoodsSkuMapper;

import java.util.List;

/**
 * 商品SKU表 服务实现类
 * <p>File：GoodsSkuServiceImpl.java </p>
 * <p>Title: GoodsSkuServiceImpl </p>
 * <p>Description:GoodsSkuServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class GoodsSkuServiceImpl extends GenericServiceImpl<GoodsSku> implements GoodsSkuService
{
    protected GoodsSkuMapper goodsSkuMapper;

    @Autowired(required = false)
    public GoodsSkuServiceImpl(GoodsSkuMapper goodsSkuMapper)
    {
        super(goodsSkuMapper);
        this.goodsSkuMapper = goodsSkuMapper;
    }

    @Override
    public void adjustGoodsSkuStock() {
        GoodsSku search = new GoodsSku();
        search.setValid(true);
        search.setSaleable(true);
        List<GoodsSku> skuList = goodsSkuMapper.findList(search);
        for (int i = 0; i < skuList.size(); i++) {
            GoodsSku goodsSkuDB = skuList.get(i);
            if(goodsSkuDB.getStock() > 0)
            {
                Integer subStock = (int) (Math.random() * 2 + 1);
                Long orgStock = goodsSkuDB.getStock();
                Long newStock = goodsSkuDB.getStock().longValue() - subStock.longValue();
                if(newStock > 0) {
                    goodsSkuDB.setStock(newStock);
                    log.info("goodsSkuDB:{}", goodsSkuDB);
                    goodsSkuMapper.updateByPrimaryKeySelective(goodsSkuDB);
                    log.info("修改成功: id:{}, orgStock:{}, substock:{}", goodsSkuDB.getId(), orgStock, subStock);
                } else {
                    log.error("修改失败: id:{}, orgStock:{}, substock:{}", goodsSkuDB.getId(), orgStock, subStock);
                }
            }
        }
    }
}
