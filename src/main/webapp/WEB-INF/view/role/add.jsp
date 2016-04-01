<%@ page contentType="text/html;charset=UTF-8"%>
<div class="row-fluid">
	<div class="page-header">
		<h1>
			New Role <small>role registration</small>
		</h1>
	</div>
	<form id="roleForm" class="form-horizontal" method="post">
		<fieldset>
			<div class="control-group">
				<label class="control-label" for="name">角色名</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="name" name="name" value="${role.name }"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="name">描述</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="description" name="description" value="${role.description}"/>
				</div>
			</div>
			<input type="hidden" name="id" value="${role.id}" id="roleId"/>
			<div class="form-actions">
				<input type="button" class="btn btn-success btn-large j_submit"
					value="Save Role" /> <a class="btn" href="#">Cancel</a>
			</div>
		</fieldset>
	</form>
</div>
<script type="text/javascript">
	$("#roleForm .j_submit").click(function() {
		var roleId = $("#roleId").val();
		var url = "role/add";
		if(roleId>0){
			url = "role/update"
		}
		var data = $("#roleForm").serialize();
		turnPage(url, "post", data);
	});
</script>



