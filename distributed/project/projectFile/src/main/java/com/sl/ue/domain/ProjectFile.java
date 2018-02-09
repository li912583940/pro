package com.sl.ue.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.RowMapper;

/**
 * 功能  [项目源码文件]
 */
public class ProjectFile implements RowMapper<ProjectFile>{

	/*数据库字段*/
	private Integer id;
	private Integer projectId;//项目id
	private String path;//文件路径
	private String explain;//说明
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private Integer createUserId;
	private String createUserName;
	private Integer fileSize;//文件大小 单位 KB
	
	/*业务属性*/
	private String projectName;//项目名称

	public ProjectFile mapRow(ResultSet arg0, int arg1) throws SQLException {
		ProjectFile t =new ProjectFile();
		/* try起来之后，sql可以指定字段*/
		try {
			t.setId(arg0.getInt("id"));
		} catch (SQLException e) {
		}
		try {
			t.setProjectId(arg0.getInt("project_id"));
		} catch (SQLException e) {
		}
		try {
			t.setPath(arg0.getString("path"));
		} catch (SQLException e) {
		}
		try {
			t.setExplain(arg0.getString("explain"));
		} catch (SQLException e) {
		}
		try {
			t.setCreateTime(arg0.getTimestamp("create_time"));
		} catch (SQLException e) {
		}
		try {
			t.setCreateUserId(arg0.getInt("create_user_id"));
		} catch (SQLException e) {
		}
		try {
			t.setCreateUserName(arg0.getString("create_user_name"));
		} catch (SQLException e) {
		}
		try {
			t.setFileSize(arg0.getInt("file_size"));
		} catch (SQLException e) {
		}
		try {
			t.setProjectName(arg0.getString("project_name"));
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
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
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
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
}
