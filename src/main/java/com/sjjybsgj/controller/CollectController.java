package com.sjjybsgj.controller;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sjjybsgj.dao.checkedlog.mapper.CheckedLogMapper;
import com.sjjybsgj.dao.collectrule.mapper.CollectRuleMapper;
import com.sjjybsgj.dao.collectrule.model.CollectRule;
import com.sjjybsgj.dao.jobinfo.mapper.JobinfoMapper;
import com.sjjybsgj.dao.jobinfo.model.Jobinfo;
import com.sjjybsgj.dao.model.MsgModel;
import com.sjjybsgj.dao.sourcedb.model.SourceDb;

import com.sjjybsgj.dao.user.model.SysUser;
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
@RequestMapping("/common/collect")
public class CollectController extends BaseController {

	private static final String NAMESPACE = "com.sjjybsgj.dao.jobinfo.mapper.JobinfoMapper";

	@MapperInject
	private DelegateMapper delegateMapper;

	@MapperInject(CollectRuleMapper.class)
	private CollectRuleMapper collectRuleMapper;

	@MapperInject(JobinfoMapper.class)
	private JobinfoMapper jobinfoMapper;

	@MapperInject(CheckedLogMapper.class)
	private CheckedLogMapper ckeckedlogMapper;

	/**
	 * 获取角色 tree 结构<br>
	 *
	 * @param id
	 *            父Id
	 * @return
	 * @return List<RoleNode> 角色节点列表集合
	 */
	@RequestMapping(value = "/ruleList", method = RequestMethod.POST)
	@ResponseBody
	public List<CollectRule> ruleList() {
		List<CollectRule> lists = collectRuleMapper.selectByExample(null);
		return lists;
	}

	@RequestMapping(value = "/collect", method = RequestMethod.POST)
	@ResponseBody
	public MsgModel collect(String dbName, String userId, String collectRule) {
		HashMap<String, String> parameter = new HashMap<String, String>();
		parameter.put("dbName", dbName);
		parameter.put("userId", userId);
		System.out.println(collectRule);
		String[] sqls = collectRule.split(";");
		if (sqls.length == 0) {
			return new MsgModel("0", "SQL出错");
		}
		Long runingJob = delegateMapper.selectOne("com.sjjybsgj.dao.jobinfo.mapper.JobinfoMapper.countRunningByUserid",
				userId);
		if (runingJob > 3) {
			return new MsgModel("0", "你当前运行的任务过多,请等任务结束后再提交");
		}
		SourceDb sourceDb = delegateMapper.selectOne("com.sjjybsgj.dao.jiaoyan.mapper.jiaoyanMapper.getjiaoyanSource",
				parameter);
		SysUser user = delegateMapper.selectOne("com.sjjybsgj.dao.user.mapper.SysUserMapper.selectByUserId", userId);
		DBUtils dbutils = new DBUtils(sourceDb); // 获取连接
		Connection conn = dbutils.getConn();
		if (conn == null) {
			System.out.println("连接失败");
			return new MsgModel("0", "数据库连接失败");
		} else {
			String jobId = this.getUUID();
			Jobinfo jobinfo = new Jobinfo();
			jobinfo.setJobId(jobId);
			jobinfo.setUserId(userId);
			jobinfo.setJobName(user.getUserName());
			jobinfo.setUserName(user.getUserName());
			jobinfo.setAllStage(String.valueOf(sqls.length));
			jobinfo.setNowStage("0");
			jobinfo.setStartTime(new Date());
			jobinfo.setStates(0);
			jobinfo.setIsEnd("0");
			jobinfoMapper.insert(jobinfo);
			new Thread() {
				public void run() {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					int num = 1;
					for (String sql : sqls) {
						sql = sql + ";";
						System.out.println(sql);
						String result = dbutils.execsql(sql);
						HashMap<String, Object> para = new HashMap<String, Object>();
						if (result != null) {
							para.put("jobId", jobId);
							para.put("nowStage", String.valueOf(num));
							if (num == sqls.length) {
								para.put("endTime", sdf.format(new Date()));
								para.put("isEnd", "1");
								para.put("states", "100");
							} else {
								para.put("endTime", null);
								para.put("isEnd", "0");
								para.put("states", String.valueOf(num * 100 / sqls.length));
							}
							delegateMapper.update(NAMESPACE + ".updateByjobid", para);
							System.out.println("updateByjobid" + " " + jobId + " " + num);
						} else {
							para.put("endTime", sdf.format(new Date()));
							para.put("isEnd", "2");
							para.put("states", String.valueOf(num * 100 / sqls.length));
							delegateMapper.update(NAMESPACE + ".updateByjobid", para);
							dbutils.closeConn();
							this.stop();
						}
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						num++;
					}
					dbutils.closeConn();
				}
			}.start();

		}
		return new MsgModel("1", "正在采集,请稍后");
	}

}
