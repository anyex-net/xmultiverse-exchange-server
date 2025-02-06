<#include "/macro.include"/>
<#include "/copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${subpackage}.controller;

import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ${basepackage}.${subpackage}.entity.${className};
import ${basepackage}.${subpackage}.service.${className}Service;

import ${basepackage}.controller.${subpackage}.req.Req${className};
import ${basepackage}.controller.${subpackage}.req.Req${className}Pagination;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;

/**
 * ${table.sqlRemark} 控制器
 * <p>File：${className}Controller.java </p>
 * <p>Title: ${className}Controller </p>
 * <p>Description:${className}Controller </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/${subpackage}/${classNameLower}")
@Api(description = "${table.sqlRemark}")
public class ${className}Controller extends GenericController
{
    @Autowired(required = false)
    private ${className}Service ${classNameLower}Service;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("${subpackage}:${classNameLower}:data")
    @ApiOperation(value = "根据ID取${table.sqlRemark}", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, ${classNameLower}Service.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("${subpackage}:${classNameLower}:operator")
    @ApiOperation(value = "保存${table.sqlRemark}", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute Req${className} info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            ${className} entity = new ${className}();
            BeanUtils.copyProperties(info, entity);
            //
            if (null == info.getId())
            {
            entity.setCreateTime(System.currentTimeMillis());
            }
            entity.setUpdateTime(System.currentTimeMillis());
            //
            log.info("entity:{}", entity);
            if(null == entity.getId()){
                ${classNameLower}Service.insert(entity);
            } else {
                ${classNameLower}Service.updateByPrimaryKey(entity);
            }
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("${subpackage}:${classNameLower}:data")
    @ApiOperation(value = "查询${table.sqlRemark}", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute Req${className}Pagination pagin) throws BusinessException
    {
        ${className} entity = new ${className}();
        BeanUtils.copyProperties(pagin, entity);
        PaginateResult<${className}> result = ${classNameLower}Service.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("${subpackage}:${classNameLower}:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        ${classNameLower}Service.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
