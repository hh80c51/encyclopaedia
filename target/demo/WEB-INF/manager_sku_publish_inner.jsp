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
	function sku_publish_inner_show_value(checked,attr_id){
		if(checked){
			$("#sku_publish_inner_value_"+attr_id).show();
		}else{
			$("#sku_publish_inner_value_"+attr_id).hide();
		}	
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new jsp</title>
</head>
<body>
	水库发布分类属性集合内嵌页:<br>
	
		<c:forEach items="${list_attr_value}" var="attr_value" varStatus="index_attr">
			<input onclick="sku_publish_inner_show_value(this.checked,${attr_value.id})" type="checkbox" value="${attr_value.id}" name="list_attr_value[${index_attr.index}].shxm_id">${attr_value.shxm_mch}
		</c:forEach>
		<hr>
		<c:forEach items="${list_attr_value}" var="attr_value" varStatus="index_attr">
			<div id="sku_publish_inner_value_${attr_value.id}" style="display:none;">
				<c:forEach items="${attr_value.list_value}" var="val" varStatus="index_val">
					<input type="radio" value="${val.id}" name="list_attr_value[${index_attr.index}].shxzh_id">${val.shxzh}${val.shxzh_mch}
				</c:forEach>
			</div>
			<br>
		</c:forEach>
		<hr>
		库存：<input type="text" name="kc"/><br>
		价格：<input type="text" name="jg"/><br>
		名称：<input type="text" name="sku_mch"/><br>
		地址：<input type="text" name="kcdz"/><br>
		<input type="submit" value="发布库存信息"/>
	
</body>
</html>