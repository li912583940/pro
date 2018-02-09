package com.sl.ue.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sl.ue.domain.Customer;
import com.sl.ue.domain.Project;
import com.sl.ue.service.CustomerService;
import com.sl.ue.util.Page;
import com.sl.ue.util.StringUtil;



@Service
public class CustomerImpl implements CustomerService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String findList(Map<String, Object> map) {
		Page<Customer> page = new Page<Customer>();
		StringBuilder sb=new StringBuilder();
		StringBuilder sbCount = new StringBuilder();
		List<Object> list=new ArrayList<Object>();
		sb.append("select * from customer a where 1=1");
		sbCount.append("select count(*) from customer a where 1=1");
		if(map.get("name") != null && !"".equals(map.get("name"))){
			sb.append(" and instr(a.name, ?)>0");
			sbCount.append(" and instr(a.name, ?)>0");
			list.add(map.get("name"));
		}
		if(map.get("address") != null && !"".equals(map.get("address"))){
			sb.append(" and instr(a.address, ?)");
			sbCount.append(" and instr(a.address, ?)");
			list.add(map.get("address"));
		}
		/**
		 * 查询总数
		 */
		int count=0;
		try{
			count= jdbcTemplate.queryForObject(sbCount.toString(), list.toArray(), Integer.class);
		}catch(DataAccessException e){
			
		}
		page.setCount(count);//设置总数
		if(map.get("pageNum") != null && !"".equals(map.get("pageNum")) 
				&& map.get("pageSize") != null && !"".equals(map.get("pageSize"))){
			int pageNum = (Integer)map.get("pageNum");
			int pageSize = (Integer)map.get("pageSize");
			int startNum = (pageNum-1)*pageSize;
			sb.append(" limit ?,?");
			list.add(startNum);
			list.add(pageSize);
			page.setPageNum(pageNum);//设置页码
			page.setPageSize(pageSize);//设置每页大小
		}
		List<Customer> cusList;;
		try{
			cusList=jdbcTemplate.query(sb.toString(),list.toArray(),new Customer());
		}catch(DataAccessException e){
			cusList = new ArrayList<Customer>();
		}
		page.setData(cusList);//设置返回数据
		JSONObject json = new JSONObject();
		//String s = json.toJSONString(page);
		String s = json.toJSONString(page, SerializerFeature.WriteMapNullValue);
		return s;
	}

	public String findOne(Integer id) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from customer where 1=1 and id=?");
		Object[] obj = new Object[]{id};
		Customer customer;
		try {
			customer = jdbcTemplate.queryForObject(sb.toString(), obj, new Customer());
		} catch (DataAccessException e) {
			customer = new Customer();
		}
		JSONObject json = new JSONObject();
		json.put("data", customer);
		return JSONObject.toJSONString(json, SerializerFeature.WriteMapNullValue);
	}

	public String insert(Customer customer) {
		JSONObject json = new JSONObject();
		if(customer == null){
			json.put("state", "error");
			json.put("message", "传入的客户信息为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder values = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("insert into customer");
		if(customer.getName() != null){
			fields.append("name,");
			values.append("?,");
			list.add(customer.getName());
		}
		if(customer.getAddress() != null){
			fields.append("address,");
			values.append("?,");
			list.add(customer.getAddress());
		}
		if(customer.getContacts1() != null){
			fields.append("contacts1,");
			values.append("?,");
			list.add(customer.getContacts1());
		}
		if(customer.getContactPhone1() != null){
			fields.append("contactPhone1,");
			values.append("?,");
			list.add(customer.getContactPhone1());
		}
		if(customer.getContacts2() != null){
			fields.append("contacts2,");
			values.append("?,");
			list.add(customer.getContacts2());
		}
		if(customer.getContactPhone2() != null){
			fields.append("contactPhone2,");
			values.append("?,");
			list.add(customer.getContactPhone2());
		}
		if(customer.getContacts3() != null){
			fields.append("contacts3,");
			values.append("?,");
			list.add(customer.getContacts3());
		}
		if(customer.getContactPhone3() != null){
			fields.append("contactPhone3,");
			values.append("?,");
			list.add(customer.getContactPhone3());
		}
		if(customer.getContacts4() != null){
			fields.append("contacts4,");
			values.append("?,");
			list.add(customer.getContacts4());
		}
		if(customer.getContactPhone4() != null){
			fields.append("contactPhone4,");
			values.append("?,");
			list.add(customer.getContactPhone4());
		}
		if(customer.getContacts5() != null){
			fields.append("contacts5,");
			values.append("?,");
			list.add(customer.getContacts5());
		}
		if(customer.getContactPhone5() != null){
			fields.append("contactPhone5,");
			values.append("?,");
			list.add(customer.getContactPhone5());
		}
		if(fields.length() == 0){
			json.put("state", "error");
			json.put("message", "传入的客户信息为空······");
			return json.toJSONString();
		}
		fields.append("create_time,");//创建时间
		values.append("?,");
		list.add(new Date());
		sb.append("(")
		.append(StringUtil.sqlSet(fields.toString()))
		.append(") VALUES(")
		.append(StringUtil.sqlSet(values.toString()))
		.append(")");
		try {
			jdbcTemplate.update(sb.toString(), list.toArray());
			json.put("state", "success");
			json.put("message", "新增成功");
		} catch (DataAccessException e) {
			json.put("state", "error");
			json.put("message", "插入数据时出现错误");
		}
		return json.toJSONString();
	}

	public String update(Customer customer) {
		JSONObject json = new JSONObject();
		if(customer == null ){
			json.put("state", "error");
			json.put("message", "传入的客户信息为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder set = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("update customer set");
		if(customer.getName() != null){
			set.append(" `name`=?,");
			list.add(customer.getName());
		}
		if(customer.getAddress() != null){
			set.append(" address=?,");
			list.add(customer.getAddress());
		}
		if(customer.getContacts1() != null){
			set.append(" `contacts1`=?,");
			list.add(customer.getContacts1());
		}
		if(customer.getContactPhone1() != null){
			set.append(" `contactPhone1`=?,");
			list.add(customer.getContactPhone1());
		}
		if(customer.getContacts2() != null){
			set.append(" `contacts2`=?,");
			list.add(customer.getContacts2());
		}
		if(customer.getContactPhone2() != null){
			set.append(" `contactPhone2`=?,");
			list.add(customer.getContactPhone2());
		}
		if(customer.getContacts3() != null){
			set.append(" `contacts3`=?,");
			list.add(customer.getContacts3());
		}
		if(customer.getContactPhone3() != null){
			set.append(" `contactPhone3`=?,");
			list.add(customer.getContactPhone3());
		}
		if(customer.getContacts4() != null){
			set.append(" `contacts4`=?,");
			list.add(customer.getContacts4());
		}
		if(customer.getContactPhone4() != null){
			set.append(" `contactPhone4`=?,");
			list.add(customer.getContactPhone4());
		}
		if(customer.getContacts5() != null){
			set.append(" `contacts5`=?,");
			list.add(customer.getContacts5());
		}
		if(customer.getContactPhone5() != null){
			set.append(" `contactPhone5`=?,");
			list.add(customer.getContactPhone5());
		}
		sb.append(StringUtil.sqlSet(set.toString()));//去掉结尾的","
		sb.append(" where id=?");
		list.add(customer.getId());
		try {
			jdbcTemplate.update(sb.toString(), list.toArray());
			json.put("state", "success");
			json.put("message", "修改成功");
		} catch (DataAccessException e) {
			json.put("state", "error");
			json.put("message", "修改时发生错误");
		}
		return json.toJSONString();
	}

	public String delete(String ids) {
		JSONObject json = new JSONObject();
		if(ids == null){
			json.put("state", "error");
			json.put("message", "传入的id为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		sb.append("delete from customer where FIND_IN_SET(id,?)");
		Object[] obj = new Object[]{ids};
		try{
			jdbcTemplate.update(sb.toString(), obj);
			json.put("state", "success");
			json.put("message", "删除成功");
		}catch(DataAccessException e){
			json.put("state", "error");
			json.put("message", "删除时发生错误");
		}
		return json.toJSONString();
	}
	
	@Override
	public String findNameList() {
		JSONObject json = new JSONObject();
		StringBuilder sb = new StringBuilder();
		sb.append("select id,`name`,address from customer");
		List<Customer> list;
		try {
			list = jdbcTemplate.query(sb.toString(), new Customer());
			for(Customer t : list){
				t.setValue(t.getName());
			}
			json.put("state", "success");
		} catch (DataAccessException e) {
			list = new ArrayList<Customer>();
			json.put("state", "error");
		}
		json.put("data", list);
		return json.toJSONString();
	}

}
