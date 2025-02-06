package com.anyex.apps.controller.common;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysRegion;
import com.anyex.apps.common.service.SysRegionService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.common.req.ReqSysRegionPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 区域代码 控制器
 * <p>File：RegionController.java </p>
 * <p>Title: RegionController </p>
 * <p>Description:RegionController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(tags = "区域代码")
public class SysRegionController extends GenericController
{
    @Autowired(required = false)
    private SysRegionService regionService;

    @GetMapping(value = "/region/findBy")
    @RequiresPermissions("common:region:data")
    @ApiOperation(value = "根据ID取区域代码", httpMethod = "GET")
    public JsonMessage<SysRegion> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, regionService.selectByPrimaryKey(id));
    }

    @GetMapping(value = "/region/findAll")
    @RequiresPermissions("common:region:data")
    @ApiOperation(value = "查询全部区域代码数据", httpMethod = "GET")
    public JsonMessage<List<SysRegion>> findAll() throws BusinessException
    {
        return this.getJsonMessage(CommonEnums.SUCCESS, regionService.selectAll());
    }

//    @PostMapping(value = "/region/save")
//    @RequiresPermissions("common:region:operator")
//    @ApiOperation(value = "保存区域代码", httpMethod = "POST")
//    public JsonMessage save(@ModelAttribute SysRegion info) throws BusinessException
//    {
//        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
//        if (beanValidator(json, info))
//        {
//            regionService.save(info);
//        }
//        return json;
//    }

    @PostMapping(value = "/region/data")
    @RequiresPermissions("common:region:data")
    @ApiOperation(value = "查询区域代码", httpMethod = "POST")
    public JsonMessage<PaginateResult<SysRegion>> data(@Validated @ModelAttribute ReqSysRegionPagination reqSysRegionPagination) throws BusinessException
    {
        //
        SysRegion sysRegion = new SysRegion();
        BeanUtils.copyProperties(reqSysRegionPagination, sysRegion);
        //
        PaginateResult<SysRegion> result = regionService.search(reqSysRegionPagination, sysRegion);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/region/del")
    @RequiresPermissions("common:region:operator")
    @ApiOperation(value = "根据指定ID删除(逗号分隔)", httpMethod = "POST")
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        regionService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
