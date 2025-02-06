//package com.anyex.apps.activiti;
//
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.activiti.entity.HistoryProcessInstanceEntity;
//import com.anyex.apps.activiti.entity.HistoryProcessTaskEntity;
//import com.anyex.apps.activiti.entity.ProcessDefinitionElementEntity;
//import com.anyex.apps.activiti.entity.ProcessTask;
//import com.anyex.apps.activiti.model.HistoryProcessesModelQuery;
//import com.anyex.apps.activiti.model.HistoryTaskQuery;
//import com.anyex.apps.activiti.model.ProcessesTaskModelQuery;
//import com.anyex.apps.activiti.model.UserTaskElementQuery;
//import com.anyex.apps.activiti.service.ActivitiService;
//import com.anyex.apps.model.PaginateResult;
//import com.anyex.apps.utils.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.task.Task;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * 业务流程查询单元测试
// * note: 1.记录变更 by WangXiao
// *
// * @author WangXiao
// * @date 2023/02/10 13:28
// **/
//@Slf4j
//public class ProcessQueryTest extends BaseServiceImplTest {
//    @Autowired
//    ActivitiService activitiService;
//
//    @Autowired
//    TaskService taskService;
//
//    @Test
//    public void testHistory() {
//        String processInstanceId = "48b9d695-ad9e-11ed-8e4e-00ff165286a9";
//        List<Task> list = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
//        log.info("流程任务历史记录:{}", JSONUtils.beanToJson(list));
//    }
//
//    /**
//     * 查询当前流程节点,已审批及待审批
//     *
//     * @param
//     * @return
//     * @author wangxiao
//     * @date 2023/02/07 16:28
//     */
//    @Test
//    public void testCurrAndHis() {
//        String procInstId = "e16d6c4c-ab63-11ed-874a-00ff165286a9";
//        HistoryTaskQuery historyTaskQuery = new HistoryTaskQuery();
//        historyTaskQuery.setProcessInstanceId(procInstId);
//        List<HistoryProcessTaskEntity> historyQueryResList = activitiService.queryHistoryTask(historyTaskQuery).getList();
//        log.info("当前及历史审批记录:{}", JSONUtils.beanToJson(historyQueryResList));
//    }
//
//    /**
//     * 查询流程定义节点及关联关系
//     *
//     * @author wangxiao
//     * @date 2023/02/07 13:14
//     */
//    @Test
//    public void queryProcessDefinition() {
//        String processDefinitionId = "addWork:10:623c525f-ac43-11ed-8197-ec2e989906a5";
//
//        UserTaskElementQuery userTaskElementQuery = new UserTaskElementQuery();
//        userTaskElementQuery.setProcessDefinitionId(processDefinitionId);
//        List<ProcessDefinitionElementEntity> resList = activitiService.queryUserTaskElement(userTaskElementQuery);
//        log.info("流程定义节点:{}", JSONUtils.beanToJson(resList));
//
//
//    }
//
//
//    /**
//     * 我的待办
//     * 通过我的id->act_ru_task:ASSIGNEE_
//     * 结果表: select * from  act_ru_task task inner join act_re_procdef procdef on task.PROC_DEF_ID_ = procdef.ID_ where task.ASSIGNEE_ = '部门领导' and procdef.KEY_ = 'leave'
//     *
//     * @author wangxiao
//     * @date 2023/01/11 15:40
//     */
//    @Test
//    public void testMyTask() {
//        String assignee = "dept_lead";
//        ProcessesTaskModelQuery taskQueryReq = new ProcessesTaskModelQuery();
//        taskQueryReq.setAssignee(assignee);
//        List<ProcessTask> taskQueryRes = activitiService.queryTask(taskQueryReq);
//        log.info("我的:{}待办:{}", assignee, JSONUtils.beanToJson(taskQueryRes));
//
//    }
//
//    /**
//     * 我发起的流程
//     * 1. 查询条件发起人id(startUserId),发起流程时startUserId传入
//     * 2. 历史流程实例记录(act_hi_procinst)过滤条件(START_USER_ID_)
//     *
//     * @author wangxiao
//     * @date 2023/01/11 16:11
//     */
//    @Test
//    public void testQueryMyStart() {
//        String startUserId = "001";
//
//        HistoryTaskQuery taskHistoryQueryReq = new HistoryTaskQuery();
//        taskHistoryQueryReq.setAssignee(startUserId);
//        HistoryProcessesModelQuery processesHistoryQueryReq = new HistoryProcessesModelQuery();
//        PaginateResult<HistoryProcessInstanceEntity> historyProcessInstancePaginateResult = activitiService.queryHistoryProcessesInstance(processesHistoryQueryReq);
//        log.info("历史流程:{}", JSONUtils.beanToJson(historyProcessInstancePaginateResult));
//    }
//
//    /**
//     * 与我相关的流程, 我发起&我审批&抄送我
//     * 1. 通过处理人查询抄送列表中抄送我的流程实例id集合(procInsIds)
//     * 1. 通过处理人(assignee)查询历史任务表: act_hi_taskinst
//     * 2. 一个流程里处理人可能有多个节点,根据流程实例id(ProcessInstanceId)去重
//     * 3. 批量查询(流程实例id集合)历史实例表act_hi_procinst(包含已完成和未完成)
//     *
//     * @author wangxiao
//     * @date 2023/01/11 16:46
//     */
//    @Test
//    public void testQueryAboutMe() {
//        String assignee = "applyuserid";
//        Set<String> instanceIdSet = new HashSet<>();
//
//        //查询抄送我的任务的流程实例id
// /*       String sql = "select * from act_send_copy where ASSIGNEE_ = '" + assignee + "'";
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
//        if (!CollectionUtils.isEmpty(maps)) {
//            for (Map<String, Object> map : maps) {
//                instanceIdSet.add(map.get("PROC_INST_ID_").toString());
//            }
//        }*/
//        //查询我发起和我审批(我发起的默认第一个都是我审批,所以不用单独查询我发起的)
//        HistoryTaskQuery taskHistoryQueryReq = new HistoryTaskQuery();
//        taskHistoryQueryReq.setAssignee(assignee);
//        List<HistoryProcessTaskEntity> historyQueryResList = activitiService.queryHistoryTask(taskHistoryQueryReq).getList();
//        log.info("历史任务:{}", JSONUtils.beanToJson(historyQueryResList));
//
//        //查询流程
//        HistoryProcessesModelQuery processesHistoryQueryReq = new HistoryProcessesModelQuery();
//        processesHistoryQueryReq.setStartByUserId(assignee);
//        PaginateResult<HistoryProcessInstanceEntity> historyProcessInstancePaginateResult = activitiService.queryHistoryProcessesInstance(processesHistoryQueryReq);
//        log.info("历史流程实例:{}", JSONUtils.beanToJson(historyProcessInstancePaginateResult));
//    }
//}
