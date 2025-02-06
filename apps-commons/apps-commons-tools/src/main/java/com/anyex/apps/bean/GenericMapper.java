package com.anyex.apps.bean;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * <p>File：GenericMapper.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-17 下午3:24:01</p>
 * <p>Company: AnyEx</p>
 *
 * @param <T>
 * @author Playguy
 * @version 1.0
 */
@NoRepositoryBean
public interface GenericMapper<T extends GenericEntity>
{
    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    int insert(T entity);
    
    /**
     * 逻辑删除
     *
     * @param id
     * @return
     * @author chenjp
     */
    int delete(Long id);
    
    /**
     * 物理删除
     *
     * @param id
     * @return
     * @see public int delete(T entity)
     */
    int remove(Long id);
    
    /**
     * 根据条件是否插入数据
     *
     * @param record
     */
    void insertSelective(T record);
    
    /**
     * 根据主键查询数据
     *
     * @param id
     * @return
     */
    T selectByPrimaryKey(Long id);

    /**
     * 根据条件查询一条数据
     *
     * @param record
     * @return
     */
    T selectOne(T record);

    /**
     * 选择性更新数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(T record);
    
    /**
     * 根据主键更新一条信息所有数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T record);
    
    /**
     * 查询数据列表
     *
     * @param entity
     * @return
     */
    List<T> findList(T entity);
    
    /**
     * 查询所有数据列表
     *
     * @return
     */
    List<T> selectAll();
    
    /**
     * 批量插入
     *
     * @param list
     * @return
     * @author
     */
    int insertBatch(List<T> list);
    
    /**
     * 批量更新
     *
     * @param list
     * @return
     * @author
     */
    int updateBatch(List<T> list);
}
