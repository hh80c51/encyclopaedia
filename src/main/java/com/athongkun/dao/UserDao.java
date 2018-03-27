package com.athongkun.dao;

import java.util.List;
import java.util.Map;

import com.athongkun.bean.Datas;
import com.athongkun.bean.Menu;
import com.athongkun.bean.Permission;
import com.athongkun.bean.User;

public interface UserDao {

	User queryUserByLogin(User formUser);

	List<Menu> queryChildMenus(Integer id);

	List<Menu> queryParentMenu();

	List<Menu> queryAllMenus();

	List<User> queryUsers(Map<String, Object> paramMap);

	int queryUserCount(Map<String, Object> paramMap);

	void insertUser(User user);

	User queryUserById(Integer id);

	int updateUser(User user);

	int deleteUserById(Integer id);

	int deleteUsersByIds(Datas ds);

	List<Integer> queryRolesByUserid(Integer id);

	void insertUserRole(Map<String, Object> objMap);

	void deleteUserRoles(Map<String, Object> paramMap);

	List<Permission> queryPermissionsByUserid(Integer id);

}
