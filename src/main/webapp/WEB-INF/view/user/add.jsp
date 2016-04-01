<%@ page contentType="text/html;charset=UTF-8"%>
<div class="row-fluid">
	<div class="page-header">
		<h1>
			New User <small>User registration</small>
		</h1>
	</div>
	<form id="userForm" class="form-horizontal" method="post">
		<fieldset>
			<div class="control-group">
				<label class="control-label" for="name">用户名</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="username" name="username" value="${user.username }"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="name">登陆名</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="usercode" name="usercode" value="${user.username }"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password">密码</label>
				<div class="controls">
					<input type="password" class="input-xlarge" id="password" name="password" value="${user.password }"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="active">是否启用</label>
				<div class="controls">
					<input type="radio" name="status" value="1" checked="checked"  /> 是
					<input type="radio" name="status" value="0" /> 否
				</div>
			</div>
			<input type="hidden" name="id" value="${user.id}" id="userId"/>
			<div class="form-actions">
				<input type="button" class="btn btn-success btn-large j_submit"
					value="Save User" /> <a class="btn" href="#">Cancel</a>
			</div>
		</fieldset>
	</form>
</div>
<script type="text/javascript">
	$("#userForm .j_submit").click(function() {
		var userId = $("#userId").val();
		var url = "user/add";
		if(userId>0){
			url = "user/update"
		}
		var data = $("#userForm").serialize();
		turnPage(url, "post", data);
	});
</script>



