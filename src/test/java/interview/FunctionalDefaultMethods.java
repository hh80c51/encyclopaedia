package interview;

/** 
* @ClassName: FunctionalDefaultMethods 
* @Description: 函数式接口，该接口只能添加一个方法
* @author hanghe@hongkunjinfu.com
* @date 2018年4月26日 下午5:03:52 
*  
*/
@FunctionalInterface
public interface FunctionalDefaultMethods {
    void method();
         
    default void defaultMethod() {
    	System.out.println("我是接口");
    }       
    
}