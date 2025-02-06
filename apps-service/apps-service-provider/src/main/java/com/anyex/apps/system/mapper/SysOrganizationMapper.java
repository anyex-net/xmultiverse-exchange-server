/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.mapper;

import com.anyex.apps.bean.GenericMapper;
import com.anyex.apps.system.entity.SysOrganization;
import org.apache.ibatis.annotations.Mapper;

/**
 * 机构信息表 持久层接口
 * <p>File：OrganizationDao.java </p>
 * <p>Title: OrganizationDao </p>
 * <p>Description:OrganizationDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface SysOrganizationMapper extends GenericMapper<SysOrganization> {

}
