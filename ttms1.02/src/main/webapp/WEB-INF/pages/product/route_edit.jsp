<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
//初始化datepicker对象
$('.datepicker').datepicker({
    format: 'yyyy/mm/dd',
    autoclose: true//选中自动关闭
})
</script>
<form  class="form-horizontal" role="form" id="editFormId">
	<div class="form-group">
		<label for="startState" class="col-sm-2 control-label" >起点:</label> 
	    <div class="col-sm-10">
			<input type="text" class="form-control required" name="name" id="startState"  placeholder="请输入起点">
	    </div>
	</div>
	<div class="form-group">
		<label for="endState" class="col-sm-2 control-label">终点:</label> 
		<div class="col-sm-10">
			<input type="text" class="form-control required" name="name" id="endState"  placeholder="请输入终点">
		</div>
	</div>
	<div class="form-group">
		<label for="productId" class="col-sm-2 control-label">所属产品:</label> 
		<div class="col-sm-10">
		 <select id="productId" class="form-control required"></select>
		</div>
	</div>
	<div class="form-group">
		<label for="bigPic" class="col-sm-2 control-label">大图片:</label> 
		<div class="col-sm-10">
			<input type="file" name="name" class="form-control required" id="bigPic" onchange="getBigPic()">
			<!-- <input type="text" class="form-control required" name="name" id="bigPic"  placeholder="img/product/size4/size4006.png">--> 
		</div>
	</div>
	<div class="form-group">
		<label for="smallPic" class="col-sm-2 control-label">小图片:</label> 
		<div class="col-sm-10">
			<input type="file" name="name" class="form-control required" id="smallPic" onchange="getSmallPic()">
			<!-- <input type="text" class="form-control required" name="name" id="smallPic"  placeholder="img/product/size2/size2006.jpg">--> 
		</div>
	</div>
	<div class="form-group">
		<label for="rimage" class="col-sm-2 control-label">缩略图:</label> 
		<div class="col-sm-10">
			<input type="file" name="name" class="form-control required" id="rimage" onchange="getRimage()">
			<!-- <input type="text" class="form-control required" name="name" id="rimage"  placeholder="img/product/small/small006.jpg"> -->
		</div>
	</div>
</form>
<c:url var="url" value="/ttms/product/route_edit.js"></c:url>
<script type="text/javascript" src="${url}"></script>