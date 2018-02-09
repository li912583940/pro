package com.sl.ue.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 描述 []
 */
@FeignClient(value="project-service")
public interface ProjectService {

	@RequestMapping(value="/project/findList",method=RequestMethod.GET)
	String findList();
}
