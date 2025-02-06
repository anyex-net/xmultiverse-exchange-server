//package com.anyex.apps.activiti;
//
//import com.alibaba.fastjson.JSONObject;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.activiti.entity.ProcessTask;
//import com.anyex.apps.activiti.model.CompleteParams;
//import com.anyex.apps.activiti.model.ProcessesTaskModelQuery;
//import com.anyex.apps.activiti.service.ActivitiService;
//import com.anyex.apps.utils.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.activiti.bpmn.model.BpmnModel;
//import org.activiti.bpmn.model.FlowElement;
//import org.activiti.bpmn.model.UserTask;
//import org.activiti.engine.RepositoryService;
//import org.apache.commons.collections.CollectionUtils;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 任务状态对应业务状态相关测试
// * note: 1.记录变更 by WangXiao
// *
// * @author WangXiao
// * @date 2023/02/14 16:08
// **/
//@Slf4j
//public class TaskStateTest extends BaseServiceImplTest {
//    @Autowired
//    private ActivitiService activitiService;
//    @Autowired
//    private RepositoryService repositoryService;
//    /**
//     * 流程任务节点定义key(sid-)对应状态
//     */
//    private final Map<String, JSONObject> taskDefKeyStatusMap = new HashMap<>();
//    /**
//     * 流程任务节点定义key对应业务状态
//     */
//    private final Map<String, String> taskState = new HashMap<>();
//
//    @Test
//    public void testRepairStatus() {
//        String assignee = "general_manager";
//        //查询当前待办任务节点
//        ProcessesTaskModelQuery taskQuery = new ProcessesTaskModelQuery();
//        taskQuery.setAssignee(assignee);
//        List<ProcessTask> processTasks = activitiService.queryTask(taskQuery);
//        if (CollectionUtils.isEmpty(processTasks)) {
//            log.info("当前待办为空");
//            return;
//        }
//        ProcessTask task = processTasks.get(0);
//        log.info("当前待办任务节点:{}", task);
//        String sid = task.getTaskDefinitionKey();
//        //查询流程定义流程(所有UserTask类型)
//        testUserTaskList();
//        //流程节点状态
//        testTaskState();
//        //当前任务节点对应流程节点
//        JSONObject object = taskDefKeyStatusMap.get(sid);
//        log.info("当前任务对应流程节点:{}", object);
//        String state = taskState.get(sid);
//        log.info("当前任务对应流程状态:{}", state);
//    }
//
//    @Test
//    public void testComplete() {
//        //运行时待办表: act_ru_task: ID_
//        String taskId = "5e624194-ac42-11ed-b436-00ff165286a9";
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("pass", "success");
//        //部门领导审批 1:通过,2: 驳回
//        variables.put("deptLdApproval", 1);
//        //总经理审批 1:通过,2:驳回
//        variables.put("managerApproval", 1);
//        CompleteParams completeReq = new CompleteParams();
//        completeReq.setTaskId(taskId);
//        completeReq.setVariables(variables);
//        log.info("请求参数:{}", JSONUtils.beanToJson(completeReq));
//        activitiService.complete(completeReq);
//    }
//
//    @Test
//    public void testUserTaskList() {
//        String processDefinitionId = "addWork:7:36763a3e-ac42-11ed-8588-00ff165286a9";
//        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
//        Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
//        if (CollectionUtils.isNotEmpty(flowElements)) {
//            flowElements.forEach(element -> {
//                // UserTask类型
//                if (element instanceof UserTask) {
//                    JSONObject json = new JSONObject();
//                    json.put("sid", element.getId());
//                    json.put("name", element.getName());
//                    json.put("assignee", ((UserTask) element).getAssignee());
//                    taskDefKeyStatusMap.put(element.getId(), json);
//                }
//            });
//            log.info("流程所有节点信息:{}", taskDefKeyStatusMap);
//        }
//    }
//
//    public void testTaskState() {
//        taskState.put("sid-0CEB5393-4404-4310-A0B2-8E4FC2AFCF71", "门领导审批状态");
//        taskState.put("sid-0E9E6701-7FCA-4F79-9EF6-696CD0C1ECFF", "调整申请状态");
//        taskState.put("sid-C5BF8AE8-5965-45CB-957D-42C2E7FF714A", "总经理审批状态");
//    }
//}
