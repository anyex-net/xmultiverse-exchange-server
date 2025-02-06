package com.anyex.apps.provider;

import com.anyex.apps.IModelProvider;
import com.anyex.apps.model.IModel;

/**
 * 数据库表模型的实现类
 * <p>File：DbTableProvider.java</p>
 * <p>Title: DbTableProvider</p>
 * <p>Description:DbTableProvider</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public class DbTableProvider implements IModelProvider
{
    /**
     * 表名
     */
    private String dbTableName;
    
    public DbTableProvider(String dbTableName)
    {
        super();
        this.dbTableName = dbTableName;
    }
    
    public IModel getModel() throws Exception
    {
        return DbModelHelper.getInstance().getTable(dbTableName);
    }
}
