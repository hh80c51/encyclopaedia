package com.athongkun.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.athongkun.bean.T_MALL_SHOPPINGCAR;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MyJsonUtil {

	public static void main(String[] args) {
		List<T_MALL_SHOPPINGCAR> list_car = new ArrayList<T_MALL_SHOPPINGCAR>();

		for (int i = 0; i < 3; i++) {

			T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCAR();

			t_MALL_SHOPPINGCAR.setSku_mch("购物车" + i);
			t_MALL_SHOPPINGCAR.setSku_jg(1000 * i);

			list_car.add(t_MALL_SHOPPINGCAR);

		}

		String object_to_json = object_to_json(list_car);

		List<T_MALL_SHOPPINGCAR> json_to_list = json_to_list(object_to_json, new T_MALL_SHOPPINGCAR());

		System.out.println(json_to_list);

	}

	/***
	 * json集合对象
	 */
	public static <T> List<T> json_to_list(String json, T tt) {
		if (json != null && !json.equals("")) {
			try {
				json = URLDecoder.decode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONArray fromObject = JSONArray.fromObject(json);

			List<T> collection = (List<T>) JSONArray.toCollection(fromObject, tt.getClass());
			return collection;
		} else {
			return null;
		}

	}

	/***
	 * 购物车json转购物车集合对象
	 */
	public static List<T_MALL_SHOPPINGCAR> car_json_to_car_list(String json) {

		Gson gson = new Gson();

		TypeToken<List<T_MALL_SHOPPINGCAR>> t = new TypeToken<List<T_MALL_SHOPPINGCAR>>() {
		};

		try {
			json = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<T_MALL_SHOPPINGCAR> fromJson = (List<T_MALL_SHOPPINGCAR>) gson.fromJson(json, t.getType());

		return fromJson;
	}

	/***
	 * 对象转json
	 * 
	 * @param t
	 * @return
	 */
	public static <T> String object_to_json(T t) {
		Gson gson = new Gson();
		String json = "";
		if (!t.getClass().getSimpleName().equals("String")) {
			json = new String(gson.toJson(t));
		} else {
			json = (String) t;
		}

		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	public void test() {
		List<T_MALL_SHOPPINGCAR> list_car = new ArrayList<T_MALL_SHOPPINGCAR>();

		for (int i = 0; i < 3; i++) {

			T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCAR();

			t_MALL_SHOPPINGCAR.setSku_mch("购物车" + i);
			t_MALL_SHOPPINGCAR.setSku_jg(1000 * i);

			list_car.add(t_MALL_SHOPPINGCAR);

		}
		// 1
		JSONArray fromObject = JSONArray.fromObject(list_car);

		String string = fromObject.toString();

		// 2
		JSONObject fromObject2 = JSONObject.fromObject(list_car.get(0));
		String string2 = fromObject2.toString();

		// 3
		JSONObject fromObject3 = JSONObject.fromObject(string2);

		T_MALL_SHOPPINGCAR bean = (T_MALL_SHOPPINGCAR) JSONObject.toBean(fromObject3, T_MALL_SHOPPINGCAR.class);

		// 4
		JSONArray fromObject4 = JSONArray.fromObject(string);

		List<T_MALL_SHOPPINGCAR> collection = (List<T_MALL_SHOPPINGCAR>) JSONArray.toCollection(fromObject4,
				T_MALL_SHOPPINGCAR.class);

		System.out.println(collection);
	}

}
