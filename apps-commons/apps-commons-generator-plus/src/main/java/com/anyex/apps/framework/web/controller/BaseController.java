package com.anyex.apps.framework.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.anyex.apps.common.constant.HttpStatus;
import com.anyex.apps.common.utils.DateUtils;
import com.anyex.apps.common.utils.StringUtils;
import com.anyex.apps.common.utils.sql.SqlUtil;
import com.anyex.apps.framework.web.domain.AjaxResult;
import com.anyex.apps.framework.web.domain.BaseEntity;
import com.anyex.apps.framework.web.domain.PageEntity;
import com.anyex.apps.framework.web.page.PageDomain;
import com.anyex.apps.framework.web.page.TableDataInfo;
import com.anyex.apps.framework.web.page.TableSupport;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * web层通用数据处理
 *
 * @author ruoyi
 */
public class BaseController {
    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     *
     * @param t 泛化实体类
     * @return 搜索参数
     */
    protected <T extends BaseEntity> QueryWrapper<T> initQuery(T t) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>(t);
        Map<String, Boolean> orderBy = t.getOrderBy();
        if (orderBy != null && orderBy.size() > 0) {
            orderBy.forEach((key, value) -> queryWrapper.orderBy(true, value, key));
        }
        return queryWrapper;
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 初始化页面
     *
     * @param t 泛化实体
     * @return mp分页实体
     */
    protected <T extends BaseEntity> IPage<T> initPage(T t) {
        return new Page<>(t.getPageNum(), t.getPageSize());
    }

    /**
     * 获取分页返回数据
     *
     * @param list 列表
     * @return 分页返回数据
     */
    protected <T> PageEntity<T> getTableData(List<T> list) {
        PageEntity<T> result = new PageEntity<>();
        result.setCode(HttpStatus.SUCCESS);
        result.setMsg("操作成功");
        result.setTotal(Long.parseLong(list.size() + ""));
        result.setRows(list);
        return result;
    }

    /**
     * 获取分页返回数据
     *
     * @param page mp分页实体
     * @return 分页返回数据
     */
    protected <T> PageEntity<T> getTableData(IPage<T> page) {
        PageEntity<T> result = new PageEntity<>();
        result.setCode(HttpStatus.SUCCESS);
        result.setMsg("操作成功");
        result.setTotal(page.getTotal());
        result.setRows(page.getRecords());
        return result;
    }

    /**
     * 获取转化后的分页返回数据
     *
     * @param page mp分页实体
     * @param list 列表
     * @return 分页返回数据
     */
    protected <T, S> PageEntity<T> getConvertTableData(IPage<S> page, List<T> list) {
        PageEntity<T> result = new PageEntity<>();
        result.setCode(HttpStatus.SUCCESS);
        result.setMsg("操作成功");
        result.setTotal(page.getTotal());
        result.setRows(list);
        return result;
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    /**
     * 返回错误码消息
     */
    public AjaxResult error(AjaxResult.Type type, String message) {
        return new AjaxResult(type, message);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }
}
