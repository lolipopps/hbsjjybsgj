<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/include_common.jsp"%>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户管理</title>
</head>
<body>
	<div id="main">
		<table id="table">

		</table>
	</div>

</body>

<script type="text/javascript">
var $table = $('#table');

$(function() {
	$table.bsTable({
		url: "${pageContext.request.contextPath}/common/jobinfo/joblist",
		idField: 'jobId',// 指定主键列
		searchOnEnterKey: true,
		 searchAlign: "left",
		search: true,
		processing: true,
		columns : [ {
			field : 'state',
			checkbox : true
		}, {
			field : 'jobId',
			title : '任务Id',
			align : 'center'
		}, {
			field : 'userName',
			title : '用户名',
			align : 'center'
		}, {
			field : 'jobName',
			title : '任务名',
			align : 'center'
		}, {
			field : 'startTime',
			title : '开始时间',
			align : 'center'
		}, {
			field : 'states',
			title : '当前状态',
			align : 'center',
			formatter: function (value, row, index) {
			    return ["<div class='progress'> <div class='progress-bar' role='progressbar' aria-valuenow='50' aria-valuemin='0' aria-valuemax='100' style='width:"+value+"%'>"+value+"</div> </div>" ];

			}
		}, {
			field : 'endTime',
			title : '结束时间',
			align : 'center'
		}
		]
	});
	
});



</script>

</html>