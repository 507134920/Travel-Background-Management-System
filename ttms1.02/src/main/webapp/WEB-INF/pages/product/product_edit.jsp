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
		<label for=code class="col-sm-2 control-label" >产品编号:</label> 
	    <div class="col-sm-10">
			<input type="text" class="form-control required" name="code" id="code">
	    </div>
	</div>
	<div class="form-group">
		<label for="name" class="col-sm-2 control-label" >产品名称:</label> 
	    <div class="col-sm-10">
			<input type="text" class="form-control required" name="name" id="name">
	    </div>
	</div>
	<div class="form-group">
		<label for="teamId" class="col-sm-2 control-label">所属团:</label> 
		<div class="col-sm-10">
		 <select id="teamId" class="form-control required">
		 	<!-- 此处填充所属团 -->
		 </select>
		</div>
	</div>
	<div class="form-group">
		<label for="exText" class="col-sm-2 control-label" >特殊提示</label> 
	    <div class="col-sm-10">
			<textarea class="form-control" name="exText" id="exText"></textarea>
	    </div>
	</div>
	<div class="form-group">
		<label for="onlineDate"  class="col-sm-2 control-label">上架时间:</label> 
		<div class="col-sm-10">
		<input type="text" class="form-control datepicker " name="online" id="onlineDate">
		</div>
	</div>
	<div class="form-group">
		<label for="offlineDate" class="col-sm-2 control-label">下架时间:</label> 
		<div class="col-sm-10">
		<input type="text" class="form-control datepicker "  name="offline" id="offlineDate">
		</div>
	</div>
	
	<div class="form-group">
		<label for="quantity" class="col-sm-2 control-label" >预售数量:</label> 
	    <div class="col-sm-10">
			<input type="text" class="form-control required" name="quantity" id="quantity">
	    </div>
	</div>
	<div class="form-group">
		<label for="minQty" class="col-sm-2 control-label" >最低数量:</label> 
	    <div class="col-sm-10">
			<input type="text" class="form-control required" name="minQty" id="minQty">
	    </div>
	</div>
	<div class="form-group">
		<label for="soldQty" class="col-sm-2 control-label" >已售数量:</label> 
	    <div class="col-sm-10">
			<input type="text" class="form-control required" name="soldQty" id="soldQty">
	    </div>
	</div>
	<div class="form-group">
		<label for="nameId" class="col-sm-2 control-label" >产品价格:</label> 
	    <div class="col-sm-10">
			<input type="text" class="form-control required" name="price" id="price">
	    </div>
	</div>
	<div class="form-group">
		<label for="class-code" class="col-sm-2 control-label">所属分类:</label> 
		<div class="col-sm-10">
		 <select id="classId" class="form-control required">
		 	<!-- 此处填充所属分类 -->
		 </select>
		</div>
	</div>
	
	<div class="form-group">
		<label for="nights" class="col-sm-2 control-label" >晚数:</label> 
	    <div class="col-sm-10">
			<input type="text" class="form-control required" name="nights" id="nights">
	    </div>
	</div>
	<div class="form-group">
         <label class="col-sm-2 control-label"> 产品状态: </label>
         <div class="col-sm-10">
            <label class="radio-inline"><input  type="radio" name="states" checked value="0" > 待售</label>
            <label class="radio-inline"><input  type="radio" name="states" value="1"> 上架</label>
            <label class="radio-inline"><input  type="radio" name="states" value="2"> 下架</label>
         </div>
    </div>
	 <div class="form-group">
		<label for="note" class="col-sm-2 control-label">备注:</label>
		<div class="col-sm-10">
		<textarea class="form-control" name="note" id="note"></textarea>
		</div> 
	 </div>
</form>
<c:url var="url" value="/ttms/product/product_edit.js"></c:url>
<script type="text/javascript" src="${url}"></script>