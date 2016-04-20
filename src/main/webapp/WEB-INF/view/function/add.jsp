<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row-fluid">
	<div class="page-header">
		<h1>
			New Function <small>function registration</small>
		</h1>
	</div>
	<form id="functionForm" class="form-horizontal" method="post">
		<fieldset>
			<div class="control-group">
				<label class="control-label" for="name">父级别</label>
				<div class="controls">
					${parentName}
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="name">资源名称</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="name" name="name" value="${function.name }"/>
				</div>
			</div>
			
			<div class="control-group">
				<label class="control-label" for="name">功能路径</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="path" name="path" value="${function.path }"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="name">排序</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="sequence" name="sequence" value="${function.sequence }"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="name">描述</label>
				<div class="controls">
					<input type="text" class="input-xlarge" id="description" name="description" value="${function.description}"/>
				</div>
			</div>
			<input type="hidden" name="id" value="${function.id}" id="functionId"/>
			<input type="hidden" name="parentId" value="${parentId}" id="parentId"/>
			<input type="hidden" name="functionType" value="${functionType}" id="functionType"/>
			<div class="form-actions">
				<input type="button" class="btn btn-success btn-large j_submit"
					value="Save Function" />
			</div>
		</fieldset>
	</form>
</div>
<script type="text/javascript">
	$("#functionForm .j_submit").click(function() {
		var functionId = $("#functionId").val();
		var url = "module/add";
		if(functionId>0){
			url = "module/update"
		}
		var data = $("#functionForm").serialize();
		turnPage(url, "post", data);
	});
</script>



