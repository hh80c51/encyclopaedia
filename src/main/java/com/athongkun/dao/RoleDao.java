package com.athongkun.dao;

import java.util.List;
import java.util.Map;

import com.athongkun.bean.Datas;
import com.athongkun.bean.Role;

public interface RoleDao {

	List<Role> queryRoleDatas(Map<String, Object> paramMap);

	int queryRoleCount(Map<String, Object> paramMap);

	void insertRole(Role role);

	Role queryById(Integer id);

	int updateRole(Role role);

	int deleteById(Integer id);

	int deleteByIds(Datas ds);

	List<Role> queryAll();

	void insertRolePermission(Map<String, Object> paramMap);

	void deleteRolePermissionsByRoleid(Integer roleid);

}
