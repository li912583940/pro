package com.sl.ue.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ue.domain.Project;
import com.sl.ue.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectWeb {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="/findList",method=RequestMethod.GET)
	public String findList(Integer pageNum, Integer pageSize, String name, String address){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("name", name);
		map.put("address", address);
		return projectService.findList(map);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(String ids){
		String str = projectService.delete(ids);
		return str;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Project project){
		String str = projectService.update(project);
		return str;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(Project project){
		String str = projectService.insert(project);
		return str;
	}
	
	@RequestMapping(value="/findOne",method=RequestMethod.GET)
	public String findOne(Integer id){
		String str = projectService.findOne(id);
		return str;
	}
}
