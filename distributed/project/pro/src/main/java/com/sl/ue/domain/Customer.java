package com.sl.ue.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.RowMapper;


/**
 * 功能  [客户]
 */
public class Customer implements RowMapper<Customer>{

	/*数据库字段*/
	private Integer id;
	private String name;//公司名称
	private String address;//地址
	private String contacts1;//联系人1
	private String contactPhone1;//联系电话1
	private String contacts2;//联系人2
	private String contactPhone2;//联系电话2
	private String contacts3;//联系人3
	private String contactPhone3;//联系电话3
	private String contacts4;//联系人4
	private String contactPhone4;//联系电话4
	private String contacts5;//联系人5
	private String contactPhone5;//联系电话5
	private Integer createUserId;//创建人id
	private String createUserName;//创建人名称
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;//创建时间
	
	/*业务属性*/
	private String value;
	
	

	public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
		Customer t =new Customer();
		/* try起来之后，sql可以指定字段*/
		try {
			t.setId(rs.getInt("id"));
		} catch (SQLException e) {
		}
		try {
			t.setName(rs.getString("name"));
		} catch (SQLException e) {
		}
		try {
			t.setAddress(rs.getString("address")); 
		} catch (SQLException e) {
		}
		try {
			t.setContacts1(rs.getString(contacts1)); 
		} catch (SQLException e) {
		}
		try {
			t.setContactPhone1(rs.getString("contact_phone1"));
		} catch (SQLException e) {
		}
		try {
			t.setContacts2(rs.getString(contacts2));
		} catch (SQLException e) {
		}
		try {
			t.setContactPhone2(rs.getString("contact_phone2")); 
		} catch (SQLException e) {
		}
		try {
			t.setContacts3(rs.getString(contacts3)); 
		} catch (SQLException e) {
		}
		try {
			t.setContactPhone3(rs.getString("contact_phone3")); 
		} catch (SQLException e) {
		}
		try {
			t.setContacts4(rs.getString(contacts4)); 
		} catch (SQLException e) {
		}
		try {
			t.setContactPhone4(rs.getString("contact_phone4"));
		} catch (SQLException e) {
		}
		try {
			t.setContacts5(rs.getString(contacts5)); 
		} catch (SQLException e) {
		}	
		try {
			t.setContactPhone5(rs.getString("contact_phone5")); 
		} catch (SQLException e) {
		}		
		try {
			t.setCreateUserId(rs.getInt("create_user_id"));
		} catch (SQLException e) {
		}	
		try {
			t.setCreateUserName(rs.getString("create_user_name"));
		} catch (SQLException e) {
		}	 
		try {
			t.setCreateTime(rs.getTimestamp("create_time"));
		} catch (SQLException e) {
		}	
		return t;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContacts1() {
		return contacts1;
	}
	public void setContacts1(String contacts1) {
		this.contacts1 = contacts1;
	}
	public String getContactPhone1() {
		return contactPhone1;
	}
	public void setContactPhone1(String contactPhone1) {
		this.contactPhone1 = contactPhone1;
	}
	public String getContacts2() {
		return contacts2;
	}
	public void setContacts2(String contacts2) {
		this.contacts2 = contacts2;
	}
	public String getContactPhone2() {
		return contactPhone2;
	}
	public void setContactPhone2(String contactPhone2) {
		this.contactPhone2 = contactPhone2;
	}
	public String getContacts3() {
		return contacts3;
	}
	public void setContacts3(String contacts3) {
		this.contacts3 = contacts3;
	}
	public String getContactPhone3() {
		return contactPhone3;
	}
	public void setContactPhone3(String contactPhone3) {
		this.contactPhone3 = contactPhone3;
	}
	public String getContacts4() {
		return contacts4;
	}
	public void setContacts4(String contacts4) {
		this.contacts4 = contacts4;
	}
	public String getContactPhone4() {
		return contactPhone4;
	}
	public void setContactPhone4(String contactPhone4) {
		this.contactPhone4 = contactPhone4;
	}
	public String getContacts5() {
		return contacts5;
	}
	public void setContacts5(String contacts5) {
		this.contacts5 = contacts5;
	}
	public String getContactPhone5() {
		return contactPhone5;
	}
	public void setContactPhone5(String contactPhone5) {
		this.contactPhone5 = contactPhone5;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
