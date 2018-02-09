package com.sl.ue.service;

import java.util.Map;

import com.sl.ue.domain.Customer;



public interface CustomerService {

	/**
	 * 功能 [查询客户列表 支持分页]
	 */
	public String findList(Map<String,Object> map);
	
	/**
	 * 功能 [查询一条客户记录  参数 id]
	 */
	public String findOne(Integer id);
	
	/**
	 * 功能 [增加一条客户记录]
	 */
	public String insert(Customer customer);
	
	/**
	 * 功能 [修改一条客户记录]
	 */
	public String update(Customer customer);
	
	/**
	 * 功能 [删除一条客户记录]
	 */
	public String delete(String ids);
	
	/**
	 * 功能描述 [查询id和名称的全部客户数据]
	 */
	public String findNameList();
}
