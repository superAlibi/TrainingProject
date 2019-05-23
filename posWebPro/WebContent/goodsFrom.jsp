<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js">
<head>
<title>POS系统 - 商品信息</title>
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
					<strong class="am-text-primary am-text-lg">基本信息</strong> / <small>商品信息</small>
				</div>
			</div>

			<form class="am-form" method="post" action="GoodsModify">
				<input type="hidden" name="act" value="${empty goods ? 'add' : 'mdy'}">
				<input type="hidden" name="gid" value="${goods.gid}">
				<div class="am-tabs am-margin" data-am-tabs>
					<ul class="am-tabs-nav am-nav am-nav-tabs">
						<li class="am-active"><a href="#tab1">${empty goods ? '新增'  :  '修改' }类别</a></li>
					</ul>
					<div class="am-tabs-bd">
						<div class="am-tab-panel am-fade am-in am-active" id="tab1">
						<div class="am-g am-margin-top">
							<div class="am-u-sm-2 am-text-right">所属类别</div>
							<div class="am-u-sm-4 am-u-end">
								<select name="tid">
									<c:forEach items="${types}" var="t">
								<option${goods.tid==t.tid?' selected="selected"':''} value="${t.tid}">${t.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="am-g am-margin-top-sm">
							<div class="am-u-sm-2 am-text-right">名称</div>
							<div class="am-u-sm-4 am-u-end">
								<input type="text" name="name" class="am-input-sm" value="${goods.name }" >
							</div>
						</div>
						<div class="am-g am-margin-top-sm">
							<div class="am-u-sm-2 am-text-right">单位</div>
							<div class="am-u-sm-4 am-u-end">
								<input type="text" name="unit" class="am-input-sm" value="${goods.unit }">
							</div>
						</div>
						<div class="am-g am-margin-top-sm">
							<div class="am-u-sm-2 am-text-right">进价</div>
							<div class="am-u-sm-4 am-u-end">
								<input type="text" name="pin" class="am-input-sm" value="${goods.pin }">
							</div>
						</div>
						<div class="am-g am-margin-top-sm">
							<div class="am-u-sm-2 am-text-right">售价</div>
							<div class="am-u-sm-4 am-u-end">
								<input type="text" name="pout" class="am-input-sm" value="${goods.pout }">
							</div>
						</div>
						<div class="am-g am-margin-top-sm">
							<div class="am-u-sm-2 am-text-right">库存</div>
							<div class="am-u-sm-4 am-u-end">
								<input type="text" name="amount" class="am-input-sm" value="${goods.amount }" ${not empty goods ? 'readonly="readonly" ' : ''}>
							</div>
						</div>
						</div>
					</div>
				</div>

				<div class="am-margin">
					<input type="submit" class="am-btn am-btn-primary am-btn-xs" value="保存" /> 
					<input type="reset" class="am-btn am-btn-primary am-btn-xs" value="取消">
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
