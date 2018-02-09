package com.sl.ue.service;

import java.util.Map;

import com.sl.ue.domain.ProjectType;

public interface ProjectTypeService {

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
	public String insert(ProjectType projectType);
	
	/**
	 * 功能 [修改一条项目类型记录]
	 */
	public String update(ProjectType projectType);
	
	/**
	 * 功能 [删除一条项目类型记录]
	 */
	public String delete(String ids);
	
	/**
	 * 功能描述 [查询id和名称的全部项目类型数据]
	 */
	public String findNameList();
}
