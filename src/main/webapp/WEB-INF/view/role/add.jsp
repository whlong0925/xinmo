<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="webRoot" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" href="${webRoot}/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${webRoot}/js/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${webRoot}/js/ztree/js/jquery.ztree.excheck.js"></script>
<style type="text/css">
div.treeContent {width:250px;height:362px;text-align:left;}
ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
</style>
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
				<label class="control-label" for="name">权限配置</label>
				<div class="controls">
					<input type="hidden" name="functionIds" id="functionIds" />
					<input type="text" class="input-xlarge" id="functionNames" name="functionNames" value=""/><a class="checkFun" href="#">选择</a>
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
				<input type="button" class="btn btn-success btn-large j_submit" value="Save Role" /> <a class="btn" href="#">Cancel</a>
			</div>
		</fieldset>
	</form>
	<div id="treeContent" class="treeContent" style="display:none; position: absolute;">
        <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
    </div>
</div>
<script type="text/javascript">

$(function () {
	var zTreeObj;
	var setting = {
			check:{
				 enable: true,
				 chkboxType:{ "Y" : "p", "N" : "s" }
			},
			data: {
				simpleData: {
					enable: true
				}
			},
	        callback: {
	            onCheck: onCheck
	        }
	}
	var zNodes = [];
	<c:forEach items="${functionList}" var="item">
		zNodes.push({id:${item.id}, pId:${item.parentId}, name:"${item.name}", open:true,checked:"${item.checked}"});
	</c:forEach>
	
	zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
	
	function onCheck(e, treeId, treeNode) {
		setCheckedIdAndNames();
    }
	
	function setCheckedIdAndNames(){
		var nodes = zTreeObj.getCheckedNodes(true),
        ids = "",names = "";
		for (var i=0, l=nodes.length; i<l; i++) {
            ids += nodes[i].id + ",";
            names += nodes[i].name + ",";
        }
        if (ids.length > 0 ) ids = ids.substring(0, ids.length-1);
        if (names.length > 0 ) names = names.substring(0, names.length-1);
        $("#functionIds").val(ids);
        $("#functionNames").val(names);
	}
	function onBodyDown(event) {
        if (!(event.target.id == "checkFun" || event.target.id == "treeContent" || $(event.target).parents("#treeContent").length>0)) {
            hideMenu();
        }
    }
	function hideMenu() {
        $("#treeContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
	setCheckedIdAndNames();
	$("#roleForm .j_submit").click(function() {
		var roleId = $("#roleId").val();
		var url = "role/add";
		if(roleId>0){
			url = "role/update"
		}
		var data = $("#roleForm").serialize();
		turnPage(url, "post", data);
	});
	$("#roleForm .checkFun").click(function() {
		 var functionObj = $("#functionNames");
         var functionOffset = functionObj.offset();
         $("#treeContent").css({left:functionOffset.left + "px", top:functionOffset.top + functionObj.outerHeight() + "px"}).slideDown("fast");
         $("body").bind("mousedown", onBodyDown);
	});
});
</script>



