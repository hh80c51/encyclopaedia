package interview;

import java.util.Objects;

@FunctionalInterface
public interface Function<T, R> {

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
	default <V> Function<V,R> compose(Function<? super V,? extends T> before){
		Objects.requireNonNull(before);	//判空
		return (V v) -> apply(before.apply(v));
	}
	
	default <V> Function<T,V> andThen(Function<? super R,?extends V> after){
		Objects.requireNonNull(after);
		return (T t) -> after.apply(apply(t));
	}
	
	static <T> Function<T,T> identity(){
		return t -> t;
	}
}
