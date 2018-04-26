package com.athongkun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athongkun.bean.Permission;
import com.athongkun.dao.PermissionDao;
import com.athongkun.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	public List<Permission> queryAll() {
		return permissionDao.queryAll();
	}

	public void insertPermission(Permission p) {
		permissionDao.insertPermission(p);
	}

	public Permission queryById(Integer id) {
		return permissionDao.queryById(id);
	}

	public void updatePermission(Permission p) {
		permissionDao.updatePermission(p);
	}

	public void deletePermissionById(Integer id) {
		permissionDao.deletePermissionById(id);
	}

	public List<Integer> queryRolePermissionIdsByRoleid(Integer roleid) {
		return permissionDao.queryRolePermissionIdsByRoleid(roleid);
	}
}
