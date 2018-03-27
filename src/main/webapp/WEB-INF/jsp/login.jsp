<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="GB18030">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/login.css">
	<style>

	</style>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><img src="img/logo.png" width="100" style="float:left;margin-top:5px;"><a class="navbar-brand" style="font-size:32px;" href="#">认证流程审批系统</a></div>
        </div>
      </div>
    </nav>

    <div class="container">

      <form class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="floginacct" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" id="fuserpswd" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" id="frememberme" value="1"> 记住我
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" id="loginBtn"><i class="glyphicon glyphicon-log-in"></i> 登录</a>
      </form>
    </div>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${APP_PATH}/layer/layer.js"></script>
    <script type="text/javascript">
    
    $("#loginBtn").click(function(){
    	
    	var floginacct = $("#floginacct");
    	if (floginacct.val() == "") {
			layer.msg("登陆账号不能为空，请输入", {time:1500, icon:6, shift:6}, function(){
				floginacct.focus();
			});
			return;
    	}
    	
    	var fuserpswd = $("#fuserpswd");
    	if (fuserpswd.val() == "") {
			layer.msg("登陆密码不能为空，请输入", {time:1500, icon:5, shift:6}, function(){
				fuserpswd.focus();
			});
			return;
    	}

    	// document : DOM对象 ==> JQuery对象
    	// $(document).ready();
    	
    	// JQuery对象[索引] ==》 DOM对象
    	
    	$.ajax({
    		type : "POST",
    		url  : "${APP_PATH}/dologin.do",
    		data : {
    			"loginacct" : $("#floginacct").val(),
    			"userpswd" : $("#fuserpswd").val(),
    			//"rememberme" : $("#frememberme")[0].checked ? "1" : "0"
    			"rememberme" : $("#frememberme")[0].checked ? "0" : "0"
    		},
    		beforeSend : function() {
    			//loadingIndex = layer.msg('用户登录处理中...', {icon: 16});
    			loadingIndex = layer.load(2, {time: 10*1000});
    			return true;
    		},
    		success : function(r) {
    			layer.close(loadingIndex);
    			if ( r.success ) {
    				debugger;
    				window.location.href = "${APP_PATH}/main.do";
    			} else {
    				if ( r.error ) {
    					//alert(r.error);
    					layer.msg(r.error, {time:1500, icon:5, shift:6}, function(){
    						
    					});
    				} else {
    					alert("用户登录失败");
    				}
    			}
    		}
    	});
    });
    </script>
  </body>
</html>