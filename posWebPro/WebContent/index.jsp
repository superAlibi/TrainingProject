<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>POS系统 - 首页</title>
<%@include file="common/head.jsp"%>
</head>
<body>
	<%@include file="common/top.jsp"%>
	<div class="am-cf admin-main">
		<%@include file="common/sidebar.jsp"%>
		<!-- content start -->
		<div class="admin-content">

			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">首页</strong> / <small>每日KPI</small>
				</div>
			</div>

			<ul
				class="am-avg-sm-1 am-avg-md-4 am-margin am-padding am-text-center admin-content-list ">
				<li><a href="custForm.jsp" class="am-text-success"><span class="am-icon-btn am-icon-file-text"></span><br />新增客户<br />${newUser.cnt}</a></li>
				<li><a href="SellList" class="am-text-warning"><span
						class="am-icon-btn am-icon-briefcase"></span><br />成交订单<br />${sellOrder.cnt}</a></li>
				<li><a href="CustomBackList" class="am-text-danger"><span
						class="am-icon-btn am-icon-recycle"></span><br />销售退货<br />${custBack.cnt}</a></li>
				<li><a href="ProviderBackList" class="am-text-secondary"><span
						class="am-icon-btn am-icon-user-md"></span><br />采购退货<br />${pvdBack.cnt}</a></li>
			</ul>

			<div class="am-g">
				<div class="am-u-md-6">
					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-1'}"> 商品类别销量排行
							<span class="am-icon-chevron-down am-fr"></span>
						</div>
						<div class="am-panel-bd am-collapse am-in" id="collapse-panel-1">
							<div id="main1" class="am-list" style="width: 100%; height: 400px;"></div>
						</div>
					</div>
				</div>

				<div class="am-u-md-6">
					<div class="am-panel am-panel-default">
						<div class="am-panel-hd am-cf" data-am-collapse="{target: '#collapse-panel-2'}"> 商品库存量排行 
							<span class="am-icon-chevron-down am-fr"></span>
						</div>
						<div class="am-panel-bd am-collapse am-in" id="collapse-panel-2">
							<div id="main2" class="am-list" style="width: 100%; height: 400px;"></div>
						</div>
					</div>

				</div>
			</div>
		</div>
		<!-- content end -->

	</div>

	<footer>
		<hr>
		<p class="am-padding-left">© 2016 zhdtedu, Inc.</p>
	</footer>

	<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/polyfill/rem.min.js"></script>
<script src="assets/js/polyfill/respond.min.js"></script>
<script src="assets/js/amazeui.legacy.js"></script>
<![endif]-->

	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/amazeui.js"></script>
	<!--<![endif]-->
	<script src="assets/js/app.js"></script>
	<script type="text/javascript" src="js/echarts.js"></script>
	<script type="text/javascript">
		$(function() {
			//基于准备好的dom，初始化echarts实例1
			var myChart1 = echarts.init(document.getElementById('main1'));
			option = {
				title : {
					text : '过去七天销售情况',
					subtext : '纯属虚构'
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					top : 'bottom',
					data : [ '类型1', '类型2', '类型3' ]
				},
				grid : {
					containLabel : true
				},
				toolbox : {
					feature : {
						saveAsImage : {}
					}
				},
				xAxis : {
					type : 'category',
					data : [ '7day', '6day', '5day', '4day', '3day', '2day', '1day' ]
				},
				yAxis : {
					type : 'value'
				},
				series : [ {
					name : '类型1',
					type : 'line',
					step : 'start',
					data : [ 120, 132, 101, 134, 90, 230, 210 ]
				}, {
					name : '类型2',
					type : 'line',
					step : 'middle',
					data : [ 220, 282, 201, 234, 290, 430, 410 ]
				}, {
					name : '类型3',
					type : 'line',
					step : 'end',
					data : [ 450, 432, 401, 454, 590, 530, 510 ]
				} ]
			};

			// 使用刚指定的配置项和数据显示图表。
			myChart1.setOption(option);

			//基于准备好的dom，初始化echarts实例1
			var myChart2 = echarts.init(document.getElementById('main2'));
			option2 = {
				title : {
					text : '商品销售量与库存对比',
					subtext : '纯属虚构'
				},
				toolbox : {
					show : true,
					feature : {
						dataZoom : {
							yAxisIndex : 'none'
						},
						dataView : {
							readOnly : false
						},
						magicType : {
							type : [ 'line', 'bar' ]
						},
						restore : {},
						saveAsImage : {}
					}
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b}: {c} ({d}%)"
				},
				legend : {
					x : 'center',
					y : 'bottom',
					data : [ '进口食品', '休闲食品', '茗茶', '饮料冲调', '粮油调味' ]
				},
				series : [ {
					name : '销售量',
					type : 'pie',
					selectedMode : 'single',
					radius : [ '10%', '30%' ],

					label : {
						normal : {
							position : 'inner'
						}
					},
					labelLine : {
						normal : {
							show : false
						}
					},
					data : [ {
						value : 335,
						name : '进口食品',
						selected : true
					}, {
						value : 679,
						name : '休闲食品'
					}, {
						value : 1548,
						name : '茗茶'
					} ]
				}, {
					name : '库存量',
					type : 'pie',
					radius : [ '40%', '55%' ],

					data : [ {
						value : 335,
						name : '进口食品'
					}, {
						value : 310,
						name : '休闲食品'
					}, {
						value : 234,
						name : '茗茶'
					}, {
						value : 135,
						name : '饮料冲调'
					}, {
						value : 1048,
						name : '粮油调味'
					} ]
				} ]
			};
			// 使用刚指定的配置项和数据显示图表。
			myChart2.setOption(option2);
		})
	</script>
</body>
</html>
