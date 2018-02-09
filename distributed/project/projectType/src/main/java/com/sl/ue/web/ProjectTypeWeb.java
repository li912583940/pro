package com.sl.ue.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ue.domain.ProjectType;
import com.sl.ue.service.ProjectTypeService;

/**
 * 描述 [项目类别]
 */
@RestController
@RequestMapping("/projectType")
public class ProjectTypeWeb {

	@Autowired
	private ProjectTypeService projectTypeService;
	
	@RequestMapping(value="/findList",method=RequestMethod.GET)
	public String findList(Integer pageNum, Integer pageSize, String name){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("name", name);
		return projectTypeService.findList(map);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(String ids){
		String str = projectTypeService.delete(ids);
		return str;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(ProjectType projectType){
		String str = projectTypeService.update(projectType);
		return str;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(ProjectType projectType){
		String str = projectTypeService.insert(projectType);
		return str;
	}
	
	@RequestMapping(value="/findOne",method=RequestMethod.GET)
	public String findOne(Integer id){
		String str = projectTypeService.findOne(id);
		return str;
	}
	
	@RequestMapping(value="/findNameList",method=RequestMethod.GET)
	public String findNameList(){
		String str = projectTypeService.findNameList();
		return str;
	}

}
