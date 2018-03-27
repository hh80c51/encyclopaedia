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
	<link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css">
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
			      <ul id="permissionTree" class="ztree"></ul>
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
	<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
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
			    var setting = {
					view: {
						selectedMulti: false,
						addDiyDom: function(treeId, treeNode){
							var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
							
							// JS是一个弱类型语言，不需要类型限制
							// 需要什么样的类型可以自动转换
							// boolean 
							if ( treeNode.icon ) {
								icoObj.removeClass("button ico_docu ico_open").addClass("fa fa-fw " + treeNode.icon).css("background","");
							}
						},
						addHoverDom: function(treeId, treeNode){  
							var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
							aObj.attr("href", "javascript:;");
							if (treeNode.editNameFlag || $("#btnGroup"+treeNode.tId).length>0) return;
							var s = '<span id="btnGroup'+treeNode.tId+'">';
							if ( treeNode.level == 0 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="window.location.href=\'${APP_PATH}/permission/add.htm?level='+treeNode.level+'&id='+treeNode.id+'\'" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level == 1 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="window.location.href=\'${APP_PATH}/permission/edit/'+treeNode.id+'.htm\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								if (treeNode.children.length == 0) {
									s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="deletePermission('+treeNode.id+', \''+treeNode.name+'\')" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
								}
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="window.location.href=\'${APP_PATH}/permission/add.htm?level='+treeNode.level+'&id='+treeNode.id+'\'" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
							} else if ( treeNode.level == 2 ) {
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  onclick="window.location.href=\'${APP_PATH}/permission/edit/'+treeNode.id+'.htm\'" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
								s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" onclick="deletePermission('+treeNode.id+', \''+treeNode.name+'\')">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
							}
			
							s += '</span>';
							aObj.after(s);
						},
						removeHoverDom: function(treeId, treeNode){
							$("#btnGroup"+treeNode.tId).remove();
						}
					},
					async: {
						enable: true,// 启用
						url:"${APP_PATH}/permission/loadTree.do", // 异步读取数据的路径
						autoParam:["id", "name=n", "level=lv"]
					}
			    };
			    
			    // 我的异步
			    /*
			    $.ajax({
			    	type : "POST",
			    	url  : "${APP_PATH}/permission/load.do",
			    	success : function( r ) {
			    		if ( r.success ) {
			    			$.fn.zTree.init($("#permissionTree"), setting, r.nodes);
			    		}
			    	}
			    });
			    */
			    
			    /*
			    {"nodes":[{"id":1,"pid":0,"name":"首页","url":"main.htm","open":true,"icon":"fa-home","children":[]},{"id":2,"pid":0,"name":"权限管理","url":"\"\"","open":true,"icon":"fa-desktop","children":[{"id":3,"pid":2,"name":"用户维护","url":"user/list.htm","open":true,"icon":"fa-user","children":[]},{"id":4,"pid":2,"name":"角色维护","url":"role/list.htm","open":true,"icon":"fa-desktop","children":[]},{"id":5,"pid":2,"name":"许可维护","url":"permission/list.htm","open":true,"icon":"fa-desktop","children":[]},{"id":9,"pid":2,"name":"zzzzzzz","url":"zzzzzz","open":true,"icon":"fa-desktop","children":[]}]},{"id":6,"pid":0,"name":"XXXXX","url":null,"open":true,"icon":"fa-desktop","children":[]}],"success":true}
			    
			    [{"id":1,"pid":0,"name":"首页","url":"main.htm","open":true,"icon":"fa-home","children":[]},{"id":2,"pid":0,"name":"权限管理","url":"\"\"","open":true,"icon":"fa-desktop","children":[{"id":3,"pid":2,"name":"用户维护","url":"user/list.htm","open":true,"icon":"fa-user","children":[]},{"id":4,"pid":2,"name":"角色维护","url":"role/list.htm","open":true,"icon":"fa-desktop","children":[]},{"id":5,"pid":2,"name":"许可维护","url":"permission/list.htm","open":true,"icon":"fa-desktop","children":[]},{"id":9,"pid":2,"name":"zzzzzzz","url":"zzzzzz","open":true,"icon":"fa-desktop","children":[]}]},{"id":6,"pid":0,"name":"XXXXX","url":null,"open":true,"icon":"fa-desktop","children":[]}]
			    
		var zNodes =[
			{ name:"父节点1 - 展开", open:true,
				children: [
					{ name:"父节点11 - 折叠",
						children: [
							{ name:"叶子节点111"},
							{ name:"叶子节点112"},
							{ name:"叶子节点113"},
							{ name:"叶子节点114"}
						]},
					{ name:"父节点12 - 折叠",
						children: [
							{ name:"叶子节点121"},
							{ name:"叶子节点122"},
							{ name:"叶子节点123"},
							{ name:"叶子节点124"}
						]},
					{ name:"父节点13 - 没有子节点", isParent:true}
				]}

		];
			    */
			    
			    // 使用ztree插件的异步, 初始化时不需要传递节点数据
			    $.fn.zTree.init($("#permissionTree"), setting);
			    
            });
            function deletePermission(id, name) {
    			layer.confirm("删除许可["+name+"]信息，是否继续",  {icon: 3, title:'提示'}, function(cindex){
    				var loadingIndex = -1;
    				$.ajax({
    					type : "POST",
    					url  : "${APP_PATH}/permission/delete.do",
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
    							var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
    							// 异步加载数据 ==> 异步刷新(清空，异步加载（ztree）数据)
    							treeObj.reAsyncChildNodes(null, "refresh");
    						} else {
    	    					layer.msg("许可信息删除失败", {time:1500, icon:5, shift:6}, function(){
    	    						
    	    					});
    						}
    					}
    				});
    				
    				layer.close(cindex);
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            }
        </script>
  </body>
</html>
