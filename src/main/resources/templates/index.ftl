<html lang="zh_CN">
<body>
<h2>Hello World!</h2>
<br/>
<@shiro.hasPermission  name="user:add">用户添加功能：<a href="add">用户添加</a></@shiro.hasPermission ><br/><br/>
<@shiro.hasPermission  name="user:update">用户修改功能：<a href="update">修改用户</a></@shiro.hasPermission >
</body>
</html>