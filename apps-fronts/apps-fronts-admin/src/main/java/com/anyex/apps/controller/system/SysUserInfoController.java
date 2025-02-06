/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.system;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.config.GlobalProperies;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.system.req.ReqSysUserInfo;
import com.anyex.apps.controller.system.req.ReqSysUserInfoPagination;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.utils.StringUtils;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.system.entity.SysUserInfo;
import com.anyex.apps.system.service.SysUserInfoService;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息表 控制器
 * <p>File：UserInfoController.java </p>
 * <p>Title: UserInfoController </p>
 * <p>Description:UserInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.SYSTEM)
@Api(tags = "用户管理")
public class SysUserInfoController extends GenericController
{
    @Autowired(required = false)
    private SysUserInfoService userInfoService;

    @Autowired
    private GlobalProperies properies;
    
    @GetMapping(value = "/user/findBy")
    @RequiresPermissions("system:user:data")
    @ApiOperation(value = "根据ID取用户信息", httpMethod = "GET")
    public JsonMessage<SysUserInfo> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        SysUserInfo userInfo = userInfoService.selectByPrimaryKey(id);
        return this.getJsonMessage(CommonEnums.SUCCESS, userInfo);
    }

    @RequiresPermissions("system:user:operator")
    @ApiOperation(value = "新增或更新用户信息", httpMethod = "POST")
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public JsonMessage save(@ModelAttribute ReqSysUserInfo info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        //
        SysUserInfo sysUserInfo = new SysUserInfo();
        BeanUtils.copyProperties(info, sysUserInfo);
        //
        if (null == info.getId())
        {
            sysUserInfo.setCreateDate(System.currentTimeMillis());
        }
        sysUserInfo.setUpdateDate(System.currentTimeMillis());
        log.info("sysUserInfo:{}", sysUserInfo);
        userInfoService.save(sysUserInfo);
        return json;
    }

//    @RequiresPermissions("system:user:operator")
//    @ApiOperation(value = "保存用户数据信息", httpMethod = "POST")
//    @RequestMapping(value = "/user/saveUserData", method = RequestMethod.POST)
//    public JsonMessage saveUserData(Long userId, String orgIds) throws BusinessException
//    {
//        if (null == userId || StringUtils.isBlank(orgIds))
//        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
//        userInfoService.saveUserData(userId, orgIds);
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }

    @RequiresPermissions("system:user:operator")
    @ApiOperation(value = "修改用户登录密码", httpMethod = "POST")
    @RequestMapping(value = "/user/changePwd", method = RequestMethod.POST)
    public JsonMessage changePwd(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd))
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        userInfoService.changePassword(principal.getId(), oldPwd, newPwd);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @RequiresPermissions("system:user:operator")
    @ApiOperation(value = "重置用户登录密码(默认)", httpMethod = "POST")
    @RequestMapping(value = "/user/resetDefaultPwd", method = RequestMethod.POST)
    public JsonMessage resetDefaultPwd(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        userInfoService.resetPassword(id, properies.getPassWord());
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @RequiresPermissions("system:user:operator")
    @ApiOperation(value = "重置用户登录密码(新密码)", httpMethod = "POST")
    @RequestMapping(value = "/user/resetNewPwd", method = RequestMethod.POST)
    public JsonMessage resetNewPwd(@RequestParam("id") Long id, @RequestParam("newPwd") String newPwd) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID); }
        userInfoService.resetPassword(id, newPwd);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @RequiresPermissions("system:user:data")
    @ApiOperation(value = "查询用户信息列表", httpMethod = "POST")
    @RequestMapping(value = "/user/data", method = RequestMethod.POST)
    public JsonMessage<PaginateResult<SysUserInfo>> data(@Validated @ModelAttribute ReqSysUserInfoPagination reqSysUserInfoPagination) throws BusinessException
    {
        //
        SysUserInfo sysUserInfo = new SysUserInfo();
        BeanUtils.copyProperties(reqSysUserInfoPagination, sysUserInfo);
        //
        PaginateResult<SysUserInfo> result = userInfoService.search(reqSysUserInfoPagination, sysUserInfo);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @RequiresPermissions("system:user:operator")
    @ApiOperation(value = "根据指定ID删除(逗号分隔)", httpMethod = "POST")
    @RequestMapping(value = "/user/del", method = RequestMethod.POST)
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        userInfoService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @PostMapping(value = "/user/changeStatus")
    @RequiresPermissions("system:user:operator")
    @ApiOperation(value = "启用或停用用户", httpMethod = "POST")
    public JsonMessage changeStatus(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException("操作编号不可为空！"); }
        SysUserInfo userInfo = userInfoService.selectByPrimaryKey(id);
        userInfo.setActive(!userInfo.getActive());
        userInfoService.updateByPrimaryKey(userInfo);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

//    @PostMapping(value = "/user/list")
//    @RequiresPermissions("system:user:operator")
//    @ApiOperation(value = "机构下所有账户", httpMethod = "POST")
//    public JsonMessage<List<SysUserInfo>> list(@ModelAttribute SysUserInfo entity, @ModelAttribute Pagination pagin) throws BusinessException
//    {
//        entity.setOrderBy("a.orgId DESC");
//        PaginateResult<SysUserInfo> result = userInfoService.search(pagin,entity);
//        return getJsonMessage(CommonEnums.SUCCESS,result);
//    }
}
