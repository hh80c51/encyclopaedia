package com.athongkun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athongkun.bean.Datas;
import com.athongkun.bean.Page;
import com.athongkun.bean.Role;
import com.athongkun.dao.RoleDao;
import com.athongkun.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	public Page<Role> queryRolePage(Map<String, Object> paramMap) {

		Page<Role> rolePage = new Page<Role>();
		
		List<Role> roles = roleDao.queryRoleDatas(paramMap);
		int count = roleDao.queryRoleCount(paramMap);
		
		rolePage.setDatas(roles);
		rolePage.setTotalsize(count);
		
		return rolePage;
	}

	public void insertRole(Role role) {
		roleDao.insertRole(role);
	}

	public Role queryById(Integer id) {
		return roleDao.queryById(id);
	}

	public int updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	public int deleteById(Integer id) {
		return roleDao.deleteById(id);
	}

	public int deleteByIds(Datas ds) {
		return roleDao.deleteByIds(ds);
	}

	public List<Role> queryAll() {
		return roleDao.queryAll();
	}

	public void insertRolePermissions(Integer roleid, Datas ds) {
		
		// 删除角色的所有许可数据
		roleDao.deleteRolePermissionsByRoleid(roleid);
		
		// 将当前选中的许可全部认为是新的许可
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleid", roleid);
		for ( Integer permissionid : ds.getIds() ) {
			paramMap.put("permissionid", permissionid);
			roleDao.insertRolePermission(paramMap);
		}
	}
}
