//页面加载完成执行
$(document).ready(function(){
	//初始化所属团、分类信息的select列表
	doGetTeamIdAndNames();
	doGetClassIdAndNames();
	
	//在模态框的对应按钮上注册事件
	$("#modal-dialog").on("click",".ok",doSaveObject);
	//在模态框隐藏时解除事件的注册
	$("#modal-dialog").on("hidden.bs.modal",function(){
		//.ok上移除click事件
		$(this).off("click",".ok");
		$(this).removeData("id");
	});
});

/*获得团的id和名称*/
function doGetTeamIdAndNames(){
	var url="product/doFindTeamIdAndNames.do";
	$.getJSON(url,function(result){
		if(result.state==1){
			doInitTeamSelect(result.data);
			//修改时,等select列表页面初始化完成要根据
			//id初始化其它数据
			//var id=$("#modal-dialog").data("id");
			//if(id)doFindObjectById();
		}else{
			alert(result.message);
		}
	});
}
/*初始化所属项目的select下拉框*/
function doInitTeamSelect(list){
	var select=$("#teamId");
	select.append(
	"<option>==请选择==</option>")
	var option=
	"<option value=[id]>[name]</option>"
	for(var i in list){
		select.append(
		option.replace("[id]",list[i].id)
		      .replace("[name]",list[i].name));
	}
}
/*获得分类信息的id和名称*/
function doGetClassIdAndNames(){
	var url="product/doFindClassIdAndNames.do";
	$.getJSON(url,function(result){
		if(result.state==1){
			doInitClassSelect(result.data);
			//修改时,等select列表页面初始化完成要根据
			//id初始化其它数据
			var id=$("#modal-dialog").data("id");
			if(id)doFindObjectById();
		}else{
			alert(result.message);
		}
	});
}
/*初始化所属项目的select下拉框*/
function doInitClassSelect(list){
	var select=$("#classId");
	select.append(
	"<option>==请选择==</option>")
	var option=
	"<option value=[id]>[name]</option>"
	for(var i in list){
		select.append(
		option.replace("[id]",list[i].id)
		      .replace("[name]",list[i].name));
	}
}

function doFindObjectById(){
	var url="product/doFindObjectById.do";
	var id=$("#modal-dialog").data("id");
	var params={"id":id};
	console.log(params);
	$.post(url,params,function(result){
		if(result.state==1){
			//初始化页面数据
			doInitEditFormData(result.data);
		}else{
			alert(result.message);
		}
	})
}
//在修改时初始化表单数据-->呈现数据
function doInitEditFormData(result){
	$("#code").val(result.code);
	$("#name").val(result.name);
	$("#teamId").val(result.teamId),
	$("#exText").html(result.exText),
	$("#onlineDate").val(result.onlineDate),
	$("#offlineDate").val(result.offlineDate),
	$("#quantity").val(result.quantity),
	$("#minQty").val(result.minQty),
	$("#soldQty").val(result.soldQty),
	$("#price").val(result.price),
	$("#classId").val(result.classId),
	$("#nights").val(result.nights),	
	$('#editFormId input[name="states"]')
		.each(function(){
			if($(this).val()==result.state){
				$(this).prop("checked",true)
			}
		});
	$("#note").html(result.note);
	
}


function doSaveObject(){
	//1.验证表单数据(非空验证)
	if(!$("#editFormId").valid())return;
	//2.获得表单数据
	var params=getEditFormData();
	var id=$("#modal-dialog").data("id");
	if(id)params.id=id;//假如是修改要追加id
	//3.提交异步请求,将数据写入到服务端
	var saveUrl="product/doSaveObject.do";
	var updateUrl="product/doUpdateObject.do";
	var url=id?updateUrl:saveUrl;
	$.post(url,params,function(result){
		if(result.state==1){
			$("#modal-dialog").modal("hide");
			doGetObjects();
		}else{
			alert(result.message);
			}
	});
}
/*获得表单数据*/
function getEditFormData(){
	var params={
			"code":$("#code").val(),
			"name":$("#name").val(),
			"teamId":$("#teamId").val(),
			"exText":$("#exText").val(),
			"onlineDate":$("#onlineDate").val(),
			"offlineDate":$("#offlineDate").val(),
			"quantity":$("#quantity").val(),
			"minQty":$("#minQty").val(),
			"soldQty":$("#soldQty").val(),
			"price":$("#price").val(),
			"classId":$("#classId").val(),
			"nights":$("#nights").val(),
			"states":$('input[name="states"]:checked').val(),
			"note":$('#noteId').val()
	};
	return params;
}