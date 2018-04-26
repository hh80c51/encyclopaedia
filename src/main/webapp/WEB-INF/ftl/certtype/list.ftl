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
                  <th>证件图片</th>
				  <th>商业公司</th>
                  <th>个体工商户 </th>
                  <th>个人经营 </th>
                  <th>政府及非营利组织  </th>
                </tr>
              </thead>
              <tbody>
                 <#list certs as cert>
                 <tr>
                     <td>${cert.name}</td>
                     <td><input data-accttype="0" data-certid="${cert.id}" type="checkbox" style="width:20px;height:20px;"></td>
                     <td><input data-accttype="1" data-certid="${cert.id}" type="checkbox" style="width:20px;height:20px;"></td>
                     <td><input data-accttype="2" data-certid="${cert.id}" type="checkbox" style="width:20px;height:20px;"></td>
                     <td><input data-accttype="3" data-certid="${cert.id}" type="checkbox" style="width:20px;height:20px;"></td>
                 </tr>
                 </#list>

              </tbody>
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
            });
            
            $(":checkbox").click(function(){
                // 获取当前复选框的选择状态
                var flg = this.checked;
                
                if ( flg ) {
                   // 新增
                   $.ajax({
                       type : "POST",
                       url  : "${APP_PATH}/certtype/insert.do",
                       data : {
                           accttype : $(this).attr("data-accttype"),
                           certid : $(this).attr("data-certid")
                       },
                       success : function() {
                       
                       }
                   });
                } else {
                   // 删除
                   $.ajax({
                       type : "POST",
                       url  : "${APP_PATH}/certtype/delete.do",
                       data : {
                           accttype : $(this).attr("data-accttype"),
                           certid : $(this).attr("data-certid")
                       },
                       success : function() {
                       
                       }
                   });
                }
                
            });
           
            // 将后台的关系数据进行循环遍历，将符合条件的复选框进行勾选
            <#list datas as data>
                $(":checkbox[data-accttype='${data.accttype}'][data-certid='${data.certid}']")[0].checked = true;
            </#list>
           
        </script>
  </body>
</html>
