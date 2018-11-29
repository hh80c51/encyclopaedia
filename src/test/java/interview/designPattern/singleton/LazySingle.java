package interview.designPattern.singleton;

/**
 * @program: demo
 * @description: 不加锁懒汉模式
 * 不加锁懒汉式与饿汉式的区别：
 * 1.懒汉式存在线程安全问题。
 * 2.饿汉在类加载时创建对象，懒汉在方法调用时创建对象
 * @author: hehang
 * @create: 2018-11-05 16:44
 **/
public class LazySingle {
    private static LazySingle newInstance = null;
    private LazySingle(){

    }
    //存在线程安全问题
/*    public static LazySingle getNewInstance(){
        if(newInstance == null){
            newInstance = new LazySingle();
        }
        return newInstance;
    }*/
    //加锁
    /*为何要加锁？一般来说大多数情况不加锁并没有任何问题，但是在多线程并发执行的时候就很容易出现安全隐患，
    第一个线程在判断newInstance==null时，还没有new出实例时，第二个线程也进来，判断的newInstance也是null，然后也会new出实例，
    这就不是我们想要的单例模式了，所以就需要加锁，使用synchronized关键字，加锁更能解决安全问题，
    但加锁同时也会出现一个问题，那就是每次都需要判断锁，这样性能就会降低，
    所以为了提高性能，我们应该尽量减少锁判断的次数，加上双重判断，也就是上图代码，
    个人推荐选择饿汉模式，直白点就是简单，安全。*/
    public static LazySingle getNewInstance(){
        if(newInstance == null){
            synchronized(LazySingle.class){
                if(newInstance == null){
                    newInstance = new LazySingle();
                }
            }
        }
        return newInstance;
    }
}
