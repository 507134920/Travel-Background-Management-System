var zTree;
var setting = {
		data : {   
			simpleData : {
				enable : true,
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		}
}
$(document).ready(function(){
	//点击上级菜单
	$("#editTypeForm").on("click",".load-product-type",doLoadZTreeNodes);
    $("#typeLayer")
    .on("click",".btn-cancle",doHideZtree)
    .on("click",".btn-confirm",doSetSelectedNode);
	//点击返回按钮执行doBack函数
    $("#btn-return").click(doBack);
    //点击确认按钮提交
	$("#btn-save").click(function(){
		doSaveOrUpdate();
	});
	//获得id值(等修改或返回以后一定要将id的值移除)
	var id=$(".content").data("id");
	alert("表单上id的值为"+id);
	//假如id有值说明是修改,则根据id执行查找
	if(id)doFindObjectById(id);
});
/*点击回退或修改结束执行此方法*/
function doBack(){
	//清空编辑页面数据,解除数据绑定?
    doClearData();
	//加载列表页面,重新显示查询结果
	var url="type/listUI.do";
	// .content 在index.jsp中
	$(".content").load(url);
}
/*根据id执行查询获得记录信息*/
function doFindObjectById(id){
	var url="type/doFindObjectById.do";
	var params={"id":id};
	$.post(url,params,function(result){
		if(result.state==1){
			doSetEditFormData(result.data);//初始化表单数据
		}else{
			alert(result.message);
		}
	});
}
/*修改时初始化表单数据*/
function doSetEditFormData(type){
	$("#editTypeForm").data("parentId",type.parentId);
	$("#typeNameId").val(type.name);
	$("#parentNameId").val(type.parentName);
	$("#typeSortId").val(type.sort);
	$("#typeNoteId").html(type.note);
}
//提交表单时实现保存和修改操作
function doSaveOrUpdate(){
	//0、验证表单数据是否为空
	if($("#editTypeForm").valid()){
		//1.获得页面表单中的数据
		var params=getEditFormData();
		alert(params);
		var id=$(".content").data("id");
		if(id)params.id=id;//在json对象中添加一个新的key/value
		//2.发送异步请求提交数据
		var saveUrl="type/doSaveObject.do";
		var updateUrl="type/doUpdateObject.do";
		var url=id?updateUrl:saveUrl;
		console.log(JSON.stringify(params));
		$.post(url,params,function(result){
			if(result.state==1){
				alert("操作成功");
				doClearData();
				$(".content").load('type/listUI.do');
			}else{
				alert(result.message);
			}
		});
	}
	
}
/*清空表单数据*/
function doClearData(){
   //1.清空所有类选择器dynamicClear标识的对象的内容
   $("#editTypeForm .dynamicClear").val("");//技巧应用
   //2.移除绑定的数据(因为添加和修改要共用一个页面)
   $(".content").data("id","");
   $("#editTypeForm").removeData("parentId");
}
//获取表单参数
function getEditFormData(){
	var params={
	  "name":$("#typeNameId").val(),
	  "parentId":$("#editTypeForm").data("parentId"),
	  "sort":$("#typeSortId").val(),
	  "note":$("#typeNoteId").val()
	};
	return params;
}

/*设置上级分类信息，选中的节点*/
function doSetSelectedNode(){
	//1.获得选中的数据信息、节点对象
	var nodes=zTree.getSelectedNodes();
	//2.将数据填充到form表单上
	//console.log(nodes[0].id);
    $("#parentNameId").val(nodes[0].name);
    $("#editTypeForm").data("parentId",nodes[0].id);
    //4.隐藏zTree
    doHideZtree();
}

/**编辑 编辑页面 的上级分类表单元素时执行此函数，显示Ztree以及树上的节点信息*/
function doLoadZTreeNodes(){
	//1.显示Ztree窗口(在type_edit.jsp页面上默认是隐藏的)
	$("#typeLayer").css("display","block");
	
	//2.发送异步请求加载分类信息,更新Ztree节点内容
	var url="type/doFindZtreeNodes.do"
	$.getJSON(url,function(result){
		console.log("result="+JSON.stringify(result))
		if(result.state==1){
			//访问zTree方法通过数据初始化节点信息
			zTree=$.fn.zTree.init($("#typeTree"),setting,result.data);
		}else{
			alert(result.message);
	 }
 });
 
}

/**隐藏Ztree*/
function doHideZtree(){
    $("#typeLayer").css("display","none");
}