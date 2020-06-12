<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
	<%
    	String path = request.getContextPath();
    	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<html>
<head>
 	<title>饼状图</title>
 	<link rel="stylesheet" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js"></script>
</head>
<body>
	<div id="main" style="width:600px; height:400px;"></div>
	<script type="text/javascript">
	debugger
		$(function(){
			var url = '${pageContext.request.contextPath}/count/queryPie.do';
			serChartPie(url);
		});
	
		function serChartPie(url) {
			var myChart = echarts.init(document.getElementById('main'));
			myChart.showLoading({text:'正在努力地读取数据中...'});
			var label = [];
			var value = [];
			
			$.ajax({
				url:url,
				type:'post',
				dataType:'json',
				success:function(json){
				    label = json.label;
					values = json.value;
					for(var i=0;i<label.length;i++){
						value[i] = {'value':values[i],'name':json.label[i]};
					} 
					console.log(value);
					var option = {
			                title: {
			                    text: '收藏统计',
			                    subtext: '',
			                    x:'center'
			                },
			                legend: {
			                	orient:'vertical',
			                	x:'left',
			                    data: label
			                },
			                tooltip: {
			                    trigger: 'item',
			                    formatter:'{a}:{b}<br/>{c}:{d}%'
			                },
			                toolbox:{
			                	feature:{
			                		mark:{show:true},
			                		dataView:{show:true,readonly:false},
			                		magicType:{
			                			show:true,
			                			type:'pie',
			                			option:{
			                				funnel:{
			                					x:'25%',
			                					width:'50%',
			                					funnelAlign:'left',
			                					max:400
			                				}
			                			}
			                		},
			                		restore:{show:true},
			                		saveAsImage:{show:true}
			                	}
			                },
			                series:[{
			                	name:'产品名称',
			                	type:'pie',
			                	redius:'55%',
			                	center:['50%','60%','80','100'],
			                	roseType:'angle',
			                	data:value
			                }]
					}
					myChart.hideLoading();
					myChart.setOption(option);
				}
			});
		}
    </script>
	
</body>
</html>