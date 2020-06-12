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
    <title>柱状图</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
</head>

<body>
    <!-- 柱状图容器 -->
    <div id="main" style="width:600px; height:400px;">
    
    </div>
    <script type="text/javascript">
       $(function(){
            // 初始化
            //var myChart = echarts.init(document.getElementById('main'));
			var myChart = echarts.init($('#main')[0]); // 注意：这里init方法的参数的javascript对象，使用jQuery获取标签时，要将jQuery对象转成JavaScript对象；
 
            // 配置图标参数
            var options = {
                title: {
                    text: '已支付订单',
                    show: true, // 是否显示标题
                    subtext: '测试数据',
                    textStyle: {
                        fontSize: 18 // 标题文字大小
                    }
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['次数']
                },
                // X轴
                xAxis: {
                    data: [] // 异步请求时,这里要置空
                },
                // Y轴
                yAxis: {},
                series: [{
                    name: '次数',
                    type: 'bar', // 设置图表类型为柱状图
                    data: [] // 设置纵坐标的刻度(异步请求时,这里要置空)
                }]
            };
            // 给图标设置配置的参数
            myChart.setOption(options);
            myChart.showLoading(); // 显示加载动画
			// 异步请求加载数据
			//alert("开始");
            $.ajax({
                url: "${pageContext.request.contextPath}/count/queryForCount.do",
                type: "post",
                dataType: "json",
                success: function(data) {
                	//alert("开始");
                	//alert(data);
                	console.log(data);
                    var names = [];
                    var nums = [];
                    $.each(data, function(index, obj) {
                        names.push(obj.name);
                        nums.push(obj.count);
                    })
 
                    myChart.hideLoading(); // 隐藏加载动画
                    myChart.setOption({
                        legend: {
                            data: ['次数']
                        },
                        xAxis: {
                            data: names
                        },
                        series: [{
                            name: '次数',
                            type: 'bar', // 设置图表类型为柱状图
                            data: nums // 设置纵坐标的刻度
                        }]
                    });
                }
            });
        });
    </script>
</body>
</html>