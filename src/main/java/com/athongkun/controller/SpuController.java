package com.athongkun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.athongkun.bean.T_MALL_PRODUCT;
import com.athongkun.service.SpuServiceImp;
import com.athongkun.utils.MyUploadUtils;

@Controller
public class SpuController {

	@Autowired
	SpuServiceImp spuservice;

	@RequestMapping(value = "spu_publish", method = RequestMethod.POST)
	public String spu_publish(T_MALL_PRODUCT spu, @RequestParam("files") MultipartFile[] files) {

		// 将图片上传到服务器指定位置
		List<String> images_name = MyUploadUtils.upload_imgs(files);

		// 调用业务层的商品发布的增删改查
		int i = spuservice.spu_publish(spu, images_name);

		return "redirect:/goto_spu_publish/gongxi.do";
	}

	@RequestMapping("goto_spu_publish/{success}")
	public String goto_spu_publish(@PathVariable String success, ModelMap map) {
		map.put("success", success);
		return "manager_spu_publish";
	}

}
