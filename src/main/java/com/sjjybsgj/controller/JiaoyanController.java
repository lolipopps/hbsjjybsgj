package com.sjjybsgj.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjjybsgj.dao.checkedlog.mapper.CheckedLogMapper;
import com.sjjybsgj.dao.checkedlog.model.CheckedLog;
import com.sjjybsgj.dao.login.model.LoginUser;
import com.sjjybsgj.dao.model.JsonModel;
import com.sjjybsgj.dao.model.MsgModel;
import com.sjjybsgj.dao.model.PageModel;
import com.sjjybsgj.dao.ruletable.model.RuleTable;
import com.sjjybsgj.dao.ruletable.model.RuleTableWithBLOBs;
import com.sjjybsgj.dao.sourcedb.mapper.SourceDbMapper;
import com.sjjybsgj.dao.sourcedb.model.SourceDb;
import com.sjjybsgj.dao.standarddb.mapper.StandardDbMapper;
import com.sjjybsgj.dao.standarddb.model.StandardDb;
import com.sjjybsgj.dao.updatelog.mapper.UpdateLogMapper;
import com.sjjybsgj.dao.updatelog.model.UpdateLog;
import com.sjjybsgj.core.annotation.MapperInject;
import com.sjjybsgj.controller.BaseController;

import com.sjjybsgj.core.persistence.DelegateMapper;
import com.sjjybsgj.support.DBUtils;

/**
 * 名称：RoleController<br>
 *
 * 描述：校验管理模块<br>
 *
 */
@Controller
@RequestMapping("/common/jiaoyan")
public class JiaoyanController extends BaseController {

	private static final String NAMESPACE = "com.sjjybsgj.dao.jiaoyan.mapper.jiaoyanMapper";

	@MapperInject
	private DelegateMapper delegateMapper;

	@MapperInject(SourceDbMapper.class)
	private SourceDbMapper SourceDbMapper;

	@MapperInject(StandardDbMapper.class)
	private StandardDbMapper standardDbMapper;

	@MapperInject(CheckedLogMapper.class)
	private CheckedLogMapper checkLogMapper;

	@RequestMapping("/manage")
	public String manage() {
		return "common/jiaoyan/manage";
	}

	/**
	 * 
	 * 获取所有校验日志
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public PageModel<CheckedLog> logList(int offset, int limit, String search, String sort, String order) {
		this.offsetPage(offset, limit);
		List<CheckedLog> list = checkLogMapper.selectByExample(null);
		return this.resultPage(list);
	}

	/**
	 * 获取角色 tree 结构<br>
	 *
	 * @param id
	 *            父Id
	 * @return List<RoleNode> 角色节点列表集合
	 */
	@RequestMapping(value = "/dbList", method = RequestMethod.POST)
	@ResponseBody
	public List<SourceDb> tableList() {
		List<SourceDb> list = SourceDbMapper.selectByExample(null);
		return list;
	}

	@RequestMapping(value = "/jiaoyandb", method = RequestMethod.POST)
	@ResponseBody
	public MsgModel jiaoyandb(String dbName, String userId) {
		String status = "1";
		String message = "";
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("dbName", dbName);
		paramMap.put("userId", userId);
		System.out.println(dbName + " " + userId);

		// 获取库的连接方式
		SourceDb sourceDb = delegateMapper.selectOne(NAMESPACE + ".getjiaoyanSource", paramMap);
		LoginUser user = this.getSessionUser();
		CheckedLog record = new CheckedLog();
		record.setUserName(user.getUserName());
		record.setUserId(userId);
		record.setDbName(dbName);
		if (sourceDb == null) {
			status = "0";
			message = "数据库连不上";
		} else {
			String dbType = sourceDb.getDbType();
			// 得到了带校验的表
			List<Map<String, String>> ruleLists = delegateMapper.selectList(NAMESPACE + ".getjiaoyantableRule", dbName);
			DBUtils dbutils = new DBUtils(sourceDb);
			List<String> tableNames = delegateMapper.selectList(NAMESPACE + ".getUpdatedTabel", userId);
			for (Map<String, String> ruleList : ruleLists) { // 校验一个库里面的所有标准
				String tableName = ruleList.get("TABLE_NAME");
				if(tableNames.contains(tableName)) {
					break;  // 如果上传成功就不用校验了
				}
				System.out.println("这个规则: " + ruleList.values() + "总规则为: " + ruleLists.size());
				String ruleId = ruleList.get("RULE_ID");
				String rangeValue = ruleList.get("RANGE_VALUE");
				String complex = ruleList.get("COMPLEX");
				String cloumnName = ruleList.get("CLOUMN_NAME");

				record.setLogId(this.getUUID());
				record.setRuleId(ruleId);
				record.setTableName(tableName);
				record.setVaildDate(new Date());
				record.setCloumnName(cloumnName);

				String results = "";
				String mss = "";
				System.out.println(rangeValue + "----------" + complex);
				if (rangeValue != null && !rangeValue.trim().equals("")) {

					record.setRuleType("校验");

					String sql = dbutils.getRangeSql(rangeValue, dbName, tableName, cloumnName, dbType);

					results = dbutils.execQuery(sql);
					System.out.println(results);
					if (results == null) {
						mss = "表: " + tableName + " 字段: " + cloumnName + " 规则: " + rangeValue + "通过";

						record.setInfo(mss);
						record.setIfPass(1);

					} else {

						mss = "表: " + tableName + " 字段:  " + cloumnName + " 规则: " + rangeValue + "这个规则不通过";
						status = "0";
						message = mss + "表 " + tableName + "规则: " + rangeValue;

						record.setInfo(mss);
						record.setIfPass(0);

					}

					checkLogMapper.insert(record);

				}
				if (complex != null && !complex.trim().equals("")) {
					results = dbutils.execQuery(complex);
					if (results == null) {

						mss = "表: " + tableName + " 字段: " + cloumnName + " 规则: " + complex + "通过";

						record.setInfo(mss);
						record.setIfPass(1);

					} else {
						mss = "表: " + tableName + " 字段:  " + cloumnName + " 规则: " + complex + "这个规则不通过";
						status = "0";
						message = mss + "表 " + tableName + "规则: " + complex;

						record.setInfo(mss);
						record.setIfPass(0);
					}

					checkLogMapper.insert(record);

				}

			}
			dbutils.closeConn();
		}

		return new MsgModel(status, message);
	}

}
