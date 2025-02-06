package com.anyex.apps.framework.web.domain;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 *
 * @author ruoyi
 * @date 2022/3/29
 */
@Data
public class BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableField(exist = false, select = false, whereStrategy = FieldStrategy.NEVER)
    private String searchValue;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(exist = false, select = false, whereStrategy = FieldStrategy.NEVER)
    private Long pageNum;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(exist = false, select = false, whereStrategy = FieldStrategy.NEVER)
    private Long pageSize;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(exist = false, select = false, whereStrategy = FieldStrategy.NEVER)
    private Map<String, Boolean> orderBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(exist = false, select = false, whereStrategy = FieldStrategy.NEVER)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @TableField(exist = false, select = false, whereStrategy = FieldStrategy.NEVER)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @TableField(exist = false, select = false)
    private Map<String, Object> params = new HashMap<>();

    public void setPageNum(String pageNum) {
    }
}