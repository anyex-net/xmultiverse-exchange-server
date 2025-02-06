/*
 * Copyright 2024 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.openim;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.controller.openim.req.ReqLogPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.openim.service.OpenImApiService;
import com.anyex.openim.api.third.req.SearchLogsReq;
import com.anyex.openim.api.third.resp.LogInfoResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 日志查询
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/openim/log")
@Api(tags = "日志查询")
public class LogController extends GenericController
{

    @Autowired(required = false)
    private OpenImApiService openImApiService;

    @PostMapping(value = "/single/list")
    @RequiresPermissions("openim:log:data")
    @ApiOperation(value = "日志列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<LogInfoResp.LogInfo>> loglist(@ModelAttribute ReqLogPagination pagin) throws BusinessException
    {
        SearchLogsReq req = new SearchLogsReq();
        req.setKeyword(pagin.getKeyword());
        req.setStartTime(pagin.getStartTime());
        req.setEndTime(pagin.getEndTime());
        req.getPagination().setPageNumber(pagin.getCurrent());
        req.getPagination().setShowNumber(pagin.getSize());
        LogInfoResp resp =openImApiService.searchLog(req);
        pagin.setTotal(resp.getTotal()*1L);
        PaginateResult<LogInfoResp.LogInfo> result = new PaginateResult<LogInfoResp.LogInfo>(pagin, resp.getLogsInfos());
        return getJsonMessage(CommonEnums.SUCCESS,result);
    }

}
