package com.sl.ue.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ue.domain.ProjectFile;
import com.sl.ue.service.ProjectFileService;

/**
 * 描述 []
 */
@RestController
@RequestMapping("/projectFile")
public class ProjectFileWeb {

	@Autowired
	private ProjectFileService projectFileService;
	
	@RequestMapping(value="/findList",method=RequestMethod.GET)
	public String findList(Integer pageNum, Integer pageSize, String explain, Integer projectId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("explain", explain);
		map.put("projectId", projectId);
		return projectFileService.findList(map);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(String ids){
		String str = projectFileService.delete(ids);
		return str;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(ProjectFile projectFile){
		String str = projectFileService.update(projectFile);
		return str;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(ProjectFile projectFile){
		String str = projectFileService.insert(projectFile);
		return str;
	}
	
	@RequestMapping(value="/findOne",method=RequestMethod.GET)
	public String findOne(Integer id){
		String str = projectFileService.findOne(id);
		return str;
	}
	
	@RequestMapping(value="/downFile",method={RequestMethod.GET,RequestMethod.POST})
	public void downFile(HttpServletRequest request, HttpServletResponse response){
		//String str = projectFileService.downFile(request, response);
		projectFileService.downFile(request, response);
	}
}
