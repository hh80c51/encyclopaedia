package com.athongkun.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athongkun.bean.T_MALL_PRODUCT;
import com.athongkun.bean.T_MALL_SKU;
import com.athongkun.bean.T_MALL_SKU_ATTR_VALUE;
import com.athongkun.dao.SkuDao;

@Service
public class SkuServiceImp implements SkuServiceInf {

	@Autowired
	SkuDao skuMapper;

	public List<T_MALL_PRODUCT> get_spu_by_ppid_class2id(int class_1_id, int class_2_id, int pp_id) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("class_1_id", class_1_id);
		hashMap.put("class_2_id", class_2_id);
		hashMap.put("pp_id", pp_id);
		List<T_MALL_PRODUCT> list_product = skuMapper.select_spu_by_ppid_class2id(hashMap);
		return list_product;
	}

	public void add_sku(List<T_MALL_SKU_ATTR_VALUE> list, T_MALL_SKU sku) {
		// 插入发布的sku数据表
		skuMapper.insert_sku(sku);

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("sku", sku);
		hashMap.put("list_attr_value", list);

		// 根据生成的skuid插入对应的属性和属性值关联表数据
		skuMapper.insert_sku_attr_values(hashMap);

	}

}
