package com.athongkun.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athongkun.bean.Datas;
import com.athongkun.bean.Menu;
import com.athongkun.bean.Page;
import com.athongkun.bean.Permission;
import com.athongkun.bean.User;
import com.athongkun.dao.UserDao;
import com.athongkun.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public Object queryUser() {
		return null;
	}

	public User queryUserByLogin(User formUser) {
		return userDao.queryUserByLogin(formUser);
	}

	public List<Menu> queryParentMenu() {
		return userDao.queryParentMenu();
	}

	public List<Menu> queryChildMenus(Integer id) {
		return userDao.queryChildMenus(id);
	}

	public List<Menu> queryAllMenus() {
		return userDao.queryAllMenus();
	}

	public Page<User> queryUser4Page(Map<String, Object> paramMap) {
		
		Page<User> userPage = new Page<User>();
		
		// 查询数据
		int pagesize = (Integer)paramMap.get("size");
		
		List<User> users = userDao.queryUsers(paramMap);
		
		// 查询数量
		int count = userDao.queryUserCount(paramMap);
		int totalno = 0;
		// 总页码
		if ( count % pagesize == 0 ) {
			totalno = count / pagesize;
		} else {
			totalno = count / pagesize + 1;
		}
		
		userPage.setDatas(users);
		userPage.setTotalno(totalno);
		userPage.setTotalsize(count);
		
		return userPage;
	}

	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	public User queryUserById(Integer id) {
		return userDao.queryUserById(id);
	}

	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	public int deleteUserById(Integer id) {
		return userDao.deleteUserById(id);
	}

	public int deleteUsersByIds(Datas ds) {
//		int cnt = 0;
//		for ( User user : ds.getUsers() ) {
//			cnt += deleteUserById(user.getId());
//		}
//		//
//		return cnt;
		
		return userDao.deleteUsersByIds(ds);
	}

	public List<Integer> queryRolesByUserid(Integer id) {
		return userDao.queryRolesByUserid(id);
	}

	public void insertUserRoles(Integer userid, Datas ds) {
		Map<String, Object> objMap = new HashMap<String, Object>();
		objMap.put("userid", userid);
		for ( Integer roleid : ds.getIds() ) {
			objMap.put("roleid", roleid);
			userDao.insertUserRole(objMap);
		}
	}

	public void deleteUserRoles(Integer userid, Datas ds) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("roleids", ds.getIds());
		
		userDao.deleteUserRoles(paramMap);
	}

	public List<Permission> queryPermissionsByUserid(Integer id) {
		return userDao.queryPermissionsByUserid(id);
	}
}
