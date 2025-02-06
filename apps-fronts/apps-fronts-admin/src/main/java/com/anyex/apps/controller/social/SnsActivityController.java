
package com.anyex.apps.controller.social;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.anyex.apps.social.entity.SnsActivity;
import com.anyex.apps.social.service.SnsActivityService;

import com.anyex.apps.controller.social.req.ReqSnsActivity;
import com.anyex.apps.controller.social.req.ReqSnsActivityPagination;
import java.util.Date;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * 社交活动 控制器
 * <p>File：SnsActivityController.java </p>
 * <p>Title: SnsActivityController </p>
 * <p>Description:SnsActivityController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/social/snsactivity")
@Api(description = "社交活动")
public class SnsActivityController extends GenericController
{
    @Autowired(required = false)
    private SnsActivityService snsactivityService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("social:snsactivity:data")
    @ApiOperation(value = "根据ID取社交活动", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, snsactivityService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("social:snsactivity:operator")
    @ApiOperation(value = "保存社交活动", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqSnsActivity info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            SnsActivity entity = new SnsActivity();
            BeanUtils.copyProperties(info, entity);
            if (null == info.getId())
            {
                entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                    snsactivityService.insert(entity);
            } else {
                    snsactivityService.updateByPrimaryKeySelective(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("social:snsactivity:data")
    @ApiOperation(value = "查询社交活动", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ReqSnsActivityPagination pagin) throws BusinessException
    {
        SnsActivity entity = new SnsActivity();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<SnsActivity> result = snsactivityService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("social:snsactivity:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        snsactivityService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
