package com.athongkun.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.athongkun.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.athongkun.bean.OBJECT_T_MALL_ATTR;
import com.athongkun.bean.T_MALL_PRODUCT;
import com.athongkun.bean.T_MALL_SKU;
import com.athongkun.service.AttrServiceImp;
import com.athongkun.service.SkuServiceImp;

@Controller
public class SkuController {

	@Autowired
	SkuServiceImp skuService;

	@Autowired
	AttrServiceImp attrServiceImp;

	@RequestMapping("add_sku")
	public ModelAndView add_sku(MODEL_T_MALL_SKU_ATTR_VALUE list_attr_value, int shp_id, T_MALL_SKU sku) {
		// int testDb = indexservice.testDb();
		sku.setShp_id(shp_id);
		skuService.add_sku(list_attr_value.getList_attr_value(), sku);
		String title = "库存信息发布";
		String encode = "";
		try {
			encode = URLEncoder.encode(title, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/index.do?title=" + encode + "&url=goto_sku_publish/.do");
		return modelAndView;
	}

	@RequestMapping("sku_get_attr_list_by_class_2_id")
	public String sku_get_attr_list_by_class_2_id(int class_2_id, ModelMap map) {

		// 根据二级分类id查询对应的分类属性集合
		List<OBJECT_T_MALL_ATTR> list_attr_value = attrServiceImp.get_attr_list_by_class_2_id(class_2_id);
		map.put("list_attr_value", list_attr_value);
		return "manager_sku_publish_inner";
	}

	@ResponseBody
	@RequestMapping("get_spu_by_ppid_class2id")
	public List<T_MALL_PRODUCT> get_spu_by_ppid_class2id(int class_1_id, int class_2_id, int pp_id) {
		List<T_MALL_PRODUCT> list_product = skuService.get_spu_by_ppid_class2id(class_1_id, class_2_id, pp_id);
		return list_product;
	}

	@RequestMapping("goto_sku_publish/{success}")
	public String goto_sku_publish(@PathVariable String success) {
		// int testDb = indexservice.testDb();
		return "manager_sku_publish";
	}

}
