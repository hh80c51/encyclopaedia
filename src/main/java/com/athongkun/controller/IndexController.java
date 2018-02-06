package com.athongkun.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athongkun.service.IndexServiceImp;

@Controller
public class IndexController {

	@Autowired
	IndexServiceImp indexservice;

	@RequestMapping("index")
	public String index(String title, String url, ModelMap map) {

		if (title != null && url != null) {
			try {
				title = URLDecoder.decode(title, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("title", title);
		map.put("url", url);
		// int testDb = indexservice.testDb();
		return "manager_index";
	}

}
