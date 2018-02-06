package com.athongkun.mapper;

import java.util.HashMap;
import java.util.List;

import com.athongkun.bean.OBJECT_T_MALL_SKU;
import com.athongkun.bean.T_MALL_VALUE;

public interface SearchMapper {

	List<T_MALL_VALUE> select_value_by_attr_id(int attr_id);

	List<OBJECT_T_MALL_SKU> select_sku_by_class_2(HashMap<String, Object> hashMap);

	List<OBJECT_T_MALL_SKU> select_sku_by_class_2_attr_value(HashMap<String, Object> hashMap);

}
