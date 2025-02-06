package com.anyex.apps.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * <p>File：BaseEntity.java</p>
 * <p>Title: BaseEntity</p>
 * <p>Description:基础实体对象</p>
 * <p>Copyright: Copyright (c) 2015/04/21 11:52</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public abstract class GenericEntity implements Serializable
{
    /**
     * 主键编号
     */
    @Id
    @ApiModelProperty(value = "记录唯一ID", position = 0)
    //@JsonSerialize(using = ToStringSerializer.class)
    protected Long    id;
    
    /**
     * 数据版本号
     */
    @JsonIgnore
    @ApiModelProperty(value = "数据版本号", hidden = true)
    protected Long    version = Long.valueOf(1);
    
    /**
     * 时间字段
     */
    @JsonIgnore
    @ApiModelProperty(value = "时间字段", hidden = true)
    private String    dateField;
    
    /**
     * 删除标识
     */
    @JsonIgnore
    @ApiModelProperty(value = "删除标识", hidden = true)
    protected Boolean delFlag = false;
}
