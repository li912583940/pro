package com.sl.ue.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sl.ue.domain.ProjectFile;
import com.sl.ue.service.ProjectFileService;
import com.sl.ue.util.Config;

@RestController
@RequestMapping("/file")
public class FileUpload {

	@Autowired
	private ProjectFileService projectFileService;
	
	@RequestMapping(value="/project",method={RequestMethod.GET,RequestMethod.POST})
	public String upload(HttpServletRequest request, HttpServletResponse response){
		String projectId = request.getParameter("projectId").trim();
		String projectName = request.getParameter("projectName").trim();
		String implementTime = request.getParameter("implementTime").trim();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile mFile = null;
		for (Iterator<?> i = fileMap.keySet().iterator(); i.hasNext();) {
			Object obj = i.next();
			mFile = (MultipartFile) fileMap.get(obj);
		}
		/*创建文件存储路径 时间->年月日  + 项目名->中文 + 时间->年月日时分秒*/
		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM");
		if(implementTime == null || "".equals(implementTime)){
			implementTime = ymd.format(new Date());
		}else{
			long imple = Long.parseLong(implementTime);
			implementTime = ymd.format(new Date(imple));
		}
		if(projectName == null || "".equals(projectName)){
			projectName = "无名";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String datePath = "/"+implementTime+"/"+projectName+"/"+sdf.format(new Date());
		String filePath = Config.getPropertiesValue("file.path");//系统固定路径
		BufferedInputStream in =null;
		BufferedOutputStream out = null;
		try {
			// 得到上传的文件的文件名
			String filename = mFile.getOriginalFilename();
			// 获取文件后缀名
			if (filename == null || ("").equals(filename)) {
				return "上传文件不存在";
			}
			File file = new File(filePath+datePath);
			if(!file.exists()){
				file.mkdirs();
			}
			String typeType = Config.getPropertiesValue("file.type");
			boolean isPattern = false;
			if( !typeType.equals("*") && !typeType.equals("")){
				String[] typevalues = typeType.trim().split(",");
				String fileExt = filename.substring(filename.lastIndexOf("."));
				for(String s : typevalues){
					if(s.equals(fileExt)){
						isPattern = true;
						break;
					}
				}
			}else{
				isPattern = true;
			}
			if(isPattern == false){
				return "不支持此格式";
			}
			String projectFilePath = datePath+"/"+filename;//存储到数据库中的相对路径
			String newFilePath = filePath+projectFilePath;
			File saveFile = new File(newFilePath);
			int l = 10*1024*1024;//10M 默认，可在配置文件中设置此值大小
			String typeBufferSize = Config.getPropertiesValue("file.buffer.size");
			if(!"".equals(typeBufferSize)){
				int tfz =Integer.parseInt(typeBufferSize.trim());
				l = tfz*1024;
			}
			in = new BufferedInputStream(mFile.getInputStream());
			out = new BufferedOutputStream(new FileOutputStream(saveFile));
			int byteCount = 0;
			byte[] buffer = new byte[l];
			int bytesRead = -1;//文件大小
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			//将数据存储到项目文件中
			ProjectFile projectFile = new ProjectFile();
			projectFile.setPath(projectFilePath);
			projectFile.setProjectId(Integer.parseInt(projectId));
			int fileSize = (int) Math.ceil(byteCount/1024);
			fileSize=fileSize==0?1:fileSize;
			projectFile.setFileSize(fileSize);
			projectFileService.insert(projectFile);
			//FileCopyUtils.copy(mFile.getBytes(), saveFile);
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}finally {
			try {
				in.close();
			}
			catch (IOException ex) {
			}
			try {
				out.close();
			}
			catch (IOException ex) {
			}
		}
		
		System.out.println("成功 "+filePath);
		return "";
	}
}
