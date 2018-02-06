package com.athongkun.utils;

public class StringUtil {
	
	private StringUtil() {
		
	}

	public static boolean isEmpty( String content ) {
		return content == null || content.equals("");
	}
	
	public static boolean isNotEmpty( String content ) {
		return !isEmpty(content);
	}
}
