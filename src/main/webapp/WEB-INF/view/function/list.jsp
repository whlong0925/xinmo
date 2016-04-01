<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row-fluid">
	<div class="page-header">
		<h1>
			Functions <small>All functions</small>
		</h1>
	</div>
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>路径</th>
				<th>排序</th>
				<th>描述</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${functionList}" var="item" varStatus="loop">
				<tr class="list-users">
					<td>${item.name}</td>
					<td>${item.path}</td>
					<td>${item.sequence}</td>
					<td>${item.description }</td>
					<td>
						<div class="btn-group">
							<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown"	href="#">Actions <span class="caret"></span></a>
							<ul class="dropdown-menu pull-right">
								<li><a href="#" onclick="edit(${item.id})"><i class="icon-pencil"></i> Edit</a></li>
								<li><a href="#" onclick="delFunction(${item.id})"><i class="icon-trash"></i> Delete</a></li>
								<li><a href="#"><i class="icon-user"></i> Details</a></li>
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
	<div class="pagination">
		<ul>
			<li><a href="#">Prev</a></li>
			<li class="active"><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">Next</a></li>
		</ul>
	</div>
	<a href="#" class="btn btn-success j_add" action="function/0">New Function</a>
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
		var url = "/function/"+id;
		turnPage(url,"get",'');
	}
	function delFunction(id){
		var url = "function/delete/"+id;
		turnPage(url,"get",'');
	}
	</script>