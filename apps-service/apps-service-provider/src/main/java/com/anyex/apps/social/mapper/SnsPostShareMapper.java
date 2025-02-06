/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.mapper;


import com.anyex.apps.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.social.entity.SnsPostShare;

/**
 * 社交帖子分享 持久层接口
 * <p>File：SnsPostShareMapper.java </p>
 * <p>Title: SnsPostShareMapper </p>
 * <p>Description:SnsPostShareMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SnsPostShareMapper extends GenericMapper<SnsPostShare>
{

}
