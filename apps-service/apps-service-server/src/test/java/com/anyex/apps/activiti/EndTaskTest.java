//package com.anyex.apps.activiti;
//
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.activiti.model.CompleteParams;
//import com.anyex.apps.activiti.service.ActivitiService;
//import org.activiti.bpmn.model.BpmnModel;
//import org.activiti.bpmn.model.EndEvent;
//import org.activiti.bpmn.model.FlowNode;
//import org.activiti.bpmn.model.SequenceFlow;
//import org.activiti.engine.RepositoryService;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 终止任务测试
// * note: 1.记录变更 by WangXiao
// *
// * @author WangXiao
// * @date 2023/02/13 15:35
// **/
//public class EndTaskTest extends BaseServiceImplTest {
//    @Autowired
//    private ActivitiService activitiService;
//
//    @Autowired
//    private RepositoryService repositoryService;
//
//    /**
//     * 终止任务
//     * 1. 把当前任务的下一个节点
//     * @author wangxiao
//     * @date 2023/02/13 16:41
//     */
//    @Test
//    public void endTaskTest(){
//        String processDefinitionId = "addWork:7:cf01d35f-ab50-11ed-84a7-00ff165286a9";
//        String taskDefinitionKey = "sid-0CEB5393-4404-4310-A0B2-8E4FC2AFCF71";
//        String taskId = "e20602d4-ab63-11ed-874a-00ff165286a9";
//        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
//        List<EndEvent> flowElementsOfType = bpmnModel.getMainProcess().findFlowElementsOfType(EndEvent.class);
//        //结束节点
//        FlowNode endFlow = flowElementsOfType.get(0);
//        //当前节点
//        FlowNode currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(taskDefinitionKey);
//        //  临时保存当前活动的原始方向
//        List originalSequenceFlowList = new ArrayList<>();
//        originalSequenceFlowList.addAll(currentFlowNode.getOutgoingFlows());
//        //  清理活动方向
//        currentFlowNode.getOutgoingFlows().clear();
//        //  建立新方向
//        SequenceFlow newSequenceFlow = new SequenceFlow();
//        newSequenceFlow.setId("newSequenceFlowId");
//        newSequenceFlow.setSourceFlowElement(currentFlowNode);
//        newSequenceFlow.setTargetFlowElement(endFlow);
//        List newSequenceFlowList = new ArrayList<>();
//        newSequenceFlowList.add(newSequenceFlow);
//        //  当前节点指向新的方向
//        currentFlowNode.setOutgoingFlows(newSequenceFlowList);
//        //  完成当前任务
//        CompleteParams completeParams = new CompleteParams();
//        completeParams.setTaskId(taskId);
//        activitiService.complete(completeParams);
//    }
//}
