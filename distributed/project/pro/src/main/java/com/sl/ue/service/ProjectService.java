package com.sl.ue.service;

import java.util.Map;

import com.sl.ue.domain.Project;

public interface ProjectService {

	public String findOne(Integer id);
	
	public String findList(Map<String,Object> map);
	
	public String findCount(Map<String,Object> map);
	
	public String update(Project project);
	
	public String delete(String ids);
	
	public String insert(Project project);
	
}
