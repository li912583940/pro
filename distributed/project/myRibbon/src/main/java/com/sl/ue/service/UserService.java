package com.sl.ue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 描述 [用户类消费者]
 */
@Service
public class UserService {

	@Autowired
	RestTemplate restTemplate;
	
	public String userFindList(String name){
		return restTemplate.getForObject("http://user-service/user/findList?name="+name, String.class);
	}
	
	public String projectFindList(){
		return restTemplate.getForObject("http://project-service/project/findList", String.class);
	}
}
