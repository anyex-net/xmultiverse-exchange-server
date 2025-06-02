/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.mapper;


import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.user.model.InviteRebateSummaryModel;
import com.anyex.apps.user.model.UserInviteRebateModel;
import org.apache.ibatis.annotations.Mapper;
import com.anyex.apps.user.entity.UserInvite;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户邀请关系 持久层接口
 * <p>File：UserInviteMapper.java </p>
 * <p>Title: UserInviteMapper </p>
 * <p>Description:UserInviteMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UserInviteMapper extends GenericMapper<UserInvite>
{
    InviteRebateSummaryModel selectInviteRebateSummary(@Param("inviterId") Long inviterId);

    List<UserInviteRebateModel> listInviteeRebatesByInviterId(@Param("inviterId") Long inviterId);
}
