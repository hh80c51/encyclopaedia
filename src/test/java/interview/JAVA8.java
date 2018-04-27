package interview;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

/** 
* @ClassName: JAVA8 
* @Description: JAVA8特性---Lambda表达式
* @author hanghe@hongkunjinfu.com
* @date 2018年4月26日 下午4:13:22 
*  
*/
public class JAVA8 {
	
	static FunctionalDefaultMethodsImpl functionalDefaultMethods = new FunctionalDefaultMethodsImpl();
	/** 
	* @Title: LambdaTest1 
	* @Description: 一个Lambda可以由逗号分隔的参数列表、->符号、函数体三部分表示
	* @param  
	* @return void 
	* @throws 
	* @date 2018年4月26日下午4:20:01
	*/
	public static void LambdaTest1() {
//		Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
//		// 也可以直接指定参数类型
//		Arrays.asList( "a", "b", "d" ).forEach( (String e) -> System.out.println( e ) );
//		// 函数体可以更复杂
//		Arrays.asList("a","b","d").forEach(e -> {
//			System.out.println(e);
//			System.out.println(e);
//		});
//		// 函数体引用变量,变量会被隐含的转化为final
//		String separator = ",";
//		Arrays.asList("a","b","d").forEach(e -> {
//			System.out.println(e + separator);
//		});
//		// Lambda推测返回值
//		Arrays.asList("a","b","d").sort((e1,e2) -> e1.compareTo(e2));
	}
	
	private interface Defaulable {
	    // Interfaces now allow default methods, the implementer may or 
	    // may not implement (override) them.
	    default String notRequired() { 
	        return "Default implementation"; 
	    }        
	}
	         
	private static class DefaultableImpl implements Defaulable {
	}
	     
	private static class OverridableImpl implements Defaulable {
	    @Override
	    public String notRequired() {
	        System.out.println("Overridden implementation");
			return "Overridden implementation";
	    }
	}	
	
	private interface DefaulableFactory {
	    // Interfaces now allow static methods
	    static Defaulable create( Supplier< Defaulable > supplier ) {
	        return supplier.get();
	    }
	}
	
	public static void main(String[] args) {
//		LambdaTest1();
//		functionalDefaultMethods.method();
//		functionalDefaultMethods.defaultMethod();
//		new OverridableImpl().notRequired();
		
//		Function<Double, Double> sqrt = new Function<Double, Double>() {
//		    public Double apply(Double input) {
//		        return Math.sqrt(input);
//		    }
//		};
//		System.out.println(sqrt.apply(4.0));//2.0
		
		// 返回布尔类型
//		Predicate<Double> sqrt = new Predicate<Double>() {
//
//			@Override
//			public boolean test(Double t) {
//				System.out.println(t);
//				return t>100;
//			}
//		};
//		System.out.println(sqrt.test(101.0));
		
		//Supplier< T>接口没有入参，返回一个T类型的对象，类似工厂方法。
		Supplier<Double> sqrt = () -> 100.0;
		System.out.println(sqrt.get());
	}
}
