package com.athongkun.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athongkun.bean.Cert;
import com.athongkun.controller.common.BaseController;
import com.athongkun.service.CertService;

@Controller
@RequestMapping("/certtype")
public class CerttypeController extends BaseController {

	@Autowired
	private CertService certService;
	
	@ResponseBody
	@RequestMapping("/insert")
	public Object insert( String accttype, Integer certid ) {
		start();
		
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("accttype", accttype);
			paramMap.put("certid", certid);
			
			certService.insertAccttypeCert(paramMap);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete( String accttype, Integer certid ) {
		start();
		
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("accttype", accttype);
			paramMap.put("certid", certid);
			
			certService.deleteAccttypeCert(paramMap);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@RequestMapping("/list")
	public String list( Model model ) {
		
		List<Cert> certs = certService.queryAll();
		model.addAttribute("certs", certs);
		
		// 查询已经勾选的资质数据
		List<Map<String, Object>> datas = certService.queryAccttypeCerts();
		model.addAttribute("datas", datas);
		
		return "certtype/list";
	}
}
