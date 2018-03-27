package com.athongkun.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.athongkun.bean.T_MALL_PRODUCT;

public interface SpuMapper {

	int insert_product(T_MALL_PRODUCT spu);

	int insert_product_images(@Param("id") int id, @Param("images_name") List<String> images_name);

}
