package com.sl.ue.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ue.service.ProjectService;

/**
 * 描述 []
 */
@RestController
@RequestMapping("/project")
public class ProjectWeb {

	@Autowired
	ProjectService projectService;
	
	@RequestMapping(value="/findList",method=RequestMethod.GET)
	public String findList(){
		return projectService.findList();
	}
}
