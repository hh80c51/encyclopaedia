package interview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/** 
* @ClassName: Annotations 
* @Description: 拓宽注解的应用场景
* 注解几乎可以使用在任何元素上：局部变量、接口类型、超类和接口实现类，甚至可以用在函数的异常定义上。
* @author hanghe@hongkunjinfu.com
* @date 2018年5月3日 下午4:39:37 
*  
*/
public class Annotations {
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE_USE, ElementType.PARAMETER})	//ElementType.TYPE_USER和ElementType.TYPE_PARAMETER是Java 8新增的两个注解，用于描述注解的使用场景。
	public @interface NonEmpty {
		
	}
	
	public static class Holder<@NonEmpty T> extends @NonEmpty Object {//注解使用在接口类型上，注解使用在超类上
		public void method() throws @NonEmpty Exception {		//注解应用在异常上
			
		}
	}
	
	public static void main(String[] args) {
		final Holder<String> holder = new @NonEmpty Holder<String>();
		@NonEmpty Collection<@NonEmpty String> strings = new ArrayList<>();
	}
}
