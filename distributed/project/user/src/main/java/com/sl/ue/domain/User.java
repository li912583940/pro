package com.sl.ue.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.RowMapper;

/**
 * 功能  [用户实体类]
 */
public class User implements RowMapper<User>{

	/*数据库字段*/
	private Integer id;
	private String username;//用户名
	private String password;//用户密码
	private String name;//姓名
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;//创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;//修改时间
	private Integer roleId;//角色id
	private String telephone;//联系电话
	private String mail;//邮箱
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastSignTime;//上次登录时间
	private Integer signCount;//登录次数
	
	/*业务属性*/
	private String value;
	
	public User mapRow(ResultSet rs, int arg1) throws SQLException {
		User t = new User();
		/* try起来之后，sql可以指定字段*/
		try {
			t.setId(rs.getInt("id"));
		} catch (SQLException e) {
		}
		try {
			t.setUsername(rs.getString("username"));
		} catch (SQLException e) {
		}
		try {
			t.setPassword(rs.getString("password"));
		} catch (SQLException e) {
		}
		try {
			t.setMail(rs.getString("mail"));
		} catch (SQLException e) {
		}
		try {
			t.setName(rs.getString("name"));
		} catch (SQLException e) {
		}
		try {
			t.setRoleId(rs.getInt("role_id"));
		} catch (SQLException e) {
		}
		try {
			t.setTelephone(rs.getString("telephone"));
		} catch (SQLException e) {
		}
		try {
			t.setUpdateTime(rs.getTimestamp("update_time"));
		} catch (SQLException e) {
		}
		try {
			t.setLastSignTime(rs.getTimestamp("last_sign_time"));
		} catch (SQLException e) {
		}
		try {
			t.setSignCount(rs.getInt("sign_count"));
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Date getLastSignTime() {
		return lastSignTime;
	}
	public void setLastSignTime(Date lastSignTime) {
		this.lastSignTime = lastSignTime;
	}
	public Integer getSignCount() {
		return signCount;
	}
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	


	
	
}
