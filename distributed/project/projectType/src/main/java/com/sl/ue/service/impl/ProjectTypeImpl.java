package com.sl.ue.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sl.ue.domain.ProjectType;
import com.sl.ue.service.ProjectTypeService;
import com.sl.ue.util.Page;
import com.sl.ue.util.StringUtil;


@Service
public class ProjectTypeImpl implements ProjectTypeService{


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String findList(Map<String, Object> map) {
		Page<ProjectType> page = new Page<ProjectType>();
		StringBuilder sb=new StringBuilder();
		StringBuilder sbCount = new StringBuilder();
		List<Object> list=new ArrayList<Object>();
		sb.append("select * from project_type a where 1=1");
		sbCount.append("select count(*) from project_type a where 1=1");
		if(map.get("name") != null && !"".equals(map.get("name"))){
			sb.append(" and instr(a.name, ?)>0");
			sbCount.append(" and instr(a.name, ?)>0");
			list.add(map.get("name"));
		}
		/**
		 * 查询总数
		 */
		int count=0;
		try{
			count= jdbcTemplate.queryForObject(sbCount.toString(), list.toArray(), Integer.class);
		}catch(DataAccessException e){
			
		}
		page.setCount(count);//设置总数
		sb.append(" GROUP BY a.id DESC");
		if(map.get("pageNum") != null && !"".equals(map.get("pageNum")) 
				&& map.get("pageSize") != null && !"".equals(map.get("pageSize"))){
			int pageNum = (Integer)map.get("pageNum");
			int pageSize = (Integer)map.get("pageSize");
			int startNum = (pageNum-1)*pageSize;
			sb.append(" limit ?,?");
			list.add(startNum);
			list.add(pageSize);
			page.setPageNum(pageNum);//设置页码
			page.setPageSize(pageSize);//设置每页大小
		}
		List<ProjectType> projectTypeList;;
		try{
			projectTypeList=jdbcTemplate.query(sb.toString(),list.toArray(),new ProjectType());
		}catch(DataAccessException e){
			projectTypeList = new ArrayList<ProjectType>();
		}
		page.setData(projectTypeList);//设置返回数据
		JSONObject json = new JSONObject();
		String s = json.toJSONString(page, SerializerFeature.WriteMapNullValue);
		return s;
	}

	public String findOne(Integer id) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from project_type where 1=1 and id=?");
		Object[] obj = new Object[]{id};
		ProjectType projectType;
		try {
			projectType = jdbcTemplate.queryForObject(sb.toString(), obj, new ProjectType());
		} catch (DataAccessException e) {
			projectType = new ProjectType();
		}
		JSONObject json = new JSONObject();
		json.put("data", projectType);
		return JSONObject.toJSONString(json, SerializerFeature.WriteMapNullValue);
	}

	public String insert(ProjectType projectType) {
		JSONObject json = new JSONObject();
		if(projectType == null){
			json.put("state", "error");
			json.put("message", "传入的项目类型信息为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder values = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("insert into project_type");
		if(projectType.getName() != null){
			fields.append("name,");
			values.append("?,");
			list.add(projectType.getName());
		}
		if(fields.length() == 0){
			json.put("state", "error");
			json.put("message", "传入的项目类型信息为空······");
			return json.toJSONString();
		}
		sb.append("(")
		.append(StringUtil.sqlSet(fields.toString()))
		.append(") VALUES(")
		.append(StringUtil.sqlSet(values.toString()))
		.append(")");
		try {
			jdbcTemplate.update(sb.toString(), list.toArray());
			json.put("state", "success");
			json.put("message", "新增成功");
		} catch (DataAccessException e) {
			json.put("state", "error");
			json.put("message", "插入数据时出现错误");
		}
		return json.toJSONString();
	}

	public String update(ProjectType projectType) {
		JSONObject json = new JSONObject();
		if(projectType == null ){
			json.put("state", "error");
			json.put("message", "传入的项目类型信息为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder set = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("update project_type set");
		if(projectType.getName() != null){
			set.append(" `name`=?,");
			list.add(projectType.getName());
		}
		sb.append(StringUtil.sqlSet(set.toString()));//去掉结尾的","
		sb.append(" where id=?");
		list.add(projectType.getId());
		try {
			jdbcTemplate.update(sb.toString(), list.toArray());
			json.put("state", "success");
			json.put("message", "修改成功");
		} catch (DataAccessException e) {
			json.put("state", "error");
			json.put("message", "修改时发生错误");
		}
		return json.toJSONString();
	}

	public String delete(String ids) {
		JSONObject json = new JSONObject();
		if(ids == null){
			json.put("state", "error");
			json.put("message", "传入的id为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("delete from project_type where FIND_IN_SET(id,?)");
		Object[] obj = new Object[]{ids};
		try{
			jdbcTemplate.update(sb.toString(), obj);
			json.put("state", "success");
			json.put("message", "删除成功");
		}catch(DataAccessException e){
			json.put("state", "error");
			json.put("message", "删除时发生错误");
		}
		return json.toJSONString();
	}
	
	public String findNameList() {
		JSONObject json = new JSONObject();
		StringBuilder sb = new StringBuilder();
		sb.append("select id,`name` from project_type");
		List<ProjectType> list;
		try {
			list = jdbcTemplate.query(sb.toString(), new ProjectType());
			for(ProjectType t : list){
				t.setValue(t.getName());
			}
			json.put("state", "success");
		} catch (DataAccessException e) {
			list = new ArrayList<ProjectType>();
			json.put("state", "error");
		}
		json.put("data", list);
		return json.toJSONString();
	}



}
