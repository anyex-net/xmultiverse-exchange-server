//package com.anyex.apps.common;
//
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.common.entity.MsgTemplate;
//import com.anyex.apps.common.service.MsgTemplateService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * @DESCRIPTION:
// * @USER: yuweichong
// * @DATE: 2023/2/14 17:27
// */
//@Slf4j
//public class MsgTemplateTest extends BaseServiceImplTest {
//    @Autowired
//    MsgTemplateService msgTemplateService;
//    @Test
//    public void test2() {
//        MsgTemplate msgTemplate = new MsgTemplate();
//        msgTemplate.setContent("111");
//        msgTemplate.setCreateBy(11L);
//        msgTemplate.setTitle("test");
//        msgTemplate.setTplKey("test");
//        msgTemplate.setType("email");
//        msgTemplate.setLang("en_US");
//        msgTemplateService.save(msgTemplate);
//    }
//}
