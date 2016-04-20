<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row-fluid">
	<div class="page-header">
		<h1>
			New Role User
		</h1>
	</div>
	<form id="userRoleForm" class="form-horizontal" method="post">
		<fieldset>
			<div class="control-group">
				<label class="control-label" for="name">用户名</label>
				<div class="controls">
					${user.username}
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="active">角色</label>
				<div class="controls">
					<c:forEach items="${roleList}" var="role">
						<input type="checkbox" name="roleIds" value="${role.id}" <c:if test="${role.checked == 1}">checked="checked"</c:if> /> ${role.name }
					</c:forEach>
					
				</div>
			</div>
			<input type="hidden" name="userId" value="${user.id}" />
			<div class="form-actions">
				<input type="button" class="btn btn-success btn-large j_submit"
					value="Save" /> <a class="btn" href="#">Cancel</a>
			</div>
		</fieldset>
	</form>
</div>
<script type="text/javascript">
	$("#userRoleForm .j_submit").click(function() {
		var url = "user/role/add";
		var data = $("#userRoleForm").serialize();
		turnPage(url, "post", data);
	});
</script>