<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>
<%@ include file="/common/include_common.jsp"%>

<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
.layout-left {
	position: fixed;
	float: left;
	width: 20%;
	background: #fff;
	height: 100%;
	transition: all;
	-webkit-transition-duration: .3s;
	transition-duration: .3s;
	z-index: 10;
	overflow-y: auto;
	box-shadow: 1px 0 4px rgba(0, 0, 0, .3);
}

.layout-right {
	position: fixed;
	float: left;
	width: 80%;
	background: #fff;
	left: 20%;
	height: 100%;
}
</style>
</head>
<body>
	<div class="layout-left">
		<div id="middle" style="background: #F5F5F5;">
			<div id="ztree" class="ztree"></div>
		</div>
	</div>
	<div class="layout-right" id="main">
		<div id="toolbar">
			<a class="waves-effect btn btn-info btn-sm"
				href="javascript:addAction();"><i class="zmdi zmdi-plus"></i>数据采集</a>

			<select id="tableList" class="selectpicker" name="tableList"
				data-max-options="1" data-live-placeholder="请选择要校验的库">
			</select> <a class="waves-effect btn btn-danger btn-sm"
				href="javascript:jiaoyanAction();"><i class="zmdi zmdi-delete"></i>
				校验</a> <a class="waves-effect btn btn-primary btn-sm"
				href="javascript:roleAction();"><i class="zmdi zmdi-male"></i>
				校验并上传</a>
		</div>
		<table id="table"></table>
	</div>
	<!-- 实现一个等待的效果 -->
	<div class="modal fade" class="crudDialog" role="dialog"
		id="loadingModal" aria-hidden="true">

		<div
			style="width: 200px; height: 20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%; margin-left: -100px; margin-top: -10px">
			<div class="progress progress-striped active"
				style="margin-bottom: 0;">
				<div class="progress-bar" style="width: 100%;"></div>

			</div>
			<h5 style="color: black">
				<strong>正在校验数据...校验可能需要花费30秒左右,请稍等！</strong>
			</h5>
		</div>
	</div>



	<!-- 采集窗口 -->
	<div class="modal fade" id="caijiModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">采集功能</h4>
				</div>


				<div class="col-md-12"
					style="margin-top: 10px; margin-bottom: 10px;">
					<div class="col-md-4"
						style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
						<label style="margin-top: 5px; font-size: 14px; color: grey;">请选择要采集的库</label>
					</div>
					<div class="col-md-8">
						<select id="collectdbname" class="selectpicker"
							data-max-options="1" onchange="Onchang1()">
						</select>
					</div>
				</div>


				<div class="col-md-12"
					style="margin-top: 10px; margin-bottom: 10px;">
					<div class="col-md-4"
						style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
						<label style="margin-top: 5px; font-size: 14px; color: grey;">请选择要采集的规则</label>
					</div>
					<div class="col-md-8">
						<select id="collectdbrule" class="selectpicker"
							name="collectdbrule" data-max-options="1"
							data-live-search-placeholder="采集规则"  onchange="Onchang2()">

						</select>
					</div>
				</div>


				<div class="col-md-12"
					style="margin-top: 10px; margin-bottom: 10px;">
					<div class="col-md-4"
						style="background-color: #D2E9FF; line-height: 26px; vertical-align: middle;">
						<label style="margin-top: 5px; font-size: 14px; color: grey;">采集的规则为</label>
					</div>
					<div class="col-md-8">
						
						<textarea id="Ruletextarea" rows="10" cols="40" readonly="readonly"></textarea>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="caijiAction()">采集</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>

				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>

<script type="text/javascript">
	$(function() {
		var setting = { //此处根据自己需要进行配置
			view : {
				fontCss : setFontCss
			},
			data : {
				simpleData : {
					enable : true
				}
			}
		};
		$
				.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/common/datasource/dbTree", //ajax请求地址
					success : function(data) {
						$.fn.zTree.init($("#ztree"), setting, data); //加载数据
					},
				});
	});

	function setFontCss(treeId, treeNode) {
		if (treeNode.isParent == true)
			return {};
		else if (treeNode.valid == false)
			return {
				color : "green"
			}
		else
			return {
				color : "red"
			}
	};

	var $table = $('#table');
	$(function() {
		$table.bsTable({
			url : '${pageContext.request.contextPath}/common/jiaoyan/list',
			idField : 'updateLogId',// 指定主键列
			singleSelect : true,
			search : true,
			columns : [ {
				field : 'state',
				checkbox : true
			}, {
				field : 'ruleType',
				title : '校验类型',
				align : 'center'
			}, {
				field : 'userName',
				title : '用户名',
				align : 'center'
			}, {
				field : 'dbName',
				title : '数据库名',
				align : 'center'
			}, {
				field : 'tableName',
				title : '表名',
				align : 'center'
			}, {
				field : 'vaildDate',
				title : '校验时间',
				align : 'center'
			}, {
				field : 'ifPass',
				title : '是否通过',
				align : 'center'
			}, {
				field : 'info',
				title : '详细信息',
				align : 'center'
			},

			]
		});

	});
	
	getdbinfo("tableList");
	
	function getdbinfo(selectid){
	$('#'+selectid).on('shown.bs.select',function(e) { // shown.bs.select bootstrap 的内置的
						// 获取checkBox值
						$
								.ajax({
									url : '${pageContext.request.contextPath}/common/jiaoyan/dbList',//写你自己的方法
									type : "post", //数据发送方式
									success : function(data) {
										$('#'+selectid+'.selectpicker')
										.append("<option>请选择</a>");
										$.each(
														data,
														function(i) {
															$('#'+selectid+'.selectpicker')
																.append("<option value=" + data[i].dbName + ">"
																					+ data[i].dbName
																					+ "</option>");});
										// 缺一不可  
										$('#'+selectid).selectpicker('refresh');
										$('#'+selectid).selectpicker('render');
									},
									error : function(data) {
										alert("查询表格失败" + data);
									}

								})
						$('#'+selectid).empty();
					});
	}

	
	var collectrules;
	
	function selectedchange(selectid,url,field,selected){
			// 获取checkBox值
			$.ajax({
						url : '${pageContext.request.contextPath}'+url,//写你自己的方法
						type : "post", //数据发送方式
						success : function(data) {
							var num = 0;
							collectrules = data;
							$.each(data,
											function(i) {
												if(selected==data[i].dbName){
													if(num==0){
														$('#'+selectid+'.selectpicker')
														.append("<option>请选择规则</option>");
													}
												$('#'+selectid+'.selectpicker')
													.append("<option value=" + data[i][field] + ">"
																		+ data[i][field]
																		+ "</option>");
												num = num+1;
												}
											}			
									);
							if(num == 0){
								$('#'+selectid+'.selectpicker')
								.append("<option>该库下没有采集规则</option>");
								$('#Ruletextarea').val("没有规则");
							}
							// 缺一不可  
							
							$('#'+selectid).selectpicker('refresh');
							$('#'+selectid).selectpicker('render');
						},
						error : function(data) {
							alert("查询表格失败" + data);
						}

					})
			$('#'+selectid).empty();
		}

	
	function Onchang1(){ //获取被选中的option标签选项 
		var optionselected  = $("#collectdbname option:selected").val();
		selectedchange("collectdbrule","/common/collect/ruleList",'collectName',optionselected);
		
	}
	
	function Onchang2(){ //获取被选中的option标签选项 

		var collectName  = $("#collectdbrule option:selected").val();
		for(j = 0; j < collectrules.length; j++) {
			 if(collectrules[j].collectName == collectName ){
				 $('#Ruletextarea').val(collectrules[j].collectSql);
				 break;
			 }
				 
		} 
		
		
	}
	
	
	
		

	
	//添加
	function jiaoyanAction() {
		var dbName = $('#tableList option:selected').val();//选中的值
		var userId = '${user.userId}'; // 获取当前用户 Id
		// <!-- 模态框不会隐藏 -->
		$('#loadingModal').modal({
			backdrop : 'static',
			keyboard : true
		});
		$("#loadingModal").modal('show');
		$.ajax({
					url : '${pageContext.request.contextPath}/common/jiaoyan/jiaoyandb',//写你自己的方法
					type : "post", //数据发送方式
					data : {
						dbName : dbName,
						userId : userId
					},
					dataType : "json",//后台处理后返回的数据格式
					success : function(data) {
						if (data.status == 0) {
							$('#loadingModal').modal('hide');
							alert("校验失败 失败的原因" + data.msg);
						} else {
							$('#loadingModal').modal('hide');
							alert("校验成功" + data.msg);
						}
					},
					error : function(data) {
						$('#loadingModal').modal('hide');
						alert("校验失败 ,失败原因,数据库异常");
					}

				})

	}
	getdbinfo("collectdbname");
	function addAction(){
		$("#caijiModal").modal('show');
		// 采集功能
	}
	

  function caijiAction(){
	  var userId = '${user.userId}'; 
	  var dbName =  $('#collectdbname option:selected').val(); 4
	  var collectName = $('#collectdbrule').val();//选中的值
	  var collectRule = $('#Ruletextarea').val();//选中的值
	  if(dbName == null){alert("请选择数据库");}
	  else if(collectRule == "没有规则" || collectName=="请选择规则" ){alert("采集失败,请选择规则");}
	  else{
	  	alert(userId+" "+dbName+" "+collectRule+" "+"11111111");
		$.ajax({
			url : '${pageContext.request.contextPath}/common/collect/collect',//写你自己的方法
			type : "post", //数据发送方式
			data : {
				dbName : dbName,
				userId : userId,
				collectRule: collectRule
			},
			dataType : "json",//后台处理后返回的数据格式
			success : function(data) {
				if (data.status == 0) {
					$('#loadingModal').modal('hide');
					alert("校验失败 失败的原因" + data.msg);
				} else {
					$('#loadingModal').modal('hide');
					alert("校验成功" + data.msg);
				}
			},
			error : function(data) {
				$('#loadingModal').modal('hide');
				alert("校验失败 ,失败原因,数据库异常");
			}

		})
	  }
  }
	
	
</script>

</html>