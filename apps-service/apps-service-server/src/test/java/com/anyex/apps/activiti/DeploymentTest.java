//package com.anyex.apps.activiti;
//
//import com.alibaba.fastjson.JSON;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.activiti.entity.ProcessDefinitionEntity;
//import com.anyex.apps.activiti.entity.ProcessDeploymentEntity;
//import com.anyex.apps.activiti.entity.ProcessInstanceEntity;
//import com.anyex.apps.activiti.model.ProcessDefinitionModelQuery;
//import com.anyex.apps.activiti.model.ProcessDeploymentModelQuery;
//import com.anyex.apps.activiti.model.ProcessInstanceModelQuery;
//import com.anyex.apps.activiti.service.CommonActivitiService;
//import com.anyex.apps.model.PaginateResult;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * 部署测试
// * note: 1.记录变更 by WangXiao
// *
// * @author WangXiao
// * @date 2023/02/24 10:18
// **/
//@Slf4j
//public class DeploymentTest extends BaseServiceImplTest {
//    @Autowired
//    private CommonActivitiService commonActivitiService;
//
//    /**
//     * 部署列表(ACT_RE_DEPLOYMENT)
//     *
//     * @author wangxiao
//     * @date 2023/02/24 10:20
//     */
//    @Test
//    public void testDeploymentList() {
//        ProcessDeploymentModelQuery processDeploymentModelQuery = new ProcessDeploymentModelQuery();
//
//        processDeploymentModelQuery.setPage(1);
//        processDeploymentModelQuery.setRows(10);
//        processDeploymentModelQuery.setDeploymentName("roomRepair");
//        processDeploymentModelQuery.setDeploymentKey("039be0d1-77b8-4cfe-90b1-259a8407b8e2");
//        processDeploymentModelQuery.setDeploymentId("0439c0c3-b80e-11ed-85ed-00ff165286a9");
//        PaginateResult<ProcessDeploymentEntity> processDeploymentEntityPaginateResult = commonActivitiService.queryProcessDeploymentList(processDeploymentModelQuery);
//        log.info("deploymentList:{}", JSON.toJSONString(processDeploymentEntityPaginateResult));
//
//    }
//
//    /**
//     * 流程定义列表
//     *
//     * @author wangxiao
//     * @date 2023/02/24 11:19
//     */
//    @Test
//    public void testDefinitionList() {
//        ProcessDefinitionModelQuery processDefinitionModelQuery = new ProcessDefinitionModelQuery();
//        processDefinitionModelQuery.setDeploymentId("0a1912b6-b0e8-11ed-bc3c-00ff165286a9");
//        processDefinitionModelQuery.setProcessDefinitionName("日常");
//        processDefinitionModelQuery.setLatestVersion(Boolean.TRUE);
//        PaginateResult<ProcessDefinitionEntity> processDefinitionEntityPaginateResult = commonActivitiService.queryProcessDefinitionList(processDefinitionModelQuery);
//        log.info("definitionList:{}", JSON.toJSONString(processDefinitionEntityPaginateResult));
//
//    }
//
//    /**
//     * 流程实例列表
//     *
//     * @author wangxiao
//     * @date 2023/02/24 11:51
//     */
//    @Test
//    public void testProcessInList() {
//        ProcessInstanceModelQuery processInstanceModelQuery = new ProcessInstanceModelQuery();
//        processInstanceModelQuery.setProcessDefinitionKey("dailyRepair");
//        processInstanceModelQuery.setPage(2);
//        processInstanceModelQuery.setRows(3);
//
////        processInstanceQuery.setStartTime(DateUtils.parseDate("2023-02-27 11:43:00"));
////        processInstanceQuery.setEndTime(DateUtils.parseDate("2023-02-27 10:43:00"));
////        processInstanceQuery.setProcessInstanceId("0ac7fed8-b663-11ed-930f-00ff165286a9");
//        PaginateResult<ProcessInstanceEntity> processInstanceEntityPaginateResult = commonActivitiService.queryProcessInstanceList(processInstanceModelQuery);
//
//        log.info("instanceList:{}", JSON.toJSONString(processInstanceEntityPaginateResult));
//    }
//}
