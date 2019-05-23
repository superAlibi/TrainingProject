<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- sidebar start -->
<div class="admin-sidebar">
  <ul class="am-list admin-sidebar-list">
    <li><a href="index.jsp"><span class="am-icon-home"></span> 首页</a></li>
    <li class="admin-parent">
      <a class="am-cf" data-am-collapse="{target: '#collapse-nav1'}"><span class="am-icon-file"></span> 基本信息 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
      <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav1">
        <li><a href="GoodsTypeList" class="am-cf"><span class="am-icon-check"></span> 商品类别<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
        <li><a href="GoodsList"><span class="am-icon-puzzle-piece"></span> 商品信息</a></li>
        <li><a href="CustList?type=1"><span class="am-icon-th"></span> 客户信息<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
        <li><a href="CustList?type=2"><span class="am-icon-calendar"></span> 供应商</a></li>
      </ul>
    </li>
    <li class="admin-parent">
    	<a class="am-cf" data-am-collapse="{target: '#collapse-nav2'}"><span class="am-icon-file"></span> 商品采购 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
      <ul class="am-list am-collapsing admin-sidebar-sub am-in" id="collapse-nav2">
        <li><a href="StockList" class="am-cf"><span class="am-icon-check"></span> 采购信息<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
        <li><a href="ProviderBackList"><span class="am-icon-puzzle-piece"></span> 采购退货</a></li>
      </ul>
    </li>
    <li class="admin-parent">
    	<a class="am-cf" data-am-collapse="{target: '#collapse-nav3'}"><span class="am-icon-file"></span> 商品销售 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
      <ul class="am-list am-collapsing admin-sidebar-sub am-in" id="collapse-nav3">
        <li><a href="SellList" class="am-cf"><span class="am-icon-check"></span> 销售信息<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
        <li><a href="CustomBackList"><span class="am-icon-puzzle-piece"></span> 销售退货</a></li>
      </ul>
    </li>
    <li><a href="#"><span class="am-icon-sign-out"></span> 库存统计</a></li>
    <li><a href="#"><span class="am-icon-sign-out"></span> 系统维护</a></li>
  </ul>

  <div class="am-panel am-panel-default admin-sidebar-panel">
    <div class="am-panel-bd">
      <p><span class="am-icon-bookmark"></span> 公告</p>
      <p>时光静好，与君语；细水流年，与君同。</p>
    </div>
  </div>
</div>

<!-- sidebar end -->
