package com.sl.ue.web;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ue.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginWeb {

	@Autowired
	private LoginService loginService;
	
//	@RequestMapping(value="", method=RequestMethod.POST)
//	public String login(String username, String password){
//		String str= loginService.login(username, password);
//		return str;
//	}
	@RequestMapping(value="", method=RequestMethod.POST)
	public String login(String username, String password){
		String str= loginService.login(username, password);
		return str;
	}
	
	@RequestMapping(value="test", method=RequestMethod.GET)
	public String test(String name, String pass){
		String str= name +" : "+pass+" 这是你输入的！";
		return str;
	}

}
