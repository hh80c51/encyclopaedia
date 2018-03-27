package com.athongkun.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.athongkun.bean.OBJECT_T_MALL_ATTR;

public interface AttrDao {

	List<OBJECT_T_MALL_ATTR> select_attr_list_by_class_2_id(@Param("class_2_id") int class_2_id);

	void insert_attr(OBJECT_T_MALL_ATTR attr);

	void insert_values(HashMap<String, Object> hashMap);


}
