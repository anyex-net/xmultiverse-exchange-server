package com.anyex.apps.bean;

import com.anyex.apps.consts.GlobalConst;
import com.anyex.apps.enums.CommonEnums;
import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.PaginateResult;
import com.anyex.apps.model.Pagination;
import com.anyex.apps.utils.SerialnoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

/**
 * 非关系型数据库基础实现类
 * <p>File：GenericNoSqlImpl.java</p>
 * <p>Title: GenericNoSqlImpl</p>
 * <p>Description: GenericNoSqlImpl</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
public abstract class GenericNoSqlImpl<T extends GenericEntity> implements GenericNoSql<T> {
    /**
     * 实体对象
     */
    private final Class<T> entity;

    /**
     * 验证Bean实例对象
     */
    @Autowired(required = false)
    protected Validator validator;

    /**
     * MongoDB操作服务
     */
    @Autowired(required = false)
    protected MongoOperations mongoTemplate;

    public GenericNoSqlImpl(Class<T> entity) {
        this.entity = entity;
    }

    @Override
    public void save(T t) throws BusinessException {
        if (!beanValidator(t)) {// 校验参数合法性
            throw new BusinessException(CommonEnums.ERROR_PARAMS_VALID);
        }
        if (null == t.getId()) {
            t.setId(SerialnoUtils.buildPrimaryKey());
        }
        mongoTemplate.save(t, entity.getSimpleName());
    }

    @Override
    public void delete(Long id) throws BusinessException {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.findAndRemove(query, entity);
    }

    @Override
    public void remove(Long id) throws BusinessException {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, entity);
    }

    @Override
    public void insertBatch(List<T> list) throws BusinessException {
        int i = list.size();
        if (i <= GlobalConst.DEFAULT_BATCH_SIZE) {
            mongoTemplate.insert(list, entity);
        }
        // 控制批量操作大小
        List<T> data = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            data.add(list.get(i));
            if (j % GlobalConst.DEFAULT_BATCH_SIZE == 0) {
                mongoTemplate.insert(data, entity);
                data = new ArrayList<>();
            }
        }
    }

    @Override
    public void deleteBatch(Long[] ids) throws BusinessException {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(ids));
        mongoTemplate.updateMulti(query, Update.update("delFlag", 1), entity);
    }

    @Override
    public void removeBatch(Long[] ids) throws BusinessException {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").in(ids));
        mongoTemplate.remove(query, entity);
    }

    @Override
    public T selectByPrimaryKey(Long id) throws BusinessException {
        return mongoTemplate.findById(id, entity);
    }

    @Override
    public List<T> findList(T params) throws BusinessException {
        Query query = new Query();
        query.addCriteria(Criteria.byExample(params));
        return mongoTemplate.find(query, entity);
    }

    @Override
    public List<T> selectAll() throws BusinessException {
        return mongoTemplate.findAll(entity);
    }

    @Override
    public abstract PaginateResult<T> search(Pagination pagin, T entity) throws BusinessException;

    /**
     * 服务端参数有效性验证
     *
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 jsonMessage rows 中
     * @throws BusinessException
     */
    protected boolean beanValidator(Object object, Class<?>... groups) throws BusinessException {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            throw new BusinessException(CommonEnums.FAIL.getCode(), ex.getLocalizedMessage(), list);
        }
        return true;
    }
}
