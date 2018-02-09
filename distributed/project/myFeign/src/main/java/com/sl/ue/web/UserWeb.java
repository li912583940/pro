package com.sl.ue.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ue.service.UserService;

/**
 * 描述 []
 */
@RestController
@RequestMapping("/user")
public class UserWeb {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/findList",method=RequestMethod.GET)
	public String findList(String name){
		return userService.findList(name);
	}
}
