$(document).ready(function(){
	$("#queryFormId").on("click",".btn-add",doAddObjects)
	$("#tbodyId").on("click",".btn-del",doDelObjects)
	//1.初始化页面select列表
	doGetUserIdAndNames();
	doGetRoleIdAndNames();
	//2.获取产品信息,并添加到tbody
	doGetObjects();
});
function doGetUserIdAndNames() {
	var url = "permis/doFindUserIdAndNames.do";
	$.getJSON(url,function(result){
		if(result.state==1){//SUCCESS			
			doInitUserSelect(result.data);//{"data":[{},{}]}
		}else{//ERROR(业务异常)
			alert(result.message);
		}
	});
}
function doInitUserSelect(list) {
	var select=$("#searchUserId");
	select.append('<option value="">选择用户名</option>');
	var option="<option value=[id]>[username]</option>"
	for(var i in list){
		select.append(
				option.replace("[id]",list[i].id)
				.replace("[username]",list[i].username));
	}
}
function doGetRoleIdAndNames() {
	var url = "permis/doFindRoleIdAndNames.do";
	$.getJSON(url,function(result){
		if(result.state==1){//SUCCESS			
			doInitRoleSelect(result.data);//{"data":[{},{}]}
		}else{//ERROR(业务异常)
			alert(result.message);
		}
	});
}

function doInitRoleSelect(list) {
	var select=$("#searchRoleId");
	select.append('<option value="">选择角色名</option>');
	var option="<option value=[id]>[name]</option>"
	for(var i in list){
		select.append(
				option.replace("[id]",list[i].id)
				.replace("[name]",list[i].name));
	}
}
function doAddObjects() {
	var params={
			  "userId":$("#searchUserId").val(),
			  "roleId":$("#searchRoleId").val()
			};
	//console.log(params);
	var url = "permis/doAddObjects.do";
	$.post(url,params,function(result){
		//console.log("result"+result);
		if(result.state==1){
			alert("分配成功");
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}
/*获得用户选中的记录的id*/
function getCheckedIds(){
	var checkedIds="";//定义一个字符串对象
	$('#tbodyId input[name="checkedItem"]')
	.each(function(){//遍历元素对象
		//$(this)在这里指向的是input[name="checkedItem"]类型的对象
		if($(this).prop("checked")){
			if(checkedIds==""){
				checkedIds+=$(this).val();
			}else{
				checkedIds+=","+$(this).val();
			}
		}
	});
	return checkedIds;//"1,2,3,4,5"
}
function doDelObjects() {
	var checkedIds=getCheckedIds();
	if(checkedIds.length==0){
		alert("请至少选择一个");
		return;
	}
	var params={"checkedIds":checkedIds};
	var url = "permis/doDelObjects.do";
	$.post(url,params,function(result){
		if(result.state==1){
			doGetObjects();
		}else{
			alert(result.message);
		}
	});
}

function doGetObjects() {
	//1.通过异步请求($.post)获得服务端权限信息
	var url="permis/doFindObjects.do";
	var pageCurrent = $("#pageId").data("pageCurrent");
	if(!pageCurrent) {
		pageCurrent=1;
	}
	var params = {"pageCurrent":pageCurrent};
	$.post(url,params,function(result){
	if(result.state==1){
	//2.将产品信息更新到页面的tbody位置
		//2.1记录信息
		setTableBodyRows(result.data.list);
		//2.2分页信息
		setPagination(result.data.pageObject);
	}else{
	  //显示业务异常信息
	  alert(result.message);
	}
 });
}
function setTableBodyRows(list){
	var tBody=$("#tbodyId");
	tBody.empty();
	var firstTd='<td><input type="checkbox" name="checkedItem" value="[id]"></td>';
	for(var i in list){//循环一次取一行
	 var tr=$("<tr></tr>");
	 tr.data("id",list[i].id);//绑定数据,便于修改时使用
	 tr.append(firstTd.replace("[id]",list[i].id));
	 tr.append("<td>"+list[i].username+"</td>");
	 tr.append("<td>"+list[i].name+"</td>");
	 tr.append('<td><button type="button" class="btn btn-default btn-del">删除</td>');
	 tBody.append(tr);
	}
}
