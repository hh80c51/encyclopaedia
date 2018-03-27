package com.athongkun.service;

import java.util.List;
import java.util.Map;

import com.athongkun.bean.Datas;
import com.athongkun.bean.Menu;
import com.athongkun.bean.Page;
import com.athongkun.bean.Permission;
import com.athongkun.bean.User;

public interface UserService {
	public Object queryUser();

	public User queryUserByLogin(User formUser);

	public List<Menu> queryParentMenu();

	public List<Menu> queryChildMenus(Integer id);

	public List<Menu> queryAllMenus();

	public Page<User> queryUser4Page(Map<String, Object> paramMap);

	public void insertUser(User user);

	public User queryUserById(Integer id);

	public int updateUser(User user);

	public int deleteUserById(Integer id);

	public int deleteUsersByIds(Datas ds);

	public List<Integer> queryRolesByUserid(Integer id);

	public void insertUserRoles(Integer userid, Datas ds);

	public void deleteUserRoles(Integer userid, Datas ds);

	public List<Permission> queryPermissionsByUserid(Integer id);
}
