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
<script type="text/javascript">
$(function(){
	// 取得一级分类的json静态数据
	$.getJSON("js/json/class_1.js",function(data){
		// 将json静态数据加载到一级分类下拉列表
		// for(i=0;i<data.length;i++){}
		$(data).each(function(i,json){
			$("#sku_class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
		});
	});
});

function sku_select_class_2_by_class_1(class_1_id){
	// 获取一级分类id
	// $("#sku_class_1_select option:selected").value;
	$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
		// 将json静态数据加载到二级分类下拉列表
		// for(i=0;i<data.length;i++){}
		$("#sku_class_2_select").empty(); 
		$(data).each(function(i,json){
			$("#sku_class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
		});
	});
	// 加载商标的下拉列表
	sku_select_tm_by_class_1(class_1_id);
}

function sku_select_tm_by_class_1(class_1_id){
	// 获取一级分类id
	// $("#sku_class_1_select option:selected").value;
	$.getJSON("js/json/tm_class_1_"+class_1_id+".js",function(data){
		// 将json静态数据加载到二级分类下拉列表
		// for(i=0;i<data.length;i++){}
		$("#sku_tm_select").empty(); 
		$(data).each(function(i,json){
			$("#sku_tm_select").append("<option value="+json.id+">"+json.ppmch+"</option>");
		});
	});
}

function sku_get_spu_by_ppid_class2id(){
	var class_1_id = $("#sku_class_1_select").val();
	var class_2_id = $("#sku_class_2_select").val();
	var tm_id = $("#sku_tm_select").val();
	
	$.post("get_spu_by_ppid_class2id.do",{"class_1_id":class_1_id,"class_2_id":class_2_id,"pp_id":tm_id},function(data){
		$("#sku_spu_select").empty();
		$(data).each(function(i,json){
			$("#sku_spu_select").append("<option value="+json.id+">"+json.shp_mch+"</option>");
		});
	});
}

function sku_get_attr_list_by_class_2_id(class_2_id){
	// 访问查询分类属性列表的方法，传入二级分类id
	$.post("sku_get_attr_list_by_class_2_id.do",{"class_2_id":class_2_id},function(data){
		$("#sku_attr_inner").html(data);
	});
}


</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库存发布</title>
</head>
<body>
	库存信息发布<br>
	<form action="add_sku.do" method="post">
	一级分类：<select id="sku_class_1_select" onChange="sku_select_class_2_by_class_1(this.value)"></select><br>
	二级分类：<select id="sku_class_2_select" onChange="sku_get_attr_list_by_class_2_id(this.value)"></select><br>
	商标分类：<select id="sku_tm_select" onChange="sku_get_spu_by_ppid_class2id()"></select><br>
	商品信息：<select name="shp_id" id="sku_spu_select" ></select><br>
	<hr>
	<div id="sku_attr_inner">
	</div>
	</form>
</body>
</html>