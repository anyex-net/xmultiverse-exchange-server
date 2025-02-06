//package com.anyex.apps.activiti;
//
//import com.alibaba.fastjson.JSON;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.activiti.consts.ActivitiConst;
//import com.anyex.apps.activiti.consts.RepairConst;
//import com.anyex.apps.activiti.entity.ProcessDefinitionEntity;
//import com.anyex.apps.activiti.entity.ProcessDefinitionElementEntity;
//import com.anyex.apps.activiti.entity.ProcessDeploymentEntity;
//import com.anyex.apps.activiti.model.*;
//import com.anyex.apps.activiti.service.CommonActivitiService;
//import com.anyex.apps.model.PaginateResult;
//import com.anyex.apps.utils.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 客房维修流程测试
// * note: 1.记录变更 by WangXiao
// *
// * @author WangXiao
// * @date 2023/02/20 14:46
// **/
//@Slf4j
//public class RoomRepairTest extends BaseServiceImplTest {
//    @Autowired
//    private CommonActivitiService commonActivitiService;
//
//    @Test
//    public void testProcessDefinitionList() {
//        ProcessDefinitionModelQuery processDefinitionModelQuery = new ProcessDefinitionModelQuery();
//        processDefinitionModelQuery.setProcessDefinitionKey("roomRepair");
//        PaginateResult<ProcessDefinitionEntity> processDefinitionEntityPaginateResult = commonActivitiService.queryProcessDefinitionList(processDefinitionModelQuery);
//        log.info("客房维修流程定义列表:{}", JSONUtils.beanToJson(processDefinitionEntityPaginateResult));
//    }
//
//
//    @Test
//    public void testQueryTaskUserElementList() {
//        UserTaskElementQuery userTaskElementQuery = new UserTaskElementQuery();
//        String processDefinitionId = "roomRepair:1:bc6d0355-b0ea-11ed-8a83-00ff165286a9";
//        userTaskElementQuery.setProcessDefinitionId(processDefinitionId);
//        List<ProcessDefinitionElementEntity> processDefinitionElementEntities = commonActivitiService.queryUserTaskElement(userTaskElementQuery);
//        log.info("客房维修流程节点列表:{}", JSONUtils.beanToJson(processDefinitionElementEntities));
//    }
//
//    /**
//     * 加班申请-部署
//     *
//     * @author wangxiao
//     * @date 2023/02/13 09:59
//     */
//    @Test
//    public void testDeployment() {
//        DeploymentParams req = new DeploymentParams();
//        req.setResourceName("roomRepair");
//        req.setResourceBpmn("processes/roomRepair/roomRepair.bpmn20.bpmn");
//        req.setResourcePng("processes/roomRepair/roomRepair.bpmn20.png");
//        req.setCategory("repair");
//        ProcessDeploymentEntity deployment = commonActivitiService.deployment(req);
//        log.info("客房维修部署结果:{}", JSON.toJSONString(deployment));
//    }
//    //710d6ea7-b830-11ed-90b5-00ff165286a9
//    @Test
//    public void testComplete() {
//        String taskId = "04df8437-b838-11ed-ab62-00ff165286a9";
//        Map<String, Object> variables = new HashMap<>();
//        //维修工用户id
//        String repairWorkerId = "721999317673578496";
//        variables.put(RepairConst.REPAIR_PERSONS, Arrays.asList(repairWorkerId));
//        CompleteCommonParams completeParams = new CompleteCommonParams();
//        completeParams.setProcessDefinitionKey(RepairConst.PROCESS_DEFINITION_KEY_ROOM_REPAIR);
//        completeParams.setTaskId(taskId);
//        completeParams.setVariables(variables);
//        completeParams.setExecuteResult(Boolean.FALSE);
//        completeParams.setExecuteComment(ActivitiConst.EXECUTE_COMMENT_REJECT);
//        commonActivitiService.completeCommon(completeParams);
//    }
//}
