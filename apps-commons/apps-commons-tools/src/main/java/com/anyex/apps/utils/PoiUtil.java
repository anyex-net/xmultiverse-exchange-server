package com.anyex.apps.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collection;

public class PoiUtil {
    /**
     * 根据接收的Excel文件来导入Excel,并封装成实体类
     *
     * @param file       上传的文件
     * @param titleRows  表标题的行数
     * @param headerRows 表头行数
     * @param needVerfiy 是否校验
     * @param pojoClass  Excel实体类
     */
    public static <T> ExcelImportResult<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Boolean needVerfiy, Class<T> pojoClass) throws Exception {
        if (file.isEmpty()) {
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        params.setNeedVerify(needVerfiy);
        ExcelImportResult<T> list = ExcelImportUtil.importExcelMore(file.getInputStream(), pojoClass, params);
        return list;
    }

    /**
     * 使用流的方式导出excel
     *
     * @param excelName   要导出的文件名称，如test.xls
     * @param workbook    要导出的数据集合
     * @param successRows successRows
     * @param errorRows   errorRows
     */
    public static void exportExcelWithStream(String excelName, Workbook workbook, Integer successRows, Integer errorRows) throws IOException {
        HttpServletResponse response = ServletsUtils.getResponse();
        String fileName = URLEncoder.encode(excelName, "UTF-8");
        if (response == null) {
            throw new IOException("当前请求参数为空或数据缺失，请联系管理员");
        }
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("successRows", String.valueOf(successRows));
        response.setHeader("errorRows", String.valueOf(errorRows));
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
    }

    /**
     * 使用流的方式导出excel
     *
     * @param excelName 要导出的文件名称，如GunsUsers.xls
     * @param pojoClass Excel实体类
     * @param data      要导出的数据集合
     * @author xuyuxiang
     * @date 2020/7/1 10:00
     */
    public static void exportExcelWithStream(String excelName, Class pojoClass, Collection data) throws IOException {
        HttpServletResponse response = ServletsUtils.getResponse();
        String fileName = URLEncoder.encode(excelName, "UTF-8");
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), pojoClass, data);
        workbook.write(outputStream);
        outputStream.close();
    }

}
