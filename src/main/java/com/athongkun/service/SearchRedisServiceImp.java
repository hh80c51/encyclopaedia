package com.athongkun.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athongkun.bean.OBJECT_T_MALL_SKU;
import com.athongkun.bean.T_MALL_SKU_ATTR_VALUE;
import com.athongkun.bean.T_MALL_VALUE;
import com.athongkun.mapper.SearchMapper;

@Service
public class SearchRedisServiceImp implements SearchRedisServiceInf {

	@Autowired
	SearchMapper searchMapper;

	/***
	 * 根据分类属性查询商品sku集合
	 */
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_attr_value(int class_2_id,
			List<T_MALL_SKU_ATTR_VALUE> list_attr_value, String order) {
		List<OBJECT_T_MALL_SKU> list_object_sku = new ArrayList<OBJECT_T_MALL_SKU>();
		if (list_attr_value == null || list_attr_value.size() == 0) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("class_2_id", class_2_id);
			hashMap.put("order", order);
			list_object_sku = searchMapper.select_sku_by_class_2(hashMap);
		} else {

			StringBuffer sbf = new StringBuffer();

			sbf.append(" and sku.id in ");
			sbf.append(" ( ");
			sbf.append(" select sku_0.sku_id from ");
			for (int i = 0; i < list_attr_value.size(); i++) {
				sbf.append(" (Select sku_id from t_mall_sku_attr_value where shxm_id ="
						+ list_attr_value.get(i).getShxm_id() + " and shxzh_id=" + list_attr_value.get(i).getShxzh_id()
						+ ") sku_" + i + " ");
				if (i < (list_attr_value.size() - 1)) {
					sbf.append(" , ");
				}
			}

			if (list_attr_value.size() > 1) {
				sbf.append(" where ");
			}

			for (int i = 0; i < list_attr_value.size(); i++) {
				if (i < (list_attr_value.size() - 1)) {
					sbf.append("sku_" + i + ".sku_id=sku_" + (i + 1) + ".sku_id");
				}

				if (i < (list_attr_value.size() - 2)) {
					sbf.append(" and ");
				}
			}
			sbf.append(" ) ");

			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("class_2_id", class_2_id);
			hashMap.put("sql", sbf.toString());
			if (order != null && !order.equals("")) {
				hashMap.put("order", order);
			}
			list_object_sku = searchMapper.select_sku_by_class_2_attr_value(hashMap);
		}
		return list_object_sku;
	}

	@Override
	public List<T_MALL_VALUE> get_value_by_attr_id(int attr_id) {
		List<T_MALL_VALUE> list_value = searchMapper.select_value_by_attr_id(attr_id);

		return list_value;

	}

}
