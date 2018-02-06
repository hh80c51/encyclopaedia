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
			$("#search_redis_class_1_select").combobox({
				method:"get",
				url:"js/json/class_1.js",
				textField:'flmch1',
				valueField:'id',
				value:'请选择',
				width:100,
				onSelect:function(){
					var class_1_id = $(this).combobox('getValue');
					$("#search_redis_class_2_select").combobox({
						method:"get",
						url:"js/json/class_2_"+class_1_id+".js",
						textField:'flmch2',
						valueField:'id',
						value:'请选择',
						width:100,
						onSelect:function(){
							var class_2_id = $(this).combobox('getValue');
							$("#search_redis_inner").datagrid({
								method:"post",
								url:"get_attr_by_class_2_id_json.do",
								queryParams:{"class_2_id":class_2_id},
								columns:[[
											{field:'id',title:'id复选框',width:100,checkbox:true},
								            {field:'shxm_mch',title:'属性名',width:100},    
								            {field:'shfqy',title:'是否启用',width:100},    
								            {field:'chjshj',title:'创建时间',width:150,
								            	formatter: function(value,row,index){
								            		var d = new Date(value);
								    				return d.toLocaleString();
								    			}	
								            },  
								            {field:'list_value',title:'属性值',width:200,
								            	formatter: function(value,row,index){
								            		// alert(row);
								            		// {"shxzh":"ssfd"}
								            		//console.debug(row);
								            		var valueStr = "";
								            		$(value).each(function(i,json){
								            			valueStr = valueStr+"/"+json.shxzh + json.shxzh_mch;
								            		});
								    				return valueStr;
								    			}	
								            }
								        ]]    
							});	
						}
					});
				}
			});
		});
		
		function search_redis_get_sku_list_by_class_2_id(){
			var class_2_id = $("#search_redis_class_2_select").combobox('getValue');
			$.post("get_sku_by_class_2.do",{class_2_id:class_2_id},function(data){
				alert("总共刷新了"+data+"条redis缓存数据");
			});
		}
		
		function search_redis_get_sku_list_by_class_2_id_attr_id_value_id(){
			var class_2_id = $("#search_redis_class_2_select").combobox('getValue');
			
			var attr_check = $("#search_redis_inner").datagrid('getChecked');
			
			var array = new Array();
			
			$(attr_check).each(function(i,json){
				array[i] = json.id;
			});
			
			$.post("get_sku_list_by_class_2_attr_value.do",{class_2_id:class_2_id,attr_array:array},function(data){
				alert("总共刷新了"+data+"条redis缓存数据");
			});
		}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new jsp</title>
</head>
<body>


	<div class="easyui-layout" data-options="fit:true" style="height:700px">
	
		<div data-options="region:'north',split:true,iconCls:'icon-tip',title:'aaa'" style="height:100px">
			<select class="easyui-combobox" name="flbh1" id="search_redis_class_1_select" >
			</select>
			<select class="easyui-combobox" name="flbh2" id="search_redis_class_2_select" >
			</select>
		</div>
		<div data-options="region:'center'">
			<div id = "search_redis_inner">
				二级分类列表
			</div>
			<br>
			<a href="javascript:search_redis_get_sku_list_by_class_2_id();">刷新二级分类的缓存</a>
			<a href="javascript:search_redis_get_sku_list_by_class_2_id_attr_id_value_id();">刷新分类属性的缓存</a>
		</div>

	</div>
		

</body>
</html>