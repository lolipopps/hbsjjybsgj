<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/global.jsp" %>
<%@ include file="/common/include_common.jsp" %>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>数据源</title>
</head>
<body>
<div id="main">
    <div id="toolbar">
        <a class="waves-effect btn btn-info btn-sm" href="javascript:addAction();"><i class="zmdi zmdi-plus"></i> 添加数据源</a>
        <a class="waves-effect btn btn-warning btn-sm" href="javascript:editAction();"><i class="zmdi zmdi-edit"></i>
            编辑数据源</a>
        <a class="waves-effect btn btn-danger btn-sm" href="javascript:deleteAction();"><i class="zmdi zmdi-delete"></i>
            删除数据源</a>
    </div>
    <table id="table"></table>
</div>

<!-- 用户 -->
<%--<div id="addDialog" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" hidden>--%>
<%--<div class="modal-dialog">--%>
<%--<div class="modal-content col-md-11" style="margin-top: 10px; margin-left: 55px; display: table;">--%>
<%--<input id="typeAction" class="hidden">--%>

<div id="addDialog" class="modal fade" hidden>
    <div class="modal-content col-md-7" style="margin-top: 10px; margin-left: 55px; display: table;">
        <div class="row" style="margin-top: 10px; margin-bottom: 10px;">
            <div class="col-md-4 text-left"
                 style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
                <label style="margin-top: 5px; font-size: 14px; color: grey;">连接名称：</label>
            </div>
            <div class="col-md-7">
                <div class="form-group">
                    <input type="text" id="connectName" name="connectName" class="form-control"
                           placeholder="连接名称（必填）"/>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 10px; margin-bottom: 10px;">
            <div class="col-md-4 text-left"
                 style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
                <label style="margin-top: 5px; font-size: 14px; color: grey;">数据库IP地址：</label>
            </div>
            <div class="col-md-7">
                <div class="form-group">
                    <input type="text" id="ip" name="ip" class="form-control" placeholder="数据库IP地址（必填）"/>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 10px; margin-bottom: 10px;">
            <div class="col-md-4 text-left"
                 style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
                <label style="margin-top: 5px; font-size: 14px; color: grey;">端口号：</label>
            </div>
            <div class="col-md-7">
                <div class="form-group">
                    <input type="text" id="port" name="port" class="form-control" placeholder="端口号（必填）"/>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 10px; margin-bottom: 10px;">
            <div class="col-md-4 text-left"
                 style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
                <label style="margin-top: 5px; font-size: 14px; color: grey;">数据库/实例名称：</label>
            </div>
            <div class="col-md-7">
                <div class="form-group">
                    <input type="text" id="dbName" name="dbName" class="form-control" placeholder="数据库/实例名称（必填）"/>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 10px; margin-bottom: 10px;">
            <div class="col-md-4 text-left"
                 style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
                <label style="margin-top: 5px; font-size: 14px; color: grey;">数据库用户名：</label>
            </div>
            <div class="col-md-7">
                <div class="form-group">
                    <input type="text" id="dbUserName" name="dbUserName" class="form-control"
                           placeholder="用户名（必填）"/>
                </div>
            </div>
        </div>
        <div class="row" style="margin-top: 10px; margin-bottom: 10px;">
            <div class="col-md-4 text-left"
                 style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
                <label style="margin-top: 5px; font-size: 14px; color: grey;">数据库密码：</label>
            </div>
            <div class="col-md-7">
                <div class="form-group">
                    <input type="password" id="dbPassword" name="dbPassword" class="form-control"
                           placeholder="密码（必填）"/>
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 10px; margin-bottom: 10px;">
            <div class="col-md-4 text-left"
                 style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
                <label style="margin-top: 5px; font-size: 14px; color: grey;">数据库类型：</label>
            </div>
            <div class="col-md-7">
                <div class="form-group">
                    <select id="dbType" name="dbType">
                        <option value="sqlserver" selected="selected">sqlserver</option>
                        <option value="oracle">oracle</option>
                        <option value="mysql">mysql</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <div class="row">
                <button type="button" class="btn btn-primary" onclick="bc()">保存</button>
                <button type="button" class="btn btn-info" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

</body>

<script type="text/javascript">

    var $table = $('#table');
    var treeObj;
    var userId;
    $(function () {

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

    function bc() {
        $('#addDialog').modal('hide');
        var connectName = $('#connectName').val();
        var dbName = $('#dbName').val();
        var ip = $('#ip').val();
        var port = $('#port').val();
        var dbUserName = $('#dbUserName').val();
        var dbPassword = $('#dbPassword').val();
        var dbType = $('#dbType').val();

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

    }

    // 添加数据源
    function addAction() {

        $('#addDialog').modal('show');
    }

    //编辑数据源
    function editAction() {
        $.confirm({
            type: 'blue',
            animationSpeed: 300,
            columnClass: 'col-md-9 col-md-offset-1',
            title: '编辑数据源',
            content: $('#addDialog').html(),
            buttons: {
                confirm: {
                    text: '保存',
                    btnClass: 'waves-effect waves-button',
                    action: function () {
                        $.alert('保存');
                    }
                },
                cancel: {
                    text: '取消',
                    btnClass: 'waves-effect waves-button'
                }
            }
        });
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