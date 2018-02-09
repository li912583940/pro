package com.sl.ue.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sl.ue.domain.ProjectFile;

public interface ProjectFileService {

	/**
	 * 功能 [查询项目类型列表 支持分页]
	 */
	public String findList(Map<String,Object> map);
	
	/**
	 * 功能 [查询一条项目类型记录  参数 id]
	 */
	public String findOne(Integer id);
	
	/**
	 * 功能 [增加一条项目类型记录]
	 */
	public String insert(ProjectFile projectFile);
	
	/**
	 * 功能 [修改一条项目类型记录]
	 */
	public String update(ProjectFile projectFile);
	
	/**
	 * 功能 [删除一条项目类型记录]
	 */
	public String delete(String ids);
	
	public void downFile(HttpServletRequest request, HttpServletResponse response);
}
