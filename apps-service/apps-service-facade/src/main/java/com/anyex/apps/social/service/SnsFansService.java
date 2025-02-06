/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.social.service;

import com.anyex.apps.bean.GenericService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.social.entity.SnsFans;
import com.anyex.apps.social.model.AccountInfoModel;

/**
 * 社交粉丝(关注我的) 服务接口
 * <p>File：SnsFansService.java </p>
 * <p>Title: SnsFansService </p>
 * <p>Description:SnsFansService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public interface SnsFansService extends GenericService<SnsFans>
{
    /**
     * userId的粉丝数量
     * @param userId
     * @return
     */
    Integer cntFans(String userId);

    PaginateResult<AccountInfoModel> listFans(Pagination pagin, String userId) throws BusinessException;
}
