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
import com.sl.ue.domain.User;
import com.sl.ue.service.UserService;
import com.sl.ue.util.Page;
import com.sl.ue.util.StringUtil;

/**
 * 描述 [用户几口实现]
 * 创建者 LXT
 * 创建时间 2017年12月25日 上午9:42:29
 */
@Service
public class UserImpl implements UserService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String updateDateAndCount(Integer id) {
		String sql="update user set last_sign_time=?, sign_count=sign_count+1 where id=?";
		Object[] obj=new Object[]{new Date(),id};
		jdbcTemplate.update(sql, obj);
		return "";
	}


	@Override
	public String findOne(Integer id) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from user where 1=1 and id=?");
		Object[] obj = new Object[]{id};
		User user;
		try {
			user = jdbcTemplate.queryForObject(sb.toString(), obj, new User());
		} catch (DataAccessException e) {
			user = new User();
		}
		JSONObject json = new JSONObject();
		json.put("data", user);
		return JSONObject.toJSONString(json, SerializerFeature.WriteMapNullValue);
	}


	/* 
	 * 功能[]
	 */
	@Override
	public String findList(Map<String, Object> map) {
		Page<User> page = new Page<User>();
		StringBuilder sb = new StringBuilder();
		StringBuilder sbCount = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select * from user where 1=1");
		sbCount.append("select count(*) from user where 1=1");
		if(map.get("name") != null && !"".equals(map.get("name"))){
			sb.append(" and instr(name, ?)>0");
			sbCount.append(" and instr(name, ?)>0");
			list.add(map.get("name"));
		}
		if(map.get("username") != null && !"".equals(map.get("username"))){
			sb.append(" and instr(username, ?)");
			sbCount.append(" and instr(username, ?)");
			list.add(map.get("username"));
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
		List<User> users;
		try{
			users=jdbcTemplate.query(sb.toString(), list.toArray(), new User());
		}catch(DataAccessException e){
			users = new ArrayList<User>();
		}
		page.setData(users);//设置返回数据
		JSONObject json = new JSONObject();
		//String s = json.toJSONString(page);
		String s = json.toJSONString(page, SerializerFeature.WriteMapNullValue);
		return s;
	}


	@Override
	public String findCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String update(User user) {
		JSONObject json = new JSONObject();
		if(user == null ){
			json.put("state", "error");
			json.put("message", "传入的用户为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder set = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("update user set");
		if(user.getName() != null){
			set.append(" `name`=?,");
			list.add(user.getName());
		}
		if(user.getUsername() != null){
			set.append(" username=?,");
			list.add(user.getUsername());
		}
		if(user.getPassword() != null){
			set.append(" password=?,");
			list.add(user.getPassword());
		}
		if(user.getTelephone() != null){
			set.append(" telephone=?,");
			list.add(user.getTelephone());
		}
		if(user.getMail() != null){
			set.append(" mail=?,");
			list.add(user.getMail());
		}
		if(set.length() == 0){
			json.put("state", "error");
			json.put("message", "传入的用户为空······");
			return json.toJSONString();
		}
		set.append(" update_time=?,");
		list.add(user.getUpdateTime());
		sb.append(StringUtil.sqlSet(set.toString()));//去掉结尾的","
		sb.append(" where id=?");
		list.add(user.getId());
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


	@Override
	public String delete(String ids) {
		JSONObject json = new JSONObject();
		if(ids == null){
			json.put("state", "error");
			json.put("message", "传入的id为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("delete from user where FIND_IN_SET(id,?)");
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
	
	public String insert(User user){
		JSONObject json = new JSONObject();
		if(user == null){
			json.put("state", "error");
			json.put("message", "传入的用户为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder values = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("insert into user");
		if(user.getUsername() != null){
			fields.append("username,");
			values.append("?,");
			list.add(user.getUsername());
		}
		if(user.getName() != null){
			fields.append("name,");
			values.append("?,");
			list.add(user.getName());
		}
		if(user.getPassword() != null){
			fields.append("password,");
			values.append("?,");
			list.add(user.getPassword());
		}
		if(user.getTelephone() != null){
			fields.append("telephone,");
			values.append("?,");
			list.add(user.getTelephone());
		}
		if(user.getMail() != null){
			fields.append("mail,");
			values.append("?,");
			list.add(user.getMail());
		}
		if(fields.length() == 0){
			json.put("state", "error");
			json.put("message", "传入的用户为空······");
			return json.toJSONString();
		}
		fields.append("create_time,");//创建时间
		values.append("?,");
		list.add(new Date());
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
	
	@Override
	public String findNameList() {
		JSONObject json = new JSONObject();
		StringBuilder sb = new StringBuilder();
		sb.append("select id,`name` from user");
		List<User> list;
		try {
			list = jdbcTemplate.query(sb.toString(), new User());
			for(User t : list){
				t.setValue(t.getName());
			}
			json.put("state", "success");
		} catch (DataAccessException e) {
			list = new ArrayList<User>();
			json.put("state", "error");
		}
		json.put("data", list);
		return json.toJSONString();
	}
}
