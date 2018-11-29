package interview.java8;

import java.util.Optional;

/** 
* @ClassName: GuavaTest 
* @Description: 在Java 8之前，Google Guava引入了Optionals类来解决NullPointerException，
* 从而避免源码被各种null检查污染，以便开发者写出更加整洁的代码。Java 8也将Optional加入了官方库。
* @author hanghe@hongkunjinfu.com
* @date 2018年5月3日 下午4:52:07 
*  
*/
public class OptionalTest {
	public static void main(String[] args) {
		
/*		如果Optional实例持有一个非空值，则isPresent()方法返回true，否则返回false；
 * 		orElseGet()方法，Optional实例持有null，则可以接受一个lambda表达式生成的默认值；
 * 		map()方法可以将现有的Opetional实例的值转换成新的值；
 * 		orElse()方法与orElseGet()方法类似，但是在持有null的时候返回传入的默认值。
*/		Optional< String > fullName = Optional.ofNullable( null );
		System.out.println( "Full Name is set? " + fullName.isPresent() );        
		System.out.println( "Full Name: " + fullName.orElseGet( () -> "[none]" ) ); 
		System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
		
		
		Optional< String > firstName = Optional.of( null );
		System.out.println( "First Name is set? " + firstName.isPresent() );        
		System.out.println( "First Name: " + firstName.orElseGet( () -> "[none]" ) ); 
		System.out.println( firstName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
		System.out.println();
		
	}
}
