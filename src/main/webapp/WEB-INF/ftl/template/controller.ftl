package ${packageName}.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ${packageName}.bean.Datas;
import ${packageName}.bean.Page;
import ${packageName}.bean.${className};
import ${packageName}.common.BaseController;
import ${packageName}.service.${className}Service;

@Controller
@RequestMapping("/${className?lower_case}")
public class ${className}Controller extends BaseController {

	@Autowired
	private ${className}Service ${className?lower_case}Service;
	
	@RequestMapping("/list")
	public String list() {
		return "${className?lower_case}/list";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "${className?lower_case}/add";
	}
	
	@RequestMapping("/edit/{id}")
	public String edit( @PathVariable("id")Integer id, Model model ) {
		
		${className} ${className?lower_case} = ${className?lower_case}Service.queryById(id);
		model.addAttribute("${className?lower_case}", ${className?lower_case});
		
		return "${className?lower_case}/edit";
	}
	
	@ResponseBody
	@RequestMapping("/deletes")
	public Object deletes( Datas ds ) {
		start();
		
		try {
			 
			int count = ${className?lower_case}Service.deleteByIds(ds);
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
			
			int count = ${className?lower_case}Service.deleteById(id);
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
	public Object update( ${className} ${className?lower_case} ) {
		start();
		
		try {
			
			int count = ${className?lower_case}Service.update${className}(${className?lower_case});
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
	public Object insert( ${className} ${className?lower_case} ) {
		start();
		
		try {
			${className?lower_case}Service.insert${className}(${className?lower_case});
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
			
			Page<${className}> ${className?lower_case}Page = ${className?lower_case}Service.query${className}Page(paramMap);
			param( "page", ${className?lower_case}Page );
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
}
