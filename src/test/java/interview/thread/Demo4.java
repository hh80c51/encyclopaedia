package interview.thread;

/**
 * @program: demo
 * @description: 数据共享
 * 出现了“线程不安全”的问题
 * @author: hehang
 * @create: 2018-11-01 15:28
 **/
public class Demo4 {
    public static void main(String[] args){
        Thread thread = new MyThread4();
        Thread a = new Thread(thread, "A");
        Thread b = new Thread(thread, "B");
        Thread c = new Thread(thread, "C");
        a.start();
        b.start();
        c.start();
    }
}

class MyThread4 extends Thread{
    private static int count = 5;

/*    @Override
    public void run() {
        super.run();
        while(count > 0){
            count--;
            //此示例不要用for语句，因为使用同步后其他线程就得不到运行的机会了。
            System.out.println(Thread.currentThread().getName() + "计算出的数据：count = " + count);
        }
    }*/

    /**
     * synchronized 可以在任意对象及方法上加锁，而加锁的这段代码称为“互斥区”或“临界区”
    */
    @Override
    synchronized public void run() {
        super.run();
        while(count > 0){
            count--;
            //此示例不要用for语句，因为使用同步后其他线程就得不到运行的机会了。
            System.out.println(Thread.currentThread().getName() + "计算出的数据：count = " + count);
        }
    }
}