//package com.anyex.apps.baiduocr;
//
//import com.alibaba.fastjson.JSON;
//import com.anyex.apps.BaseServiceImplTest;
//import com.anyex.apps.baidu.ocr.idcard.BaiduOcrUtil;
//import com.anyex.apps.baidu.ocr.idcard.OcrResponse;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * ocr测试类
// * note: 1.记录变更 by WangXiao
// *
// * @author WangXiao
// * @date 2023/03/07 15:08
// **/
//public class BaiduOcrTest extends BaseServiceImplTest {
//
//    @Autowired
//    private BaiduOcrUtil baiduOcrUtil;
//
//    @Test
//    public void testImage(){
//        String front = "/Users/Blocain/Downloads/idcard_front.png";
//        String back = "/Users/Blocain/Downloads/idcard_back.png";
//        String sideFront = "front";
//        String sideBack = "back";
//        String image = front;
//        String idCardSide = sideFront;
//        OcrResponse response = baiduOcrUtil.idCardImage(image, idCardSide);
//        System.out.println(JSON.toJSONString(response));
//    }
//
//    @Test
//    public void testBase64(){
//        String imageBase64 = "";
//        String sideFront = "front";
//        String sideBack = "back";
//        String idCardSide = sideFront;
//        OcrResponse response = baiduOcrUtil.idCardBase64(imageBase64, idCardSide);
//        System.out.println("base64-ocr识别结果");
//        System.out.println(JSON.toJSONString(response));
//    }
//}
