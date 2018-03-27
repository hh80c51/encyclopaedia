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
    var add_attr_table_index = 0;
	function add_attr_add_table(){
		add_attr_table_index++;
		var a='<table id="add_attr_table_'+add_attr_table_index+'" border="1">';
		var b='<tr><td>属性名：<input type="text" name="list_attr['+add_attr_table_index+'].shxm_mch"/></td><td></td><td><a href="javascript:add_attr_add_tr('+add_attr_table_index+');">添加值</a></td></tr>';
		var c='<tr><td>属性值：<input type="text" name="list_attr['+add_attr_table_index+'].list_value[0].shxzh"/></td><td>属性值名：<input type="text" name="list_attr['+add_attr_table_index+'].list_value[0].shxzh_mch"/></td><td>删除</td></tr>';
		var d='</table>';
		$("#add_attr_table").append(a+b+c+d+"<hr>");
	}
	
	function add_attr_add_tr(table_index){
		var tr_index = $("#add_attr_table_"+table_index+" tr").length-1;
		var tr='<tr><td>属性值：<input type="text" name="list_attr['+table_index+'].list_value['+tr_index+'].shxzh"/></td><td>属性值名：<input type="text" name="list_attr['+table_index+'].list_value['+tr_index+'].shxzh_mch"/></td><td>删除</td></tr>';
		$("#add_attr_table_"+table_index).append(tr);
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>属性添加</title>
</head>
<body>
	添加${class_2_name}属性：<a href="javascript:add_attr_add_table();">添加属性</a><br>
	<form action="add_attr.do" method="post">
		<input type="hidden" name="class_2_id" value="${class_2_id}" />
		<input type="hidden" name="class_2_name" value="${class_2_name}" />
		<div id="add_attr_table">
			<table id="add_attr_table_0" border="1">
				<tr><td>属性名：<input type="text" name="list_attr[0].shxm_mch"/></td><td></td><td><a href="javascript:add_attr_add_tr(0);">添加值</a></td></tr>
				<tr><td>属性值：<input type="text" name="list_attr[0].list_value[0].shxzh"/></td><td>属性值名：<input type="text" name="list_attr[0].list_value[0].shxzh_mch"/></td><td>删除</td></tr>
			</table>
			<hr>
		</div>
		
		<input type="submit" value="添加分类属性"/>
	</form>
</body>
</html>