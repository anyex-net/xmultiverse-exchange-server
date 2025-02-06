//package com.anyex.apps.bean;
//
//import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
//import com.anyex.apps.aop.AccessLog;
//import com.anyex.apps.model.JsonMessage;
//import com.anyex.apps.page.Query;
//import com.anyex.apps.utils.PoiUtil;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.CollectionUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @description:
// * @author: hb
// * @create: 2021-02-05 13:15
// **/
//@Slf4j
//public class BaseNewNoPermissonController<T, X, M extends BaseService<T, X>> {
//
//    @Autowired
//    private M baseService;
//
//    public M getService() {
//        return baseService;
//    }
//
//    @ApiOperation(value = "获取分页集合", httpMethod = "GET")
//    @GetMapping("/page")
//    @AccessLog(module = "框架抽象", type = "分页查询", desc = "获取分页集合")
//    public JsonMessage<Page<T>> page(Query query, T t) {
//        log.info("query:{}, t:{}", query, t);
//        return JsonMessage.data(baseService.page(t, query));
//    }
//
//    @ApiOperation(value = "获取所有集合", httpMethod = "GET")
//    @GetMapping("/list")
//    @AccessLog(module = "框架抽象", type = "查询所有", desc = "获取所有集合")
//    public JsonMessage<List<T>> list(T t) {
//        log.info("t:{}", t);
//        return JsonMessage.data(baseService.list(t));
//    }
//
//    @ApiOperation(value = "根据Id获取一条记录", httpMethod = "GET")
//    @GetMapping("/detail/{id}")
//    @AccessLog(module = "框架抽象", type = "获取明细", desc = "根据Id获取一条记录")
//    public JsonMessage<T> info(@PathVariable("id") Long id) {
//        log.info("id:{}", id);
//        return JsonMessage.data(baseService.detail(id));
//    }
//
//    @ApiOperation(value = "新增一条记录", httpMethod = "POST")
//    @PostMapping("/add")
//    @AccessLog(module = "框架抽象", type = "新增", desc = "新增一条记录")
//    public JsonMessage save(@RequestBody T t) throws Exception {
//        log.info("t:{}", t);
//        baseService.add(t);
//        return JsonMessage.data("Ok");
//    }
//
//    @ApiOperation(value = "修改一条记录", httpMethod = "PUT")
//    @PutMapping("/edit")
//    @AccessLog(module = "框架抽象", type = "修改", desc = "修改一条记录")
//    public JsonMessage update(@RequestBody T t) throws Exception {
//        log.info("t:{}", t);
//        baseService.update(t);
//        return JsonMessage.data(null);
//    }
//
//    @ApiOperation(value = "根据Id集合删除", httpMethod = "DELETE")
//    @DeleteMapping("/del")
//    @AccessLog(module = "框架抽象", type = "删除", desc = "根据Id集合删除")
//    public JsonMessage delete(@RequestBody Long[] ids) throws Exception {
//        log.info("ids:{}", ids);
//        baseService.delete(ids);
//        return JsonMessage.data(null);
//    }
//
//    @ApiOperation(value = "下发命令", httpMethod = "GET")
//    @GetMapping("/issue")
//    @AccessLog(module = "框架抽象", type = "下发命令", desc = "下发命令")
//    public JsonMessage issueCmd(Long id, String method) throws Exception {
//        log.info("id:{}, method:{}", id, method);
//        baseService.issue(id, method);
//        return JsonMessage.data(null);
//    }
//
//    @ApiOperation(value = "导入excel", httpMethod = "POST")
//    @PostMapping("/import")
//    @AccessLog(module = "框架抽象", type = "导入excel", desc = "导入excel")
//    public void importData(@RequestParam("file") MultipartFile uploadFile, T t) throws Exception {
//        log.info("importData uploadFile:{}", uploadFile);
//        ExcelImportResult<T> result = (ExcelImportResult<T>) PoiUtil.importExcel(uploadFile, 0, 1, true, t.getClass());
//        assert result != null;
//        List<T> list = result.getList();
//        if (!CollectionUtils.isEmpty(list)) {
//            baseService.saveBatch(list, 100);
//        }
//        if (result.isVerifyFail()) {
//            List<T> failList = result.getFailList();
//            int successRows = list.size();
//            int errorRows = failList.size();
//            Workbook failWorkbook = result.getFailWorkbook();
//            PoiUtil.exportExcelWithStream("失败信息列表.xls", failWorkbook, successRows, errorRows);
//        }
//    }
//
//    @ApiOperation(value = "excel导入模板下载", httpMethod = "POST")
//    @PostMapping("/importDemo")
//    @AccessLog(module = "框架抽象", type = "excel导入模板下载", desc = "excel导入模板下载")
//    public void importDemo(X x) throws Exception {
//        log.info("x:{}", x);
//        List<T> list = new ArrayList<>();
//        PoiUtil.exportExcelWithStream("信息列表导入模版demo.xls", x.getClass(), list);
//    }
//
//    @ApiOperation(value = "导出excel", httpMethod = "GET")
//    @GetMapping(value = "/export")
//    @AccessLog(module = "框架抽象", type = "导出excel", desc = "导出excel")
//    public void exportData(X x, T t) throws Exception {
//        log.info("x:{}, t:{}", x, t);
//        PoiUtil.exportExcelWithStream("信息列表.xls", x.getClass(), baseService.list(t));
//    }
//}
