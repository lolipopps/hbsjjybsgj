package com.sjjybsgj.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.sjjybsgj.dao.model.MsgModel;
import com.sjjybsgj.support.DBUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjjybsgj.core.annotation.MapperInject;

import com.sjjybsgj.core.persistence.DelegateMapper;
import com.sjjybsgj.dao.model.PageModel;
import com.sjjybsgj.dao.sourcedb.mapper.SourceDbMapper;
import com.sjjybsgj.dao.sourcedb.model.SourceDb;
import com.sjjybsgj.dao.standarddb.mapper.StandardDbMapper;
import com.sjjybsgj.dao.standarddb.model.StandardDb;


/**
 * 名称：RoleController<br>
 *
 * 描述：角色管理模块<br>
 *
 * @author Yanzheng 严正<br>
 *         时间：<br>
 *         2017-09-21 10:00:57<br>
 *         版权：<br>
 *         Copyright 2017 <a href="https://github.com/micyo202" target=
 *         "_blank">https://github.com/micyo202</a>. All rights reserved.
 */
@Controller
@RequestMapping("/common/datasource")
public class SourceDbController extends BaseController {

	private static final String NAMESPACE = "com.sjjybsgj.dao.jiaoyan.mapper.jiaoyanMapper";

	@MapperInject(DelegateMapper.class)
	private DelegateMapper delegateMapper;

	@MapperInject(SourceDbMapper.class)
	private SourceDbMapper mapper;
	
	
	@MapperInject(StandardDbMapper.class)
	private StandardDbMapper standardDbMapper;

	@RequestMapping("/manage")
	public String manage() {
		return "common/datasource/manage";
	}

	/**
	 * 
	 * 获取当前用户配置好的数据源信息
	 */
	@RequestMapping(value = "/listSourceDb", method = RequestMethod.POST)
	@ResponseBody
	public PageModel<SourceDb> listSource(int offset, int limit, String search, String sort, String order) {
		this.offsetPage(offset, limit);

		Map<String,String> parameterMap=new HashMap<>();
		parameterMap.put("userId",this.getSessionUser().getUserId());

		List<SourceDb> list = delegateMapper.selectList("com.sjjybsgj.dao.sourcedb.mapper.SourceDbMapper.selectSourceDbByUserId",parameterMap);
		return this.resultPage(list);
	}

	@RequestMapping(value = "/dbTree", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> dbTree() {
		ArrayList<HashMap<String, Object>> listnode = new ArrayList<HashMap<String, Object>>();
		System.out.println(this.getSessionUser().getUserId());
		List<SourceDb> sourceDbs = delegateMapper.selectList(NAMESPACE+".getjiaoyanDbnamebyUserId",
				this.getSessionUser().getUserId());
		List<String> tableNames = delegateMapper.selectList(NAMESPACE + ".getUpdatedTabel",
				this.getSessionUser().getUserId());
		for (SourceDb sourceDb : sourceDbs) {
			HashMap<String, Object> rmap = new HashMap<String, Object>();
			rmap.put("id", sourceDb.getDbSourceId());
			rmap.put("name", sourceDb.getDbName());
			rmap.put("isParent", true);
			rmap.put("pId", sourceDb.getDbSourceId()); // pId 要大写才能有

			rmap.put("open", false);
			listnode.add(rmap);
			List<StandardDb> standardDbs = delegateMapper
					.selectList(NAMESPACE+".getStandardDb", sourceDb.getDbName());
			for (StandardDb standardDb : standardDbs) {
				HashMap<String, Object> cmap = new HashMap<String, Object>();
				cmap.put("id", sourceDb.getDbSourceId() + "-" + standardDb.getDbRuleId());
				cmap.put("name", standardDb.getTableName());
				cmap.put("isParent", false);
				cmap.put("pId", sourceDb.getDbSourceId());
				if (tableNames.contains(standardDb.getTableName())) {
					cmap.put("valid", false);
				}

				listnode.add(cmap);
			}

		}
		return listnode;
	}


	/**
	 * 存储数据源
	 * @param connectName
	 * @param dbName
	 * @param ip
	 * @param port
	 * @param dbUserName
	 * @param dbPassword
	 * @param dbType
	 * @return
	 */
	@RequestMapping(value = "saveSourceDb", method = RequestMethod.POST)
	@ResponseBody
	public MsgModel saveSource(String connectName, String dbName, String ip, Integer port, String dbUserName, String dbPassword, String dbType) {
		SourceDbMapper mapper = this.getMapper(SourceDbMapper.class);

		SourceDb sourceDb = new SourceDb();
		sourceDb.setDbSourceId(this.getUUID());
		sourceDb.setUserId(this.getSessionUser().getUserId());
		sourceDb.setConnectName(connectName);
		sourceDb.setDbName(dbName);
		sourceDb.setIp(ip);
		sourceDb.setPort(port);
		sourceDb.setDbUserName(dbUserName);
		sourceDb.setDbPassword(dbPassword);
		sourceDb.setDbType(dbType);

		DBUtils db = new DBUtils(sourceDb,dbName);
		Connection connection = db.getConn();
		if (!(isNull(connection))) {
			db.closeConn();
			if (mapper.insertSelective(sourceDb) == 1) {

				return this.resultMsg("添加数据源成功");

			} else {
				return this.resultMsg("添加数据源失败，请重新添加！");
			}

		} else {
			return this.resultMsg("添加数据源失败，不能连接配置的数据源，请更正数据源信息后重新添加！");
		}
	}


	@RequestMapping(value = "updateSourceDb", method = RequestMethod.POST)
	@ResponseBody
	public MsgModel updateSourceDb(String dbSourceId,String connectName, String dbName, String ip, Integer port, String dbUserName, String dbPassword, String dbType) {

		SourceDbMapper mapper = this.getMapper(SourceDbMapper.class);

		SourceDb sourceDb = new SourceDb();
		sourceDb.setDbSourceId(dbSourceId);
		sourceDb.setUserId(this.getSessionUser().getUserId());
		sourceDb.setConnectName(connectName);
		sourceDb.setDbName(dbName);
		sourceDb.setIp(ip);
		sourceDb.setPort(port);
		sourceDb.setDbUserName(dbUserName);
		sourceDb.setDbPassword(dbPassword);
		sourceDb.setDbType(dbType);

		DBUtils db = new DBUtils(sourceDb,dbName);
		Connection connection = db.getConn();

		if (!(isNull(connection))) {
			db.closeConn();
			if (mapper.updateByPrimaryKey(sourceDb) == 1) {

				return this.resultMsg("修改数据源成功");

			} else {
				return this.resultMsg("修改数据源失败，请重新修改！");
			}
		}else {
			return this.resultMsg("修改数据源失败，不能连接配置的数据源，请更正数据源信息后重新添加！");
		}

	}

	/**
	 * 删除数据源
	 * @param dbSourceId
	 * @return
	 */
	@RequestMapping(value = "deleteSourceDb", method = RequestMethod.POST)
	@ResponseBody
	public MsgModel deleteSource(String dbSourceId) {
		SourceDbMapper mapper = this.getMapper(SourceDbMapper.class);

		if (mapper.deleteByPrimaryKey(dbSourceId) == 1) {
			return this.resultMsg("删除数据源成功");
		} else {
			return this.resultMsg("删除数据源失败，请重新删除！");
		}
	}

	@RequestMapping(value = "biaojiegoujiaoyan",method = RequestMethod.POST)
	@ResponseBody
	public MsgModel biaojiegoujiaoyan(String dbSourceId,String connectName, String dbName, String ip, Integer port, String dbUserName, String dbPassword, String dbType) {
		SourceDbMapper mapper = this.getMapper(SourceDbMapper.class);

		//根据数据库名获取该标准库的所有标准表
		Map<String,String> parameterMap=new HashMap<>();
		parameterMap.put("dbName",dbName);
		List<StandardDb> StandardDblist = delegateMapper.selectList("com.sjjybsgj.dao.standarddb.mapper.StandardDbMapper.selectStandardDbBydbName",parameterMap);

		ArrayList<String> standardDbTableList=new ArrayList<>();

		SourceDb sourceDb = new SourceDb();
		sourceDb.setDbSourceId(dbSourceId);
		sourceDb.setUserId(this.getSessionUser().getUserId());
		sourceDb.setConnectName(connectName);
		sourceDb.setDbName(dbName);
		sourceDb.setIp(ip);
		sourceDb.setPort(port);
		sourceDb.setDbUserName(dbUserName);
		sourceDb.setDbPassword(dbPassword);
		sourceDb.setDbType(dbType);

		DBUtils db = new DBUtils(sourceDb,dbName);
		Connection connection = db.getConn();
		if (!(isNull(connection))) {
			List<List<String>> daiJiaoYanTablelist=db.execQuerySql("show tables;");
			Map<String,List<List<String>>> daiTableAndZiDuan=new HashMap<String,List<List<String>>>();

            ArrayList<String> dai=new ArrayList<>();
            for(int i = 1; i < daiJiaoYanTablelist.size(); i++) {
                dai.add(daiJiaoYanTablelist.get(i).get(0));

            }

            //全集
			HashSet<String> quanjiTable=new HashSet<String>();
            quanjiTable.addAll(dai);

			//通过的表
			ArrayList<String> tongguoTable=new ArrayList<String>();

			//缺表
			ArrayList<String> queTable=new ArrayList<String>();

			//多余的表
			ArrayList<String> duoyuTable=new ArrayList<String>();

                for (StandardDb standardDb:StandardDblist){
                	standardDbTableList.add(standardDb.getTableName());

                    System.out.println("xiaomai2;standardDb:"+standardDb.getTableName());
                    if (dai.contains(standardDb.getTableName())){
                        tongguoTable.add(standardDb.getTableName());
						List<List<String>> daiJiaoYanZiDuanlist=db.execQuerySql("desc "+standardDb.getTableName()+";");
						daiTableAndZiDuan.put(standardDb.getTableName(),daiJiaoYanZiDuanlist);
                        System.out.println("tongguoTable:"+tongguoTable);

                    }else {
                        System.out.println("xiaomai3;standardDb:"+standardDb.getTableName());
                        queTable.add(standardDb.getTableName());
                        System.out.println("queTable:"+queTable);

                    }
                }
                quanjiTable.addAll(standardDbTableList);
                for (String s:quanjiTable){
                	if (!(standardDbTableList.contains(s))){
                		duoyuTable.add(s);
					}

				}
			db.closeConn();
			System.out.println("通过的表："+tongguoTable);
			System.out.println("缺表:"+queTable);
			System.out.println("字段："+daiTableAndZiDuan);
			return this.resultMsg("校验结果："+"通过的表："+tongguoTable+"+++++缺表:"+queTable+"+++++多余的表:"+duoyuTable+"字段："+daiTableAndZiDuan);

		}else {
			return this.resultMsg("校验表结构失败，原因：不能连接配置的数据源，请更正数据源信息后重新添加！");
		}

	}
}
