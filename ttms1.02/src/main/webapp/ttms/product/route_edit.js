$(function(){
	var bigPic;
	var smallPic;
	var rimage;
	doGetProIdAndNames();
	$(".modal-dialog")
	.on("click",".ok",doSaveOrUpdate);
	
	//在模态框隐藏后 移除 .ok 上的点击事件 防止数据多次提交
	$("#modal-dialog").on("hidden.bs.modal",function(){
		//off 移除on()绑定的事件;移除一个事件处理函数。
		$("#modal-dialog").off("click",".ok");
		//模态框隐藏以后移除绑定的数据
		$("#modal-dialog").removeData("idKey");
	});
	
	//获取模态框上绑定的值
	var id=$("#modal-dialog").data("idKey");
	//alert(id);
	if(id)doFindObjectById(id);
});

/*获得产品的id和名称*/
function doGetProIdAndNames(){
	var url="route/doFindProIdAndNames.do";
	$.getJSON(url,function(result){
		if(result.state==1){
			doInitProSelect(result.data);
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
function doInitProSelect(list){
	var select=$("#productId");
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
//根据id执行查询操作
function doFindObjectById(id){
	var url="route/doFindObjectById.do";
	var params ={"rid":id};
	console.log(params);
	$.getJSON(url,params,function(result){
		if(result.state == 1){
			doInitFormData(result.data);//result.data返回的对象，项目信息
		}else if(result.state == 0){
			alert(result.message);
		}
	});
}

//在修改时初始化表单数据-->呈现数据
function doInitFormData(data){
	$("#startState").val(data.startState);
	$("#endState").val(data.endState);
	$("#productId").val(data.productId);
	$("#bigPic").val(data.bigPic);
	$("#smallPic").val(data.smallPic);
	$("#rimage").val(data.rimage);
	
}

//添加或修改数据
function doSaveOrUpdate(){
	//0、验证表单数据是否为空
	if(!$("#editFormId").valid())return;
	//1、获得表单数据
	var params=getEditFormDate();
	//alert(params);
	//console.log(params);
	//2、异步提交表单数据
	var insertUrl="route/doSaveObject.do";
	var updateUrl="route/doUpdateObject.do";
	//获取模态框上绑定的id值
	var id =$("#modal-dialog").data("idKey");
	//根据id值判定时insert还是update？？
	var url =id?updateUrl:insertUrl;
	//在修改时需要id的值，所以假如时修改需要动态添加id
	if(id)params.rid=id;
	//alert(url);
	
	$.post(url,params,function(result){
		if(result.state == 1){
			//隐藏模态框
			$("#modal-dialog").modal("hide");
			alert(result.message);
			//重新查询数据
			doGetObjects();
			/*$(function(){
			    if($.cookie("refresh")!="no"){
			        setTimeout(function(){
			            window.location.reload();
			            $.cookie("refresh","no");
			        },5000);
			    }
			});*/
		}else{
			alert(result.message);
		}
	});
}
/*获得表单数据*/
function getEditFormDate(){
	
	var bigPics = $("#bigPic").val();
	var smallPics = $("#smallPic").val();
	var rimages = $("#rimage").val();
	if (!/.(jpg)$/.test(bigPics) || !/.(jpg)$/.test(smallPics) ||!/.(jpg)$/.test(rimages) ) {
		alert("请选择jpg格式的图片！！");
		return;
	}
	var params={
			"startState":$("#startState").val(),
			"endState":$("#endState").val(),
			"productId":$("#productId").val(),
			"bigPic":bigPic,
			"smallPic":smallPic,
			"rimage":rimage,
	};
	return params;
}

function getBigPic() {
	var file = document.getElementById("bigPic").files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function (e) {
		bigPic = this.result.substring(this.result.indexOf(',') + 1);
	}
}

function getSmallPic() {
	var file = document.getElementById("smallPic").files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function (e) {
		smallPic = this.result.substring(this.result.indexOf(',') + 1);
	}
}

function getRimage(){
	var file = document.getElementById("rimage").files[0];
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function (e) {
		rimage = this.result.substring(this.result.indexOf(',') + 1);
	}
}

