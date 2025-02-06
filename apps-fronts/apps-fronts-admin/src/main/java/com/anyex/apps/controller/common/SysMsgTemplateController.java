package com.anyex.apps.controller.common;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysMsgTemplate;
import com.anyex.apps.common.service.SysMsgTemplateService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.common.req.ReqSysMsgTemplate;
import com.anyex.apps.controller.common.req.ReqSysMsgTemplatePagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.shiro.model.UserPrincipal;
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
 * 消息模版 控制器
 * <p>File：MsgTemplateController.java </p>
 * <p>Title: MsgTemplateController </p>
 * <p>Description:MsgTemplateController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(tags = "消息模版")
public class SysMsgTemplateController extends GenericController
{
    @Autowired(required = false)
    private SysMsgTemplateService templateService;
    
    @GetMapping(value = "/msgTemplate/findBy")
    @RequiresPermissions("common:msgTemplate:data")
    @ApiOperation(value = "根据ID取消息模版", httpMethod = "GET")
    public JsonMessage<SysMsgTemplate> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, templateService.selectByPrimaryKey(id));
    }
    
    @PostMapping(value = "/msgTemplate/save")
    @RequiresPermissions("common:msgTemplate:operator")
    @ApiOperation(value = "保存或更新消息模版", httpMethod = "POST")
    public JsonMessage save(@Validated @ModelAttribute ReqSysMsgTemplate info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            SysMsgTemplate sysMsgTemplate = new SysMsgTemplate();
            BeanUtils.copyProperties(info, sysMsgTemplate);
            //
            if (null == info.getId())
            {
                UserPrincipal principal = OnLineUserUtils.getPrincipal();
                sysMsgTemplate.setCreateBy(principal.getId());
                sysMsgTemplate.setCreateDate(System.currentTimeMillis());
            }
            //
            log.info("sysMsgTemplate:{}", sysMsgTemplate);
            if(null == sysMsgTemplate.getId()){
                templateService.insert(sysMsgTemplate);
            } else {
                templateService.updateByPrimaryKey(sysMsgTemplate);
            }
        }
        return json;
    }
    
    @PostMapping(value = "/msgTemplate/data")
    @RequiresPermissions("common:msgTemplate:data")
    @ApiOperation(value = "查询消息模版", httpMethod = "POST")
    public JsonMessage<PaginateResult<SysMsgTemplate>> data(@ModelAttribute ReqSysMsgTemplatePagination reqSysMsgTemplatePagination) throws BusinessException
    {
        //
        SysMsgTemplate sysMsgTemplate = new SysMsgTemplate();
        BeanUtils.copyProperties(reqSysMsgTemplatePagination, sysMsgTemplate);
        //
        PaginateResult<SysMsgTemplate> result = templateService.search(reqSysMsgTemplatePagination, sysMsgTemplate);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @PostMapping(value = "/msgTemplate/del")
    @RequiresPermissions("common:msgTemplate:operator")
    @ApiOperation(value = "根据指定ID删除(逗号分隔)", httpMethod = "POST")
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        templateService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
