package com.anyex.apps.controller.common;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysFeedBack;
import com.anyex.apps.common.service.SysFeedBackService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 意见反馈 控制器
 * <p>File：FeedBackController.java </p>
 * <p>Title: FeedBackController </p>
 * <p>Description:FeedBackController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
//@RestController
//@RequestMapping(GlobalConst.COMMON)
//@Api(tags = "意见反馈")
public class SysFeedBackController extends GenericController
{
    @Autowired(required = false)
    private SysFeedBackService feedBackService;

    @GetMapping(value = "/feedBack/findBy")
    @RequiresPermissions("common:feedBack:data")
    @ApiOperation(value = "根据ID取FeedBack", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, feedBackService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/feedBack/save")
    @RequiresPermissions("common:feedBack:operator")
    @ApiOperation(value = "保存FeedBack", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute SysFeedBack info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            feedBackService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/feedBack/data")
    @RequiresPermissions("common:feedBack:data")
    @ApiOperation(value = "查询FeedBack", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute SysFeedBack entity, @Validated @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<SysFeedBack> result = feedBackService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/feedBack/del")
    @RequiresPermissions("common:feedBack:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        feedBackService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
