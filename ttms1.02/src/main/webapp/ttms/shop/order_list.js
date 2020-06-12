$(function(){
	$("#queryFormId").on("click",".btn-search",doQueryObjects)
	//页面加载完成执行数据的获取操作
	doGetObjects();
});
/*点击查询按钮时执行此方法*/
function doQueryObjects(){
	//1.初始化pageCurrent的值
	$("#pageId").data("pageCurrent",1);
	//2.执行查询操作
	doGetObjects();
}

//获取订单信息
function doGetObjects(){
	 //1.通过异步请求($.post)获得服务端团信息
	 var url="order/doFindObjects.do";
	 var pageCurrent=$("#pageId").data("pageCurrent");
	 if(!pageCurrent)pageCurrent=1;
	 var params={"pageCurrent":pageCurrent};
	 params.states=$("#searchStatesId").val();
	 $.post(url,params,function(result){
		if(result.state==1){
			//2.将数据显示到页面的tbody位置
			setTableBodyRows(result.data.list);
			//设置分页信息
			setPagination(result.data.pageObject);
		}else{
			//显示业务异常信息
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
	var firstTd='<td><input type="checkbox" name="checkedItem" value="[id]"></td>';
	//2、迭代数据集result
	for(var i in result){
		//2.1、构建一个tr对象
		var tr = $("<tr></tr>");
			//2.1.1、绑定id 为修改操作提供id
		tr.data("id",result[i].id);
		//2.2、构建每行td对象
		//2.3、在td对象内填充具体数据
		var tds = "<td><input type='checkbox' name='checkId' value=' "+result[i].id+" '></td>"+	
		          "<td>"+result[i].code+"</td>"+
				  "<td>"+result[i].productName+"</td>"+
				  "<td>"+result[i].num+"</td>"+
				  "<td>"+result[i].money+"</td>"+  
				  "<td>"+(result[i].states?"已支付":"未支付")+"</td>"+
				  "<td>"+result[i].createTime+"</td>";
		//2.4、将td添加到tr中
		tr.append(tds);
		//2.5、将tr追加到tbody中
		tBody.append(tr);
	}
}