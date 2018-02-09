package com.sl.ue.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sl.ue.domain.Customer;
import com.sl.ue.service.CustomerService;

/**
 * 描述 [客户类]
 */
@RestController
@RequestMapping("/customer")
public class CustomerWeb {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/findList",method=RequestMethod.GET)
	public String findList(Integer pageNum, Integer pageSize, String name){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNum", pageNum);
		map.put("pageSize", pageSize);
		map.put("name", name);
		return customerService.findList(map);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(String ids){
		String str = customerService.delete(ids);
		return str;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Customer customer){
		String str = customerService.update(customer);
		return str;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(Customer customer){
		String str = customerService.insert(customer);
		return str;
	}
	
	@RequestMapping(value="/findOne",method=RequestMethod.GET)
	public String findOne(Integer id){
		String str = customerService.findOne(id);
		return str;
	}
	
	@RequestMapping(value="/findNameList",method=RequestMethod.GET)
	public String findNameList(){
		String str = customerService.findNameList();
		return str;
	}
}
