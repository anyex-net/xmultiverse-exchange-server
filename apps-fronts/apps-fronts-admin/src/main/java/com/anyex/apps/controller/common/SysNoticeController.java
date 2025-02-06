package com.anyex.apps.controller.common;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysNotice;
import com.anyex.apps.common.service.SysNoticeService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.controller.common.req.ReqSysNotice;
import com.anyex.apps.controller.common.req.ReqSysNoticePagination;
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

import java.util.List;

/**
 * 平台公告表 控制器
 * <p>File：NoticeController.java </p>
 * <p>Title: NoticeController </p>
 * <p>Description:NoticeController </p>
 * <p>Copyright: Copyright (c) May 26, 2021 </p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(tags = "平台公告")
public class SysNoticeController extends GenericController
{
    @Autowired(required = false)
    private SysNoticeService noticeService;

    @GetMapping(value = "/notice/findBy")
    @RequiresPermissions("common:notice:data")
    @ApiOperation(value = "根据ID取平台公告", httpMethod = "GET")
    public JsonMessage<SysNotice> findBy(@RequestParam("id") Long id) throws BusinessException
    {
        if (null == id) throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        return this.getJsonMessage(CommonEnums.SUCCESS, noticeService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/notice/data")
    @RequiresPermissions("common:notice:data")
    @ApiOperation(value = "查询平台公告", httpMethod = "POST")
    public JsonMessage<PaginateResult<SysNotice>> data(@Validated @ModelAttribute ReqSysNoticePagination reqSysNoticePagination) throws BusinessException
    {
        //
        SysNotice sysNotice = new SysNotice();
        BeanUtils.copyProperties(reqSysNoticePagination, sysNotice);
        //
        PaginateResult<SysNotice> result = noticeService.search(reqSysNoticePagination, sysNotice);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/notice/save")
    @RequiresPermissions("common:notice:operator")
    @ApiOperation(value = "保存平台公告", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ReqSysNotice info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            UserPrincipal principal = OnLineUserUtils.getPrincipal();
            //
            SysNotice sysNotice = new SysNotice();
            BeanUtils.copyProperties(info, sysNotice);
            //
            if (null == info.getId())
            {
                sysNotice.setCreateBy(principal.getId());
                sysNotice.setCreateDate(System.currentTimeMillis());
            }
            if (info.getStatus())
            {
                sysNotice.setPublishBy(principal.getId());
                sysNotice.setPublishDate(System.currentTimeMillis());
            }
            sysNotice.setUpdateBy(principal.getId());
            sysNotice.setUpdateDate(System.currentTimeMillis());
            //
            log.info("sysNotice:{}", sysNotice);
            noticeService.save(sysNotice);
        }
        return json;
    }

    @PostMapping(value = "/notice/updateStatus")
    @RequiresPermissions("common:notice:operator")
    @ApiOperation(value = "更新平台公告状态", httpMethod = "POST")
    public JsonMessage updateStatus(@RequestParam("id") Long id, @RequestParam("status") Boolean status) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        SysNotice notice = noticeService.selectByPrimaryKey(id);
        if (status) {
            notice.setStatus(Boolean.TRUE);
            UserPrincipal principal = OnLineUserUtils.getPrincipal();
            notice.setPublishBy(principal.getId());
            notice.setPublishDate(System.currentTimeMillis());
        } else {
            notice.setStatus(Boolean.FALSE);
        }
        //
        log.info("notice:{}", notice);
        noticeService.updateByPrimaryKeySelective(notice);
        return json;
    }

    @PostMapping(value = "/notice/del")
    @RequiresPermissions("common:notice:operator")
    @ApiOperation(value = "根据指定ID删除(逗号分隔)", httpMethod = "POST")
    public JsonMessage del(@RequestParam("ids") String ids) throws BusinessException
    {
        noticeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
