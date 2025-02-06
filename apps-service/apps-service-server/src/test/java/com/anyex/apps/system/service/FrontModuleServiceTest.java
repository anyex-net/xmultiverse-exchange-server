//package com.anyex.apps.system.service;
//
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.system.entity.FrontModule;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.Assert;
//
//import java.util.List;
//
//@Slf4j
//class FrontModuleServiceTest extends BaseServiceImplTest {
//
//    @Autowired
//    private FrontModuleService frontModuleService;
//
//    @Test
//    public void findByRoleIdTest(){
//        List<FrontModule> frontModuleList = frontModuleService.findByRoleId(200000000001L);
//        Assert.isTrue(frontModuleList!=null&&frontModuleList.size()>0,"根据角色id查询前端功能失败");
//        log.info("根据角色获取前端功能测试通过，获取结果{}",frontModuleList);
//    }
//
//    @Test
//    public void queryAndSave(){
//        FrontModule frontModuleQuery = new FrontModule();
//        frontModuleQuery.setRemark("日常维修");
//        List<FrontModule> frontModuleList = frontModuleService.findList(frontModuleQuery);
//        log.info("根据角色获取前端功能测试通过，获取结果{}",frontModuleList);
//        frontModuleService.updateBatch(frontModuleList);
//        System.out.println("updateBatch:" + frontModuleQuery.toString());
//    }
//
//}
