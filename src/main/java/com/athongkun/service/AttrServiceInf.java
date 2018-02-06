package com.athongkun.service;

import java.util.List;

import com.athongkun.bean.OBJECT_T_MALL_ATTR;

public interface AttrServiceInf {
	public List<OBJECT_T_MALL_ATTR> get_attr_list_by_class_2_id(int class_2_id);

	public void add_attr(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id);

}
