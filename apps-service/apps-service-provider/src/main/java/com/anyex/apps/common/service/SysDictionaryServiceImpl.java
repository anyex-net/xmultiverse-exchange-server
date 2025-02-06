/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.exception.BusinessException;
import com.google.common.collect.Lists;
import com.anyex.apps.common.entity.SysDictionary;
import com.anyex.apps.common.mapper.SysDictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据典 服务实现类
 * <p>File：Dictionary.java </p>
 * <p>Title: Dictionary </p>
 * <p>Description:Dictionary </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysDictionaryServiceImpl extends GenericServiceImpl<SysDictionary> implements SysDictionaryService
{
    private SysDictionaryMapper dictionaryMapper;
    
    @Autowired
    public SysDictionaryServiceImpl(SysDictionaryMapper dictionaryMapper)
    {
        super(dictionaryMapper);
        this.dictionaryMapper = dictionaryMapper;
    }
    
    @Override
    public List<SysDictionary> findByCode(String code, String lang) throws BusinessException
    {
        return dictionaryMapper.findByCode(code, lang);
    }
    
    @Override
    public List<SysDictionary> treeData() throws BusinessException
    {
        List<SysDictionary> data = Lists.newArrayList();
        List<SysDictionary> entitys = dictionaryMapper.selectAll();
        for (SysDictionary parent : entitys)
        {
            if (null == parent.getParentId() || 0L == parent.getParentId())
            {
                data.add(parent);
            }
            for (SysDictionary child : entitys)
            {
                if (parent.getId().equals(child.getParentId()))
                {
                    if (parent.getChildren() == null)
                    {
                        parent.setChildren(Lists.newArrayList(child));
                    }
                    else
                    {
                        parent.getChildren().add(child);
                    }
                }
            }
        }
        return data;
    }
}
