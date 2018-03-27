package com.athongkun.controller.console;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athongkun.bean.OBJECT_T_MALL_SKU;
import com.athongkun.bean.T_MALL_SKU_ATTR_VALUE;
import com.athongkun.bean.T_MALL_VALUE;
import com.athongkun.service.SearchRedisServiceInf;
import com.athongkun.utils.JedisPoolUtils;
import com.athongkun.utils.MyJsonUtil;

import redis.clients.jedis.Jedis;

@Controller
public class SearchRedisController {

	@Autowired
	SearchRedisServiceInf searchRedisServiceImp;

	/***
	 * 分类属性检索sku结果
	 * 
	 * @param class_2_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_sku_list_by_class_2_attr_value")
	public int get_sku_list_by_class_2_attr_value(int class_2_id, @RequestParam("attr_array[]") int[] attr_array) {

		Jedis jedis = JedisPoolUtils.getJedis();

		int sum = 0;

		for (int i = 0; i < attr_array.length; i++) {
			List<T_MALL_VALUE> list_value = searchRedisServiceImp.get_value_by_attr_id(attr_array[i]);

			for (int j = 0; j < list_value.size(); j++) {

				T_MALL_SKU_ATTR_VALUE t_MALL_SKU_ATTR_VALUE = new T_MALL_SKU_ATTR_VALUE();
				t_MALL_SKU_ATTR_VALUE.setShxm_id(attr_array[i]);
				t_MALL_SKU_ATTR_VALUE.setShxzh_id(list_value.get(j).getId());

				List<T_MALL_SKU_ATTR_VALUE> list_attr_value = new ArrayList<T_MALL_SKU_ATTR_VALUE>();

				list_attr_value.add(t_MALL_SKU_ATTR_VALUE);
				// 检索sku列表集合
				List<OBJECT_T_MALL_SKU> list_object_sku = searchRedisServiceImp
						.get_sku_by_class_2_attr_value(class_2_id, list_attr_value, null);

				// 生成redis的key
				String key = "attr_value_" + class_2_id + "_" + attr_array[i] + "_" + list_value.get(j).getId();
				jedis.del(key);// 刷新缓存之前，清理下对应的key
				for (int k = 0; k < list_object_sku.size(); k++) {
					String object_sku = MyJsonUtil.object_to_json(list_object_sku.get(k));
					jedis.zadd(key, k, object_sku);

					sum++;
				}
			}
		}

		// 将查询结果存入redis

		return sum;
	}

	/***
	 * 二级分类检索sku结果
	 * 
	 * @param class_2_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_sku_by_class_2")
	public int get_sku_by_class_2(int class_2_id) {

		// 检索sku列表集合
		List<OBJECT_T_MALL_SKU> list_object_sku = searchRedisServiceImp.get_sku_by_class_2_attr_value(class_2_id, null,
				null);

		// 将查询结果存入redis
		Jedis jedis = JedisPoolUtils.getJedis();

		String key = "class_2_id_" + class_2_id;
		jedis.del(key);// 刷新缓存之前，清理下对应的key
		for (int i = 0; i < list_object_sku.size(); i++) {
			String object_sku = MyJsonUtil.object_to_json(list_object_sku.get(i));
			jedis.zadd(key, i, object_sku);
		}

		return list_object_sku.size();
	}

	@RequestMapping("goto_search_redis")
	public String goto_search_redis() {

		return "jsp/admin/manager_search_redis";
	}
}
