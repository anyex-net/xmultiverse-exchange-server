package com.anyex.apps.controller.common;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysAppVersion;
import com.anyex.apps.common.service.SysAppVersionService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.common.req.ReqSysAppVersion;
import com.anyex.apps.controller.common.req.ReqSysAppVersionPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.utils.OnLineUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * app版本 控制器
 * <p>File：AppVersionController.java </p>
 * <p>Title: AppVersionController </p>
 * <p>Description:AppVersionController </p>
 * <p>Copyright: Copyright (c) May 26, 2018 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(tags = "app版本控制")
public class SysAppVersionController extends GenericController
{
    @Autowired(required = false)
    private SysAppVersionService appVersionService;

    @GetMapping(value = "/appVersion/findBy")
    @RequiresPermissions("common:appVersion:data")
    @ApiOperation(value = "根据ID取AppVersion", httpMethod = "GET")
    public JsonMessage<SysAppVersion> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, appVersionService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/appVersion/save")
    @RequiresPermissions("common:appVersion:operator")
    @ApiOperation(value = "保存AppVersion", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqSysAppVersion info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            SysAppVersion sysAppVersion = new SysAppVersion();
            BeanUtils.copyProperties(info, sysAppVersion);
            //
            sysAppVersion.setCreateBy(OnLineUserUtils.getId());
            sysAppVersion.setCreateDate(System.currentTimeMillis());
            //
            log.info("sysAppVersion:{}", sysAppVersion);
            if(null == sysAppVersion.getId()){
                appVersionService.insert(sysAppVersion);
            } else {
                appVersionService.updateByPrimaryKey(sysAppVersion);
            }
        }
        return json;
    }

    @PostMapping(value = "/appVersion/data")
    @RequiresPermissions("common:appVersion:data")
    @ApiOperation(value = "查询AppVersion", httpMethod = "POST")
    public JsonMessage<PaginateResult<SysAppVersion>> data(@Validated @ModelAttribute ReqSysAppVersionPagination reqSysAppVersionPagination) throws BusinessException
    {
        //
        SysAppVersion sysAppVersion = new SysAppVersion();
        BeanUtils.copyProperties(reqSysAppVersionPagination, sysAppVersion);
        //
        PaginateResult<SysAppVersion> result = appVersionService.search(reqSysAppVersionPagination, sysAppVersion);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/appVersion/del")
    @RequiresPermissions("common:appVersion:operator")
    @ApiOperation(value = "根据指定ID删除(逗号分隔)", httpMethod = "POST")
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        appVersionService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
