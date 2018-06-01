package com.sjjybsgj.controller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sjjybsgj.dao.checkedlog.mapper.CheckedLogMapper;
import com.sjjybsgj.dao.checkedlog.model.CheckedLog;
import com.sjjybsgj.dao.jobinfo.mapper.JobinfoMapper;
import com.sjjybsgj.dao.jobinfo.model.Jobinfo;
import com.sjjybsgj.dao.login.model.LoginUser;
import com.sjjybsgj.dao.model.MsgModel;
import com.sjjybsgj.dao.model.PageModel;
import com.sjjybsgj.dao.sourcedb.mapper.SourceDbMapper;
import com.sjjybsgj.dao.sourcedb.model.SourceDb;
import com.sjjybsgj.dao.standarddb.mapper.StandardDbMapper;
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

	@MapperInject(JobinfoMapper.class)
	private JobinfoMapper jobinfoMapper;

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

	@RequestMapping(value = "/jiaoyanandUpload", method = RequestMethod.POST)
	@ResponseBody
	public MsgModel jiaoyanandUpload(String dbName, String userId) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("dbName", dbName);
		paramMap.put("userId", userId);
		System.out.println(dbName + " " + userId);
		int runingJob = delegateMapper.selectOne("com.sjjybsgj.dao.jobinfo.mapper.JobinfoMapper.countRunningByUserid",
				userId);
		if (runingJob > 3) {
			return new MsgModel("0", "你当前运行的任务过多,请等任务结束后再提交");
		}

		// 获取库的连接方式
		SourceDb sourceDb = delegateMapper.selectOne(NAMESPACE + ".getjiaoyanSource", paramMap);
		LoginUser user = this.getSessionUser();
		CheckedLog record = new CheckedLog();
		record.setUserName(user.getUserName());
		record.setUserId(userId);
		record.setDbName(dbName);
		if (sourceDb == null) {
			return new MsgModel("0", "web系统数据库连不上");
		} else {
			String dbType = sourceDb.getDbType();
			// 得到了带校验的表
			List<Map<String, String>> ruleLists = delegateMapper.selectList(NAMESPACE + ".getjiaoyantableRule", dbName);

			DBUtils dbutils = new DBUtils(sourceDb);
			List<String> tableNames = delegateMapper.selectList(NAMESPACE + ".getUpdatedTabel", userId);

			Connection conn = dbutils.getConn();
			if (conn == null) {
				System.out.println("连接失败");
				return new MsgModel("0", "数据库连接失败");
			} else {

				// 生成一个新job
				String jobId = this.getUUID();
				Jobinfo jobinfo = new Jobinfo();
				jobinfo.setJobId(jobId);
				jobinfo.setUserId(userId);
				jobinfo.setJobName(user.getUserName());
				jobinfo.setUserName(user.getUserName());
				jobinfo.setAllStage(String.valueOf(ruleLists.size()));
				jobinfo.setNowStage("0");
				jobinfo.setStartTime(new Date());
				jobinfo.setStates(0);
				jobinfo.setIsEnd("0");
				jobinfoMapper.insert(jobinfo);

				// 开启一个新进程来校验
				new Thread() {
					public void run() {
						int num = 1;
						// 校验一个库里面的所有标准 ruleLists 总规则数量
						boolean update = true;
						String dbName = "";
						HashMap<String, String> tablesMap = new HashMap<String, String>();
						for (Map<String, String> ruleList : ruleLists) {
							String tableName = ruleList.get("TABLE_NAME");
							if (!tablesMap.containsKey(tableName)) {
								tablesMap.put(tableName, ruleList.get("CLOUMN_NAME"));
							} else {
								tablesMap.put(tableName, tablesMap.get(tableName) + "," + ruleList.get("CLOUMN_NAME"));
							}
							if (dbName == "") {
								dbName = ruleList.get("DB_NAME");
							}
							if (tableNames.contains(tableName)) {
								break; // 如果上传成功就不用校验了
							}
							// 值域和复杂规则
							String rangeValue = ruleList.get("RANGE_VALUE");
							String complex = ruleList.get("COMPLEX");
							// 生成一条校验日志
							record.setLogId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
							record.setRuleId(ruleList.get("RULE_ID"));
							record.setTableName(tableName);
							record.setVaildDate(new Date());
							record.setCloumnName(ruleList.get("CLOUMN_NAME"));
							// 记录校验结果信息
							String result = "";
							String mss = "";

							// 校验值域
							if (rangeValue != null && !rangeValue.trim().equals("")) {
								record.setRuleType("校验");
								String sql = dbutils.getRangeSql(rangeValue, dbName, tableName,
										ruleList.get("CLOUMN_NAME"), dbType);
								result = dbutils.execsql(sql);
								System.out.println(result);
								// 规则通过
								if (result == null) {
									mss = "表: " + tableName + " 字段: " + ruleList.get("CLOUMN_NAME") + " 规则: "
											+ rangeValue + "通过";
									record.setIfPass(1);
								} else {
									update = false;
									mss = "表: " + tableName + " 字段:  " + ruleList.get("CLOUMN_NAME") + " 规则: "
											+ rangeValue + "这个规则不通过";
									record.setIfPass(0);
								}
								record.setInfo(mss);
								checkLogMapper.insert(record); // 插入校验日志
								updatejobMess(jobId, num, ruleLists.size());// 更新作业日志
							}

							// 复杂规则
							if (complex != null && !complex.trim().equals("")) {
								result = dbutils.execQuery(complex);
								// 通过
								if (result == null) {
									mss = "表: " + tableName + " 字段: " + ruleList.get("CLOUMN_NAME") + " 规则: " + complex
											+ "通过";
									record.setIfPass(1);
								} else {
									update = false;
									mss = "表: " + tableName + " 字段:  " + ruleList.get("CLOUMN_NAME") + " 规则: " + complex
											+ "这个规则不通过";
									record.setIfPass(0);
								}
								record.setInfo(mss); // 校验详细信息
								checkLogMapper.insert(record); // 插入校验日志
								updatejobMess(jobId, num, ruleLists.size()); // 更新作业日志
							}
							num++;
						}
						if (update) { // 是否可以上传
							System.out.println("开始上传");
							if (dbType.toUpperCase().equals("SQLSERVER")) {
								dbName = dbName + ".dbo.";
							}

							// 用keySet()
							Iterator it = tablesMap.keySet().iterator();
							String tableName = "";
							String columnNames = "";
							String sql = "";
							while (it.hasNext()) {
								tableName = (String) it.next();
								columnNames = tablesMap.get(tableName);
								sql = "select * from " + dbName + tableNames + " group by " + columnNames;
								List<List<String>> result = dbutils.getQuerySql(sql);
								//writeToFile(result, dbName + tableNames);
							}
						} else {
							System.out.println("校验不通过");
						}
					}
				}.start();
				dbutils.closeConn();
			}
		}
		return new MsgModel("1", "正在校验请稍后........");
	}

	@RequestMapping(value = "/jiaoyandb", method = RequestMethod.POST)
	@ResponseBody
	public MsgModel jiaoyandb(String dbName, String userId) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("dbName", dbName);
		paramMap.put("userId", userId);
		System.out.println(dbName + " " + userId);

		int runingJob = delegateMapper.selectOne("com.sjjybsgj.dao.jobinfo.mapper.JobinfoMapper.countRunningByUserid",
				userId);
		if (runingJob > 3) {
			return new MsgModel("0", "你当前运行的任务过多,请等任务结束后再提交");
		}

		// 获取库的连接方式
		SourceDb sourceDb = delegateMapper.selectOne(NAMESPACE + ".getjiaoyanSource", paramMap);
		LoginUser user = this.getSessionUser();
		CheckedLog record = new CheckedLog();
		record.setUserName(user.getUserName());
		record.setUserId(userId);
		record.setDbName(dbName);
		if (sourceDb == null) {
			return new MsgModel("0", "web系统数据库连不上");
		} else {
			String dbType = sourceDb.getDbType();
			// 得到了带校验的表
			List<Map<String, String>> ruleLists = delegateMapper.selectList(NAMESPACE + ".getjiaoyantableRule", dbName);
			DBUtils dbutils = new DBUtils(sourceDb);
			List<String> tableNames = delegateMapper.selectList(NAMESPACE + ".getUpdatedTabel", userId);

			Connection conn = dbutils.getConn();
			if (conn == null) {
				System.out.println("连接失败");
				return new MsgModel("0", "数据库连接失败");
			} else {

				// 生成一个新job
				String jobId = this.getUUID();
				Jobinfo jobinfo = new Jobinfo();
				jobinfo.setJobId(jobId);
				jobinfo.setUserId(userId);
				jobinfo.setJobName(user.getUserName());
				jobinfo.setUserName(user.getUserName());
				jobinfo.setAllStage(String.valueOf(ruleLists.size()));
				jobinfo.setNowStage("0");
				jobinfo.setStartTime(new Date());
				jobinfo.setStates(0);
				jobinfo.setIsEnd("0");
				jobinfoMapper.insert(jobinfo);
				// 开启一个新进程来校验
				new Thread() {
					public void run() {
						int num = 1;
						// 校验一个库里面的所有标准 ruleLists 总规则数量
						for (Map<String, String> ruleList : ruleLists) {
							String tableName = ruleList.get("TABLE_NAME");
							if (tableNames.contains(tableName)) {
								break; // 如果上传成功就不用校验了
							}
							// 值域和复杂规则
							String rangeValue = ruleList.get("RANGE_VALUE");
							String complex = ruleList.get("COMPLEX");
							// 生成一条校验日志
							record.setLogId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
							record.setRuleId(ruleList.get("RULE_ID"));
							record.setTableName(tableName);
							record.setVaildDate(new Date());
							record.setCloumnName(ruleList.get("CLOUMN_NAME"));
							// 记录校验结果信息
							String result = "";
							String mss = "";
							// 校验值域
							if (rangeValue != null && !rangeValue.trim().equals("")) {
								record.setRuleType("校验");
								String sql = dbutils.getRangeSql(rangeValue, dbName, tableName,
										ruleList.get("CLOUMN_NAME"), dbType);
								result = dbutils.execsql(sql);
								System.out.println(result);
								// 规则通过
								if (result == null) {
									mss = "表: " + tableName + " 字段: " + ruleList.get("CLOUMN_NAME") + " 规则: "
											+ rangeValue + "通过";
									record.setIfPass(1);
								} else {
									mss = "表: " + tableName + " 字段:  " + ruleList.get("CLOUMN_NAME") + " 规则: "
											+ rangeValue + "这个规则不通过";
									record.setIfPass(0);
								}
								record.setInfo(mss);
								checkLogMapper.insert(record); // 插入校验日志
								updatejobMess(jobId, num, ruleLists.size());// 更新作业日志
							}

							// 复杂规则
							if (complex != null && !complex.trim().equals("")) {
								result = dbutils.execQuery(complex);
								// 通过
								if (result == null) {
									mss = "表: " + tableName + " 字段: " + ruleList.get("CLOUMN_NAME") + " 规则: " + complex
											+ "通过";
									record.setIfPass(1);
								} else {
									mss = "表: " + tableName + " 字段:  " + ruleList.get("CLOUMN_NAME") + " 规则: " + complex
											+ "这个规则不通过";
									record.setIfPass(0);
								}
								record.setInfo(mss); // 校验详细信息
								checkLogMapper.insert(record); // 插入校验日志
								updatejobMess(jobId, num, ruleLists.size()); // 更新作业日志
							}
							num++;
						}
					}
				}.start();
				dbutils.closeConn();
			}
		}
		return new MsgModel("1", "正在校验请稍后........");
	}

	public void updatejobMess(String jobId, int now, int all) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		HashMap<String, Object> para = new HashMap<String, Object>();
		para.put("jobId", jobId);
		para.put("nowStage", String.valueOf(now));
		if (now == all) {
			para.put("endTime", sdf.format(new Date()));
			para.put("isEnd", "1");
			para.put("states", "100");
		} else {
			para.put("endTime", null);
			para.put("isEnd", "0");
			para.put("states", String.valueOf(now * 100 / all));
		}
		delegateMapper.update(NAMESPACE + ".updateByjobid", para);
		System.out.println("updateByjobid" + " " + jobId + " " + now);
	}

	public void writeToFile(List<List<String>> lists, String filename) {
		// 输入文件
		// File inFile = new File(filename);
		// FileReader fileReader = new FileReader(inFile);
		// BufferedReader bufferedReader = new BufferedReader(fileReader);
		// 输出文件
		File outFile = new File(filename);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(outFile, true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.newLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String line = null;
		for (List<String> list : lists) {
			for (String str : list) {
				if (line == null) {
					line = str;
				} else {
					line = line + "\t" + str;
				}
			}
			try {
				bufferedWriter.write(line);
				bufferedWriter.newLine();
				bufferedWriter.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// 关闭输入输出流
				try {
					bufferedWriter.close();
					fileWriter.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

}
