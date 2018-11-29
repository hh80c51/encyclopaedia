package interview.java8;

import java.util.Objects;

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

	@FunctionalInterface
    public static interface Function<T, R> {

        //必须实现apply()方法
        R apply(T t);

        /**
        * @Title: compose
        * @Description: TODO(这里用一句话描述这个方法的作用)
        * @param @param before V表示包括V在内的任何V的父类，T表示包括T在内的任何T的子类
        * @param @return
        * @return Function<V,R>
        * @throws
        * @date 2018年4月26日下午5:44:09
        */
        default <V> Function<V,R> compose(Function<? super V, ? extends T> before){
            Objects.requireNonNull(before);	//判空
            return (V v) -> apply(before.apply(v));
        }

        default <V> Function<T,V> andThen(Function<? super R, ? extends V> after){
            Objects.requireNonNull(after);
            return (T t) -> after.apply(apply(t));
        }

        static <T> Function<T,T> identity(){
            return t -> t;
        }
    }
}
