$(document).ready(function(){
   $("#uploadFormId")
   .on("click",".btn-upload",doUpload)
   .on("click",".btn-down",doDownload)
	//1.初始化页面select列表
	doGetPrjIdAndNames();
	doGetObjects();
});
function doDownload(){
	var id=$(this).parent().parent().data("id");
	var url="attachment/doDownload.do?id="+id;
	document.location.href=url;
}

function doGetPrjIdAndNames(){
	var url="attachment/doFindPrjIdAndNames.do";
	$.getJSON(url,function(result){
		console.log(result);
		if(result.state==1){//SUCCESS			
			doInitPrjSelect(result.data);//{"data":[{},{}]}
		}else{//ERROR(业务异常)
			alert(result.message);
		}
	});
}
/*初始化项目select(id与name)列表*/
function doInitPrjSelect(list){
	console.log(list);
	var select=$("#searchBelongId");
	select.append('<option value="">选择所属产品名</option>');
	var option="<option value=[id]>[name]</option>"
	for(var i in list){
		select.append(
				option.replace("[id]",list[i].id)
				.replace("[name]",list[i].name));
	}
}
function doGetObjects(){
	var url="attachment/doFindObjects.do";
	$.getJSON(url,function(result){
		console.log(result);
		if(result.state==1){
			setTableBodyRows(result.data.list);
		}else{
			alert(result.message);
		}
	});
}
function setTableBodyRows(list){
	//console.log(list);
	//alert(list[0].name);
	var tBody=$("#tbodyId");
	tBody.empty();
	for(var i in list){
		var tr=$("<tr></tr>");
		tr.data("id",list[i].id);
		tr.append("<td>"+list[i].title+"</td>");
		tr.append("<td>"+list[i].fileName+"</td>");
		tr.append("<td>"+list[i].contentType+"</td>");
		tr.append("<td>"+list[i].name+"</td>");
		tr.append('<td><button type="button" class="btn btn-default btn-down">down</button></td>')
	    tBody.append(tr);
	}
}

/*点击文件上传按钮执行此函数*/
function doUpload(){
	//异步提交表单($.ajaxSubmit为异步提交表单)
	//使用此函数时需要在页面引入(jquery.form.js )
	$("#uploadFormId").ajaxSubmit({
		type:"post",
		url:"attachment/doUpload.do",
		dataType:"json",
		success:function(result){
		alert(result.message);
		doGetObjects();
		}
	});
	//$("#uploadFormId").resetForm();
	return false;//防止表单重复提交的一种方式
}
