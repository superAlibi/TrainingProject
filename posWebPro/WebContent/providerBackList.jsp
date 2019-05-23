<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="no-js">
<head>
  <title>POS系统 - 采购退货信息</title>
  <%@include file="common/head.jsp" %>
</head>
<body>

<%@include file="common/top.jsp" %>

<div class="am-cf admin-main">
  <%@include file="common/sidebar.jsp" %>

  <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">基本信息</strong> / <small>采购退货信息</small></div>
    </div>

	<div class="am-g">
      <div class="am-u-md-6 am-cf">
        <div class="am-btn-group am-btn-group-xs">
          <a href="ProviderBackModify" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</a>
        </div>
      </div>
    </div>

	<div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-title">采购退货单号</th><th>供应商</th><th>采购ID</th><th>退货时间</th><th>退货总价</th><th class="table-set">操作</th>
              </tr>
          </thead>
          <tbody>
          <c:forEach items="${requestScope.probs}" var="pb">
            <tr>
              <td>${pb.pbid}</td>
              <td>${pb.cname}</td>
              <td>${pb.sid}</td>
              <td>${pb.date}</td>
              <td>${pb.totalprice}</td>
              <td>
                <div class="am-btn-toolbar">
                  <div class="am-btn-group am-btn-group-xs">
                    <a href="ProviderBackModify?id=${pb.pbid}" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 编辑</a>
                    <a href="ProviderBackModify?id=${pb.pbid}&flag=d" class="am-btn am-btn-default am-btn-xs am-text-danger"><span class="am-icon-trash-o"></span> 删除</a>
                    <a href="ProviderBackDetailModify?pbid=${pb.pbid}" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span> 查看明细</a>
                  </div>
                </div>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
          <hr />
          <p>注：这里是对采购退货的维护！</p>
        </form>
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
</body>
</html>
