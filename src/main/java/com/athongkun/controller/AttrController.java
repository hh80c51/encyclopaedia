package com.athongkun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athongkun.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.athongkun.bean.OBJECT_T_MALL_ATTR;
import com.athongkun.service.AttrServiceImp;

@Controller
public class AttrController {

	@Autowired
	AttrServiceImp attrServiceImp;

	@RequestMapping("add_attr")
	public ModelAndView add_attr(MODEL_OBJECT_T_MALL_ATTR list_attr, int class_2_id, String class_2_name) {

		// 业务层的属性添加
		attrServiceImp.add_attr(list_attr.getList_attr(), class_2_id);

		ModelAndView mv = new ModelAndView("redirect:/goto_add_attr/{class_2_id}/{class_2_name}.do");

		mv.addObject("class_2_id", class_2_id);
		mv.addObject("class_2_name", class_2_name);

		return mv;

	}

	@RequestMapping("goto_add_attr/{class_2_id}/{class_2_name}")
	public String goto_add_attr(@PathVariable String class_2_id, @PathVariable String class_2_name) {

		return "manager_add_attr";
	}

	@RequestMapping("goto_attr_publish/{success}")
	public String goto_attr_publish(ModelMap map, @PathVariable String success) {

		map.put("success", success);
		return "manager_attr_publish";
	}

	@ResponseBody
	@RequestMapping("get_attr_list_by_class_2_id")
	public List<OBJECT_T_MALL_ATTR> get_attr_list_by_class_2_id(int class_2_id, ModelMap map) {

		// 根据二级分类id查询对应的分类属性集合
		List<OBJECT_T_MALL_ATTR> list_attr_value = attrServiceImp.get_attr_list_by_class_2_id(class_2_id);
		return list_attr_value;
	}

	/***
	 * 根据二级分类id查询分类属性_json
	 * 
	 * @param class_2_id
	 * @param map
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "get_attr_by_class_2_id_json", method = RequestMethod.POST)
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id_json(int class_2_id) {

		List<OBJECT_T_MALL_ATTR> list_object_attr_value = attrServiceImp.get_attr_list_by_class_2_id(class_2_id);

		return list_object_attr_value;
	}

}
