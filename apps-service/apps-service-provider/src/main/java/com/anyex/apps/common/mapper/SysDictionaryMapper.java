/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.common.entity.SysDictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据典 持久层接口
 * <p>File：DictionaryDao.java </p>
 * <p>Title: DictionaryDao </p>
 * <p>Description:DictionaryDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysDictionaryMapper extends GenericMapper<SysDictionary>
{
    /**
     * 根据字典编码取字典数据
     * @param lang
     * @param code
     * @return
     */
    List<SysDictionary> findByCode(@Param("code") String code, @Param("lang") String lang);

    /**
     * 根据上线ID取数据
     * @param parentId
     * @return
     */
    List<SysDictionary> findByParentId(@Param("parentId") String parentId);
}
