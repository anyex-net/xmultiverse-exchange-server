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
//import java.util.*;
//
///**
// * 报销流程(财务部或签)
// * note: 1.记录变更 by WangXiao
// *
// * @author WangXiao
// * @date 2023/02/10 13:19
// **/
//@Slf4j
//public class ReimbursementTest extends BaseServiceImplTest {
//    @Autowired
//    ActivitiService activitiService;
//
//    /**
//     * 报销流程-部署
//     *
//     * @author wangxiao
//     * @date 2023/02/10 11:30
//     */
//    @Test
//    public void testDeployment() {
//        DeploymentParams req = new DeploymentParams();
//        req.setResourceName("reimbursement");
//        req.setResourceBpmn("processes/demo/reimbursement.bpmn20.bpmn");
//        req.setResourcePng("processes/demo/reimbursement.bpmn20.png");
//        ProcessDeploymentEntity deployment = activitiService.deployment(req);
//        log.info("报销部署结果:{}", JSON.toJSONString(deployment));
//    }
//
//    /**
//     * 报销流程-启动
//     *
//     * @author wangxiao
//     * @date 2023/02/13 09:59
//     */
//    @Test
//    public void testStart() {
//        String processDefinitionKey = "reimbursement";
//        //业务标识
//        String businessKey = UUID.randomUUID().toString();
//        Map<String, Object> variables = new HashMap<>();
//        //会计部门人员有一人审批通过
//        List<String> users = new ArrayList<>();
//        users.add("会计1");
//        users.add("会计2");
//        users.add("会计3");
//        variables.put("users", users);
//        variables.put("startUserId", "001");
//        variables.put("name", "报销流程");
//
//        ProcessInstanceStartParams req = new ProcessInstanceStartParams();
//        req.setBusinessKey(businessKey);
//        req.setProcessDefinitionKey(processDefinitionKey);
//        req.setVariables(variables);
//        ProcessInstanceStartEntity processInstance = activitiService.startProcessInstance(req);
//        log.info("报销流程(财务部或签)-启动成功,流程实例id:{}", processInstance);
//        //自动完成第一个任务(第一个任务为业务流程发起)
//        System.out.println("报销流程(财务部或签)-流程启动后,自动完成第一个任务...");
//        String assignee = "applyuserid";
//        ProcessesTaskModelQuery taskQueryReq = new ProcessesTaskModelQuery();
//        taskQueryReq.setProcessDefinitionKey(processDefinitionKey);
//        taskQueryReq.setAssignee(assignee);
//        List<ProcessTask> taskList = activitiService.queryTask(taskQueryReq);
//        log.info("报销流程(财务部或签),查询结果:{}", JSONUtils.beanToJson(taskList));
//        ProcessTask task = taskList.get(0);
//
//        CompleteParams completeReq = new CompleteParams();
//        completeReq.setTaskId(task.getTaskId());
//        completeReq.setVariables(variables);
//        activitiService.complete(completeReq);
//    }
//
//    /**
//     * 报销流程-审批
//     *
//     * @author wangxiao
//     * @date 2023/02/13 09:59
//     */
//    @Test
//    public void testComplete() {
//        //运行时待办表: act_ru_task: ID_
//        String taskId = "8645cf1b-931d-11ed-bedc-00ff165286a9";
//        Map<String, Object> variables = new HashMap<>();
//        CompleteParams completeReq = new CompleteParams();
//        completeReq.setTaskId(taskId);
//        completeReq.setVariables(variables);
//        log.info("报销流程(财务部或签),请求参数:{}", JSONUtils.beanToJson(completeReq));
//        activitiService.complete(completeReq);
//    }
//
//    /**
//     * 报销流程-查询待办
//     *
//     * @author wangxiao
//     * @date 2023/02/13 09:59
//     */
//    @Test
//    public void testMyTask() {
//        //流程部署定义key
//        String processDefinitionKey = "reimbursement";
//        //根据当前处理人  act_ru_task::ASSIGNEE_
//        String assignee = "applyuserid";
//        ProcessesTaskModelQuery taskQueryReq = new ProcessesTaskModelQuery();
//        taskQueryReq.setProcessDefinitionKey(processDefinitionKey);
//        taskQueryReq.setAssignee(assignee);
//        List<ProcessTask> taskList = activitiService.queryTask(taskQueryReq);
//        log.info("报销流程(财务部或签),当前待办任务:{}", JSONUtils.beanToJson(taskList));
//    }
//}
