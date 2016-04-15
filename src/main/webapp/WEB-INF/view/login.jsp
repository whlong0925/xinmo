<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <body>
  <h1>${errorMessage}</h1>
  <form action="login" method="post">
	    用户名：<input type="text" id="username" name="username" />
	    密码:<input type="password" id="password" name="password" />
	   验证码：<img src="kaptcha.jpg" /> <input type="text" name="kaptcha" value="" />
    <input type="submit" value="提交" />
    </form>
  </body>
  <script src="${webRoot}/js/jquery.js"></script>
  <script type="text/javascript">
  $(function(){
      $('#kaptchaImage').click(function () { $(this).attr('src', '/kaptcha.jpg?' + Math.floor(Math.random()*100) ); })
  });
  </script>
</html>
