<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/include_common.jsp" %>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>数据源管理</title>
</head>
<body>
<div id="main">
	<div id="toolbar">
		<a class="waves-effect btn btn-info btn-sm" href="javascript:actionType('add');" ><i class="zmdi zmdi-plus"></i> 添加数据源</a>
		<a class="waves-effect btn btn-warning btn-sm" href="javascript:actionType('edit');" ><i class="zmdi zmdi-edit"></i> 编辑数据源</a>
		<a class="waves-effect btn btn-danger btn-sm" href="javascript:deleteAction();" ><i class="zmdi zmdi-delete"></i> 删除数据源</a>
		<a class="waves-effect btn btn-warning btn-sm" href="javascript:biaojiegoujiaoyanAction();" ><i class="zmdi zmdi-edit"></i> 校验表结构</a>

	</div>
	<table id="table"></table>
</div>

<!-- 用户 -->
<div id="addDialog" class="modal fade" hidden>
	<div class="modal-content col-md-7" style="margin-top: 10px; margin-left: 55px; display: table;">
		<input id="typeAction" class="hidden">
		<div class="row" style="margin-top: 10px; margin-bottom: 10px;">
			<div class="col-md-4 text-left"
				style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
				<label style="margin-top: 5px; font-size: 14px; color: grey;">连接名称：</label>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<input type="text" id="connectName" name="connectName" class="form-control" placeholder="连接名称（必填）" />
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 10px; margin-bottom: 10px;">
			<div class="col-md-4 text-left"
				style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
				<label style="margin-top: 5px; font-size: 14px; color: grey;">数据库IP地址：</label>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<input type="text" id="ip" name="ip" class="form-control" placeholder="数据库IP地址（必填）" />
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 10px; margin-bottom: 10px;">
			<div class="col-md-4 text-left"
				style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
				<label style="margin-top: 5px; font-size: 14px; color: grey;">端口号：</label>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<input type="text" id="port" name="port" class="form-control" placeholder="端口号（必填）" />
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 10px; margin-bottom: 10px;">
			<div class="col-md-4 text-left"
				style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
				<label style="margin-top: 5px; font-size: 14px; color: grey;">数据库/实例名称：</label>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<input type="text" id="dbName" name="dbName" class="form-control" placeholder="数据库/实例名称（必填）" />
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 10px; margin-bottom: 10px;">
			<div class="col-md-4 text-left"
				style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
				<label style="margin-top: 5px; font-size: 14px; color: grey;">数据库用户名：</label>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<input type="text" id="dbUserName" name="dbUserName" class="form-control" placeholder="数据库用户名（必填）" />
				</div>
			</div>
		</div>
		<div class="row" style="margin-top: 10px; margin-bottom: 10px;">
			<div class="col-md-4 text-left"
				style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
				<label style="margin-top: 5px; font-size: 14px; color: grey;">数据库密码：</label>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<input type="password" id="dbPassword" name="dbPassword" class="form-control" placeholder="数据库密码（必填）" />
				</div>
			</div>
		</div>

		<div class="row" style="margin-top: 10px; margin-bottom: 10px;">
			<div class="col-md-4 text-left"
				style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
				<label style="margin-top: 5px; font-size: 14px; color: grey;">数据库类型：</label>
			</div>
			<div class="col-md-5">
				<div class="form-group">
					<select id="dbType" name="dbType" class="selectpicker">
						<option value="sqlserver" selected="selected">sqlserver</option>
						<option value="oracle">oracle</option>
						<option value="mysql">mysql</option>
					</select>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<div class="row">
				<button type="button" class="btn btn-primary"
						onclick="saveDbSource($('#typeAction').val())">保存</button>
				<button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
				</di>
			</div>
		</div>
		
	</div>
</div>

</body>

<script type="text/javascript">

var $table = $('#table');
var treeObj;
var userId;
$(function() {

    $table.bsTable({
        url: '${pageContext.request.contextPath}/common/datasource/listSourceDb',
        idField: 'dbSourceId',// 指定主键列
        singleSelect: true,
        search: true,
        columns: [
            {field: 'state', checkbox: true},
            {field: 'connectName', title: '连接名称', align: 'center'},
            {field: 'dbName', title: '数据库/实例名称', align: 'center'},
            {field: 'ip', title: '数据库IP地址', align: 'center'},
            {field: 'port', title: '端口号', align: 'center'},
            {field: 'dbUserName', title: '数据库用户名', align: 'center'},
            {field: 'dbPassword', title: '数据库密码', align: 'center'},
            {field: 'dbType', title: '数据库类型', align: 'center'},

        ]
    });
	
});

function saveDbSource(type) {

    $('#addDialog').modal('hide');
    var connectName = $('#connectName').val();
    var dbName = $('#dbName').val();
    var ip = $('#ip').val();
    var port = $('#port').val();
    var dbUserName = $('#dbUserName').val();
    var dbPassword = $('#dbPassword').val();
    var dbType = $('#dbType').val();

    if (type=='add'){
    $.post('${pageContext.request.contextPath}/common/datasource/saveSourceDb',
        {
            'connectName': connectName,
            'dbName': dbName,
            'ip': ip,
            'port': port,
            'dbUserName': dbUserName,
            'dbPassword': dbPassword,
            'dbType': dbType
        },
        function (data) {

            $.alert(data.msg);

        });
	}else if (type=='edit') {
        var rows = $table.bootstrapTable('getSelections');
        $.post('${pageContext.request.contextPath}/common/datasource/updateSourceDb',
            {
                'dbSourceId':rows[0].dbSourceId,
                'connectName': connectName,
                'dbName': dbName,
                'ip': ip,
                'port': port,
                'dbUserName': dbUserName,
                'dbPassword': dbPassword,
                'dbType': dbType
            },
            function (data) {

                $.alert(data.msg);

            });
    }
}

// 添加或者修改数据源
function actionType(type) {
    if (type=='add') {
        $('#connectName').val(null);
        $('#dbName').val(null);
        $('#ip').val(null);
        $('#port').val(null);
        $('#dbUserName').val(null);
        $('#dbPassword').val(null);
        $('#typeAction').val(type);
        $('#addDialog').modal('show');
    }else if(type=='edit') {
        var rows = $table.bootstrapTable('getSelections');
        if (rows.length == 0) {
            $.confirm({
                title: false,
                content: '请至少选择一条记录！',
                autoClose: 'cancel|3000',
                backgroundDismiss: true,
                buttons: {
                    cancel: {
                        text: '取消',
                        btnClass: 'waves-effect waves-button'
                    }
                }
            });
        } else {
            $('#connectName').val(rows[0].connectName);
            $('#dbName').val(rows[0].dbName);
            $('#ip').val(rows[0].ip);
            $('#port').val(rows[0].port);
            $('#dbUserName').val(rows[0].dbUserName);
            $('#dbPassword').val(rows[0].dbPassword);
            $('#typeAction').val(type);
            $('#addDialog').modal('show');

        }
    }

}

function biaojiegoujiaoyanAction() {
    var rows = $table.bootstrapTable('getSelections');
    if (rows.length == 0) {
        $.confirm({
            title: false,
            content: '请至少选择一条记录！',
            autoClose: 'cancel|3000',
            backgroundDismiss: true,
            buttons: {
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    } else {

    var connectName = rows[0].connectName;
    var dbName =rows[0].dbName;
    var ip =rows[0].ip;
    var port = rows[0].port;
    var dbUserName = rows[0].dbUserName;
    var dbPassword = rows[0].dbPassword;
    var dbType = rows[0].dbType;
    $.post('${pageContext.request.contextPath}/common/datasource/biaojiegoujiaoyan',
        {
            'dbSourceId':rows[0].dbSourceId,
            'connectName': connectName,
            'dbName': dbName,
            'ip': ip,
            'port': port,
            'dbUserName': dbUserName,
            'dbPassword': dbPassword,
            'dbType': dbType
        },
        function (data) {

            $.alert(data.msg);

        });
	}
}

// 删除数据源
function deleteAction() {
    var rows = $table.bootstrapTable('getSelections');
    if (rows.length == 0) {
        $.confirm({
            title: false,
            content: '请至少选择一条记录！',
            autoClose: 'cancel|3000',
            backgroundDismiss: true,
            buttons: {
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    } else {
        $.confirm({
            type: 'red',
            animationSpeed: 300,
            title: rows[0].connectName,
            content: '确认删除该数据源吗？',
            buttons: {
                confirm: {
                    text: '确认',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        var ids = new Array();
                        for (var i in rows) {

                            ids.push(rows[i].dbSourceId);
                        }

                        $.post('${pageContext.request.contextPath}/common/datasource/deleteSourceDb',
                            {'dbSourceId': ids.join("-")},
                            function (data) {

                                $.alert(data.msg);

                            });
                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
    }
}

</script>

</html>