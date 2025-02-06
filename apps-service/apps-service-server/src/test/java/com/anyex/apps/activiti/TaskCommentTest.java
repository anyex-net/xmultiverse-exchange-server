//package com.anyex.apps.activiti;
//
//import com.alibaba.fastjson.JSON;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.activiti.consts.ActivitiConst;
//import com.anyex.apps.activiti.entity.ProcessTaskCommentEntity;
//import com.anyex.apps.activiti.model.TaskCommentByInstanceQuery;
//import com.anyex.apps.activiti.service.ActivitiService;
//import com.anyex.apps.utils.JSONUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.activiti.engine.TaskService;
//import org.activiti.engine.task.Comment;
//import org.apache.commons.collections.CollectionUtils;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
///**
// * 审批意见测试
// * note: 1.记录变更 by WangXiao
// *
// * @author WangXiao
// * @date 2023/03/06 10:48
// **/
//@Slf4j
//public class TaskCommentTest extends BaseServiceImplTest {
//    @Autowired
//    private ActivitiService activitiService;
//
//    @Autowired
//    private TaskService taskService;
//
//    @Test
//    public void testQueryInstanceComment(){
//        String processInstanceId = "1707a268-bbe3-11ed-b284-00ff165286a9";
//        TaskCommentByInstanceQuery taskCommentByInstanceQuery = new TaskCommentByInstanceQuery();
//        taskCommentByInstanceQuery.setProcessInstanceId(processInstanceId);
//        List<ProcessTaskCommentEntity> processTaskCommentEntities = activitiService.queryProcessInstanceComment(taskCommentByInstanceQuery);
//        log.info("流程审批意见:{}", JSONUtils.beanToJson(processTaskCommentEntities));
//    }
//    /**
//     * 测试添加流程任务节点执行意见
//     *
//     * @author wangxiao
//     * @date 2023/02/23 10:23
//     */
//    @Test
//    public void testAddComment() {
//        String taskId = "04daffbc-b80d-11ed-8041-00ff165286a9";
//        String processInstanceId = "04cc32a5-b80d-11ed-8041-00ff165286a9";
//        String message = "维修部部门经理1审批: 请假-批准";
//        String resultType = ActivitiConst.TASK_COMMON_TYPE_RESULT;
//        String commonType = ActivitiConst.TASK_COMMON_TYPE_COMMON;
//        taskService.addComment(taskId, processInstanceId,resultType, "true");
//        taskService.addComment(taskId, processInstanceId,commonType, message);
//        List<Comment> taskResult = taskService.getTaskComments(taskId, resultType);
//        List<Comment> taskComment = taskService.getTaskComments(taskId, commonType);
//        log.info("任务节点审批结果:{}", JSON.toJSONString(taskResult));
//        log.info("任务节点审批意见:{}",JSON.toJSONString(taskComment));
//    }
//    /**
//     * 测试获取流程任务节点的执行意见
//     *
//     * @author wangxiao
//     * @date 2023/02/23 10:23
//     */
//    @Test
//    public void testGetComment() {
//        String taskId = "7c494f7a-b32e-11ed-bf1e-00ff165286a9";
//        List<Comment> taskComments = taskService.getTaskComments(taskId);
//        List<Comment> taskComments1 = taskService.getTaskComments(taskId, ActivitiConst.EXECUTE_RESULT);
//        List<Comment> taskComments2 = taskService.getTaskComments(taskId, ActivitiConst.EXECUTE_COMMENT);
//        if (CollectionUtils.isNotEmpty(taskComments)){
//            Comment comment = taskComments.get(0);
//            String fullMessage = comment.getFullMessage();
//            log.info("获取任务意见:{}", JSONUtils.beanToJson(taskComments));
//        }
//    }
//}
