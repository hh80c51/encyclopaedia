package com.athongkun.utils;

import com.athongkun.utils.date.DateUtils;

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

	/**
	 * @Description : 获取当前时间str，格式yyyyMMddHHmmss
	 * @Method_Name : getCurrentDateTimeStr;
	 * @return
	 * @return : String;
	 * @Creation Date : 2017年11月1日 上午10:31:54;
	 * @Author : yanbinghuang@hongkun.com.cn 黄艳兵;
	 */
	public static String getCurrentDateTimeStr() {
		return DateUtils.format(new Date(), DateUtils.DATE_HHMMSS);
	}
}
