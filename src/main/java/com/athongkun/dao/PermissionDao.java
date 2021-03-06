package com.athongkun.dao;

import java.util.List;

import com.athongkun.bean.Permission;

public interface PermissionDao {

	List<Permission> queryAll();

	void insertPermission(Permission p);

	Permission queryById(Integer id);

	void updatePermission(Permission p);

	void deletePermissionById(Integer id);

	List<Integer> queryRolePermissionIdsByRoleid(Integer roleid);

}
