//package com.anyex.apps.activiti;
//
//import com.alibaba.fastjson.JSON;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.activiti.entity.ProcessDeploymentEntity;
//import com.anyex.apps.activiti.entity.ProcessInstanceStartEntity;
//import com.anyex.apps.activiti.entity.ProcessTask;
//import com.anyex.apps.activiti.model.CompleteParams;
//import com.anyex.apps.activiti.model.DeploymentParams;
//import com.anyex.apps.activiti.model.ProcessInstanceStartParams;
//import com.anyex.apps.activiti.model.ProcessesTaskModelQuery;
//import com.anyex.apps.activiti.service.ActivitiService;
//import com.anyex.apps.utils.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
///**
// * 请假流程(条件分支)
// * note: 1.记录变更 by WangXiao
// *
// * @author WangXiao
// * @date 2023/02/10 13:06
// **/
//@Slf4j
//public class LeaveTest extends BaseServiceImplTest {
//    @Autowired
//    ActivitiService activitiService;
//
//    /**
//     * 请假流程-部署
//     *
//     * @author wangxiao
//     * @date 2023/02/10 11:30
//     */
//    @Test
//    public void testDeployment() {
//        DeploymentParams req = new DeploymentParams();
//        req.setResourceName("leave");
//        req.setResourceBpmn("processes/demo/leave.bpmn20.bpmn");
//        req.setResourcePng("processes/demo/leave.bpmn20.png");
//        ProcessDeploymentEntity deployment = activitiService.deployment(req);
//        log.info("请假-部署结果:{}", JSON.toJSONString(deployment));
//    }
//
//    /**
//     * 请假流程-发起
//     *
//     * @author wangxiao
//     * @date 2023/02/10 11:30
//     */
//    @Test
//    public void testStart() {
//        String processDefinitionKey = "leave";
//        //业务标识
//        String businessKey = UUID.randomUUID().toString();
//        Map<String, Object> variables = new HashMap<>();
//        //请假天数,大于3天需要总经理审批
//        Integer days = 5;
//        variables.put("days", days);
//
//        ProcessInstanceStartParams req = new ProcessInstanceStartParams();
//        req.setBusinessKey(businessKey);
//        req.setProcessDefinitionKey(processDefinitionKey);
//        req.setVariables(variables);
//        ProcessInstanceStartEntity processInstance = activitiService.startProcessInstance(req);
//        log.info("请假流程(条件分支)-启动成功,流程实例id:{}", processInstance);
//        //自动完成第一个任务(第一个任务为业务流程发起)
//        System.out.println("请假流程(条件分支)-流程启动后,自动完成第一个任务...");
//        String assignee = "applyuserid";
//        ProcessesTaskModelQuery taskQueryReq = new ProcessesTaskModelQuery();
//        taskQueryReq.setProcessDefinitionKey(processDefinitionKey);
//        taskQueryReq.setAssignee(assignee);
//        List<ProcessTask> taskList = activitiService.queryTask(taskQueryReq);
//        log.info("请假流程(条件分支),查询结果:{}", JSONUtils.beanToJson(taskList));
//        ProcessTask task = taskList.get(0);
//
//        CompleteParams completeReq = new CompleteParams();
//        completeReq.setTaskId(task.getTaskId());
//        completeReq.setVariables(variables);
//        activitiService.complete(completeReq);
//    }
//
//    /**
//     * 请假流程-审批
//     *
//     * @author wangxiao
//     * @date 2023/02/10 11:30
//     */
//    @Test
//    public void testComplete() {
//        //运行时待办表: act_ru_task: ID_
//        String taskId = "8645cf1b-931d-11ed-bedc-00ff165286a9";
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("pass", "success");
//        //部门领导审批 1:通过,2: 驳回
//        variables.put("deptLdApproval", 1);
//        //总经理审批 1:通过,2:驳回
//        variables.put("managerApproval", 1);
//        CompleteParams completeReq = new CompleteParams();
//        completeReq.setTaskId(taskId);
//        completeReq.setVariables(variables);
//        log.info("请假流程(条件分支),请求参数:{}", JSONUtils.beanToJson(completeReq));
//        activitiService.complete(completeReq);
//    }
//
//    /**
//     * 请假流程-我的待办
//     *
//     * @author wangxiao
//     * @date 2023/02/10 11:30
//     */
//    @Test
//    public void testMyTask() {
//        //流程部署定义key
//        String processDefinitionKey = "leave";
//        //根据当前处理人  act_ru_task::ASSIGNEE_
//        String assignee = "applyuserid";
//        ProcessesTaskModelQuery taskQueryReq = new ProcessesTaskModelQuery();
//        taskQueryReq.setProcessDefinitionKey(processDefinitionKey);
//        taskQueryReq.setAssignee(assignee);
//        List<ProcessTask> taskList = activitiService.queryTask(taskQueryReq);
//        log.info("请假流程(条件分支),当前待办任务:{}", JSONUtils.beanToJson(taskList));
//    }
//}
