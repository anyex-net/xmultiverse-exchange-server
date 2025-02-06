package com.anyex.apps;

import com.anyex.apps.provider.DbModelHelper;
import com.anyex.apps.provider.DbTableProvider;
import com.anyex.apps.model.Table;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件生成器
 * <p>File：GeneratorFile.java</p>
 * <p>Title: GeneratorFile</p>
 * <p>Description:GeneratorFile</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
public class GeneratorFile
{
    GeneratorCore generator = new GeneratorCore();
    
    // 相对于项目的地址
    String        path      = "apps-commons/apps-commons-generator/src/main/resources/template/crud";
    
    /**
     * 删除文件夹
     * @author Playguy
     * @throws IOException
     */
    public void clean() throws IOException
    {
        generator.removeJavaFile();
        generator.removePageFile();
    }
    
    /**
     * 根据指定的表生成文件代码（指定单个表）
     * @author Playguy
     * @param tableName
     * @throws Exception
     */
    public void generateCRUDByTable(String tableName) throws Exception
    {
        generator.templateRootDir = new File(path).getAbsoluteFile();
        generator.modelProvider = new DbTableProvider(tableName);
        generator.generateByModelProvider();
    }
    
    /**
     * 根据指定的表生成文件代码(指定具体的一部份表)
     * @author Playguy
     * @param tableNames
     * @throws Exception
     */
    public void generateCRUDByTables(String[] tableNames) throws Exception
    {
        for (String tableName : tableNames)
        {
            generator.templateRootDir = new File(path).getAbsoluteFile();
            generator.modelProvider = new DbTableProvider(tableName);
            generator.generateByModelProvider();
        }
    }
    
    /**
     * 生成数据库中所有表的文件及代码
     * @author Playguy
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void generateCRUDByAllTable() throws Exception
    {
        generator.templateRootDir = new File(path).getAbsoluteFile();
        List<Table> allTable = DbModelHelper.getInstance().getAllTables();
        for (Table t : allTable)
        {
            if (!"fk_sequence".equals(t.getSqlName()))
            {
                generator.modelProvider = new DbTableProvider(t.getSqlName());
                generator.generateByModelProvider();
            }
        }
    }
}
