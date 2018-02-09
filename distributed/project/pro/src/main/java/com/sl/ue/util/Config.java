package com.sl.ue.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 描述 [读取配置配置文件]
 */
public class Config {
	private static final Properties propert =  load("config.properties");
	
	public static Properties load(String proper){
		Properties pro = new Properties();
		InputStream in = null;
		try {
			in = Config.class.getClassLoader().getResourceAsStream(proper);
			pro.load(in);
		} catch (Exception e) {
		}finally{
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		return pro;
	} 
	
	public static String getPropertiesValue(String key){
		return propert.getProperty(key);
	}
}
