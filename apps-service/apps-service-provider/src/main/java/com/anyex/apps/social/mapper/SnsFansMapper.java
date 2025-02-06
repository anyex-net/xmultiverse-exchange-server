/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.social.model.AccountInfoModel;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.social.entity.SnsFans;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社交粉丝(关注我的) 持久层接口
 * <p>File：SnsFansMapper.java </p>
 * <p>Title: SnsFansMapper </p>
 * <p>Description:SnsFansMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SnsFansMapper extends GenericMapper<SnsFans>
{
    /**
     * userId的粉丝数量
     * @param userId
     * @return
     */
    Integer cntFans(String userId);

    List<AccountInfoModel> listFans(@Param("userId") String userId);

}
