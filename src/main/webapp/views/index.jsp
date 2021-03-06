<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/include_index.jsp" %>
<%@ include file="/common/indexcommon.jsp" %>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>湖北审计数据智能采集校验报送工具</title>
	<style>
	/** skins **/
	#header {
		background: #4B96B4;
		/* Old browsers */
		background: -moz-linear-gradient(-45deg, #4B96B4 0%, #59C0BF 100%);
		/* FF3.6+ */
		background: -webkit-gradient(linear, left top, right bottom, color-stop(0%, #4B96B4), color-stop(100%, #59C0BF));
		/* Chrome,Safari4+ */
		background: -webkit-linear-gradient(-45deg, #4B96B4 0%, #59C0BF 100%);
		/* Chrome10+,Safari5.1+ */
		background: -o-linear-gradient(-45deg, #4B96B4 0%, #59C0BF 100%);
		/* Opera 11.10+ */
		background: -ms-linear-gradient(-45deg, #4B96B4 0%, #59C0BF 100%);
		/* IE10+ */
		background: linear-gradient(-45deg, #4B96B4 0%, #59C0BF 100%);
		/* W3C */
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#4B96B4, endColorstr=#59C0BF, GradientType=1);
		/* IE6-9 fallback on horizontal gradient */
	}
	.content_tab{
		background: #4B96B4;
		/* Old browsers */
		background: -moz-linear-gradient(-45deg, #4B96B4 0%, #57B9BD 100%);
		/* FF3.6+ */
		background: -webkit-gradient(linear, left top, right bottom, color-stop(0%, #4B96B4), color-stop(100%, #57B9BD));
		/* Chrome,Safari4+ */
		background: -webkit-linear-gradient(-45deg, #4B96B4 0%, #57B9BD 100%);
		/* Chrome10+,Safari5.1+ */
		background: -o-linear-gradient(-45deg, #4B96B4 0%, #57B9BD 100%);
		/* Opera 11.10+ */
		background: -ms-linear-gradient(-45deg, #4B96B4 0%, #57B9BD 100%);
		/* IE10+ */
		background: linear-gradient(-45deg, #4B96B4 0%, #57B9BD 100%);
		/* W3C */
		filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#4B96B4, endColorstr=#57B9BD, GradientType=1);
		/* IE6-9 fallback on horizontal gradient */
	}
	.s-profile>a{background: url(${pageContext.request.contextPath}/resources/images/background.png) left top no-repeat;}
	</style>
</head>
<body>

<!-- 角色选择 -->
<div class="modal fade" id="selRoleModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 id="selRoleModalTitle" class="modal-title">
					请选择用户角色
				</h4>
			</div>
			<div class="modal-body">
				<select id="userRole" name="userRole" class="selectpicker">
					<c:forEach items="${user.userRoles }" var="item">
						<option value="${item.ROLE_ID}">${item.ROLE_NAME}</option>
					</c:forEach>
				</select>
			</div>
			<div class="modal-footer">
				<button id="selRole-btn" class="waves-effect btn btn-success btn-sm"
					style="margin-left: 10px; type="button"
					href="javascript:;">
					<i class="zmdi zmdi-save"></i> 确定
				</button>
			</div>
		</div>
	</div>
</div>

<header id="header">
	<ul id="menu">
		<li id="guide" class="line-trigger">
			<div class="line-wrap">
				<div class="line top"></div>
				<div class="line center"></div>
				<div class="line bottom"></div>
			</div>
		</li>
		<li id="logo" class="hidden-xs">
			<!-- <img src="${pageContext.request.contextPath}/resources/images/logo.png"/> -->
			<span id="system_title"> 湖北审计数据智能采集校验报送工具</span>
		</li>
		<li class="pull-right">
			<ul class="hi-menu">
				<!-- 搜索 -->
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-search"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<form id="search-form" class="form-inline">
							<div class="input-group">
								<input id="keywords" type="text" name="keywords" class="form-control" placeholder="搜索"/>
								<div class="input-group-btn">
									<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
								</div>
							</div>
						</form>
					</ul>
				</li>
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-transform"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li class="skin-switch">
							<i class="zmdi zmdi-swap" style="font-size: 16px;" ></i>&nbsp;&nbsp;&nbsp;&nbsp;角色切换
						</li>
						<li class="divider"></li>
						<c:if test="${user.userType == 'admin' }">
							<li style="text-align: center;">
								<a class="waves-effect" href="javascript:;">管理员角色</a>
							</li>
						</c:if>
						<c:if test="${user.userType == 'general' }">
							<c:forEach items="${user.userRoles }" var="item">
								<li style="text-align: center;">
									<c:if test="${user.userRoles.size() <= 1 }">
										<a class="waves-effect" href="javascript:;">${item.ROLE_NAME }</a>
									</c:if>
									<c:if test="${user.userRoles.size() > 1 }">
										<a class="waves-effect" href="javascript:changeRole('${item.ROLE_ID}');">${item.ROLE_NAME }</a>
									</c:if>
								</li>
							</c:forEach>
						</c:if>
					</ul>
				</li>
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-more-vert"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li class="hidden-xs">
							<a class="waves-effect" data-ma-action="fullscreen" href="javascript:fullPage();"><i class="zmdi zmdi-fullscreen"></i> 全屏模式</a>
						</li>
						<li>
							<a class="waves-effect" href="javascript:Tab.addTab('模板查看', '${pageContext.request.contextPath}/common/personal/manage');"><i class="zmdi zmdi-account"></i> 个人资料</a>
						</li>
						<li>
							<a class="waves-effect" href="javascript:Tab.addTab('模板查看', '${pageContext.request.contextPath}/common/setting/manage');"><i class="zmdi zmdi-settings"></i> 系统设置</a>
						</li>
						<li>
							<a class="waves-effect" href="javascript:logout();"><i class="zmdi zmdi-run"></i> 退出登录</a>
						</li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</header>
<section id="main">
	<!-- 左侧导航区 -->
	<aside id="sidebar">
		<!-- 个人资料区 -->
		<div class="s-profile">
			<a class="waves-effect waves-light" href="javascript:;">
				<div class="sp-pic">
					<img src="${pageContext.request.contextPath}/resources/images/avatar.png"/>
				</div>
				<div class="sp-info">
					${user.userName }，您好！
					<i class="zmdi zmdi-caret-down"></i>
				</div>
			</a>
			<ul class="main-menu">
				<li>
					<a class="waves-effect" href="javascript:Tab.addTab('模板查看', '${pageContext.request.contextPath}/common/personal/manage');"><i class="zmdi zmdi-account"></i> 个人资料</a>
				</li>
				<li>
					<a class="waves-effect" href="javascript:Tab.addTab('模板查看', '${pageContext.request.contextPath}/common/setting/manage');"><i class="zmdi zmdi-settings"></i> 系统设置</a>
				</li>
				<li>
					<a class="waves-effect" href="javascript:logout();"><i class="zmdi zmdi-run"></i> 退出登录</a>
				</li>
			</ul>
		</div>
		<!-- /个人资料区 -->
		<!-- 菜单区 -->
		<ul id="main-menu" class="main-menu">
			<!-- 
			<li>
			<a class="waves-effect" href="javascript:Tab.addTab('首页', 'home');"><i class="zmdi zmdi-home"></i> 首页</a>
			</li>
			<li class="sub-menu system_menus">
			<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-accounts"></i> 角色用户管理</a>
			<ul>
				<li><a class="waves-effect" href="javascript:Tab.addTab('角色管理', '/manage/role/index');">角色管理</a></li>
				<li><a class="waves-effect" href="javascript:Tab.addTab('用户管理', '${pageContext.request.contextPath}/common/user/init');">用户管理</a></li>
				<li><a class="waves-effect" href="javascript:Tab.addTab('产品管理', '${pageContext.request.contextPath}/demo/product/init');">产品管理</a></li>
			</ul>
			</li>
			<li class="sub-menu system_menus">
			<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-lock-outline"></i> 权限资源管理</a>
			<ul>
				<li><a class="waves-effect" href="javascript:Tab.addTab('权限管理', '/manage/resources/index');">权限管理</a></li>
				<li><a class="waves-effect" href="javascript:Tab.addTab('菜单管理', '${pageContext.request.contextPath}/common/menu/init');">菜单管理</a></li>
			</ul>
			</li>
			<li class="sub-menu system_menus">
			<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-more"></i> 其他数据管理</a>
			<ul>
				<li><a class="waves-effect" href="javascript:Tab.addTab('日志记录', '${pageContext.request.contextPath}/common/logging/init');">日志记录</a></li>
				<li><a class="waves-effect" href="javascript:Tab.addTab('模板查看', '${pageContext.request.contextPath}/template');">模板查看</a></li>
			</ul>
			</li>
			<li>
			<div class="upms-version">
				&copy; YAN FRAME V1.0
			</div>
			</li>
			 -->
		</ul>
		<!-- /菜单区 -->
	</aside>
	<!-- /左侧导航区 -->
	<section id="content">
		<div class="content_tab">
			<div class="tab_left">
				<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-left"></i></a>
			</div>
			<div class="tab_right">
				<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-right"></i></a>
			</div>
			<ul id="tabs" class="tabs">
				<li id="tab_home" data-index="home" data-closeable="false" class="cur">
					<a class="waves-effect waves-light">首页</a>
				</li>
			</ul>
		</div>
		<div class="content_main">
		<div id="iframe_home" class="iframe cur">
				<!-- <p><h4>Yan Demo</h4></p>
				<p><b>本项目是一个整合 SpringMVC+Spring+MyBatis（SSM）框架的Demo</b></p>
				<p><b>拥有高效率便捷开发模式，使开发人员更专注于业务，达到面向业务开发</b></p>
				<p><b>项目使用 Maven 构建，便于项目管理，支持 Oracle、MySql 等主流数据库</b></p>
				<p><b>前端展示界面采用基于 Boostrap 实现的响应式布局，并集成了一系列的动画效果插件，整体界面简洁、美观大方并可优雅的与后台完成交互操作</b></p>
				<p><b>项目封装了一系列常用方法、部署运行简单，便于个人或企业进行高效开发</b></p>
				<p><b>代码已托管至<a href="https://github.com/micyo202/sjjybsgj" target="_blank"> github</a>可下载进行查看</b></p>
				<p>Created by Yanzheng Since 2017</p>
				<p>Copyright <a href="https://github.com/micyo202" target="_blank">https://github.com/micyo202</a>. All rights reserved.</p> -->
				
				<table id="table"></table>
				
			</div>
				
		</div>
	</section>
</section>
<footer id="footer"></footer>

<script type="text/javascript">


var $table = $('#table');
var treeObj;
var userId;
$(function() {
	
	$table.bootstrapTable({
		url: '${pageContext.request.contextPath}/common/checked_log/listTotal',
		idField: 'userId',// 指定主键列
		singleSelect: true,
		search: true,
		contentType: "application/x-www-form-urlencoded",
		method: 'post',
		toolbar: '#toolbar',
		idField: 'id',
		clickToSelect: true,// 点击行时选中
		singleSelect: false,// 只能单选

		detailFormatter: 'detailFormatter',// 详细页格式化
		pagination: true,// 显示分页条
		sidePagination: 'server',// 可选值为 client 或者 server
		search: false,// 是否启用搜索框
		classes: 'table table-hover',// table table-hover table-no-bordered
		columns: [
			/* {field: 'state', checkbox: false}, */
			{field: 'YEAR_MONTHS', title: '月份', align: 'center'},
			{field: 'USER_NAME', title: '提交用户', align: 'center'},
			{field: 'upload', title: '上传数', align: 'center' },
			{field: 'jiaoYan', title: '校验数', align: 'center'},
			{field: 'caiJi', title: '采集', align: 'center' }
		]
	});
	
});






	var roleSize = '${user.userRoles.size() }';
	if(roleSize > 1 ){
		$('#selRoleModal').modal('show');
	}else{
		loadMenu(null);
	}
	$('#selRole-btn').click(function(){
		var roleId = $('#userRole').val();
		loadMenu(roleId);
		$('#selRoleModal').modal('hide');
	});
	
	function changeRole(roleId){
		loadMenu(roleId);
	}
	function logout(){
		$.confirm({
			type: 'grey',
			animationSpeed: 300,
			title: false,
			content: '您确认要退出系统吗？',
			buttons: {
				confirm: {
					text: '确认',
					btnClass: 'waves-effect waves-button',
					action: function () {
						location.href = "${pageContext.request.contextPath}/common/login/signout";
					}
				},
				cancel: {
					text: '取消',
					btnClass: 'waves-effect waves-button'
				}
			}
		});
	}
	
	function loadMenu(roleId){
		// Yan左侧菜单数据初始化
		var menuHtml="<li><a class='waves-effect' href='javascript:Tab.addTab(\"首页\", \"home\");'><i class='zmdi zmdi-home'></i> 首页</a></li>";
		$.post('${pageContext.request.contextPath}/'+roleId+'/menu',null,function(data){
			$.each(data,function(index,item){
				menuHtml += "<li class='sub-menu system_menus'><a class='waves-effect'><i class='"+item.icon+"'></i> "+item.name+"</a><ul>";
				$.each(item.children,function(ids,itm){
					menuHtml += "<li><a class='waves-effect' href='javascript:Tab.addTab(\""+itm.name+"\", \"${pageContext.request.contextPath}"+itm.url+"\");'>"+itm.name+"</a></li>";
				});
				menuHtml += "</ul></li>";
			});
			menuHtml += "<li><div class='upms-version'>&copy; YAN FRAME V1.0</div></li>";
			$('#main-menu').html(menuHtml);
		});
	}
</script>
</body>
</html>