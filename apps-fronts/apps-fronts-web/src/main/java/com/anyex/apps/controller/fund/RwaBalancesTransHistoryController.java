/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.fund;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.fund.req.ReqRwaBalancesTransHistoryPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.rwa.entity.RwaBalancesTransHistory;
import com.anyex.apps.rwa.service.RwaBalancesTransHistoryService;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * RWA账户交易历史 控制器
 * <p>File：RwaBalancesTransHistoryController.java </p>
 * <p>Title: RwaBalancesTransHistoryController </p>
 * <p>Description:RwaBalancesTransHistoryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rwa/rwaBalancesTransHistory")
@Api(tags = "RWA账户交易历史")
public class RwaBalancesTransHistoryController extends GenericController
{
    @Autowired(required = false)
    private RwaBalancesTransHistoryService rwaBalancesTransHistoryService;

    @PostMapping(value = "/data")
    @ApiOperation(value = "查询RWA账户交易历史", httpMethod = "POST")
    public JsonMessage<PaginateResult<RwaBalancesTransHistory>> data(@ModelAttribute ReqRwaBalancesTransHistoryPagination pagin) throws BusinessException
    {
        RwaBalancesTransHistory rwaBalancesTransHistoryQuery = new RwaBalancesTransHistory();
        BeanUtils.copyProperties(pagin, rwaBalancesTransHistoryQuery);
        rwaBalancesTransHistoryQuery.setUserId(OnLineUserUtils.getPrincipal().getId());
        //
        PaginateResult<RwaBalancesTransHistory> result = rwaBalancesTransHistoryService.search(pagin, rwaBalancesTransHistoryQuery);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy")
    @ApiOperation(value = "根据ID取RWA账户交易历史", httpMethod = "GET")
    public JsonMessage<RwaBalancesTransHistory> findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, rwaBalancesTransHistoryService.selectByPrimaryKey(id));
    }
}
