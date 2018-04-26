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
<button type="button" id="uploadBtn" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i> 上传流程定义文件</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th>Key</th>
                  <th>名称</th>
                  <th>版本号</th>
                  <th >操作</th>
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
    <form id="uploadForm" action="${APP_PATH}/process/uploadFile.do" method="post" enctype="multipart/form-data">
        <input style="display:none;" type="file" id="fprocDefFile" name="procDefFile">
    </form>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
	<script src="${APP_PATH}/jquery/jquery.pagination.js"></script>
	<script src="${APP_PATH}/jquery/jquery.form.js"></script>
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
            
            $("#uploadBtn").click(function(){
               // 点击文件域表单元素 
               // 使用JS点击按钮
               $("#fprocDefFile").click();
               
            });
            
            $("#fprocDefFile").change(function(event){
            
                var files = event.target.files, file;
                if (files && files.length > 0) {
                    file = files[0]; 
                    
                    if(file.size > 1024 * 1024 * 10) {
    					layer.msg("流程定义文件不能大于10M，请重新选择", {time:2000, icon:5, shift:6}, function(){
    						
    					});
    					return;
                    }
                } else {
                    return;
                }
                return;
                // 提交表单
		    	var loadingIndex = -1;
		    	$("#uploadForm").ajaxSubmit({
		    		beforeSubmit : function() {
		    			loadingIndex = layer.load(2, {time: 10*1000});
		    			return true;
		    		},
		    		success : function(r) {
		    			layer.close(loadingIndex);
		    			if ( r.success ) {
		    				pageQuery(0);
		    				$("#fprocDefFile").val("");
		    			} else {
	    					layer.msg("流程定义文件上传失败", {time:1500, icon:5, shift:6}, function(){
	    						
	    					});
		    			}
		    		}
		    	});
            });
           
            var pagesize = 10;
            function pageQuery( pageIndex ) {
			    var dataObj = {
		    		"pageno" : pageIndex + 1,
		    		"pagesize" : pagesize
			    };
			    
			    var loadingIndex = -1;
			    $.ajax({
			    	url : "${APP_PATH}/process/pageQuery.do",
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
			    			$.each(datas, function(index, pd){
			    				// 循环体
			    				//content = content + "";
				                content += '<tr>';
				                content += '  <td>'+(index+1)+'</td>';
				                content += '  <td>'+pd.key+'</td>';
				                content += '  <td>'+pd.name+'</td>';
				                content += '  <td>'+pd.version+'</td>';
				                content += '  <td>';
				                content += '	  <button type="button" onclick="window.location.href=\'${APP_PATH}/process/showimg.htm?id='+pd.id+'\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-search"></i></button>';
								content += '	  <button type="button" onclick="deleteProDef(\''+pd.id+'\', \''+pd.name+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
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
	    					layer.msg("流程定义信息分页查询失败", {time:1500, icon:5, shift:6}, function(){
	    						
	    					});
			    		}
			    	}
			    	
			    });
            }
            
            function deleteProDef(id, name) {
            	//confirm
    			layer.confirm("删除流程定义["+name+"]信息，是否继续",  {icon: 3, title:'提示'}, function(cindex){
    				var loadingIndex = -1;
    				$.ajax({
    					type : "POST",
    					url  : "${APP_PATH}/process/delete.do",
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
    	    					layer.msg("流程定义信息删除失败", {time:1500, icon:5, shift:6}, function(){
    	    						
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
