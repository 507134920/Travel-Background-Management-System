<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="active treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>产品管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a id="load-project-id"><i class="fa fa-circle-o"></i>项目管理</a></li>
            <li><a id="load-team-id"><i class="fa fa-circle-o"></i>团目管理</a></li>
            <li><a id="load-type-id"><i class="fa fa-circle-o"></i>产品分类</a></li>
            <li><a id="load-product-id"><i class="fa fa-circle-o"></i>产品管理</a></li>
             <li><a id="load-route-id"><i class="fa fa-circle-o"></i>路线管理</a></li>
            <li><a id="load-attachment-id"><i class="fa fa-circle-o"></i>附件管理</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>系统管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a id="load-permis-id"><i class="fa fa-circle-o"></i>权限管理</a></li>
            <li><a id="load-role-id"><i class="fa fa-circle-o"></i>角色管理</a></li>
            <li><a id="load-menu-id"><i class="fa fa-circle-o"></i>菜单管理</a></li>
            <li><a id="load-user-id"><i class="fa fa-circle-o"></i>用户管理</a></li>
          </ul>
        </li>
        
        <li class="active treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>销售管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a id="load-order-id"><i class="fa fa-circle-o"></i>订单管理</a></li>
          	<li><a id="load-count-id"><i class="fa fa-circle-o"></i>收藏统计</a></li>
          	<li><a id="load-ssum-id"> <i class="fa fa-circle-o"></i>产品购买量统计</a></li>
          </ul>
        </li>
        
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>
<script type="text/javascript">
$('#load-project-id').click(function(){
	var url="project/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-team-id').click(function(){
	var url="team/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-type-id').click(function(){
	var url="type/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-product-id').click(function(){
	var url="product/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
<!--路线管理-->
$('#load-route-id').click(function(){
	var url="route/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-attachment-id').click(function(){
	var url="attachment/attachmentUI.do?t="+Math.random(1000);
	$(".content").load(url);
})

$('#load-permis-id').click(function(){
	var url="permis/permisUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-role-id').click(function(){
	var url="role/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-menu-id').click(function(){
	var url="menu/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
$('#load-user-id').click(function(){
	var url="user/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})

<!--订单管理-->
$('#load-order-id').click(function(){
	var url="order/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
})
<!--收藏统计-->
$('#load-count-id').click(function(){
	var url="count/toCount.do?t="+Math.random(1000);
	$(".content").load(url);
})
<!--已支付订单中购买数量统计-->
$('#load-ssum-id').click(function(){
	var url="count/toCountNum.do?t="+Math.random(1000);
	$(".content").load(url);
})
</script>