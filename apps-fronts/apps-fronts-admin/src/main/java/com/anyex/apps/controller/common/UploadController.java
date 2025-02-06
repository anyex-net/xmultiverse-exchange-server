package com.anyex.apps.controller.common;

import java.util.Map;

import com.anyex.apps.bean.AliyunOSS;
import com.anyex.apps.bean.GenericController;
import com.anyex.apps.config.GlobalProperies;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.anyex.apps.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 公共文件上传控制器
 * <p>File：UploadController.java</p>
 * <p>Title: UploadController</p>
 * <p>Description: UploadController</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(tags = "文件上传下载")
public class UploadController extends GenericController
{
    @Autowired
    private GlobalProperies properies;

    @Autowired
    private AliyunOSS aliyunOSS;

    @GetMapping("/upload/policy")
    @ApiOperation(value = "取正式环境文件上传策略", httpMethod = "GET")
    @ApiImplicitParam(name = "dir", value = "上传目录", paramType = "form")
    public JsonMessage getPostPolicy(String dir) throws BusinessException
    {
        if (StringUtils.isBlank(dir)) dir = "";
        Map<String, Object> policy = aliyunOSS.getPostPolicy(dir, properies.getAliyun().getBucket().getReleaseUrl());
        return getJsonMessage(CommonEnums.SUCCESS, policy);
    }

//    @PostMapping(value = "/upload/transfer")
//    @ApiOperation(value = "转移临时空间中的文件到正式空间", httpMethod = "POST")
//    @ApiImplicitParam(name = "fileName", value = "文件名", required = true, paramType = "form")
//    public JsonMessage transferObject(String fileName) throws BusinessException
//    {
//        aliyunOSS.transferObject(fileName);
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
//
//    @PostMapping(value = "/upload/delete")
//    @ApiOperation(value = "删除临时空间中的文件", httpMethod = "POST")
//    @ApiImplicitParam(name = "fileName", value = "文件名", required = true, paramType = "form")
//    public JsonMessage deleteObject(String fileName) throws BusinessException
//    {
//        aliyunOSS.deleteObject(fileName);
//        return getJsonMessage(CommonEnums.SUCCESS);
//    }
}
