
package com.athongkun.service;

import java.util.List;
import java.util.Map;

import com.athongkun.bean.Datas;
import com.athongkun.bean.Page;
import com.athongkun.bean.Role;

public interface RoleService {

	Page<Role> queryRolePage(Map<String, Object> paramMap);

	void insertRole(Role role);

	Role queryById(Integer id);

	int updateRole(Role role);

	int deleteById(Integer id);

	int deleteByIds(Datas ds);

	List<Role> queryAll();

	void insertRolePermissions(Integer roleid, Datas ds);

}
