package com.athongkun.controller.common;
import java.util.HashMap;
import java.util.Map;

import com.athongkun.utils.Const;


public class BaseController {
	
	private ThreadLocal<Map<String, Object>> results = new ThreadLocal<Map<String, Object>>();
	
	protected void start() {
		// ThreadLocal 可以在线程中开辟内存空间，存储数据，这个数据可以在线程的运行过程的任何地方共享数据
		results.set(new HashMap<String, Object>());
	}
	
	protected Object end() {
		Object obj = results.get();
		results.remove();
		return obj;
	}
	
	protected void param( String key, Object value ) {
		Map<String, Object> resultMap = results.get();
		resultMap.put(key, value);
	}
	
	protected void success() {
		Map<String, Object> resultMap = results.get();
		resultMap.put(Const.RESULT_OUT_SUCCESS, Const.RESULT_SUCCESS);
	}
	protected void fail() {
		Map<String, Object> resultMap = results.get();
		resultMap.put(Const.RESULT_OUT_SUCCESS, Const.RESULT_FAIL);
	}
}
