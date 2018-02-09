package com.sl.ue.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="myRibbon-service",fallback=ComputeClientHystrix.class)
public interface ComputeClient {

	@RequestMapping(value="/add",method=RequestMethod.GET)
	String add(@RequestParam(value="a") String a,@RequestParam(value="b") String b);
}
