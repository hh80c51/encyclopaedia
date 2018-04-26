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
                <#include "common/userinfo.ftl">
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
			    <#include "common/menu.ftl">
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th>会员名称</th>
                  <th>流程名称</th>
                  <th>任务名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="5" align="center">
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
			    
			    pageQuery(0);
            });
           
            var pagesize = 10;
            function pageQuery( pageIndex ) {
			    var dataObj = {
		    		"pageno" : pageIndex + 1,
		    		"pagesize" : pagesize
			    };
			    
			    var loadingIndex = -1;
			    $.ajax({
			    	url : "${APP_PATH}/auth/pageQuery.do",
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

			    			var content = "";
			    			$.each(datas, function(index, task){
			    				// 循环体
			    				//content = content + "";
				                content += '<tr>';
				                content += '  <td>'+(index+1)+'</td>';
				                content += '  <td>'+task.membername+'</td>';
				                content += '  <td>'+task.pdname+'</td>';
				                content += '  <td>'+task.taskname+'</td>';
				                content += '  <td>';
								content += '      <button type="button" class="btn btn-success btn-xs" onclick="window.location.href=\'${APP_PATH}/auth/showcert.htm?taskid='+task.id+'&memberid='+task.memberid+'\'"><i class=" glyphicon glyphicon-check"></i></button>';
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
			    		} else {
	    					layer.msg("资质信息分页查询失败", {time:1500, icon:5, shift:6}, function(){
	    						
	    					});
			    		}
			    	}
			    	
			    });
            }
            
            function deleteCert(id, name) {
            	//confirm
    			layer.confirm("删除资质["+name+"]信息，是否继续",  {icon: 3, title:'提示'}, function(cindex){
    			    
    				// 删除资质信息
    				var loadingIndex = -1;
    				$.ajax({
    					type : "POST",
    					url  : "${APP_PATH}/cert/delete.do",
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
    	    					layer.msg("资质信息删除失败", {time:1500, icon:5, shift:6}, function(){
    	    						
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
            	
            	// 获取资质的复选框
            	//$("input[type='checkbox']")
            	var boxes = $("tbody :checkbox");
            	//alert(boxes.length);
            	
            	// 将复选框进行勾选或取消
            	$.each(boxes, function(){
            		this.checked = flg;
            	});
            }
            
            function deleteCerts() {
            	// 判断资质复选框是否被选中
            	var boxes = $("tbody :checked");
            	if ( boxes.length == 0 ) {
					layer.msg("请选择需要删除的资质信息", {time:1500, icon:5, shift:6}, function(){
						
					});
					return;
            	} else {
            		// 让资质进行删除确认
        			layer.confirm("删除选择的资质信息，是否继续",  {icon: 3, title:'提示'}, function(cindex){
        			    
        				// 删除资质信息
        				
        				var dataObj = {};
        				
        				$.each(boxes, function(i, box){
        					//dataObj["users["+i+"].id"] = box.value;
        					dataObj["ids["+i+"]"] = box.value;
        				});
        				
        				var loadingIndex = -1;
        				$.ajax({
        					type : "POST",
        					url  : "${APP_PATH}/cert/deletes.do",
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
        	    					layer.msg("资质信息删除失败", {time:1500, icon:5, shift:6}, function(){
        	    						
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
