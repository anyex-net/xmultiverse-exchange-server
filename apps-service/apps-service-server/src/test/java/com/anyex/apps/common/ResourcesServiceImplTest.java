package com.anyex.apps.common;

import com.anyex.apps.BaseServiceImplTest;
import com.anyex.apps.common.entity.SysAppDevice;
import com.anyex.apps.common.service.SysAppDeviceService;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.system.entity.SysResources;
import com.anyex.apps.system.service.SysResourcesService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ResourcesServiceImplTest extends BaseServiceImplTest {

    @Autowired
    private SysResourcesService sysResourcesService;

    @Autowired
    private SysAppDeviceService sysAppDeviceService;

    @Test
    public void test() throws BusinessException {
        List<SysResources> resources = sysResourcesService.findByRoleId(200000000000L);
        System.out.println(resources);
    }

    @Test
    public void testSysAppDevice() throws BusinessException {
        Pagination pagination = new Pagination();
        SysAppDevice sysAppDevice = new SysAppDevice();
        PaginateResult<SysAppDevice> result = sysAppDeviceService.search(pagination, sysAppDevice);
        System.out.println(result);
    }
}
