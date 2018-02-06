package com.athongkun.service;

import java.util.List;

import com.athongkun.bean.OBJECT_T_MALL_SKU;
import com.athongkun.bean.T_MALL_SKU_ATTR_VALUE;
import com.athongkun.bean.T_MALL_VALUE;

public interface SearchRedisServiceInf {

	public List<T_MALL_VALUE> get_value_by_attr_id(int i);

	List<OBJECT_T_MALL_SKU> get_sku_by_class_2_attr_value(int class_2_id, List<T_MALL_SKU_ATTR_VALUE> list_attr_value,
			String order);

}
