package com.anyex.apps.utils;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import com.anyex.apps.exception.BusinessException;
import com.anyex.apps.model.TreeModel;
import com.anyex.apps.shiro.model.UserPrincipal;
import com.anyex.apps.system.entity.SysResources;
import com.anyex.apps.system.entity.SysRoleInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * <p>File：OnLineUserUtils.java </p>
 * <p>Title: 在线用户工具类 </p>
 * <p>Description: OnLineUserUtils </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 20:50</p>
 * <p>Company: AnyEx</p>
 *
 * @author playguy
 * @version 1.0
 */
public class OnLineUserUtils
{
    private static Logger logger = LoggerFactory.getLogger(OnLineUserUtils.class);

    private OnLineUserUtils()
    {
    }

    /**
     * 获取当前登录者对象
     */
    public static UserPrincipal getPrincipal()
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            Object object = subject.getPrincipal();
            if (null != object)
            { return (UserPrincipal) object; }
        }
        catch (UnavailableSecurityManagerException e)
        {
            logger.error(e.getLocalizedMessage());
        }
        catch (InvalidSessionException e)
        {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }

    /**
     * 取用户对应的厂店信息
     * @return
     */
    public static Long getId()
    {
        UserPrincipal principal = getPrincipal();
        return null != principal ? principal.getId() : null;
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }

    /**
     * 取当前用户的菜单
     * @return
     * @throws BusinessException
     */
    public static List<TreeModel> getUserResourcesMenu() throws BusinessException
    {
        TreeSet<TreeModel> zTreeModels = getAllDataMenu();
        List<TreeModel> treeList = Lists.newArrayList();
        for (TreeModel parent : zTreeModels)
        {
            if (null == parent.getPid())
            {
                treeList.add(parent);
            }
            for (TreeModel child : zTreeModels)
            {
                if (parent.getId().equals(child.getPid()))
                {
                    if (parent.getChildren() == null)
                    {
                        parent.setChildren(Lists.newArrayList(child));
                    }
                    else
                    {
                        parent.getChildren().add(child);
                    }
                }
            }
        }
        return treeList;
    }

    /**
     * 取去重后的所有数据(菜单)
     * @return {@link TreeSet< TreeModel >}
     * @throws BusinessException
     */
    protected static TreeSet<TreeModel> getAllDataMenu() throws BusinessException
    {
        UserPrincipal principal = getPrincipal();
        if (null == principal) throw new BusinessException("用户未登录");
        TreeSet<TreeModel> treeModels = Sets.newTreeSet(Comparator.comparingInt(TreeModel::getSort).thenComparingLong(TreeModel::getId));
        TreeModel menu;
        for (SysRoleInfo role : principal.getRoles())
        {
            for (SysResources res : role.getResources())
            {
                if (res.getType()) continue;// 跳过权限
                menu = new TreeModel();
                menu.setId(res.getId());
                menu.setPid(res.getParentId());
                menu.setText(res.getResName());
                menu.setName(res.getResShortUrl().replace("/", ""));
                menu.setTitle(res.getResName());
                menu.setIconCls(res.getIcon());
                menu.setIcon(res.getIcon());
                menu.setAttributes(res.getResUrl());
                menu.setPath(res.getResShortUrl());
                menu.setComponent(res.getResUrl());
                menu.setSort(null == res.getSortNum() ? 99 : res.getSortNum());
                treeModels.add(menu);
            }
        }
        return treeModels;
    }
}
