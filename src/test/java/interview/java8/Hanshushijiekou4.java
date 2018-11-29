package interview.java8;

import java.util.function.Predicate;

/** 
* @ClassName: Hanshushijiekou3 
* @Description: 断言型接口
* @author hanghe@hongkunjinfu.com
* @date 2018年5月4日 下午3:37:13 
*  
*/
public class Hanshushijiekou4 {
	public static void main(String[] args) {
		//创建字符串
		String str = "hello world";
		
		//调用
		boolean testSup = testSup(str, (s) -> s.length()>0);
		
		System.out.println(testSup);
	}
	
	/** 
	* @Title: testFun 
	* @Description: 有传入参数，有返回值Boolean
	* 接收一个参数，返回一个Boolean类型值
	* 多用于判断与过滤，使用test()方法执行
	* 如：输入字符串，判断长度是否大于0
	* @param @param str
	* @param @param fun
	* @param @return 
	* @return int 
	* @throws 
	* @date 2018年5月4日下午3:07:16
	*/
	public static boolean testSup(String str, Predicate<String> pre) {
		// 执行
		boolean flag = pre.test(str);
		return flag;
		
	}
}
