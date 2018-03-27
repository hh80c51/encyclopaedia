<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />

<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>

<script type="text/javascript">

	$(function(){
		var title = "${title}";
		var url = "${url}";
		if(title!=""){
			index_add_tabs2(title,url);
		}
	});
	
	function index_add_tabs(title,url){
		if(!$("#index_center").tabs('exists',title)){
			$("#index_center").tabs('add',{
				 title:title,    
				 href:url,
				 closable:true
			}); 
		}
	}
	
	function index_add_tabs2(title,url){
		if(!$("#index_center").tabs('exists',title)){
			$.get(url,function(data){
				$("#index_center").tabs('add',{
					 title:title,    
					 content:data,
					 closable:true
				}); 
				
			})
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理主页面</title>
</head>
<body  class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">north region</div>
	<div data-options="region:'west',split:true,title:'West'" style="width:150px;padding:10px;">
		<ul class="easyui-tree">
			<li>
				<span>商品发布模块</span>
				<ul>
					<li><a href="javascript:index_add_tabs2('商品信息发布','goto_spu_publish/.do');">商品信息发布</a></li><!-- goto_spu_publish/.do -->
					<li><a href="javascript:index_add_tabs2('属性信息发布','goto_attr_publish/.do');">属性信息发布</a></li><!-- goto_attr_publish/.do -->
					<li><a href="javascript:index_add_tabs2('库存信息发布','goto_sku_publish/.do');">库存信息发布</a></li><!-- goto_sku_publish/.do -->
				</ul>
			</li>
			
			<li>
				<span>订单管理模块</span>
				<ul>
					<li>订单查询</li>
					<li>订单修改</li>
					<li>订单删除</li>
				</ul>
			</li>
			
			<li>
				<span>缓存管理模块</span>
				<ul>
					<li><a href="javascript:index_add_tabs2('分类检索缓存管理','goto_search_redis.do');" >检索缓存管理</a></li>
					<li>用户缓存管理</li>
				</ul>
			</li>
		</ul>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width:100px;padding:10px;">east region</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center',title:'Center'">
		<div class="easyui-tabs" id="index_center" style="height:700px"></div>
	</div>

</body>
</html>