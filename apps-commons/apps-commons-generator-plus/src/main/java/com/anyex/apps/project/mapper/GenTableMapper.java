package com.anyex.apps.project.mapper;

import com.anyex.apps.project.domain.GenTable;
import com.anyex.apps.project.domain.GenTableColumn;
import com.anyex.apps.project.domain.SysDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务 数据层
 *
 * @author ruoyi
 */
@Mapper
public interface GenTableMapper {
    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
    public List<GenTable> selectGenTableList(GenTable genTable);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    public GenTable selectGenTableByName(@Param("tableName") String tableName);

    /**
     * 查询所有表信息
     *
     * @param dataSourceId 数据源主键
     * @return 表信息集合
     */
    public List<GenTable> selectGenTableAll(@Param("dataSourceId") Long dataSourceId);

    /**
     * 查询表ID业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    public GenTable selectGenTableById(@Param("tableId")Long id);

    /**
     * 新增业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    public int insertGenTable(GenTable genTable);

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    public int updateGenTable(GenTable genTable);

    /**
     * 批量删除业务
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteGenTableByIds(@Param("ids")Long[] ids);

    /**
     * 查询据库列表
     *
     * @param genTable   业务信息
     * @param dbType     数据库类型
     * @param dataSource 数据源
     * @return 数据库表集合
     */
    public List<GenTable> selectDbTableList(@Param("genTable") GenTable genTable, @Param("dbType") String dbType, @Param("dataSource") SysDataSource dataSource);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @param dbType     数据库类型
     * @param dataSource 数据源
     * @return 数据库表集合
     */
    public List<GenTable> selectDbTableListByNames(@Param("tableNames") String[] tableNames, @Param("dbType") String dbType, @Param("dataSource") SysDataSource dataSource);

    /**
     * 根据表名称查询列信息
     *
     * @param tableName  表名称
     * @param dbType     数据库类型
     * @param dataSource 数据源
     * @return 列信息
     */
    public List<GenTableColumn> selectDbTableColumnsByName(@Param("tableName") String tableName, @Param("dbType") String dbType, @Param("dataSource") SysDataSource dataSource);
}