$(function(){
	//在queryFormId对应对象的 btn-search 等元素上注册click事件
	$("#queryFormId")
	.on("click",".btn-delete",doDeleteObject)
	.on("click",".btn-search",doQueryObjects)
	.on("click",".btn-add,.btn-update",doLoadEditPage)
	//页面加载完成执行数据的获取操作
	doGetObjects();
});
//删除
function doDeleteObject(){
	var url="route/doDeleteObject.do";
	//获取模态框上绑定的值
	//2、获得选中的checkbox的id值
	var ids="";
	$("#tbodyId input[name ='checkId']").each(function() {
		if($(this).prop("checked")){//判断此input对象是否是选中的
			if(ids == ""){
				ids+=$(this).val();
			}else{
				ids+=","+$(this).val();
			}
		}
	});
	alert(ids);
	if(ids ==""){
		alert("请至少选择一个");
		return;
	}
	var params ={"rid":ids};
	$.getJSON(url,params,function(result){
		if(result.state == 1){
			doGetObjects();
		}else if(result.state == 0){
			alert(result.message);
		}
	});
}
//查询按钮
function doQueryObjects(){
	//1.修改当前页的值为1
	$("#pageId").data("pageCurrent",1);
	//2.执行查询动作(重用doGetObjects方法)
	doGetObjects();	
}

//加载编辑页面
function doLoadEditPage(){
	var url ="route/editUI.do";
	var title;
	
	if($(this).hasClass("btn-add")){
		title= "添加路线信息";
	}
	if($(this).hasClass("btn-update")){
		//将获得的要修改记录的id值绑定到模态框上
		//为什么要绑定这个id值？？
		//目的是根据模态框的这个id值判定要执行的是修改操作还是添加操作
		var idValue = $(this).parent().parent().data("id");
		$("#modal-dialog").data("idKey",idValue);
		title= "修改路线信息,id="+idValue;
	}
	//在模态框内异步加载显示编辑页面
	$("#modal-dialog .modal-body").load(url,function(){
		$(".modal-title").html(title);
		//页面架子啊模态框，show 表示显示 ，hide表示隐藏
		$("#modal-dialog").modal("show");
	});
	
}

//获取路线信息
function doGetObjects(){
	//初始化url
	var url="route/doFindPageObjects.do";
	//初始化参数对象
	var params={startState:$("#searchstartState").val(),
				endState:$("#searchendState").val() };	
	console.log(params);
	//根据pageId绑定的值 动态确定当前页
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(!pageCurrent)pageCurrent=1;
	params['pageCurrent']=pageCurrent;
		
	//发送异步启动获取分页数据
	$.post(url,params,function(result){
		if(result.state==1){
			//将team数据显示在table的tbody中
			setTableBodyRows(result.data.list);
			//设置分页信息
			setPagination(result.data.pageObject);
		}else{
			alert(result.message);
		}	
	});
}


//将数据填充到table的body中
function setTableBodyRows(result) {
	debugger
	//1、获得tbody对象
	var tBody = $("#tbodyId");
	tBody.empty(); 
	//2、迭代数据集result
	for(var i in result){
		//2.1、构建一个tr对象
		var tr = $("<tr></tr>");
			//2.1.1、绑定id 为修改操作提供id
		tr.data("id",result[i].rid);
		//2.2、构建每行td对象
		//2.3、在td对象内填充具体数据
		var tds = "<td><input type='checkbox' name='checkId' value=' "+result[i].rid+" '></td>"+	
				  "<td>"+result[i].startState+"</td>"+
				  "<td>"+result[i].endState+"</td>"+
				  "<td>"+result[i].name+"</td>"+ 
				  "<td><img width = '100' height = '100' src ='dist/"+result[i].bigPic+" ' ></td>"+ 
				  "<td><img width = '100' height = '100' src ='dist/"+result[i].smallPic+" '></td>"+ 
				  "<td><img width = '100' height = '100' src ='dist/"+result[i].rimage+" '></td>"+ 
				  "<td><input type='button' class='btn btn-default btn-update' value='修改'/></td>";
		//2.4、将td添加到tr中
		tr.append(tds);
		//2.5、将tr追加到tbody中
		tBody.append(tr);
	}
}