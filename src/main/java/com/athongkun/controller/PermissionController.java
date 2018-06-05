package com.athongkun.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athongkun.bean.Permission;
import com.athongkun.controller.common.BaseController;
import com.athongkun.service.PermissionService;

@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController {

	@Autowired
	private PermissionService permissionService;
	
	/** 
	* @Description: 许可管理：菜单配置 
	* @Param: [] 
	* @return: java.lang.String 
	* @Author: HeHang
	* @Date: 2018/6/5 
	*/
	@RequestMapping("/list")
	public String list() {
		return "permission/list";
	}
	
	/** 
	* @Description: 增加菜单 
	* @Param: [] 
	* @return: java.lang.String 
	* @Author: HeHang
	* @Date: 2018/6/5 
	*/
	@RequestMapping("/add")
	public String add() {
		return "permission/add";
	}
	
	/** 
	* @Description: 修改菜单 
	* @Param: [id, model] 
	* @return: java.lang.String 
	* @Author: HeHang
	* @Date: 2018/6/5 
	*/
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id")Integer id, Model model) {
		Permission p = permissionService.queryById(id);
		model.addAttribute("p", p);
		return "permission/edit";
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id ) {
		
		start();
		
		try {
			permissionService.deletePermissionById(id);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update( Permission p ) {
		
		start();
		
		try {
			permissionService.updatePermission(p);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	/** 
	* @Description: 增加菜单
	* @Param: [p]
	* @return: java.lang.Object
	* @Author: HeHang
	* @Date: 2018/6/5
	*/
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( Permission p ) {
		
		start();
		
		try {
			permissionService.insertPermission(p);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}

	@ResponseBody
	@RequestMapping("/load")
	public Object load() {
		
		start();
		
		try {
			
			// 查询许可数据
			List<Permission> permissions = permissionService.queryAll();
			Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
			List<Permission> roots = new ArrayList<Permission>();
			
			permissions.forEach(p -> permissionMap.put(p.getId(), p));
//			for ( Permission p : permissions ) {
//				permissionMap.put(p.getId(), p);
//			}
			
			permissions.forEach(p -> {
				Permission childPermission = p;
				if ( p.getPid() == 0 ) {
					roots.add(p);
				} else {
					Permission parentPermission = permissionMap.get(childPermission.getPid());
					parentPermission.getChildren().add(childPermission);
				}
			});
			
//			for ( Permission p : permissions ) {
//				Permission childPermission = p;
//				if ( p.getPid() == 0 ) {
//					roots.add(p);
//				} else {
//					Permission parentPermission = permissionMap.get(childPermission.getPid());
//					parentPermission.getChildren().add(childPermission);
//				}
//			}
			param("nodes", roots);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/loadTree")
	public Object loadTree() {
			
		// 查询许可数据
		List<Permission> permissions = permissionService.queryAll();
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		List<Permission> roots = new ArrayList<Permission>();
		
		for ( Permission p : permissions ) {
			permissionMap.put(p.getId(), p);
		}
		
		for ( Permission p : permissions ) {
			Permission childPermission = p;
			if ( p.getPid() == 0 ) {
				roots.add(p);
			} else {
				Permission parentPermission = permissionMap.get(childPermission.getPid());
				parentPermission.getChildren().add(childPermission);
			}
		}
		
		return roots;
	}
	
	@ResponseBody
	@RequestMapping("/loadCheckedTree")
	public Object loadCheckedTree( Integer roleid ) {
			
		// 查询许可数据
		List<Permission> permissions = permissionService.queryAll();
		Map<Integer, Permission> permissionMap = new HashMap<Integer, Permission>();
		List<Permission> roots = new ArrayList<Permission>();
		
		// 获取当前角色已经分配的许可数据
		List<Integer> permissionids = permissionService.queryRolePermissionIdsByRoleid(roleid);
		
		for ( Permission p : permissions ) {
			if ( permissionids.contains(p.getId()) ) {
				p.setChecked(true);
			} else {
				p.setChecked(false);
			}
			permissionMap.put(p.getId(), p);
		}
		
		for ( Permission p : permissions ) {
			Permission childPermission = p;
			if ( p.getPid() == 0 ) {
				roots.add(p);
			} else {
				Permission parentPermission = permissionMap.get(childPermission.getPid());
				parentPermission.getChildren().add(childPermission);
			}
		}
		
		return roots;
	}
}
