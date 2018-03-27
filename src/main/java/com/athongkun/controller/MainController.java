package com.athongkun.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athongkun.bean.Permission;
import com.athongkun.bean.User;
import com.athongkun.service.UserService;
import com.athongkun.utils.Const;
import com.athongkun.utils.MD5Util;
import com.athongkun.utils.StringUtil;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;	
	@RequestMapping("/logout")
	public String logout( HttpSession session ) {
		// 删除当前的登陆用户信息
		//session.removeAttribute("loginUser");
		session.invalidate();
		// 跳转回到登陆页面
		return "redirect:/";
	}
	
	@RequestMapping("/login")
	public String login( HttpServletRequest req ) {
		
		boolean needLogin = true;
		
		// 判断是否记住我, 判断cookie是否存在
		Cookie[] cs = req.getCookies();
		if ( cs != null ) {
			for ( Cookie c : cs ) {
				String name = c.getName();
				if ( "login".equals(name) ) {
					String value = c.getValue();
					String[] data = value.split("&"); // loginacct=zhangsn, userpswd=123123
					String loginacct = data[0].split("=")[1];
					String userpswd  = data[1].split("=")[1];
					User user = new User();
					user.setLoginacct(loginacct);
					user.setUserpswd(userpswd);
					User dbUser = userService.queryUserByLogin(user);
					if ( dbUser != null ) {
						needLogin = false;
						req.getSession().setAttribute("loginUser", dbUser);
					}
				}
			}
		}
		
		if ( needLogin ) {
			return "jsp/login";
		} else {
			return "redirect:/main.htm";
		}
	}
	
	@ResponseBody
	@RequestMapping("/dologin")
	public Object dologin( String rememberme, User formUser, HttpServletResponse resp, HttpSession session ) {
		
		// 向页面返回验证结果，结果中应该包含业务成功与否的状态数据以及可能的错误信息
		//AJAXResult result = new AJAXResult(); ==> {success:false,error:"xxxx"}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// ==> {}
		formUser.setUserpswd(MD5Util.digest(formUser.getUserpswd()));
		User dbUser = userService.queryUserByLogin(formUser);
		if ( dbUser == null ) {
			// 用户不存在的场合，跳转回登陆页面
			String error = "用户账号或密码错误，请重新输入";
			// model存储数据等同于存储在请求范围内
			resultMap.put("success", false);
			resultMap.put("error", error);
		} else {
			
			// 登陆成功时，增加记住我的功能
			if ( "1".equals(rememberme) ) {
				String name  = "login";
				String value = "loginacct="+formUser.getLoginacct()+"&userpswd="+formUser.getUserpswd();
				Cookie c = new Cookie(name, value);
				c.setMaxAge(60*60*24*14);
				c.setPath("/");
				resp.addCookie(c);
			}
			
			session.setAttribute(Const.LOGIN_USER, dbUser);
			resultMap.put("success", true);
			
			List<Permission> userPermissions = userService.queryPermissionsByUserid(dbUser.getId());
			
			Set<String> userAuthURLSet = new HashSet<String>();
			
			for ( Permission permission : userPermissions ) {
				if ( StringUtil.isNotEmpty(permission.getUrl()) ) {
					userAuthURLSet.add(permission.getUrl());
				}
			}
			session.setAttribute("authURL", userAuthURLSet);
		}
		return resultMap;
	}	
	
	/**
	 * 如果在开发过程中，发现程序的逻辑调用之间有规律，
	 * 并且方法逻辑调用的参数之间有规律，那么可以采用特殊的算法实现：递归
	 * 递归中一定要包含跳出逻辑算法的操作
	 * @param model
	 * @return
	 */
	@RequestMapping("/main")
	public String main( HttpSession session ) {
		// 查询当前登录用户的所有分配的许可信息（菜单）
		User loginUser = (User)session.getAttribute(Const.LOGIN_USER);
		List<Permission> userPermissions = userService.queryPermissionsByUserid(loginUser.getId());
		
		// 整合许可数据的父子关系
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		
		for ( Permission permission : userPermissions ) {
			permissionMap.put(permission.getId(), permission);
		}
		
		Permission root = null;
		for ( Permission permission : userPermissions ) {
			Permission child = permission;
			
			if ( permission.getPid() == 0 ) {
				root = permission;
			} else {
				Permission parent = permissionMap.get(child.getPid());
				parent.getChildren().add(child);
			}
		}
		
		// 将数据保存到session中
		session.setAttribute("root", root);
		
		return "jsp/main";
	}
	
}
