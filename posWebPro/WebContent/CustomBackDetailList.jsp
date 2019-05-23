<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js">
<head>
<title>POS系统 - 销售退货明细</title>
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
					<strong class="am-text-primary am-text-lg">详细信息</strong> / <small>销售退货详细信息</small>
				</div>
			</div>
			<form class="am-form" action="CustomBackDetailModify">
				<input type="hidden" name="act" value="${empty custombd ? 'add':'mdy'}"> 
					<input type="hidden" name="gid" value="${custombd.gid}">
				<div class="am-tabs am-margin" data-am-tabs>
					<ul class="am-tabs-nav am-nav am-nav-tabs">
						<li class="am-active"><a href="#tab1">销售退货明细${empty custombd.cbdid ? '为空' : '如下'}</a></li>
					</ul>
					<div class="am-tabs-bd">

						<div class="am-tab-panel am-fade am-in am-active" id="tab1">
							<div class="am-g am-margin-top-sm">
								<div class="am-u-sm-2 am-text-right">销售退货明细ID</div>
								<div class="am-u-sm-4 am-u-end">
									<input type="text" name="pbdid" class="am-input-sm" readonly="readonly" value="${ empty custombd ? '空' : custombd.cbdid }">
								</div>
							</div>
							<div class="am-g am-margin-top-sm">
								<div class="am-u-sm-2 am-text-right">销售退货ID</div>
								<div class="am-u-sm-4 am-u-end">
									<input type="text" name="cbid" class="am-input-sm" readonly="readonly" value="${empty custombd ? cbid : custombd.cbid}">
								</div>
							</div>
							<div class="am-g am-margin-top-sm">
								<div class="am-u-sm-2 am-text-right">商品名称</div>
								<div class="am-u-sm-4 am-u-end">
									<input type="text" name="gname" class="am-input-sm" readonly="readonly" value="${empty custombd ? '空' : custombd.gname }">
								</div>
							</div>

							<div class="am-g am-margin-top-sm">
								<div class="am-u-sm-2 am-text-right">销售退货数量</div>
								<div class="am-u-sm-4 am-u-end">
									<input type="text" name="totalprice" class="am-input-sm" readonly="readonly" value="${empty custombd ? '空' : custombd.amount }">
								</div>
							</div>
							<div class="am-g am-margin-top-sm">
								<div class="am-u-sm-2 am-text-right">退款总额</div>
								<div class="am-u-sm-4 am-u-end">
									<input type="text" name="price" class="am-input-sm" readonly="readonly" value="${empty custombd ? '空' : custombd.price }">
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="am-margin">
					<input type="submit" class="am-btn am-btn-primary am-btn-xs"
						value="${empty custombd.cbdid ? '添加' : '编辑'}" />
				</div>
			</form>

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
</body>
</html>
