package com.athongkun.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athongkun.bean.Datas;
import com.athongkun.bean.Page;
import com.athongkun.bean.Role;
import com.athongkun.bean.User;
import com.athongkun.controller.common.BaseController;
import com.athongkun.service.RoleService;
import com.athongkun.service.UserService;
import com.athongkun.utils.Const;
import com.athongkun.utils.DateUtil;
import com.athongkun.utils.MD5Util;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/test")
	public String test() {
		return "jsp/user/test";
	}
	
	/** 
	* @Description: 账户列表 
	* @Param: [] 
	* @return: java.lang.String 
	* @Author: HeHang
	* @Date: 2018/6/5 
	*/
	@RequestMapping("/list")
	public String list() {
		return "user/list";
	}
	
	/** 
	* @Description: 新增账户页面 
	* @Param: [] 
	* @return: java.lang.String 
	* @Author: HeHang
	* @Date: 2018/6/5 
	*/
	@RequestMapping("/add")
	public String add() {
		return "user/add";
	}
	
	/** 
	* @Description: 修改账户页面
	* @Param: [id, model] 
	* @return: java.lang.String 
	* @Author: HeHang
	* @Date: 2018/6/5 
	*/
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")Integer id, Model model ) {
		
		User user = userService.queryUserById(id);
		model.addAttribute("user", user);		
		return "user/edit";
	}
	
	
	@ResponseBody
	@RequestMapping("/unAssign")
	public Object unAssign( Integer userid, Datas ds ) {
		start();
		
		try {
			userService.deleteUserRoles(userid, ds);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/doAssign")
	public Object doAssign( Integer userid, Datas ds ) {
		start();
		
		try {
			
			userService.insertUserRoles(userid, ds);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@RequestMapping("/assign/{id}")
	public String assign( @PathVariable("id")Integer id, Model model ) {
		User user = userService.queryUserById(id);
		model.addAttribute("user", user);
		
		// 获取所有的角色数据 [1,2,3,4]
		List<Role> roles = roleService.queryAll();
		
		// [1]
		List<Integer> roleids = userService.queryRolesByUserid(id);
		
		// 获取当前用户已经分配的角色列表
		List<Role> assignList = new ArrayList<Role>();
		
		// 获取当前用户未分配的角色列表
		List<Role> unassignList = new ArrayList<Role>();
		
		roles.forEach(role -> {
			if(roleids.contains(role.getId())){
				assignList.add(role);
			} else {
				unassignList.add(role);
			}
		});
//		for ( Role role : roles ) {
//			if ( roleids.contains(role.getId()) ) {
//				assignList.add(role);
//			} else {
//				unassignList.add(role);
//			}
//		}
		
		model.addAttribute("assignList", assignList);
		model.addAttribute("unassignList", unassignList);
		
		return "user/assign";
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			int count = userService.deleteUsersByIds(ds);
			if ( count == ds.getIds().size() ) {
				resultMap.put(Const.RESULT_OUT_SUCCESS, Const.RESULT_SUCCESS);
			} else {
				resultMap.put(Const.RESULT_OUT_SUCCESS, Const.RESULT_FAIL);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put(Const.RESULT_OUT_SUCCESS, Const.RESULT_FAIL);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id ) {
		start();
		
		try {
			int cnt = userService.deleteUserById(id);
			if ( cnt == 1 ) {
				success();
			} else {
				fail();
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		return end();
		
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( User user ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			int cnt = userService.updateUser(user);
			if ( cnt == 1 ) {
				resultMap.put(Const.RESULT_OUT_SUCCESS, Const.RESULT_SUCCESS);
			} else {
				resultMap.put(Const.RESULT_OUT_SUCCESS, Const.RESULT_FAIL);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put(Const.RESULT_OUT_SUCCESS, Const.RESULT_FAIL);
		}
		
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/insertUsers")
	//(OK)public Object insertUsers(String[] loginacct, String[] username， String[] email, String[] address) {
	//(X)public Object insertUsers(User[] user) {
	//(X)public Object insertUsers(List<User> user) {
	//(X)public Object insertUsers(Map user) {
	public Object insertUsers(Datas ds) {
		
		// 将页面中的多条数据在后台封装成集合对象
		//users[0].loginacct=zhangsan
		// 1. 页面中传递的表单元素name属性应该增加集合的索引值
		//     users[0].loginacct
		// 2. 后台需要准备包装类，类中增加数据对应的集合属性
		//    private List<User> users
		// 3. 如果前台页面的数据传递的索引中间有中断的场合，那么后台集合数据也会封装成对象，
		//    但是没有任何的属性值, 所以需要特殊处理
		
		/*
		for ( User user : ds.getUsers() ) {
			if ( user.getLoginacct() == null ) {
				ds.getUsers().remove(user);
			}
		}
		*
		*/
		Iterator<User> userIter = ds.getUsers().iterator();
		while ( userIter.hasNext() ) {
			User user = userIter.next();
			if ( user.getLoginacct() == null ) {
				userIter.remove();
			}
		}
		
		return new Object();
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( User user ) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			user.setUserpswd(MD5Util.digest("123123", (s) -> "123123"));
			// 将时间转换为特定格式的字符串
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//user.setCreatetime(sdf.format(new Date()));
			user.setCreatetime(DateUtil.getNow());
			userService.insertUser(user);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String querytext, Integer pageno, Integer pagesize ) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("start", (pageno-1)*pagesize);
			paramMap.put("size", pagesize);
			//querytext.replaceAll(regex, replacement)
			paramMap.put("querytext", querytext);
			
			Page<User> userPage = userService.queryUser4Page(paramMap);
			
			userPage.setPageno(pageno);
			userPage.setPagesize(pagesize);
			resultMap.put("page", userPage);
			resultMap.put("success", true);
		} catch ( Exception e ) {
			e.printStackTrace();
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
}
