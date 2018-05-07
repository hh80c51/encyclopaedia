package interview;

import java.util.function.Consumer;

import com.google.common.base.Supplier;

/** 
* @ClassName: Hanshushijiekou3 
* @Description: 供给型接口
* @author hanghe@hongkunjinfu.com
* @date 2018年5月4日 下午3:37:13 
*  
*/
public class Hanshushijiekou3 {
	public static void main(String[] args) {
		//创建字符串
		String str = "hello world";
		
		//调用
		String sup = testSup(() -> str);
		System.out.println(sup);
		
	}
	
	/** 
	* @Title: testFun 
	* @Description: 无传入参数，有返回值
	* 该接口对应的方法类型不接受参数，但是提供一个返回值
	* 使用get()方法获得这个返回值
	* @param @param str
	* @param @param fun
	* @param @return 
	* @return int 
	* @throws 
	* @date 2018年5月4日下午3:07:16
	*/
	public static String testSup(Supplier<String> sup) {
		// 执行
		String s = sup.get();
		return s;
		
	}
}
