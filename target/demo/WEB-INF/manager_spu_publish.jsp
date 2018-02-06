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
		$('#spu_class_1_select').combobox({    
		    url:"js/json/class_1.js",    
		    valueField:'id',    
		    textField:'flmch1',
		    width:100,
		    value:'请选择',
		    onSelect:function spu_select_class_2_by_class_1(){
		    	//var class_1_id = $("#spu_class_1_select").combobox('getValue');
		    	var class_1_id = $(this).combobox('getValue');
		    	$("#spu_class_2_select").combobox({
		    		url:"js/json/class_2_"+class_1_id+".js",  
				    valueField:'id',    
				    textField:'flmch2',
				    width:100,
				    value:'请选择'
		    	});
				// 加载商标的下拉列表
				spu_select_tm_by_class_1(class_1_id);
			}
		}); 

	});
	
	
	
	function spu_select_tm_by_class_1(class_1_id){
		$("#spu_tm_select").combobox({
    		url:"js/json/tm_class_1_"+class_1_id+".js",  
		    valueField:'id',    
		    textField:'ppmch',
		    width:100,
		    value:'请选择'
    	});
	}
	
	function spu_image_click(index){
		$("#spu_file_"+index).click();
	}
	
	function spu_image_change(index){
		
		// 获得缩略图
		var image = $("#spu_file_"+index)[0].files[0];
		
		// 将缩略图替换掉被点击的图片按钮
		var url = window.URL.createObjectURL(image);
		$("#spu_image_"+index).attr("src",url);
		
		// 获取页面上的所有file对象
		var length = $(":file").length;
		
		if(length==(index+1)){
			// 调用图片追加方法
			spu_image_append(index);
		}
	}
	
	function spu_image_append(index){
		var img = '<img  id="spu_image_'+(index+1)+'" width="100px" onclick="spu_image_click('+(index+1)+')" src = "image/upload_hover.png" style="cursor:pointer;"/>';
		var input = '<input  style="display:none;" id="spu_file_'+(index+1)+'" type="file" name="files" onChange="spu_image_change('+(index+1)+')" /><br>';
		
		$("#spu_image_div").append(img);
		$("#spu_image_div").append(input);
	}
	
	function spu_publish_submit(){
		$("#spu_publish_form").submit();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品信息发布</title>
</head>
<body>
	
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true" style="width:300px">
			&nbsp;&nbsp;&nbsp;&nbsp;${success}:商品信息发布<br>
			一级分类：<select class="easyui-combobox" name="flbh1" id="spu_class_1_select"></select><br>
			二级分类：<select class="easyui-combobox" name="flbh2" id="spu_class_2_select">
			        	<option>请选择</option>
			        </select><br>
			商标分类：<select class="easyui-combobox" name="pp_id" id="spu_tm_select" >
			        	<option>请选择</option>
			        </select><br>
		</div>
		<div data-options="region:'center'">
				<form id="spu_publish_form" action="spu_publish.do" enctype="multipart/form-data" method="post">
							商品spu名称：<input class="easyui-validatebox" type="text" name="shp_mch"  data-options="required:true"/><br>
							商品spu描述：<br>
							<textarea name="shp_msh" style="height:60px;"></textarea><br>
							商品图片组：<br>
					<div id="spu_image_div">
						<img id="spu_image_0" width="100px" onclick="spu_image_click(0)" src = "image/upload_hover.png" style="cursor:pointer;"/>
						<input style="display:none;" id="spu_file_0" type="file" name="files" onChange="spu_image_change(0)" /><br>
					</div>
					<a class="easyui-linkbutton" href="javascript:spu_publish_submit();">发布商品信息</a>
				</form>
		</div>
	</div>

</body>
</html>