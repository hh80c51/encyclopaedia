package com.athongkun.controller;

import com.athongkun.bean.Cert;
import com.athongkun.bean.Datas;
import com.athongkun.bean.Page;
import com.athongkun.controller.common.BaseController;
import com.athongkun.service.CertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description: 资质管理
 * @author: hehang
 * @create: 2018-06-05 16:18
 **/
@Controller
@RequestMapping("/cert")
public class CertController extends BaseController {

    @Autowired
    private CertService certService;

    @RequestMapping("list")
    public String list(){
        return "cert/list";
    }

    @RequestMapping("/add")
    public String add(){
        return "cert/add";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id")Integer id, Model model){
        Cert cert = certService.queryById(id);
        model.addAttribute("cert",cert);

        return "cert/edit";

    }

    /** 
    * @Description: 批量删除 
    * @Param: [ds] 
    * @return: java.lang.Object 
    * @Author: HeHang
    * @Date: 2018/6/5 
    */
    @ResponseBody
    @RequestMapping("/deletes")
    public Object deletes(Datas ds){
        start();

        try{
            int count = certService.deleteByIds(ds);
            if(count == ds.getIds().size()){
                success();
            }else{
                fail();
            }
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        return end();
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(Integer id){
        start();
        try {
            int count = certService.deleteById(id);
            if(count == 1){
                success();
            }else{
                fail();
            }
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

        return end();
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(Cert cert){
        start();
        try {
            int count = certService.updateCert(cert);
            if(count == 1){
                success();
            }else {
                fail();
            }
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }
        return end();
    }

    @ResponseBody
    @RequestMapping("/insert")
    public Object insert(Cert cert){
        start();
        try {
            certService.insertCert(cert);
            success();
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

        return end();
    }

    @ResponseBody
    @RequestMapping("/pageQuery")
    public Object pageQuery(String querytext, Integer pageno, Integer pagesize) {
        start();

        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("querytext",querytext);
            paramMap.put("start",(pageno-1)*pagesize);
            paramMap.put("size",pagesize);

            Page<Cert> certPage = certService.queryCertPage(paramMap);
            param("page",certPage);
            success();
        }catch (Exception e){
            e.printStackTrace();
            fail();
        }

        return end();
    }
}
