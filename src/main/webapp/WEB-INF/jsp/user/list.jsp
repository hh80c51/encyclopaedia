<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<link rel="stylesheet" href="${APP_PATH}/css/pagination.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><img src="${APP_PATH}/img/logo.png" width="100" style="float:left;margin-top:5px;"><a class="navbar-brand" style="font-size:32px;" href="#">认证流程审批系统</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
            <%-- 
                <%@include file="/WEB-INF/jsp/common/userinfo.jsp"%>
                --%>
                <jsp:include page="/WEB-INF/jsp/common/userinfo.jsp"></jsp:include>
                
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" id="querytext" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" onclick="deleteUsers()"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/user/add.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" onclick="selAllBox(this)"></th>
                  <th>登陆账号</th>
                  <th>用户名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
                 <%-- 
                 <c:forEach items="${userPage.datas}" var="user" varStatus="status">
	                <tr>
	                  <td>${status.count}</td>
					  <td><input type="checkbox"></td>
	                  <td>${user.loginacct}</td>
	                  <td>${user.username}</td>
	                  <td>
					      <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-search"></i></button>
					      <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
						  <button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
					  </td>
	                </tr>
	                 </c:forEach>
	                 --%>
                
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<!-- <ul class="pagination"></ul> -->
						<div id="Pagination" class="pagination">
					 </td>
				 </tr>

			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
	<script src="${APP_PATH}/jquery/jquery.pagination.js"></script>
        <script type="text/javascript">
            var likeFlg = false;
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    
			    $("#queryBtn").click(function(){
			    	var querytext = $("#querytext");
			    	if ( querytext.val() == "" ) {
    					layer.msg("请输入查询条件", {time:1500, icon:5, shift:6}, function(){
    						querytext.focus();
    					});
    					return;
			    	}
			    	likeFlg = true;
			    	pageQuery(0);
			    });
			    
/* 			    $(".table tbody").delegate($(".editBtn"), "click", function(){
			    	alert(123);
			    }); */
			    
			    pageQuery(0);
            });
           
            var pagesize = 10;
            function pageQuery( pageIndex ) {
			    // 发送ajax请求。异步查询分页数据
			    var dataObj = {
		    		"pageno" : pageIndex + 1,
		    		"pagesize" : pagesize
			    };
			    
			    if ( likeFlg == true ) {
			    	dataObj.querytext = $("#querytext").val();
			    	//dataObj["query.text"] = $("#querytext").val();
			    	likeFlg = false;
			    }
			    
			    var loadingIndex = -1;
			    $.ajax({
			    	url : "${APP_PATH}/user/pageQuery.do",
			    	type : "POST",
			    	data : dataObj,
			    	beforeSend : function() {
			    		loadingIndex = layer.load(2, {time: 10*1000});
			    		return true;
			    	},
			    	success : function(r) {
			    		layer.close(loadingIndex);
			    		if ( r.success ) {
			    			// 局部刷新数据
			    			var pageObj = r.page;
			    			var datas = pageObj.datas;
			    			
			    			//for ( var i = 0; i < datas.length; i++ ) {
			    			//	var user = datas[i];
			    			//}
			    			// jquery 循环
			    			var content = "";
			    			$.each(datas, function(index, user){
			    				// 循环体
			    				//content = content + "";
				                content += '<tr>';
				                content += '  <td>'+(index+1)+'</td>';
								content += '  <td><input type="checkbox" value="'+user.id+'"></td>';
				                content += '  <td>'+user.loginacct+'</td>';
				                content += '  <td>'+user.username+'</td>';
				                content += '  <td >';
								content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/user/assign/'+user.id+'.htm\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
								content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/user/edit/'+user.id+'.htm\'" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
								content += '	  <button type="button" onclick="deleteUser('+user.id+', \''+user.loginacct+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
								content += '  </td>';
				                content += '</tr>';
			    			});
			    			
			    			// 刷新表格数据
			    			$(".table tbody").html(content);
			    			

			    			
			    			// 使用分页插件实现分页效果
							$("#Pagination").pagination(pageObj.totalsize, {
								num_edge_entries: 2, //边缘页数
								num_display_entries: 3, //主体页数
								callback: pageQuery,// 点击页码执行查询操作
								items_per_page:pagesize, // 每页显示的数据条数
								current_page:pageIndex, // 当前页码索引
								prev_text:"上一页",
								next_text:"下一页"
							});
			    			
/* 			    			// 页码
			    			var s = "";
			    			if ( pageObj.pageno > 1 ) {
			    				s += '<li onclick="pageQuery('+(pageObj.pageno-1)+')"><a href="#">上一页</a></li>';
			    			}
							
			    			for ( var i = 0; i < pageObj.totalno; i++ ) {
			    				if ( pageObj.pageno == (i+1) ) {
			    					s += '<li class="active" onclick="pageQuery('+(i+1)+')"><a href="#">'+(i+1)+'</a></li>';
			    				} else {
			    					s += '<li onclick="pageQuery('+(i+1)+')"><a href="#">'+(i+1)+'</a></li>';
			    				}
			    				
			    			}
			    			if ( pageObj.pageno < pageObj.totalno ) {
			    				s += '<li onclick="pageQuery('+(pageObj.pageno+1)+')"><a href="#">下一页</a></li>';
			    			}
							
			    			$(".pagination").html(s); */
			    		} else {
	    					layer.msg("用户信息分页查询失败", {time:1500, icon:5, shift:6}, function(){
	    						
	    					});
			    		}
			    	}
			    	
			    });
            }
            
            function deleteUser(id, loginacct) {
            	//confirm
    			layer.confirm("删除用户["+loginacct+"]信息，是否继续",  {icon: 3, title:'提示'}, function(cindex){
    			    
    				// 删除用户信息
    				//alert("删除用户");
    				var loadingIndex = -1;
    				$.ajax({
    					type : "POST",
    					url  : "${APP_PATH}/user/delete.do",
    					data : {
    						id : id
    					},
    					beforeSend : function() {
    						loadingIndex = layer.load(2, {time: 10*1000});
    						return true;
    					},
    					success : function(r) {
    						layer.close(loadingIndex);
    						if ( r.success ) {
    							pageQuery(0);
    						} else {
    	    					layer.msg("用户信息删除失败", {time:1500, icon:5, shift:6}, function(){
    	    						
    	    					});
    						}
    					}
    				});
    				
    				layer.close(cindex);
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
            
            function selAllBox(obj) {
            	
            	// 获取全选框的选中状态
            	var flg = obj.checked;
            	
            	// 获取用户的复选框
            	//$("input[type='checkbox']")
            	var boxes = $("tbody :checkbox");
            	//alert(boxes.length);
            	
            	// 将复选框进行勾选或取消
            	$.each(boxes, function(){
            		this.checked = flg;
            	});
            }
            
            function deleteUsers() {
            	// 判断用户复选框是否被选中
            	var boxes = $("tbody :checked");
            	if ( boxes.length == 0 ) {
					layer.msg("请选择需要删除的用户信息", {time:1500, icon:5, shift:6}, function(){
						
					});
					return;
            	} else {
            		// 让用户进行删除确认
        			layer.confirm("删除选择的用户信息，是否继续",  {icon: 3, title:'提示'}, function(cindex){
        			    
        				// 删除用户信息
        				
        				var dataObj = {};
        				
        				$.each(boxes, function(i, box){
        					//dataObj["users["+i+"].id"] = box.value;
        					dataObj["ids["+i+"]"] = box.value;
        				});
        				
        				var loadingIndex = -1;
        				$.ajax({
        					type : "POST",
        					url  : "${APP_PATH}/user/deletes.do",
        					data : dataObj,
        					beforeSend : function() {
        						loadingIndex = layer.load(2, {time: 10*1000});
        						return true;
        					},
        					success : function(r) {
        						layer.close(loadingIndex);
        						if ( r.success ) {
        							pageQuery(0);
        						} else {
        	    					layer.msg("用户信息删除失败", {time:1500, icon:5, shift:6}, function(){
        	    						
        	    					});
        						}
        					}
        				});
        				
        				layer.close(cindex);
        			}, function(cindex){
        			    layer.close(cindex);
        			});
            	}
            }
        </script>
  </body>
</html>
