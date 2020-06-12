$(document).ready(function(){
	$("#queryFormId")
	.on("click",".btn-search",doQueryObjects)
	.on("click",".btn-statesD,.btn-statesS,.btn-statesX",doStatesById)
	.on("click",".btn-add,.btn-update",doShowEditDialog)
	//1.初始化页面select列表
	doGetTeamIdAndNames();
	doGetClassIdAndNames();
	//2.获取产品信息,并添加到tbody
	doGetObjects();
});
//添加、修改操作 显示编辑对话框(内部会加载url对应的页面)
function doShowEditDialog(){
	var title;
	if($(this).hasClass("btn-add")){
		title="添加产品信息"
	}
	if($(this).hasClass("btn-update")){
	//修改时一般会在模态框上绑定一个id值
	//编辑页面加载完成要根据此id值查询产品信息
		//1.获得当前行的id值
		var trId=$(this).parent().parent().data("id");
		//2.将id值绑定到模态框
		$("#modal-dialog").data("id",trId);
		title="修改产品信息,id="+$("#modal-dialog").data("id");
	}
	var url="product/editUI.do"
	 //异步加载url
	$("#modal-dialog .modal-body")
	.load(url,function(){
	 //更新模态框标题
	 $(".modal-title").html(title);
	 //显示模态框
	 $("#modal-dialog").modal("show");
	});
}
/*点击查询按钮时执行此方法*/
function doQueryObjects(){
	//1.初始化pageCurrent的值
	$("#pageId").data("pageCurrent",1);
	//2.执行查询操作
	doGetObjects();
}

//获取团信息中id和name,然后通过此数据初始化select列表
function doGetTeamIdAndNames(){
	var url="product/doFindTeamIdAndNames.do";
	$.getJSON(url,function(result){
		if(result.state==1){//SUCCESS			
			doInitTeamSelect(result.data);//{"data":[{},{}]}
		}else{//ERROR(业务异常)
			alert(result.message);
		}
	});
}
//获取分类信息中id和name,然后通过此数据初始化select列表
function doGetClassIdAndNames(){
	var url="product/doFindClassIdAndNames.do";
	$.getJSON(url,function(result){
		if(result.state==1){//SUCCESS
			doInitClassSelect(result.data);//{"data":[{},{}]}
		}else{//ERROR(业务异常)
			alert(result.message);
		}
	});
}
/*初始化项目select(id与name)列表*/
function doInitTeamSelect(list){
	var select=$("#searchTeamId");
	select.append('<option value="">选择团目名</option>');
	var option="<option value=[id]>[name]</option>"
	for(var i in list){
		select.append(
				option.replace("[id]",list[i].id)
				.replace("[name]",list[i].name));
	}
}
/*初始化分类信息中的select(id与name)列表*/
function doInitClassSelect(list){
	var select=$("#searchClassId");
	select.append('<option value="">选择产品分类</option>');
	var option="<option value=[id]>[name]</option>"
	for(var i in list){
		select.append(
				option.replace("[id]",list[i].id)
				.replace("[name]",list[i].name));
	}
}

//实现产品信息的状态修改操作
function doStatesById(){
	//1.获得团信息相关数据
	//1)状态(states)数据
	var states=getStates($(this));
	//2)团ID(唯一标识):一个或多个
	var checkedIds=getCheckedIds();
	if(checkedIds.length==0){
		alert("请至少选择一个");
		return;
	}
	var params={"states":states,
		"checkedIds":checkedIds}
	//2.将数据异步提交到服务端,修改对应记录的状态.
    var url="product/doStatesById.do"
	$.post(url,params,function(result){
    	if(result.state==1){//SUCCESS
    		doGetObjects();//重新查询
    		alert(result.message);
    	}else{//ERROR
    		alert(result.message);
    	}
    });
}
/*获得产品待售、上架、下架状态信息*/
function getStates(obj){
	var states;
	if(obj.hasClass("btn-statesD")){
		states=0;//待售
	}
	if(obj.hasClass("btn-statesS")){
		states=1;//上架
	}
	if(obj.hasClass("btn-statesX")){
		states=2;//下架
	}
	return states;
}
/*获得用户选中的产品记录的id*/
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

function doGetObjects() {
	//1.通过异步请求($.post)获得服务端团信息
	var url="product/doFindObjects.do";
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent) {
		pageCurrent=1;
	}
	var params=getQueryFormData();
	params.pageCurrent=pageCurrent;
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

/*获取查询表单中的数据*/
function getQueryFormData(){
	var params={
	  "teamId":$("#searchTeamId").val(),
	  "classId":$("#searchClassId").val(),
	  "states":$("#searchStatesId").val()
	}
	return params;
}

function setTableBodyRows(list){
	var tBody=$("#tbodyId");
	tBody.empty();
	var firstTd='<td><input type="checkbox" name="checkedItem" value="[id]"></td>';
	for(var i in list){//循环一次取一行
	 var tr=$("<tr></tr>");
	 tr.data("id",list[i].id);//绑定数据,便于修改时使用
	 tr.append(firstTd.replace("[id]",list[i].id));
	 tr.append("<td>"+list[i].code+"</td>");
	 tr.append("<td>"+list[i].name+"</td>");
	 tr.append("<td>"+list[i].teamName+"</td>");
	 tr.append("<td>"+list[i].onlineDate+"</td>");
	 tr.append("<td>"+list[i].offlineDate+"</td>");
	 tr.append("<td>"+list[i].quantity+"</td>");
	 tr.append("<td>"+list[i].minQty+"</td>");
	 tr.append("<td>"+list[i].price+"</td>");
	 tr.append("<td>"+list[i].className+"</td>");
	 tr.append("<td>"+list[i].nights+"</td>");
	 tr.append("<td>"+(list[i].states==0?"待售":((list[i].states==1)?"上架":"下架"))+"</td>")
	 tr.append('<td><button type="button" class="btn btn-default btn-update">修改</td>');
	 tBody.append(tr);
	}
}
