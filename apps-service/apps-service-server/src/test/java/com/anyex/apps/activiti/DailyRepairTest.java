//package com.anyex.apps.activiti;
//
//import com.alibaba.fastjson.JSON;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.activiti.consts.ActivitiConst;
//import com.anyex.apps.activiti.consts.RepairConst;
//import com.anyex.apps.activiti.entity.*;
//import com.anyex.apps.activiti.model.*;
//import com.anyex.apps.activiti.service.CommonActivitiService;
//import com.anyex.apps.model.PaginateResult;
//import com.anyex.apps.process.service.ProcessDefNodeExecutorService;
//import com.anyex.apps.utils.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.activiti.engine.RuntimeService;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.service.persistence.entity.VariableInstance;
//import org.activiti.engine.task.Comment;
//import org.apache.commons.collections.CollectionUtils;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.*;
//
///**
// * 日常报修流程
// * note: 1.记录变更 by WangXiao
// *
// * @author WangXiao
// * @date 2023/02/15 14:49
// **/
//@Slf4j
//public class DailyRepairTest extends BaseServiceImplTest {
//    @Autowired
//    private CommonActivitiService commonActivitiService;
//
//    @Autowired
//    private RuntimeService runtimeService;
//
//    @Autowired
//    private TaskService taskService;
//
//
//    @Autowired
//    private ProcessDefNodeExecutorService processDefNodeExecutorService;
//
//    @Test
//    public void testHistoryTaskByInstance(){
//        String processInstanceId = "15b5dca9-bbe8-11ed-aedc-00ff165286a9";
//        HistoryTaskByInstanceQuery historyTaskByInstanceQuery = new HistoryTaskByInstanceQuery();
//        historyTaskByInstanceQuery.setProcessInstanceId(processInstanceId);
//        List<HistoryProcessTaskEntity> entityList = commonActivitiService.queryHistoryTaskByInstanceId(historyTaskByInstanceQuery);
//        log.info("根据实例查询历史任务列表:{}",JSON.toJSONString(entityList));
//    }
//
//
//    @Test
//    public void testProcessElementRelationUser() {
//        String processDefinitionKey = "dailyRepair";
//        String processElementId = "sid_repair_manager";
//        Map<String, String> map = processDefNodeExecutorService.getExecutorIds(processDefinitionKey);
//        log.info("流程节点关联用户列表:{}", JSONUtils.beanToJson(map));
//    }
//
//    @Test
//    public void testProcessDefinitionList() {
//        ProcessDefinitionModelQuery processDefinitionModelQuery = new ProcessDefinitionModelQuery();
//        processDefinitionModelQuery.setProcessDefinitionKey("dailyRepair");
//        processDefinitionModelQuery.setPage(1);
//        processDefinitionModelQuery.setRows(3);
//        processDefinitionModelQuery.setProcessDefinitionName("报修");
//        processDefinitionModelQuery.setLatestVersion(true);
//
//        PaginateResult<ProcessDefinitionEntity> processDefinitionEntityPaginateResult = commonActivitiService.queryProcessDefinitionList(processDefinitionModelQuery);
//        log.info("流程定义列表:{}", JSONUtils.beanToJson(processDefinitionEntityPaginateResult));
//    }
//
//    @Test
//    public void testQueryProcessElementList() {
//        ProcessElementQuery processElementQuery = new ProcessElementQuery();
//        String processDefinitionId = "dailyRepair:2:a58956c4-b8a1-11ed-aa8d-00ff165286a9";
//        processElementQuery.setProcessDefinitionId(processDefinitionId);
//        List<ProcessDefinitionElementEntity> processDefinitionElementEntities = commonActivitiService.queryProcessElementList(processElementQuery);
//        log.info("日常报修流程所有节点列表:{}", JSONUtils.beanToJson(processDefinitionElementEntities));
//    }
//
//    @Test
//    public void testQueryUserTaskElementList() {
//        UserTaskElementQuery userTaskElementQuery = new UserTaskElementQuery();
//        String processDefinitionId = "dailyRepair:2:a58956c4-b8a1-11ed-aa8d-00ff165286a9";
//        userTaskElementQuery.setProcessDefinitionId(processDefinitionId);
//        List<ProcessDefinitionElementEntity> processDefinitionElementEntities = commonActivitiService.queryUserTaskElement(userTaskElementQuery);
//        log.info("日常报修流程用户任务类型节点列表:{}", JSONUtils.beanToJson(processDefinitionElementEntities));
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
//        req.setResourceName("dailyRepair");
//        req.setResourceBpmn("processes/dailyRepair/dailyRepair.bpmn20.bpmn");
//        req.setResourcePng("processes/dailyRepair/dailyRepair.bpmn20.png");
//        req.setCategory("repair");
//        ProcessDeploymentEntity deployment = commonActivitiService.deployment(req);
//        log.info("日常报修-部署结果:{}", JSON.toJSONString(deployment));
//    }
//
//    @Test
//    public void testStart() {
//        String processDefinitionKey = "dailyRepair";
//        String userId = "001";
//        String businessKey = UUID.randomUUID().toString();
//        ProcessInstanceStartParams instanceStartParams = new ProcessInstanceStartParams();
//        instanceStartParams.setUserId(userId);
//        instanceStartParams.setBusinessKey(businessKey);
//        instanceStartParams.setProcessDefinitionKey(processDefinitionKey);
//        ProcessInstanceStartEntity result = commonActivitiService.startProcessInstance(instanceStartParams);
//        log.info("日常报修返回实例id:{}", JSONUtils.beanToJson(result));
//    }
//
//
//    /**
//     * 日常报修-审批
//     *
//     * @author wangxiao
//     * @date 2023/02/13 09:59
//     */
//    @Test
//    public void testComplete() {
//        String taskId = "813ba63e-bbe8-11ed-90fb-00ff165286a9";
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("approval_sid_dept_manager", "部门经理意见: 通过");
//        //维修工用户id
//        String repairWorkerId = "721999317673578496";
//        variables.put(RepairConst.REPAIR_PERSONS, Arrays.asList(repairWorkerId));
//        CompleteCommonParams completeParams = new CompleteCommonParams();
//
//        completeParams.setProcessDefinitionKey(RepairConst.PROCESS_DEFINITION_KEY_DAILY_REPAIR);
//        completeParams.setTaskId(taskId);
//        completeParams.setVariables(variables);
//        completeParams.setExecuteResult(Boolean.TRUE);
//        completeParams.setExecuteComment("部门经理执行意见: 修个锤锤,买新的");
//        commonActivitiService.completeCommon(completeParams);
//    }
//
//    /**
//     * 出差申请-查询待办
//     *
//     * @author wangxiao
//     * @date 2023/02/13 09:59
//     */
//    @Test
//    public void testMyTask() {
//        //流程部署定义key
//        String processDefinitionKey = "dailyRepair";
//        //根据当前处理人  act_ru_task::ASSIGNEE_
//        String assignee = "200000000000";
//        ProcessesTaskModelQuery taskQueryReq = new ProcessesTaskModelQuery();
//        taskQueryReq.setProcessDefinitionKey(processDefinitionKey);
//        taskQueryReq.setAssignee(assignee);
//        List<ProcessTask> taskList = commonActivitiService.queryTask(taskQueryReq);
//        log.info("********************************************");
//        log.info("用户:{},流程:{},当前待办", assignee, processDefinitionKey);
//        log.info("********************************************");
//        taskList.forEach(task -> {
//            log.info("====================================");
//            log.info("任务id: {}", task.getTaskId());
//            log.info("处理人: {}", task.getAssignee());
//            log.info("任务Name: {}", task.getName());
//            log.info("流程实例Id: {}", task.getProcessInstanceId());
//            log.info("====================================");
//        });
//    }
//
//    /**
//     * 与我相关
//     *
//     * @author wangxiao
//     * @date 2023/02/16 15:50
//     */
//    @Test
//    public void aboutMeTask() {
//        String assignee = "200000000000";
//        HistoryTaskQuery historyTaskQuery = new HistoryTaskQuery();
//        historyTaskQuery.setAssignee(assignee);
//        PaginateResult<HistoryProcessTaskEntity> historyProcessTaskPaginateResult = commonActivitiService.queryHistoryTask(historyTaskQuery);
//        log.info("{}:与我相关任务:{}", assignee, JSON.toJSONString(historyProcessTaskPaginateResult));
//    }
//
//    @Test
//    public void aboutMeProcess() {
//        String assignee = "200000000000";
//        String processDefinitionKey = "dailyRepair";
//        String processInstanceId = "0cbd5614-b743-11ed-b1c8-988fe06b91b8";
//        HistoryProcessesModelQuery historyProcessesModelQuery = new HistoryProcessesModelQuery();
//        historyProcessesModelQuery.setStartByUserId(assignee);
//        historyProcessesModelQuery.setPage(2);
//        historyProcessesModelQuery.setRows(3);
////        historyProcessesModelQuery.setProcessDefinitionKey(processDefinitionKey);
////        historyProcessesModelQuery.setProcessInstanceId(processInstanceId);
//        PaginateResult<HistoryProcessInstanceEntity> historyProcessInstancePaginateResult = commonActivitiService.queryHistoryProcessesInstance(historyProcessesModelQuery);
//        log.info("{}: 与我相关流程:{}", assignee, JSONUtils.beanToJson(historyProcessInstancePaginateResult));
//    }
//
//    /**
//     * 流程任务列表
//     *
//     * @author wangxiao
//     * @date 2023/02/16 17:29
//     */
//    @Test
//    public void testProcessTaskList() {
//        String processInstanceId = "84f9cfcd-b346-11ed-8df6-00ff165286a9";
//        HistoryTaskQuery historyTaskQuery = new HistoryTaskQuery();
//        historyTaskQuery.setProcessInstanceId(processInstanceId);
//        List<HistoryProcessTaskEntity> historyProcessTaskEntities = commonActivitiService.queryHistoryTask(historyTaskQuery).getList();
//        log.info("流程历史任务列表:{}", JSONUtils.beanToJson(historyProcessTaskEntities));
//    }
//
//    @Test
//    public void testProcessVariable() {
//        String executionId = "30b26900-ae87-11ed-9057-988fe06b91b8";
//        Map<String, Object> variables = runtimeService.getVariables(executionId);
//        log.info("变量列表:{}", JSONUtils.beanToJson(variables));
//        String variableName = "approval_sid_apply";
//        VariableInstance variableInstance = runtimeService.getVariableInstance(executionId, variableName);
//        log.info("变量实例对象:{}", JSONUtils.beanToJson(variableInstance));
//    }
//
//    @Test
//    public void testTaskVariable() {
//        String taskId = "a839c906-ae96-11ed-add3-00ff165286a9";
//        String variableName = "repairManagerApproval";
//        Object value = "维修部经理1执行意见: 通过";
//        taskService.setVariable(taskId, variableName, value);
//
//        Map<String, Object> variables = taskService.getVariables(taskId);
//        log.info("任务变量:{}", JSONUtils.beanToJson(variables));
//    }
//}
