/*
 * Copyright 2023 Apps, Inc. All rights reserved. com.anyex
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.anyex.apps.system.service;

import com.anyex.apps.bean.GenericServiceImpl;
import com.anyex.apps.system.entity.SysUserData;
import com.anyex.apps.system.mapper.SysUserDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserData 服务实现类
 * <p>File：UserDataServiceImpl.java </p>
 * <p>Title: UserDataServiceImpl </p>
 * <p>Description:UserDataServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: AnyEx</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class SysUserDataServiceImpl extends GenericServiceImpl<SysUserData> implements SysUserDataService
{
	protected SysUserDataMapper userDataMapper;

	@Autowired(required = false)
	public SysUserDataServiceImpl(SysUserDataMapper userDataMapper)
	{
		super(userDataMapper);
		this.userDataMapper = userDataMapper;
	}
}
