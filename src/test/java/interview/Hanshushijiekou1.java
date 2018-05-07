package interview;

/** 
* @ClassName: Hanshushijiekou1 
* @Description: 功能型接口
* @author hanghe@hongkunjinfu.com
* @date 2018年5月4日 下午3:36:59 
*  
*/
public class Hanshushijiekou1 {
	public static void main(String[] args) {
		//定义字符串
		String str = "helloworld";
		
		//调用方法
		//在调用的时候写方法体，方法比较灵活
		int length = testFun(str,(s) -> s.length());
		
		System.out.println(length);
	}
	
	/** 
	* @Title: testFun 
	* @Description: 接收一个T类型参数，返回R类型的结果的方法的抽象，通过调用apply方法执行内容
	* @param @param str
	* @param @param fun
	* @param @return 
	* @return int 
	* @throws 
	* @date 2018年5月4日下午3:07:16
	*/
	public static int testFun(String str,Function<String,Integer> fun) {
		//执行
		Integer length = fun.apply(str);
		
		return length;
	}
}
