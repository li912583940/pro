package com.sl.ue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sl.ue.domain.User;
import com.sl.ue.service.LoginService;

@Service
public class LoginImpl implements LoginService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public String login(String username, String password) {
		String sql="select * from user where username=? and password=?";
		Object[] obj=new Object[]{username,password};
		User user=null;
		JSONObject json = new JSONObject();
		if(username == null || password ==null){
			json.put("message", "帐号或密码为NULL");
		}
		try {
			 user=jdbcTemplate.queryForObject(sql, obj, new User());
			 if(user!=null){
				 json.put("state", "success");
				 json.put("message", "登录成功");
				 json.put("data", user);
			 }
		} catch (EmptyResultDataAccessException e) {
			json.put("state", "error");
			if(!json.containsKey("message")){
				json.put("message", "帐号不存在");
			}
		}
		return JSONObject.toJSONString(json, SerializerFeature.WriteMapNullValue);
	}


	
	

}
