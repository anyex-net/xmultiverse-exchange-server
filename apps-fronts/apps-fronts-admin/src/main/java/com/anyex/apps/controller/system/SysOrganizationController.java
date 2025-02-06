/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.controller.system;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.system.req.ReqSysOrganization;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.CalendarUtils;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.system.entity.SysOrganization;
import com.anyex.apps.system.service.SysOrganizationService;
import com.anyex.apps.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构信息表 控制器
 * <p>File：OrganizationController.java </p>
 * <p>Title: OrganizationController </p>
 * <p>Description:OrganizationController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.SYSTEM)
@Api(tags = "组织机构管理")
public class SysOrganizationController extends GenericController {
    @Autowired(required = false)
    private SysOrganizationService organizationService;

    @GetMapping(value = "/organization/findBy")
    @RequiresPermissions("system:organization:data")
    @ApiOperation(value = "根据ID取机构信息", httpMethod = "GET")
    public JsonMessage<SysOrganization> findBy(@RequestParam("id") Long id) throws BusinessException {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, organizationService.selectByPrimaryKey(id));
    }

    @GetMapping(value = "/organization/tree")
    @RequiresPermissions("system:organization:data")
    @ApiOperation(value = "返回以TREEMODEL对象的所有数据", httpMethod = "GET")
    public JsonMessage<List<SysOrganization>> tree() throws BusinessException {
        return getJsonMessage(CommonEnums.SUCCESS, organizationService.treeData());
    }

    @RequiresPermissions("system:organization:operator")
    @ApiOperation(value = "保存或更新组织信息", httpMethod = "POST")
    @RequestMapping(value = "/organization/save", method = RequestMethod.POST)
    public JsonMessage save(@ModelAttribute ReqSysOrganization info) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        //
        SysOrganization sysOrganization = new SysOrganization();
        BeanUtils.copyProperties(info, sysOrganization);
        //
        long currentDate = CalendarUtils.getCurrentLong();
        if (null == sysOrganization.getId()) {
            sysOrganization.setCreateDate(currentDate);
            sysOrganization.setCreateBy(principal.getId());
        }
        sysOrganization.setUpdateBy(principal.getId());
        sysOrganization.setUpdateDate(currentDate);
        log.info("sysOrganization:{}", sysOrganization);
        organizationService.save(sysOrganization);
        return json;
    }

    @RequiresPermissions("system:organization:data")
    @ApiOperation(value = "查询组织信息", httpMethod = "POST")
    @RequestMapping(value = "/organization/data", method = RequestMethod.POST)
    public JsonMessage<List<SysOrganization>> data(Long id) throws BusinessException {
        log.info("id:{}", id);
        return getJsonMessage(CommonEnums.SUCCESS, organizationService.findList(new SysOrganization(id)));
    }

    @RequiresPermissions("system:organization:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @RequestMapping(value = "/organization/del", method = RequestMethod.POST)
    public JsonMessage del(@RequestParam("id") Long id) throws BusinessException {
        SysOrganization organization = new SysOrganization();
        organization.setParentId(id);
        List<SysOrganization> organizationList = organizationService.findList(organization);
        if (organizationList == null || organizationList.size() == 0) {
            organizationService.remove(id);
            return this.getJsonMessage(CommonEnums.SUCCESS);
        } else {
            log.error("存在子节点，当前节点无法删除!");
            return this.getJsonMessage(CommonEnums.ERROR_EXIST_SUBNODE);
        }
    }
}
