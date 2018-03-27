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
				$("#attr_class_1_select").append("<option value="+json.id+">"+json.flmch1+"</option>");
			});
		});
	});

	function attr_select_class_2_by_class_1(class_1_id){
		// 获取一级分类id
		// $("#attr_class_1_select option:selected").value;
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			// 将json静态数据加载到二级分类下拉列表
			// for(i=0;i<data.length;i++){}
			$("#attr_class_2_select").empty(); 
			$(data).each(function(i,json){
				$("#attr_class_2_select").append("<option value="+json.id+">"+json.flmch2+"</option>");
			});
		});
	}
	
	function attr_get_attr_list_by_class_2_id(class_2_id){
		// 访问查询分类属性列表的方法，传入二级分类id
		$("#attr_attr_inner").datagrid({
			url:"get_attr_list_by_class_2_id.do",
			method:"post",
			queryParams: {
				class_2_id:class_2_id
			},
			columns:[[    
		            {field:'shxm_mch',title:'属性名',width:100},  
		            {field:'chjshj',title:'创建时间',width:200,
		            	formatter: function(value,row,index){
		            		var d = new Date(value);
		            		return d.toLocaleString();
		            	}
		            },
		            {field:'list_value',title:'属性值',width:300,
		            	formatter: function(value,row,index){
		            		var v ="属性值";
		            		$(value).each(function(i,json){
		            			v +=" "+json.shxzh + json.shxzh_mch;
		            		});
		            		return v;
		            	}
		            }
			        ]]   
		});
	}
	
	function attr_open_add_attr(){
		var class_2_id = $("#attr_class_2_select").val();
		var class_2_name = $("#attr_class_2_select option:selected").html();		
		//window.open("goto_add_attr/"+class_2_id+"/"+class_2_name+".do");
		index_add_tabs2("添加分类属性","goto_add_attr/"+class_2_id+"/"+class_2_name+".do");
	}
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分类属性</title>
</head>
<body>
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',split:true" style="height:50px">
				一级分类：<select name="flbh1" id="attr_class_1_select" onChange="attr_select_class_2_by_class_1(this.value)"></select><br>
				二级分类：<select name="flbh2" id="attr_class_2_select" onChange="attr_get_attr_list_by_class_2_id(this.value)"></select><br>
			</div>
			<div data-options="region:'center'">
				<div class="easyui-datagrid" id="attr_attr_inner">
				</div>
				<a  class="easyui-linkbutton" href="javascript:attr_open_add_attr();">添加分类属性</a>
			</div>
		</div>
		
</body>
</html>