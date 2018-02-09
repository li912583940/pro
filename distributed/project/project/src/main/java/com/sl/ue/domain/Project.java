package com.sl.ue.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.RowMapper;

/**
 * 功能  [项目信息]
 */
public class Project implements RowMapper<Project>{

	/* 数据库字段*/
	private Integer id;
	private String name;//项目名称 如：晋中监狱会见系统
	private Integer projectTypeId; //项目类别id
	private Integer customerId;//客户id
	private String lineInfo;//线路信息
	private String hardwareInfo;//硬件信息
	private String softwareInfo;//软件信息
	private String address;//项目实施地址
	private String province;//省
	private String city;//市
	private String area;//区
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date implementTime;//实施时间
	private Integer implementUserId;//实施人id
	private String implementUserName;//实施人姓名
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;//创建时间
	private Integer createUserId;//创建人id
	private String createUserName;//创建人姓名
	
	/*视图需要的属性*/
	private String projectTypeName;
	private String customerName;


	public Project mapRow(ResultSet rs, int arg1) throws SQLException {
		Project t =new Project();
		/* try起来之后，sql可以指定字段*/
		try{
			t.setId(rs.getInt("id"));
		}catch(SQLException e){
		}
		try {
			t.setName(rs.getString("name"));
		} catch (SQLException e) {
		}
		try {
			t.setProjectTypeId(rs.getInt("project_type_id"));
		} catch (SQLException e) {
		}
		try {
			t.setCustomerId(rs.getInt("customer_id")); 
		} catch (SQLException e) {
		}
		try {
			t.setLineInfo(rs.getString("line_info")); 
		} catch (SQLException e) {
		}
		try {
			t.setHardwareInfo(rs.getString("hardware_info")); 
		} catch (SQLException e) {
		}
		try {
			t.setSoftwareInfo(rs.getString("software_info")); 
		} catch (SQLException e) {
		}
		try {
			t.setAddress(rs.getString("address")); 
		} catch (SQLException e) {
		}
		try {
			t.setProvince(rs.getString("province")); 
		} catch (SQLException e) {
		}
		try {
			t.setCity(rs.getString("city"));
		} catch (SQLException e) {
		}
		try {
			t.setArea(rs.getString("area")); 
		} catch (SQLException e) {
		}
		try {
			t.setImplementTime(rs.getTimestamp("implement_time")); 
		} catch (SQLException e) {
		}
		try {
			t.setImplementUserId(rs.getInt("implement_user_id"));
		} catch (SQLException e) {
		}
		try {
			t.setImplementUserName(rs.getString("implement_user_name"));
		} catch (SQLException e) {
		}
		try {
			t.setCreateTime(rs.getTimestamp("create_time")); 
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
		/*视图需要的属性*/
		try {
			t.setProjectTypeName(rs.getString("project_type_name"));
		} catch (SQLException e) {
		}
		try {
			t.setCustomerName(rs.getString("customer_name"));
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
	public Integer getProjectTypeId() {
		return projectTypeId;
	}
	public void setProjectTypeId(Integer projectTypeId) {
		this.projectTypeId = projectTypeId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getLineInfo() {
		return lineInfo;
	}
	public void setLineInfo(String lineInfo) {
		this.lineInfo = lineInfo;
	}
	public String getHardwareInfo() {
		return hardwareInfo;
	}
	public void setHardwareInfo(String hardwareInfo) {
		this.hardwareInfo = hardwareInfo;
	}
	public String getSoftwareInfo() {
		return softwareInfo;
	}
	public void setSoftwareInfo(String softwareInfo) {
		this.softwareInfo = softwareInfo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Date getImplementTime() {
		return implementTime;
	}
	public void setImplementTime(Date implementTime) {
		this.implementTime = implementTime;
	}
	public Integer getImplementUserId() {
		return implementUserId;
	}
	public void setImplementUserId(Integer implementUserId) {
		this.implementUserId = implementUserId;
	}
	public String getImplementUserName() {
		return implementUserName;
	}
	public void setImplementUserName(String implementUserName) {
		this.implementUserName = implementUserName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public String getProjectTypeName() {
		return projectTypeName;
	}
	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}
