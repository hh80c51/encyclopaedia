package com.athongkun.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String format( Date d, String format ) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}
	
	public static String getNow(String format) {
		return format(new Date(), format);
	}
	
	public static String getNow() {
		return format(new Date(), Const.DATE_FORMAT_ALL);
	}
}
