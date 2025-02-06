package com.anyex.apps.controller.common;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysMsgRecord;
import com.anyex.apps.common.service.SysMsgRecordService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.common.req.ReqSysMsgRecordPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

/**
 * 消息发送记录 介绍
 * <p>File：MsgRecordController.java </p>
 * <p>Title: MsgRecordController </p>
 * <p>Description:MsgRecordController </p>
 * <p>Copyright: Copyright (c) 2017/7/20 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(tags = "消息发送记录")
public class SysMsgRecordController extends GenericController
{
    @Autowired(required = false)
    private SysMsgRecordService msgRecordService;

    @PostMapping(value = "/msgRecord/data")
    @RequiresPermissions("common:msgRecord:data")
    @ApiOperation(value = "查询消息记录列表", httpMethod = "POST")
    public JsonMessage<PaginateResult<SysMsgRecord>> data(@Validated @ModelAttribute ReqSysMsgRecordPagination reqSysMsgRecordPagination) throws BusinessException
    {
        //
        SysMsgRecord sysMsgRecord = new SysMsgRecord();
        BeanUtils.copyProperties(reqSysMsgRecordPagination, sysMsgRecord);
        //
        PaginateResult<SysMsgRecord> result = msgRecordService.search(reqSysMsgRecordPagination, sysMsgRecord);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/msgRecord/findBy")
    @RequiresPermissions("common:msgRecord:data")
    @ApiOperation(value = "根据ID取消息记录", httpMethod = "GET")
    public JsonMessage<SysMsgRecord> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, msgRecordService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/msgRecord/del")
    @RequiresPermissions("common:msgRecord:operator")
    @ApiOperation(value = "根据指定ID删除(逗号分隔)", httpMethod = "POST")
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        msgRecordService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
