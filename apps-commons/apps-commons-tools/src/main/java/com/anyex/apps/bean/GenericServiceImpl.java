package com.anyex.apps.bean;

import com.anyex.apps.annotation.SlaveDataSource;
import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.utils.SerialnoUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>File：GenericServiceImpl.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-8-7 下午6:10:09</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class GenericServiceImpl<T extends GenericEntity> implements GenericService<T>
{
    private GenericMapper<T> dao;
    
    @Autowired(required = false)
    protected Validator      validator;
    
    public GenericServiceImpl(GenericMapper<T> dao)
    {
        this.dao = dao;
    }
    
    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 jsonMessage rows 中
     * @throws BusinessException
     */
    protected boolean beanValidator(Object object, Class<?> ... groups) throws BusinessException
    {
        try
        {
            BeanValidators.validateWithException(validator, object, groups);
        }
        catch (ConstraintViolationException ex)
        {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            throw new BusinessException(CommonEnums.FAIL, list);
        }
        return true;
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(T entity) throws BusinessException
    {
        this.beanValidator(entity);
        if (null == entity.getId())
        {
            entity.setId(SerialnoUtils.buildPrimaryKey());
            return dao.insert(entity);
        }
        else
        {
            return dao.updateByPrimaryKeySelective(entity);
        }
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insert(T entity) throws BusinessException
    {
        this.beanValidator(entity);
        if (null == entity.getId())
        {
            entity.setId(SerialnoUtils.buildPrimaryKey());
        }
        return dao.insert(entity);
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int delete(Long id) throws BusinessException
    {
        return dao.delete(id);
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int remove(Long id) throws BusinessException
    {
        return dao.remove(id);
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteBatch(Long[] ids) throws BusinessException
    {
        int count = 0;
        for (Long id : ids)
        {
            count += delete(id);
        }
        return count;
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteBatch(String[] ids) throws BusinessException
    {
        int count = 0;
        for (String id : ids)
        {
            count += delete(Long.parseLong(id));
        }
        return count;
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeBatch(String[] ids) throws BusinessException
    {
        int count = 0;
        for (String id : ids)
        {
            count += remove(Long.parseLong(id));
        }
        return count;
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int removeBatch(Long[] ids) throws BusinessException
    {
        int count = 0;
        for (Long id : ids)
        {
            count += remove(id);
        }
        return count;
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insertSelective(T record) throws BusinessException
    {
        dao.insertSelective(record);
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertBatch(List<T> list) throws BusinessException
    {
        int i = list.size();
        if (i <= GlobalConst.DEFAULT_BATCH_SIZE)
        { return dao.insertBatch(list); }
        // 控制批量操作大小
        List<T> data = new ArrayList<>();
        for (int j = 0; j < i; j++)
        {
            data.add(list.get(j));
            if ((j + 1) % GlobalConst.DEFAULT_BATCH_SIZE == 0)
            {
                dao.insertBatch(data);
                data = new ArrayList<>();
            }
        }
        if (data.size() > 0)
        {
            dao.insertBatch(data);
        }
        return i;
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateBatch(List<T> list) throws BusinessException
    {
        return dao.updateBatch(list);
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = true)
    public T selectByPrimaryKey(Long id) throws BusinessException
    {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = true)
    public T selectOne(T record) throws BusinessException
    {
        return dao.selectOne(record);
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(T record) throws BusinessException
    {
        return dao.updateByPrimaryKeySelective(record);
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateByPrimaryKey(T record) throws BusinessException
    {
        return dao.updateByPrimaryKey(record);
    }
    
    @Override
    @SlaveDataSource()
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> findList(T entity) throws BusinessException
    {
        return dao.findList(entity);
    }
    
    @Override
    @SlaveDataSource()
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> selectAll() throws BusinessException
    {
        return dao.selectAll();
    }
    
    @Override
    @SlaveDataSource()
    public PaginateResult<T> search(Pagination pagin, T entity) throws BusinessException
    {
        PageHelper.startPage(pagin.getCurrent(), pagin.getSize());
        PageInfo<T> pageInfo = PageInfo.of(findList(entity));
        pagin.setTotal(pageInfo.getTotal());
        pagin.setCurrent(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
