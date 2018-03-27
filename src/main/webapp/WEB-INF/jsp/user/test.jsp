<%@page pageEncoding="UTF-8"%>
<html>
    <head>
        <title>动态新增用户</title>
        <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    </head>
    <body>
        <h1>动态添加用户</h1>
        <button type="button" onclick="insertRow()">新增用户</button><br>
        <br>
        <form id="userForm" action="${APP_PATH}/user/insertUsers.do" method="post">
        <table border="1">
            <tr>
                <td >登陆账号</td>
                <td >用户名称</td>
                <td >操作</td>
            </tr>
<!--             <tr>
                <td ><input type="text" name="users[0].loginacct"></td>
                <td ><input type="text" name="users[0].username"></td>
                <td ><button type="button" onclick="deleteRow(this)">删除</button></td>
            </tr> -->
        </table>
        </form>
        <br><br>
        <button type="button" onclick="saveUsers()">保存</button>
        <script>
        var index = 0;
        function insertRow() {
        	var tr = "";
        	
            tr += '<tr>';
	        tr += '    <td ><input type="text" name="users['+index+'].loginacct"></td>';
	        tr += '    <td ><input type="text" name="users['+index+'].username"></td>';
	        tr += '    <td ><button type="button" onclick="deleteRow(this)">删除</button></td>';
	        tr += '</tr>';
	        
	        $("table").append(tr);
	        
	        index++;
        	
        }
        
        function deleteRow(btn) {
        	//alert(btn);
        	var tr = $(btn).parent().parent();
        	
        	tr.remove();
        }
        
        function saveUsers() {
        	$("#userForm").submit();
        }
        </script>
    </body>
</html>