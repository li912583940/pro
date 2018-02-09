package com.sl.ue.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sl.ue.domain.Project;
import com.sl.ue.service.ProjectService;
import com.sl.ue.util.Page;
import com.sl.ue.util.StringUtil;

@Service
public class ProjectImpl implements ProjectService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String findOne(Integer id) {
		StringBuilder sb = new StringBuilder();
		sb.append("select a.*,b.`name` AS project_type_name,c.`name` AS customer_name from project a left join project_type b ON a.project_type_id=b.id"
				+ " left join customer c ON a.customer_id=c.id"
				+ " where 1=1 and a.id=?");
		Object[] obj = new Object[]{id};
		Project project;
		try {
			project = jdbcTemplate.queryForObject(sb.toString(), obj, new Project());
		} catch (DataAccessException e) {
			project = new Project();
		}
		JSONObject json = new JSONObject();
		json.put("data", project);
		return JSONObject.toJSONString(json, SerializerFeature.WriteMapNullValue);
	}

	public String findList(Map<String, Object> map) {
		Page<Project> page = new Page<Project>();
		StringBuilder sb = new StringBuilder();
		StringBuilder sbCount = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select a.*,b.name AS project_type_name,c.`name` AS customer_name"
				+ " from project a left join project_type b ON a.project_type_id=b.id "
				+ " left join customer c ON a.customer_id=c.id"
				+ " where 1=1");
		sbCount.append("select count(*) from project a  where 1=1");
		if(map.get("name") != null && !"".equals(map.get("name"))){
			sb.append(" and instr(a.name, ?)>0");
			sbCount.append(" and instr(a.name, ?)>0");
			list.add(map.get("name"));
		}
		if(map.get("address") != null && !"".equals(map.get("address"))){
			sb.append(" and instr(a.address, ?)");
			sbCount.append(" and instr(a.address, ?)");
			list.add(map.get("address"));
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
		sb.append(" GROUP BY a.id DESC");//
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
		List<Project> project;
		try{
			project=jdbcTemplate.query(sb.toString(), list.toArray(), new Project());
		}catch(DataAccessException e){
			project = new ArrayList<Project>();
		}
		page.setData(project);//设置返回数据
		JSONObject json = new JSONObject();
		//String s = json.toJSONString(page);
		String s = json.toJSONString(page, SerializerFeature.WriteMapNullValue);
		return s;
	}

	public String findCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public String update(Project project) {
		JSONObject json = new JSONObject();
		if(project == null ){
			json.put("state", "error");
			json.put("message", "传入的项目为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder set = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("update project set");
		if(project.getName() != null){
			set.append(" `name`=?,");
			list.add(project.getName());
		}
		if(project.getProjectTypeId() != null){
			set.append(" project_type_id=?,");
			list.add(project.getProjectTypeId());
		}
		if(project.getCustomerId() != null){
			set.append(" customer_id=?,");
			list.add(project.getCustomerId());
		}
		if(project.getLineInfo() != null){
			set.append(" line_info=?,");
			list.add(project.getLineInfo());
		}
		if(project.getHardwareInfo() != null){
			set.append(" hardware_info=?,");
			list.add(project.getHardwareInfo());
		}
		if(project.getSoftwareInfo() != null){
			set.append(" software_info=?,");
			list.add(project.getSoftwareInfo());
		}
		if(project.getAddress() != null){
			set.append(" address=?,");
			list.add(project.getAddress());
		}
		if(project.getProvince() != null){
			set.append(" province=?,");
			list.add(project.getProvince());
		}
		if(project.getCity() != null){
			set.append(" city=?,");
			list.add(project.getCity());
		}
		if(project.getArea() != null){
			set.append(" area=?,");
			list.add(project.getArea());
		}
		if(project.getImplementTime() != null){
			set.append(" implement_time=?,");
			list.add(project.getImplementTime());
		}
		if(project.getImplementUserId() != null){
			set.append(" implement_user_id=?,");
			list.add(project.getImplementUserId());
		}
		if(project.getImplementUserName() != null){
			set.append(" implement_user_name=?,");
			list.add(project.getImplementUserName());
		}
		if(set.length() == 0){
			json.put("state", "error");
			json.put("message", "传入的项目信息为空······");
			return json.toJSONString();
		}
		sb.append(StringUtil.sqlSet(set.toString()));//去掉结尾的","
		sb.append(" where id=?");
		list.add(project.getId());
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
		sb.append("delete from project where FIND_IN_SET(id,?)");
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

	public String insert(Project project) {
		JSONObject json = new JSONObject();
		if(project == null){
			json.put("state", "error");
			json.put("message", "传入的项目信息为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder values = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("insert into project");
		if(project.getName() != null){
			fields.append("name,");
			values.append("?,");
			list.add(project.getName());
		}
		if(project.getProjectTypeId() != null && project.getProjectTypeId() != 0){
			fields.append("project_type_id,");
			values.append("?,");
			list.add(project.getProjectTypeId());
		}
		if(project.getCustomerId() != null && project.getCustomerId() != 0){
			fields.append("customer_id,");
			values.append("?,");
			list.add(project.getCustomerId());
		}
		if(project.getLineInfo() != null){
			fields.append("line_info,");
			values.append("?,");
			list.add(project.getLineInfo());
		}
		if(project.getHardwareInfo() != null){
			fields.append("hardware_info,");
			values.append("?,");
			list.add(project.getHardwareInfo());
		}
		if(project.getSoftwareInfo() != null){
			fields.append("software_info,");
			values.append("?,");
			list.add(project.getSoftwareInfo());
		}
		if(project.getAddress() != null){
			fields.append("address,");
			values.append("?,");
			list.add(project.getAddress());
		}
		if(project.getProvince() != null){
			fields.append("province,");
			values.append("?,");
			list.add(project.getProvince());
		}
		if(project.getCity() != null){
			fields.append("city,");
			values.append("?,");
			list.add(project.getCity());
		}
		if(project.getArea() != null){
			fields.append("area,");
			values.append("?,");
			list.add(project.getArea());
		}
		if(project.getImplementTime() != null){
			fields.append("implement_time,");
			values.append("?,");
			list.add(project.getImplementTime());
		}
		if(project.getImplementUserId() != null && project.getImplementUserId() != 0){
			fields.append("implement_user_id,");
			values.append("?,");
			list.add(project.getImplementUserId());
		}
		if(project.getImplementUserName() != null){
			fields.append("implement_user_name,");
			values.append("?,");
			list.add(project.getImplementUserName());
		}
	
		fields.append("create_time,");
		values.append("?,");
		list.add(new Date());
	
//		fields.append("create_user_id,");
//		values.append("?,");
//		list.add(project.getCreateUserId());
//	
//		fields.append("create_user_name,");
//		values.append("?,");
//		list.add(project.getCreateUserName());
		
		if(fields.length() == 0){
			json.put("state", "error");
			json.put("message", "传入的用户为空······");
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
			// TODO: handle exception
			json.put("state", "error");
			json.put("message", "插入数据时出现错误");
		}
		return json.toJSONString();
	}

}
