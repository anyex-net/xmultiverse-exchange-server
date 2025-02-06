/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.social.model.AccountInfoModel;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.social.entity.SnsFollow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社交关注(我关注的) 持久层接口
 * <p>File：SnsFollowMapper.java </p>
 * <p>Title: SnsFollowMapper </p>
 * <p>Description:SnsFollowMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SnsFollowMapper extends GenericMapper<SnsFollow>
{
    Integer cntFollow(@Param("userId") String userId);

    List<AccountInfoModel> listFollows( @Param("userId") String userId);

}
