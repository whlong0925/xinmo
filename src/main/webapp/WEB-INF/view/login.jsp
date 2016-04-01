<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <body>
  <h1>${errorMessage}</h1>
  <form action="login" method="post">
	    用户名：<input type="text" id="usercode" name="usercode" />
	    密码:<input type="password" id="password" name="password" />
    <input type="submit" value="提交" />
    </form>
  </body>
</html>
