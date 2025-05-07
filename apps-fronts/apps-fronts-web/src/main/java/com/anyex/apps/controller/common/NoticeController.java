/*
 * Copyright 2021 AnyEx, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.common;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysNotice;
import com.anyex.apps.common.service.SysNoticeService;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 平台公告 控制器
 * <p>File：NoticeController.java </p>
 * <p>Title: NoticeController </p>
 * <p>Description:NoticeController </p>
 * <p>Copyright: Copyright (c) May 26, 2021 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/common/notice")
@Api(tags = "平台公告")
public class NoticeController extends GenericController
{
    @Autowired(required = false)
    private SysNoticeService noticeService;

    @GetMapping(value = "/data")
    @ApiOperation(value = "查询平台公告", httpMethod = "GET")
    public JsonMessage data() throws BusinessException
    {
        SysNotice notice = new SysNotice();
        notice.setStatus(true); // 1已发布
        List<SysNotice> result = noticeService.findList(notice);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findBy/{id}", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "根据ID取平台公告", httpMethod = "GET")
    public JsonMessage findBy(@PathVariable Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, noticeService.selectByPrimaryKey(id));
    }
}
