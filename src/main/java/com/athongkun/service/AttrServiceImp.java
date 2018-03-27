package com.athongkun.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athongkun.bean.OBJECT_T_MALL_ATTR;
import com.athongkun.dao.AttrDao;

@Service
public class AttrServiceImp implements AttrServiceInf {

	@Autowired
	AttrDao attrMapper;

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_list_by_class_2_id(int class_2_id) {
		List<OBJECT_T_MALL_ATTR> list_attr_value = attrMapper.select_attr_list_by_class_2_id(class_2_id);
		return list_attr_value;
	}

	public void add_attr(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id) {

		for (int i = 0; i < list_attr.size(); i++) {
			OBJECT_T_MALL_ATTR object_T_MALL_ATTR = list_attr.get(i);
			object_T_MALL_ATTR.setFlbh2(class_2_id);
			attrMapper.insert_attr(object_T_MALL_ATTR);

			HashMap<String, Object> hashMap = new HashMap<String, Object>();

			hashMap.put("shxm_id", object_T_MALL_ATTR.getId());
			hashMap.put("class_2_id", class_2_id);
			hashMap.put("list_value", object_T_MALL_ATTR.getList_value());
			attrMapper.insert_values(hashMap);
		}
	}



}
