//package com.anyex.apps.easyexcel;
//
//import com.alibaba.excel.EasyExcel;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//
//import java.io.File;
//
//@Slf4j
//public class EasyExcelTest {
//
//    /**
//     * 最简单的读
//     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
//     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
//     * <p>3. 直接读即可
//     */
//    @Test
//    public void simpleRead() {
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        fileName = "/Users/Blocain/Downloads/demo.xlsx";
//        log.info("fileName:{}", fileName);
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
//    }
//}
