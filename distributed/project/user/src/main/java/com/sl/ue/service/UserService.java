package com.sl.ue.service;

import java.util.Map;

import com.sl.ue.domain.User;

/**
 * 描述 [用户]
 * 创建者 LXT
 * 创建时间 2017年12月25日 上午9:41:44
 */
public interface UserService {

	/**
	 * 功能 [更新当前用户的登录时间和登录次数]
	 */
	public String updateDateAndCount(Integer id);
	
	public String findOne(Integer id);
	
	public String findList(Map<String,Object> map);
	
	public String findCount(Map<String,Object> map);
	
	public String update(User user);
	
	public String delete(String ids);
	
	public String insert(User user);
	/**
	 * 功能描述 [查询id和名称的全部用户数据]
	 */
	public String findNameList();
}
