/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.common.entity.SysDictionary;
import com.anyex.apps.exception.BusinessException;

import java.util.List;

/**
 * 数据典 服务接口
 * <p>File：DictionaryService.java </p>
 * <p>Title: DictionaryService </p>
 * <p>Description:DictionaryService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface SysDictionaryService extends GenericService<SysDictionary>
{
    /**
     * 根据字典编码取字典数据
     *
     * @param code
     * @param lang
     * @return {@link List}
     * @throws BusinessException
     */
    List<SysDictionary> findByCode(String code, String lang) throws BusinessException;
    
    /**
     * 查询字典信息并返回树形对象
     * @return
     * @throws BusinessException
     */
    List<SysDictionary> treeData() throws BusinessException;
}
