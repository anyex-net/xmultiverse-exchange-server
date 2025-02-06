/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.social.model.AccountInfoModel;
import com.anyex.apps.social.service.SnsFriendService;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.social.entity.SnsFriend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社交好友 持久层接口
 * <p>File：SnsFriendMapper.java </p>
 * <p>Title: SnsFriendMapper </p>
 * <p>Description:SnsFriendMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SnsFriendMapper extends GenericMapper<SnsFriend>
{
    Integer getFriendsCntByUserId(@Param("userId") String userId);

    List<AccountInfoModel> myFriends(SnsFriend entity);

}
