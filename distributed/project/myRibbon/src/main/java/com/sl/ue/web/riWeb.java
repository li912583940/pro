package com.sl.ue.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ue.service.UserService;

/**
 * 描述 []
 */
@RestController
@RequestMapping("/ri")
public class riWeb {

	@Autowired
	UserService userService;
	
	@RequestMapping("/user")
	public String userFindList(String name){
		return userService.userFindList(name);
	}
	
	@RequestMapping("/project")
	public String projectFindList(){
		return userService.projectFindList();
	}
}
