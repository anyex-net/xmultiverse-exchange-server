//package com.anyex.apps.bean;
//
//import com.anyex.apps.page.Condition;
//import com.anyex.apps.page.Query;
//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @description:
// * @author: qiuhk
// * @create: 2021-01-22 10:16
// **/
//public class BaseServiceImpl<T, X, M extends BaseMapper<T>> extends ServiceImpl<M, T> implements BaseService<T, X> {
//
//    @Autowired
//    private M baseMapper;
//
//    @Override
//    public Wrapper<T> getQueryWrapper(T t) {
//        QueryWrapper<T> wrapper = new QueryWrapper<T>();
//        return wrapper;
//    }
//
//    @Override
//    public Page<T> page(T t, Query query) {
//        Wrapper<T> queryWrapper = getQueryWrapper(t);
//        return page(Condition.getPage(query), queryWrapper);
//    }
//
//    @Override
//    public List<T> list(T t) {
//        Wrapper<T> queryWrapper = getQueryWrapper(t);
//        return  baseMapper.selectList(queryWrapper);
//    }
//
//    @Override
//    public T detail(Long id) {
//        return (T) baseMapper.selectById(id);
//    }
//
//    @Override
//    public void add(T t) throws Exception {
//        baseMapper.insert(t);
//    }
//
//    @Override
//    public void update(T t) throws Exception {
//        baseMapper.updateById(t);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public void delete(Long[] ids) throws Exception {
//        baseMapper.deleteBatchIds(Arrays.asList(ids));
//    }
//
//    @Override
//    public void importData(MultipartFile file) throws Exception {
//
//    }
//
//    @Override
//    public void importDemo(Class exportClass) throws Exception {
//
//    }
//
//    @Override
//    public void export(Class exportClass, T t) throws Exception {
//
//    }
//
//    @Override
//    public void issue(Long id, String method) throws Exception {
//
//    }
//
//}
