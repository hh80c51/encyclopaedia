package com.athongkun.controller.console;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpSession;

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
		return "jsp/admin/manager_index";
	}
	
}
