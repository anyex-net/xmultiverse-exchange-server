package com.anyex.apps.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ZTreeModel Introduce
 * <p>File：ZTreeModel.java </p>
 * <p>Title: ZTreeModel </p>
 * <p>Description:ZTreeModel </p>
 * <p>Copyright: Copyright (c) 17/6/22</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class ZTreeModel implements Serializable
{
    private static final long serialVersionUID = 4813767839529192739L;

    @JsonSerialize(using = ToStringSerializer.class)
    private String            id;

    @JsonSerialize(using = ToStringSerializer.class)
    private String            parentId;
    
    private String            text;
    
    private String            url;
    
    private boolean           leaf;
    
    private boolean           open;
    
    private Integer           isOpen;
    
    private Integer           depath;
    
    private Integer           sort;
    
    private boolean           checked          = false;
    
    private boolean           show             = false;
    
    public List<ZTreeModel>   children;
}
