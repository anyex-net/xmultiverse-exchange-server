package com.anyex.apps.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * outlook2 JSON MODEL
 * <p>File：TreeModel.java </p>
 * <p>Title: TreeModel </p>
 * <p>Description:TreeModel </p>
 * <p>Copyright: Copyright (c) 17/6/22</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class TreeModel implements Serializable
{
    private static final long serialVersionUID = 4813767839529192339L;
    
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              id;
    
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              pid;
    
    private String            text;
    private String            name;
    private String            title;
    
    private String            state            = "open";              // open,closed
    
    private boolean           checked          = false;
    
    private Object            attributes;
    private Object            path;
    private Object            component;
    
    private String            iconCls;
    private String            icon;
    
    private String            openMode;

    /**类型（菜单、权限）*/
    private Boolean           type;

    /**资源编码*/
    private String            resCode;

    @JsonIgnore
    private Integer           sort;

    private List<TreeModel>   children;
}
