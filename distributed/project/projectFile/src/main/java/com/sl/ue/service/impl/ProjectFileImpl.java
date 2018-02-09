package com.sl.ue.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sl.ue.domain.ProjectFile;
import com.sl.ue.service.ProjectFileService;
import com.sl.ue.util.Config;
import com.sl.ue.util.Page;
import com.sl.ue.util.StringUtil;

@Service
public class ProjectFileImpl implements ProjectFileService{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String findList(Map<String, Object> map) {
		Page<ProjectFile> page = new Page<ProjectFile>();
		StringBuilder sb=new StringBuilder();
		StringBuilder sbCount = new StringBuilder();
		List<Object> list=new ArrayList<Object>();
		sb.append("select * from project_file a where 1=1");
		sbCount.append("select count(*) from project_file a where 1=1");
		if(map.get("explain") != null && !"".equals(map.get("explain"))){
			sb.append(" and instr(a.explain, ?)>0");
			sbCount.append(" and instr(a.explain, ?)>0");
			list.add(map.get("explain"));
		}
		if(map.get("projectId") != null && !"".equals(map.get("projectId"))){
			sb.append(" and project_id=?");
			sbCount.append(" and project_id=?");
			list.add(map.get("projectId"));
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
		sb.append(" GROUP BY a.id DESC");
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
		List<ProjectFile> ProjectFileList;;
		try{
			ProjectFileList=jdbcTemplate.query(sb.toString(),list.toArray(),new ProjectFile());
		}catch(DataAccessException e){
			ProjectFileList = new ArrayList<ProjectFile>();
		}
		page.setData(ProjectFileList);//设置返回数据
		JSONObject json = new JSONObject();
		String s = json.toJSONString(page, SerializerFeature.WriteMapNullValue);
		return s;
	}

	public String findOne(Integer id) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * from project_file where 1=1 and id=?");
		Object[] obj = new Object[]{id};
		ProjectFile ProjectFile;
		try {
			ProjectFile = jdbcTemplate.queryForObject(sb.toString(), obj, new ProjectFile());
		} catch (DataAccessException e) {
			ProjectFile = new ProjectFile();
		}
		JSONObject json = new JSONObject();
		json.put("data", ProjectFile);
		return JSONObject.toJSONString(json, SerializerFeature.WriteMapNullValue);
	}

	public String insert(ProjectFile ProjectFile) {
		JSONObject json = new JSONObject();
		if(ProjectFile == null){
			json.put("state", "error");
			json.put("message", "传入的项目类型信息为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder fields = new StringBuilder();
		StringBuilder values = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("insert into project_file");
		if(ProjectFile.getProjectId() != null){
			fields.append("project_id,");
			values.append("?,");
			list.add(ProjectFile.getProjectId());
		}
		
		if(ProjectFile.getPath() != null){
			fields.append("path,");
			values.append("?,");
			list.add(ProjectFile.getPath());
		}
		if(ProjectFile.getExplain() != null){
			fields.append("`explain`,");
			values.append("?,");
			list.add(ProjectFile.getExplain());
		}
		if(ProjectFile.getCreateUserId() != null){
			fields.append("create_user_id,");
			values.append("?,");
			list.add(ProjectFile.getCreateUserId());
		}
		if(ProjectFile.getCreateUserName() != null){
			fields.append("create_user_name,");
			values.append("?,");
			list.add(ProjectFile.getCreateUserName());
		}
		if(ProjectFile.getFileSize()!= null){
			fields.append("file_size,");
			values.append("?,");
			list.add(ProjectFile.getFileSize());
		}
		if(fields.length() == 0){
			json.put("state", "error");
			json.put("message", "传入的项目类型信息为空······");
			return json.toJSONString();
		}
		fields.append("create_time,");
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

	public String update(ProjectFile ProjectFile) {
		JSONObject json = new JSONObject();
		if(ProjectFile == null ){
			json.put("state", "error");
			json.put("message", "传入的项目类型信息为空······");
			return json.toJSONString();
		}
		StringBuilder sb = new StringBuilder();
		StringBuilder set = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("update project_file set");
		if(ProjectFile.getExplain() != null){
			set.append(" `explain`=?,");
			list.add(ProjectFile.getExplain());
		}
		sb.append(StringUtil.sqlSet(set.toString()));//去掉结尾的","
		sb.append(" where id=?");
		list.add(ProjectFile.getId());
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
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("select path from project_file where FIND_IN_SET(id,?)");
		sb.append("delete from project_file where FIND_IN_SET(id,?)");
		Object[] obj = new Object[]{ids};
		try{
			//先删除文件
			List<ProjectFile> projectFileList =jdbcTemplate.query(sbQuery.toString(), obj, new ProjectFile());
			String basePath = Config.getPropertiesValue("file.path");
			for(ProjectFile projectFile : projectFileList){
				File file = new File(basePath+projectFile.getPath());
				if(file.exists()){
					file.delete();
				}
			}
			jdbcTemplate.update(sb.toString(), obj);
			json.put("state", "success");
			json.put("message", "删除成功");
		}catch(DataAccessException e){
			json.put("state", "error");
			json.put("message", "删除时发生错误");
		}
		return json.toJSONString();
	}
	
	public void downFile(HttpServletRequest request, HttpServletResponse response){
		String path = request.getParameter("path");
		InputStream in = null;
		OutputStream out = null;
		try {
			String fileName =  path.substring(path.lastIndexOf("/")+1, path.length());
			String userAgent=request.getHeader("USER-AGENT");
			if(userAgent.indexOf("Chrome")!=-1 || userAgent.indexOf("Safari")!=-1 || userAgent.indexOf("Firefox")!=-1){
				fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
			}else{
				fileName = URLEncoder.encode(fileName,"UTF8");
			}
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			response.setContentType("application/octet-stream");
			String basePath = Config.getPropertiesValue("file.path");
			in = new FileInputStream(basePath+path);
			int l = 10*1024*1024;//10M 默认，可在配置文件中设置此值大小
			String typeBufferSize = Config.getPropertiesValue("file.buffer.size");
			if(!"".equals(typeBufferSize)){
				int tfz =Integer.parseInt(typeBufferSize.trim());
				l = tfz*1024;
			}
			out = response.getOutputStream();
			byte[] buffer = new byte[l];
			int bytesRead = -1;//文件大小
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
				//bos.close();
			}
			catch (IOException ex) {
			}
			try {
				out.close();
			} catch (IOException e2) {
			}
		}
	}
}
