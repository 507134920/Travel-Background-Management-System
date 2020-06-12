<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<script type="text/javascript" src="${basePath}/ttms/product/product_list.js"></script>
<script type="text/javascript" src="${basePath}/ttms/common/page.js"></script>
 <!-- 表单 -->
	<div class="container">
	   <!-- 页面导航 -->
	   <div class="page-header">
			<div class="page-title" style="padding-bottom: 5px">
				<ol class="breadcrumb">
				  <li class="active">产品信息管理</li>
				</ol>
			</div>
			<div class="page-stats"></div>
		</div>
		<form method="post" id="queryFormId">
		    <!-- 查询表单 -->
			<div class="row page-search">
			 <div class="col-md-12">
				<ul class="list-unstyled list-inline">
					<li>
						<select id="searchTeamId" class="form-control"></select>
					</li>
					<li>
						<select id="searchClassId" class="form-control"></select>
					</li>
					<li>
					  <select id="searchStatesId" class="form-control">
							<option value="">选择状态</option>
							<option value="0">待售</option>
							<option value="1">上架</option>
							<option value="2">下架</option>
					  </select>
					</li> 
					<li class='O1'><button type="button" class="btn btn-primary btn-search" >查询</button></li>
					<li class='O2'><button type="button" class="btn btn-primary btn-add">添加</button></li>
					<li class='O3'><button type="button" class="btn btn-primary btn-statesD">待售</button></li>
					<li class='O4'><button type="button" class="btn btn-primary btn-statesS">上架</button></li>
					<li class='O5'><button type="button" class="btn btn-primary btn-statesX">下架</button></li>
				</ul>
			  </div>
			</div>
			<!-- 列表显示内容 -->
			<div class="row col-md-12">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>选择</th>
							<th>产品编号</th>
							<th>产品名称</th>
							<th>所属团</th>			
							<th>上架时间</th>
							<th>下架时间</th>
							<th>预售数量</th>
							<th>最低数量</th>
							<th>产品价格</th>
							<th>所属分类</th>
							<th>晚数</th>
							<th>产品状态</th>
							<th>操作</th>
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