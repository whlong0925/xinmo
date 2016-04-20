<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="webRoot" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${webRoot}/js/treetable/css/jquery.treetable.css">
<link rel="stylesheet" href="${webRoot}/js/treetable/css/jquery.treetable.theme.default.css">
<script src="${webRoot}/js/treetable/jquery.treetable.js"></script>
<div class="row-fluid">
	<div class="page-header">
		<h1>
			Modules <small>All modules</small>
		</h1>
	</div>
	<table class="table table-striped table-bordered table-condensed" id="moduleTable">
		<thead>
			<tr>
				<th>名称</th>
				<th>描述</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${moduleList}" var="item" varStatus="loop">
				<%-- <tr class="list-users" data-tt-id='${item.id}' <c:if test="${item.parentId>0}">data-tt-parent-id='${item.parentId}'</c:if>> --%>
				<tr class="list-users" data-tt-id='${item.id}' <c:if test="${item.parentId>0}">data-tt-parent-id='${item.parentId}'</c:if>>
					<td>${item.name}</td>
					<td>${item.description }</td>
					<td>
						<c:if test="${item.functionType==0 }">
							<a href="#" onclick="add(${item.id},1)"><i class="icon-pencil"></i>添加功能</a>
						</c:if>
						<c:if test="${item.functionType==1 }">
							<a href="#" onclick="add(${item.id},2)"><i class="icon-pencil"></i>添加子功能</a>
						</c:if>
						<a href="#" onclick="edit(${item.id},${item.functionType})"><i class="icon-pencil"></i> Edit</a>
						<a href="#" onclick="delModule(${item.id})"><i class="icon-trash"></i> Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="#" class="btn btn-success j_add" action="module/show">New Module</a>
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
		
		$("#moduleTable").treetable({ expandable: true });
	});
	
	function add(id,functionType){
		var url = "function/show/"+id;
		turnPage(url,"get",'');
	}
	function edit(id,functionType){
		var url = "module/edit/"+id;
		if(functionType>0){
			url = "function/edit/"+id;
		}
		turnPage(url,"get",'');
	}
	function delModule(id){
		var url = "module/delete/"+id;
		turnPage(url,"get",'');
	}
	</script>