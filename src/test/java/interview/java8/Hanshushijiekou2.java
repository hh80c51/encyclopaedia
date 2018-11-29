package interview.java8;

import java.util.function.Consumer;

/** 
* @ClassName: Hanshushijiekou2 
* @Description: 消费型接口 
* @author hanghe@hongkunjinfu.com
* @date 2018年5月4日 下午3:36:39 
*  
*/
public class Hanshushijiekou2 {
	public static void main(String[] args) {
		//创建字符串
		String str = "hello world";
		
		//调用
		testCon(str,(s) -> System.out.println(s));
	}
	
	/** 
	* @Title: testFun 
	* @Description: 有输入参数，没有返回值
	* 一般来说使用Consumer接口往往伴随着一些期望状态的改变，或者事件的发生
	* 典型的forEach就是使用的Consumer接口
	* @param @param str
	* @param @param fun
	* @param @return 
	* @return int 
	* @throws 
	* @date 2018年5月4日下午3:07:16
	*/
	public static void testCon(String str,Consumer<String> con) {
		//执行
		con.accept(str);
		
	}
}
