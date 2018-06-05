package com.athongkun.controller;

import java.util.HashMap;
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
import com.athongkun.controller.common.BaseController;
import com.athongkun.service.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/list")
	public String list() {
		return "role/list";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "role/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")Integer id, Model model ) {
		
		Role role = roleService.queryById(id);
		model.addAttribute("role", role);
		
		return "role/edit";
	}
	
	@RequestMapping("/assign/{id}")
	public String assign( @PathVariable("id")Integer id, Model model ) {
		
		Role role = roleService.queryById(id);
		model.addAttribute("role", role);
		
		return "role/assign";
	}
	
	@ResponseBody
	@RequestMapping("/doAssign")
	public Object doAssign( Integer roleid, Datas ds ) {
		start();
		
		try {
			roleService.insertRolePermissions(roleid, ds);
			success();
 		} catch ( Exception e ) {
 			e.printStackTrace();
 			fail();
 		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		start();
		
		try {
			 
			int count = roleService.deleteByIds(ds);
			if ( count == ds.getIds().size() ) {
				success();
			} else {
				fail();
			}
		} catch( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( Integer id ) {
		
		start();
		
		try {
			
			int count = roleService.deleteById(id);
			if ( count == 1 ) {
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
	public Object update( Role role ) {
		start();
		
		try {
			
			int count = roleService.updateRole(role);
			if ( count == 1 ) {
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
	@RequestMapping("/insert")
	public Object insert( Role role ) {
		start();
		
		try {
			roleService.insertRole(role);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery( String querytext, Integer pageno, Integer pagesize ) {
		start();
		
		try {
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("querytext", querytext);
			paramMap.put("start", (pageno-1)*pagesize);
			paramMap.put("size", pagesize);
			
			Page<Role> rolePage = roleService.queryRolePage(paramMap);
			param( "page", rolePage );
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
}
