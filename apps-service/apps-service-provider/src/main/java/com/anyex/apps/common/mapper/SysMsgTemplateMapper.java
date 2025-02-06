/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.common.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.common.entity.SysMsgTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 消息模版 持久层接口
 * <p>File：MsgTemplateDao.java </p>
 * <p>Title: MsgTemplateDao </p>
 * <p>Description:MsgTemplateDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysMsgTemplateMapper extends GenericMapper<SysMsgTemplate>
{
    /**
     * 根据模版KEY和语言类型取消息模版
     * @param tplKey
     * @param type
     * @param lang
     * @return
     */
    SysMsgTemplate findByKeyAndLang(@Param("tplKey") String tplKey, @Param("type") String type, @Param("lang") String lang);
}
