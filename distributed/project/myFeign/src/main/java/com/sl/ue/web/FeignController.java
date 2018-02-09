package com.sl.ue.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ue.service.ComputeClient;

@RequestMapping("/feign")
@RestController
public class FeignController {

	@Autowired
	ComputeClient computeClient;
	
	@RequestMapping(value="/find",method=RequestMethod.GET)
	public String find(){
		return computeClient.add("hello a", "lixiaotian");
	}
}
