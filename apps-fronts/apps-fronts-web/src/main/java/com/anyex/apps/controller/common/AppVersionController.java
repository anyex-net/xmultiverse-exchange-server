package com.anyex.apps.controller.common;

import com.anyex.apps.bean.GenericController;
import com.anyex.apps.common.entity.SysAppVersion;
import com.anyex.apps.controller.common.req.ReqAppVersion;
import com.anyex.apps.common.service.SysAppVersionService;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.JsonMessage;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * AppVersionController
 * <p>File：AppVersionController.java</p>
 * <p>Title: AppVersionController</p>
 * <p>Description: AppVersionController</p>
 * <p>Copyright: Copyright (c) 2019/10/23</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(tags = "app版本控制")
public class AppVersionController extends GenericController
{
    @Autowired(required = false)
    private SysAppVersionService appVersionService;

    //@PostMapping(value = "/appversion")
    //@ApiOperation(value = "获取app版本号", httpMethod = "POST")
    public JsonMessage appVersion(@RequestBody ReqAppVersion reqAppVersion) throws BusinessException
    {
        Map<String, String> map = Maps.newHashMap();
        map.put("forceUpdate", "0");
        //
        SysAppVersion appVersionDb = new SysAppVersion();
        appVersionDb.setDeviceType(reqAppVersion.getDeviceType());
        appVersionDb.setCanSupport(true);
        List<SysAppVersion> appVersions = appVersionService.findList(appVersionDb);
        appVersions.sort(Comparator.comparing(SysAppVersion::getAppVersion));
        // 当前版本不是最新版本，建议升级
        if(appVersions.get(appVersions.size()-1).getAppVersion().compareTo(reqAppVersion.getAppVersion()) > 0)
            map.put("forceUpdate", "-1");

        // 将当前版本与设备可支持版本相比较，当前版本不支持强制升级
        if(appVersions.stream().anyMatch(a -> a.getAppVersion().equals(reqAppVersion.getAppVersion())))
            map.put("forceUpdate", "1");
        log.info("appVersion map:{}", map);
        //
        return getJsonMessage(CommonEnums.SUCCESS, map);
    }

    @GetMapping(value = "/getAndroidAppVersion")
    @ApiOperation(value = "获取Android对应App最低可用版本号", httpMethod = "GET")
    public JsonMessage<SysAppVersion> getAndroidAppVersion() throws BusinessException
    {
        //
        SysAppVersion appVersionDb = new SysAppVersion();
        appVersionDb.setDeviceType("android");
        appVersionDb.setCanSupport(true);
        List<SysAppVersion> appVersions = appVersionService.findList(appVersionDb);
        //
        return getJsonMessage(CommonEnums.SUCCESS, appVersions.get(0));
    }
}
