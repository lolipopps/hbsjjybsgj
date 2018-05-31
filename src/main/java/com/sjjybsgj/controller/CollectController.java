package com.sjjybsgj.controller;

import java.util.ArrayList;
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
import com.sjjybsgj.dao.collectrule.mapper.CollectRuleMapper;
import com.sjjybsgj.dao.collectrule.model.CollectRule;
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
@RequestMapping("/common/collect")
public class CollectController extends BaseController {

	private static final String NAMESPACE = "com.sjjybsgj.dao.jiaoyan.mapper.jiaoyanMapper";

	@MapperInject
	private DelegateMapper delegateMapper;

	@MapperInject(CollectRuleMapper.class)
	private CollectRuleMapper collectRuleMapper;
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
	public  List<CollectRule> ruleList() {
		List<CollectRule> lists = collectRuleMapper.selectByExample(null);
		return lists;
	}


}
