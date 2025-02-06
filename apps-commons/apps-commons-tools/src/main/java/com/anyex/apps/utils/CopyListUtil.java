package com.anyex.apps.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author changjin wei
 * @since 2022/12/19
 */
public class CopyListUtil {

    public interface target<T,M> {
        T object(M m);
    }

    /**
     * 列表对象拷贝
     * @param sources 源列表
     * @param target 目标列表对象Class
     * @param <T> 目标列表对象类型
     * @param <M> 源列表对象类型
     * @return 目标列表
     */
    public static <T, M> List<T> copyListProperties(List<M> sources, target<T, M> target) {
        if (Objects.isNull(sources) || Objects.isNull(target) || sources.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<T> targets = new ArrayList<>(sources.size());
        for (M source : sources) {
            T t = target.object(source);
            BeanUtils.copyProperties(source, t);
            targets.add(t);
        }
        return targets;
    }

//    public static void main(String[] args) {
//        copyListProperties(new ArrayList<Object>(), o -> {
//            if (o instanceof String) {
//                return new String();
//            }
//            return new Object();
//        });
//    }
}
