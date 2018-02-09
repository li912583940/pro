package com.sl.ue.util;

public class StringUtil {

	public static String sqlSet(String set){
		if(set.endsWith(",")){
			return set.substring(0, set.length()-1);
		}else{
			return set;
		}
	}
}
