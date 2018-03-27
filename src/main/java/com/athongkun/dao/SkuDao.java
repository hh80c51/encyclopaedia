package com.athongkun.dao;

import java.util.HashMap;
import java.util.List;

import com.athongkun.bean.T_MALL_PRODUCT;
import com.athongkun.bean.T_MALL_SKU;

public interface SkuDao {

	List<T_MALL_PRODUCT> select_spu_by_ppid_class2id(HashMap<String, Object> hashMap);

	void insert_sku(T_MALL_SKU sku);

	void insert_sku_attr_values(HashMap<String, Object> hashMap);

}
