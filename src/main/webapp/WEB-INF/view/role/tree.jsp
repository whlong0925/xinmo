<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="webRoot" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${webRoot}/js/MzTreeView11.js"></script>
<script>
function doSubmit(){
		var funs = document.getElementsByName('functionIds');
		var s = false;
		for(i = 0; i < funs.length; i++){
			if(funs[i].checked){
				s = true;
				break;
			}
		}
		if(!s){
			if(confirm('确定要修改吗')){
				document.role_add_form.submit();
				return true;
			}
			else{
				return false;
			}
		}
		document.role_add_form.submit();
		return true;
}
</script>
<form id="role_add_form" action="" method="post">
<script language="JavaScript" type="text/javascript">
    window.tree = new MzTreeView("tree");
    tree.icons["property"] = "property.gif";
    tree.icons["css"] = "collection.gif";
    tree.icons["event"] = "collection.gif";
    tree.icons["book"] = "book.gif";
    tree.iconsExpand["book"] = "bookopen.gif"; //展开时对应的图片
    tree.setIconPath("${webRoot}/img/TreeView/"); //可用相对路径
    tree.nodes["-1_0"] = "ctrl:true;ctrlName:root_ctrl;text:All;";
  	<c:forEach items="${sys_module}" var="fun"> 
  		tree.nodes["${fun.parentId}_${fun.id}"] = "ctrl:true;ctrlName:functionIds;text:${fun.name};ctrlChecked:${fun.isChecked!=1}"; 
  	</c:forEach> 
    tree.setURL("#");
    tree.wordLine = false;
    tree.setTarget("main");
    document.write(tree.toString());
    tree.expandAll();
</script>	
<input name="roleId" id="roleId" type="hidden" value="${roleId}"/>						
 <input type="button" name="submit" value="提交" onclick="doSubmit()" />
</form>