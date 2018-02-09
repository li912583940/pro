package com.sl.ue.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ue.domain.User;
import com.sl.ue.service.UserService;

/**
 * 描述 []
 * 创建者 LXT
 * 创建时间 2017年12月25日 上午9:45:28
 */
@RequestMapping("/user")
@RestController
public class UserWeb {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/findList",method=RequestMethod.GET)
	public String findList(Integer pageNum, Integer pageSize, String name, String username){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("name", name);
		map.put("username", username);
		return userService.findList(map);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(String ids){
		String str = userService.delete(ids);
		return str;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(User user){
		String str = userService.update(user);
		return str;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(User user){
		String str = userService.insert(user);
		return str;
	}
	
	@RequestMapping(value="/findOne",method=RequestMethod.GET)
	public String findOne(Integer id){
		String str = userService.findOne(id);
		return str;
	}
	
	@RequestMapping(value="/findNameList",method=RequestMethod.GET)
	public String findNameList(){
		String str = userService.findNameList();
		return str;
	}
}
