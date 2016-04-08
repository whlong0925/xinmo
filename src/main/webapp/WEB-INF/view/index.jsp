<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>xx后台系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Admin panel developed with the Bootstrap from Twitter.">
    <meta name="author" content="travis">

    <link href="${webRoot}/css/bootstrap.css" rel="stylesheet" />
	<link href="${webRoot}/css/site.css" rel="stylesheet" />
    <link href="${webRoot}/css/bootstrap-responsive.css" rel="stylesheet" />
    
    <script src="${webRoot}/js/jquery.js"></script>
	<script src="${webRoot}/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	$.ajaxSetup({
    		contentType: "application/x-www-form-urlencoded;charset=utf-8",
    		//type: "POST",
    		beforeSend: function(){
    			//$.messager.progress();
    		},
    		complete:function(XMLHttpRequest,textStatus){
    			debugger;
    			var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头，sessionstatus，  
    	         if(sessionstatus=="timeout"){ 
    	        	 //如果超时就处理 ，指定要跳转的页面 
    	        	 location.href = '/login';
    	         }
    		}
    		
    	});
    	//左侧菜单点击
    	$("ul.j_menu li").click(function(){
    		$("ul.j_menu li").removeClass("active");
    		$(this).addClass("active");
    		var url = $(this).find("a").attr("action");
    		turnPage(url,"get",'');
    	});
    	
    	//添加操作
    	$(".j_content").delegate('a.j_add','click',function(){
    		var url = $(this).attr("action");
    		turnPage(url,"get",'');
    	});
    });
    //跳转操作
    function turnPage(url,methodType,data){
   		if (url === undefined || url == "" || url == null){
 			return;
 		}
      $.ajax({
        type:methodType,
        url:url,
        data:data,
        success:function(data){
          $(".j_content").html(data);
        }
      })
    }
    </script>
  </head>
  <body>
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">欢迎进入xx系统</a>
          <div class="btn-group pull-right">
			<a class="btn" href="my-profile.html"><i class="icon-user"></i> Admin</a>
            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
			  <li><a href="my-profile.html">个人信息</a></li>
              <li class="divider"></li>
              <li><a href="logout">退出</a></li>
            </ul>
          </div>
          <div class="nav-collapse">
            <ul class="nav">
			<li><a href="index.html">主页</a></li>
              <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">顶部菜单1 <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#">功能1</a></li>
					<li class="divider"></li>
					<li><a href="#">功能2</a></li>
				</ul>
			  </li>
              <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">顶部菜单2 <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#">功能1</a></li>
					<li class="divider"></li>
					<li><a href="#">功能2</a></li>
				</ul>
			  </li>
			  <li><a href="stats.html">帮助</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
      <!-- 左侧菜单开始 -->
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list j_menu">
              <li class="nav-header"><i class="icon-wrench"></i> 系统管理</li>
              <li><a href="#" action="user/list">用户管理</a></li>
              <li><a href="#" action="role/list">角色管理</a></li>
              <li><a href="role/tree">角色树</a></li>
              <li><a href="#" action="module/list">模块管理</a></li>
              <li><a href="#" action="function/list">功能管理</a></li>
              <li class="nav-header"><i class="icon-signal"></i> 菜单列表2</li>
              <li><a href="#">功能菜单1</a></li>
              <li><a href="#">功能菜单2</a></li>
              <li><a href="#">功能菜单3</a></li>
              <li class="nav-header"><i class="icon-user"></i> 菜单列表3</li>
              <li><a href="#">功能菜单1</a></li>
              <li><a href="#">功能菜单2</a></li>
			  <li><a href="#">功能菜单3</a></li> 
            </ul>
          </div>
        </div>
        <!-- 左侧菜单结束 -->
        
        <!-- 中间内容开始 -->
        <div class="span9 j_content">
           		我是主体内容
        </div>
        <!-- 中间内容结束 -->
      </div>

      <hr>
	  <!-- 底部 -->
      <footer class="well">
        &copy; xx有限公司 <a href="#" target="_blank">xx系统</a>
      </footer>
    </div>
  </body>
</html>
