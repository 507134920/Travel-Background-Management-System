<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${basePath}/ttms/shop/order_list.js"></script>
<script type="text/javascript" src="${basePath}/ttms/common/page.js"></script>
 <!-- 表单 -->
	<div class="container">
	   <!-- 页面导航 -->
	   <div class="page-header">
			<div class="page-title" style="padding-bottom: 5px">
				<ol class="breadcrumb">
				  <li class="active">订单信息管理</li>
				</ol>
			</div>
			<div class="page-stats"></div>
		</div>
		<form method="post" id="queryFormId">
		    <!-- 查询表单 -->
			<div class="row page-search">
			 <div class="col-md-12">
				<ul class="list-unstyled list-inline">
					<li><select id="searchStatesId" class="form-control">
							<option value="">选择状态</option>
							<option value="1">已支付</option>
							<option value="0">未支付</option>
					</select></li>
					<li class='O1'><button type="button" class="btn btn-primary btn-search" >查询</button></li>					
				</ul>
			  </div>
			</div>
			<!-- 列表显示内容 -->
			<div class="row col-md-12">
				<table class="table table-bordered">
					<thead>
						<tr>
						   	<th>选择</th>
							<th>订单编码</th>
							<th>所购商品</th>
							<th>数量</th>
							<th>总金额</th>
							<th>状态</th>
							<th>订单创建时间</th>						
						</tr>
					</thead>
					<!-- ajax异步获得,并将数据填充到tbody中 -->
					<tbody id="tbodyId">
						
					</tbody>
				</table>
          <%@include file="../common/page.jsp" %>
			</div>
		</form>
	</div>  