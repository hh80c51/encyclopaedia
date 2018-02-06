package com.athongkun.service;

import java.util.List;

import com.athongkun.bean.T_MALL_PRODUCT;
import com.athongkun.bean.T_MALL_SKU;
import com.athongkun.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuServiceInf {

	public List<T_MALL_PRODUCT> get_spu_by_ppid_class2id(int class_1_id, int class_2_id, int pp_id);

	public void add_sku(List<T_MALL_SKU_ATTR_VALUE> list, T_MALL_SKU sku);
}
