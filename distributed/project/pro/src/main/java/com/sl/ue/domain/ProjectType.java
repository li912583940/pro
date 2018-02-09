package com.sl.ue.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


/**
 * 功能  [项目类型]
 */
public class ProjectType implements RowMapper<ProjectType>{

	/*数据库字段*/
	private Integer id;
	private String name;
	
	/*业务属性*/
	private String value;
	
	public ProjectType mapRow(ResultSet rs, int arg1) throws SQLException {
		ProjectType t = new ProjectType();
		/* try起来之后，sql可以指定字段*/
		try {
			t.setId(rs.getInt("id"));
		} catch (SQLException e) {
		}
		try {
			t.setName(rs.getString("name"));
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


	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
	
	
}
