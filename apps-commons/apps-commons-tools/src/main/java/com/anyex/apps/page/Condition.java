//package com.anyex.apps.page;
//
//import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import org.apache.commons.lang3.math.NumberUtils;
//
//import java.util.List;
//
///**
// * @Author
// * @Date 2023/4/25 16:55
// */
//public class Condition {
//    /**
//     * 构造查询分页请求
//     *1
//     * @param query 前端分页请求
//     * @return
//     */
//    public static <T> Page<T> getPage(Query query) {
//        // 构造分页请求
//        Page<T> page = new Page<>(NumberUtils.toInt(String.valueOf(query.getPageNo()),0), NumberUtils.toInt(String.valueOf(query.getPageSize()),10));
//        return page;
//    }
//
//    /**
//     * 列表页面
//     * 集合转换
//     *
//     * @param list 列表
//     * @param page 页面
//     * @param size 大小
//     * @return {@link Page}<{@link T}>
//     */
//    public static <T> Page<T> listToPage(List<T> list, Integer page, Integer size){
//        Page<T> page1 = new Page<>(page,size);
//        page1.setTotal(list.size());
//        int startIndex = (page - 1) * size;
//        if(CollectionUtils.isEmpty(list)){
//            page1.setRecords(null);
//        }
//        else {
//            int endIndex = page * size;
//            //集合的截取 startIndex(包含) endIndex(不包含)
//            page1.setRecords(list.subList(startIndex,endIndex > list.size() ? list.size() : endIndex));
//        }
//        return page1;
//    }
//}
