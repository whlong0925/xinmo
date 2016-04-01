<%@ page contentType="text/html;charset=UTF-8"%>
<div class="row-fluid">
	<div class="page-header">
		<h1>
			New Module <small>module registration</small>
		</h1>
	</div>
	<form id="moduleForm" class="form-horizontal" method="post">
		<fieldset>
			<div class="control-group">
				<label class="control-label" for="name">模块名</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="name" name="name" value="${module.name }"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="name">描述</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="description" name="description" value="${module.description}"/>
				</div>
			</div>
			<input type="hidden" name="id" value="${module.id}" id="moduleId"/>
			<div class="form-actions">
				<input type="button" class="btn btn-success btn-large j_submit"
					value="Save Module" /> <a class="btn" href="#">Cancel</a>
			</div>
		</fieldset>
	</form>
</div>
<script type="text/javascript">
	$("#moduleForm .j_submit").click(function() {
		var moduleId = $("#moduleId").val();
		var url = "module/add";
		if(moduleId>0){
			url = "module/update"
		}
		var data = $("#moduleForm").serialize();
		turnPage(url, "post", data);
	});
</script>



