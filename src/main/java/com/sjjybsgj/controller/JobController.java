package com.sjjybsgj.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjjybsgj.dao.checkedlog.mapper.CheckedLogMapper;
import com.sjjybsgj.dao.checkedlog.model.CheckedLog;
import com.sjjybsgj.dao.jobinfo.mapper.JobinfoMapper;
import com.sjjybsgj.dao.jobinfo.model.Jobinfo;
import com.sjjybsgj.dao.login.model.LoginUser;
import com.sjjybsgj.dao.model.JsonModel;
import com.sjjybsgj.dao.model.MsgModel;
import com.sjjybsgj.dao.model.PageModel;
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
@RequestMapping("/common/jobinfo")
public class JobController extends BaseController {

	private static final String NAMESPACE = "com.sjjybsgj.dao.jiaoyan.mapper.jobMapper";

	@MapperInject
	private DelegateMapper delegateMapper;

	@MapperInject(JobinfoMapper.class)
	private JobinfoMapper jobinfoMapper;


	@RequestMapping("/jobinfo")
	public String manage() {
		return "common/jobinfo/jobinfo";
	}

	/**
	 * 
	 * 获取所有校验日志
	 */
	@RequestMapping(value = "/joblist", method = RequestMethod.POST)
	@ResponseBody
	public PageModel<Jobinfo> joblist(int offset, int limit, String search, String sort, String order) {
		this.offsetPage(offset, limit);
		List<Jobinfo> list = jobinfoMapper.selectByExample(null);
		return this.resultPage(list);
	}

	

}
