package com.anyex.apps.framework.web.domain;

import lombok.Data;

import java.util.List;

/**
 * 分页数据
 *
 * @author mose
 * @date 2022/4/8
 */
@Data
public class PageEntity<T> {
    private static final long serialVersionUID = 1L;

    /**
     * 总数
     */
    private Long total;

    /**
     * 数据列表
     */
    private List<T> rows;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;
}