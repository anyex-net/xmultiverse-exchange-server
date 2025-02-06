//package com.anyex.apps.bean;
//
//import com.anyex.apps.page.Query;
//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.baomidou.mybatisplus.extension.service.IService;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
///**
// * @description:
// * @author: hb
// * @create: 2021-02-05 13:15
// **/
//public interface BaseService<T, X> extends IService<T> {
//
//    /**
//     * 封装查询条件
//     *
//     * @param t 实体类
//     * @return 查询条件构造器
//     */
//    Wrapper<T> getQueryWrapper(T t);
//
//    /**
//     * 分页查询
//     *
//     * @param t        实体类
//     * @return 分页结果集
//     */
//    Page<T> page(T t, Query query);
//
//    /**
//     * 获取所有列表
//     *
//     * @param t 实体类
//     * @return
//     */
//    List<T> list(T t);
//
//    /**
//     * 获取编辑详情
//     *
//     * @param id 主键Id
//     * @return 编辑详情
//     */
//    T detail(Long id);
//
//    /**
//     * 新增
//     *
//     * @param t 实体类
//     * @throws Exception 异常
//     */
//    void add(T t) throws Exception;
//
//    /**
//     * 编辑
//     *
//     * @param t 实体类
//     * @throws Exception 异常
//     */
//    void update(T t) throws Exception;
//
//    /**
//     * 批量删除
//     *
//     * @param ids 主键Id集合
//     * @throws Exception 异常
//     */
//    void delete(Long[] ids) throws Exception;
//
//    /**
//     * 导入
//     *
//     * @param file 文件
//     * @throws Exception 异常
//     */
//    void importData(MultipartFile file) throws Exception;
//
//    /**
//     * 导入模板下载
//     *
//     * @param exportClass 导出的class类
//     * @throws Exception 异常
//     */
//    void importDemo(Class exportClass) throws Exception;
//
//    /**
//     * 导出
//     *
//     * @param exportClass 导出class类
//     * @param t           实体类查询条件
//     * @throws Exception 异常
//     */
//    void export(Class exportClass, T t) throws Exception;
//
//    /**
//     * 下发命令
//     *
//     * @param id     设备Id
//     * @param method 命令方法
//     * @throws Exception 异常
//     */
//    void issue(Long id, String method) throws Exception;
//
//}
