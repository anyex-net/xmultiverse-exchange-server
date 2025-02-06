/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.system;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.system.req.ReqSysRoleInfo;
import com.anyex.apps.controller.system.req.ReqSysRoleInfoPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.system.entity.SysResources;
import com.anyex.apps.system.entity.SysRoleInfo;
import com.anyex.apps.system.service.SysResourcesService;
import com.anyex.apps.system.service.SysRoleInfoService;
import com.anyex.apps.utils.*;
import com.google.common.collect.Lists;
import com.anyex.apps.shiro.model.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 角色信息表 控制器
 * <p>File：RoleInfoController.java </p>
 * <p>Title: RoleInfoController </p>
 * <p>Description:RoleInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.SYSTEM)
@Api(tags = "角色管理")
public class SysRoleInfoController extends GenericController
{
    @Autowired(required = false)
    private SysRoleInfoService roleInfoService;
    
    @Autowired(required = false)
    private SysResourcesService resourcesService;
    
    @GetMapping(value = "/role/findBy")
    @RequiresPermissions("system:role:data")
    @ApiOperation(value = "根据ID取角色信息", httpMethod = "GET")
    public JsonMessage<SysRoleInfo> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, roleInfoService.selectByPrimaryKey(id));
    }
    
    @GetMapping(value = "/role/findAll")
    @RequiresPermissions("system:role:data")
    @ApiOperation(value = "获取所有角色信息", httpMethod = "GET")
    public JsonMessage<List<SysRoleInfo>> findAll() throws BusinessException
    {
        return this.getJsonMessage(CommonEnums.SUCCESS, roleInfoService.selectAll());
    }
    
    @RequiresPermissions("system:role:operator")
    @ApiOperation(value = "保存或更新角色信息", httpMethod = "POST")
    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    public JsonMessage save(@ModelAttribute ReqSysRoleInfo info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        //
        SysRoleInfo sysRoleInfo = new SysRoleInfo();
        BeanUtils.copyProperties(info, sysRoleInfo);
        //
        long currentDate = CalendarUtils.getCurrentLong();
        if (null == sysRoleInfo.getId())
        {
            sysRoleInfo.setCreateBy(principal.getId());
            sysRoleInfo.setCreateDate(currentDate);
        }
        sysRoleInfo.setUpdateBy(principal.getId());
        sysRoleInfo.setUpdateDate(currentDate);
        log.info("sysRoleInfo:{}", sysRoleInfo);
        roleInfoService.save(sysRoleInfo);
        return json;
    }
    
    @RequiresPermissions("system:role:operator")
    @ApiOperation(value = "保存角色授权信息(resourceIds逗号分隔)", httpMethod = "POST")
    @RequestMapping(value = "/role/saveGrant", method = RequestMethod.POST)
    public JsonMessage saveGrant(@RequestParam("id") Long id, @RequestParam("resourceIds") String resourceIds) throws BusinessException
    {
        if (null == id || StringUtils.isBlank(resourceIds))
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        roleInfoService.saveGrant(id, resourceIds);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

//    @RequiresPermissions("system:role:operator")
//    @ApiOperation(value = "保存角色数据信息", httpMethod = "POST")
//    @RequestMapping(value = "/role/saveRoleData", method = RequestMethod.POST)
//    public JsonMessage saveRoleData(Long roleId, String orgIds) throws BusinessException
//    {
//        if (null == roleId || StringUtils.isBlank(orgIds))
//        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
//        roleInfoService.saveRoleData(roleId, orgIds);
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
    
    @RequiresPermissions("system:role:data")
    @ApiOperation(value = "查询角色信息", httpMethod = "POST")
    @RequestMapping(value = "/role/data", method = RequestMethod.POST)
    public JsonMessage<PaginateResult<SysRoleInfo>> data(@Validated @ModelAttribute ReqSysRoleInfoPagination reqSysRoleInfoPagination) throws BusinessException
    {
        //
        SysRoleInfo sysRoleInfo = new SysRoleInfo();
        BeanUtils.copyProperties(reqSysRoleInfoPagination, sysRoleInfo);
        //
        PaginateResult<SysRoleInfo> result = roleInfoService.search(reqSysRoleInfoPagination, sysRoleInfo);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping("/role/exportExcel")
    @RequiresPermissions("system:role:data")
    @ApiOperation(value = "导出Excel",httpMethod = "GET")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws BusinessException, IOException
    {
        log.info("exportExcel");
        ExportExcel excel = new ExportExcel("角色信息", SysRoleInfo.class);
        SysRoleInfo roleInfo = new SysRoleInfo();
        List<SysRoleInfo> list = roleInfoService.findList(roleInfo);
        log.info("list:{}", list);
        // list = new ArrayList<RoleInfo>();
        excel.setDataList(list);
        excel.write(response, "角色信息.xls");

       // PoiUtil.exportExcelWithStream("角色信息.xls", SysRoleInfo.class, list);
//        exportExcelWithStream(request, response, "角色信息.xls", SysRoleInfo.class, list);
    }

    @RequestMapping("/role/importExcel")
    @RequiresPermissions("system:role:data")
    @ApiOperation(value = "导入Excel",httpMethod = "POST")
    public void importExcel(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request, HttpServletResponse response) throws BusinessException, IOException
    {
        log.info("uploadFile:{}", uploadFile);
        String fileName = uploadFile.getOriginalFilename();
        try
        {
            ImportExcel importExcel = new ImportExcel(fileName, uploadFile.getInputStream(), 0, 0);
            //Map<String, RoleInfo> withdrawMap = Maps.newHashMap();
            List<SysRoleInfo> list = Lists.newArrayList();
            String[] titleArry = {"roleCode", "roleName"};
            for (int i = importExcel.getDataRowNum(); i <= importExcel.getLastDataRowNum(); i++)
            {
                SysRoleInfo roleInfo = new SysRoleInfo();
                try
                {
                    for (int j = 0; j < importExcel.getLastCellNum(); j++) {
                        log.info("importExcel.getCellValue(importExcel.getRow(i), j):{}", importExcel.getCellValue(importExcel.getRow(i), j));
                        setObjValue(roleInfo, titleArry[j], importExcel.getCellValue(importExcel.getRow(i), j));
                    }
                    //
                    list.add(list.size()-1, roleInfo);
                } catch (Exception e) {
                    continue;
                }
            }

            //
            log.info("list:{}", list);
            roleInfoService.insertBatch(list);
        }
        catch (Exception e)
        {
            log.error("记录导入失败:" + e.getMessage());
            throw new BusinessException("记录导入失败:" + e.getMessage());
        }
    }
    
    @RequiresPermissions("system:role:operator")
    @ApiOperation(value = "根据指定ID删除(逗号分隔)", httpMethod = "POST")
    @RequestMapping(value = "/role/del", method = RequestMethod.POST)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        roleInfoService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @GetMapping(value = "/role/findByRoleId")
    @RequiresPermissions("system:role:data")
    @ApiOperation(value = "获取角色授权信息", httpMethod = "GET")
    public JsonMessage<List<SysResources> > findByRoleId(@RequestParam("roleId") Long roleId) throws BusinessException
    {
        List<SysResources> data = resourcesService.findByRoleId(roleId);
        return getJsonMessage(CommonEnums.SUCCESS, data);
    }

    private void setObjValue(Object obj, String titleName, Object value) throws NoSuchFieldException, IllegalAccessException {
        try
        {
            if (null != value)
            {
                Field f = obj.getClass().getDeclaredField(titleName);
                f.setAccessible(true);
                Class<?> classType = f.getType();
                if (classType.equals(BigDecimal.class))
                {
                    f.set(obj, new BigDecimal(value.toString()));
                }
                else if (classType.equals(Date.class))
                {
                    f.set(obj, CalendarUtils.getDate(Long.parseLong(value.toString())));
                }
                else
                {
                    f.set(obj, value);
                }
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

}
