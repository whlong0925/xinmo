<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="g" uri="http://www.xinmo.net/xinmo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row-fluid">
	<div class="page-header">
		<h1>
			Users <small>All users</small>
		</h1>
	</div>
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>用户名</th>
				<th>Status</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.result}" var="item" varStatus="loop">
				<tr class="list-users">
					<td>${loop.count}</td>
					<td>${item.username}</td>
					<td>${item.status }</td>
					<td>
						<div class="btn-group">
							<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown"	href="#">Actions <span class="caret"></span></a>
							<ul class="dropdown-menu pull-right">
								<li><a href="#" onclick="edit(${item.id})"><i class="icon-pencil"></i> Edit</a></li>
								<li><a href="#" onclick="delUser(${item.id})"><i class="icon-trash"></i> Delete</a></li>
								<li><a href="#" onclick="role(${item.id})"><i class="icon-user"></i>Roles</a></li>
								<li class="nav-header">Permissions</li>
								<li><a href="#"><i class="icon-lock"></i> Make <strong>Admin</strong></a></li>
								<li><a href="#"><i class="icon-lock"></i> Make <strong>Moderator</strong></a></li>
								<li><a href="#"><i class="icon-lock"></i> Make <strong>User</strong></a></li>
							</ul>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<g:pagination pageObj="${pager}" isAjax="true" contentId=".j_content" hrefLink="user/list" />
	<a href="#" class="btn btn-success j_add" action="user/show">New User</a>
</div>
<script>
	$(document).ready(function() {
		$('.dropdown-menu li a').hover(
		function() {
			$(this).children('i').addClass('icon-white');
		},
		function() {
			$(this).children('i').removeClass('icon-white');
		});
		
		if($(window).width() > 760)
		{
			$('tr.list-users td div ul').addClass('pull-right');
		}
	});
	
	function edit(id){
		var url = "user/"+id+"/edit";
		turnPage(url,"get",'');
	}
	function delUser(id){
		var url = "user/"+id+"/delete";
		turnPage(url,"get",'');
	}
	function role(userId){
		var url = "user/"+userId+"/role";
		turnPage(url,"get",'');
	}
	</script>