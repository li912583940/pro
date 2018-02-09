package com.sl.ue.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述 []
 */
@FeignClient(value="user-service")
public interface UserService {

	@RequestMapping(value="/user/findList",method=RequestMethod.GET)
	String findList(@RequestParam(value = "name") String name);
}
