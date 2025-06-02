/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.user.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.user.entity.UserInvite;
import com.anyex.apps.user.model.InviteRebateSummaryModel;
import com.anyex.apps.user.model.UserInviteRebateModel;

/**
 * 用户邀请关系 服务接口
 * <p>File：UserInviteService.java </p>
 * <p>Title: UserInviteService </p>
 * <p>Description:UserInviteService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface UserInviteService extends GenericService<UserInvite>
{
    InviteRebateSummaryModel selectInviteRebateSummary(Long inviterId);

    PaginateResult<UserInviteRebateModel> listInviteeRebatesByInviterId(Pagination pagin, Long inviterId);
}
