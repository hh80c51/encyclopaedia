package interview.thread;

/**
 * @program: demo
 * @description: 数据不共享
 * 每个线程都有各自的count变量，自己减少自己的count变量的值。
 * @author: hehang
 * @create: 2018-11-01 15:19
 **/
public class Demo3 {
    public static void main(String[] args){
        Thread a = new MyThread3("A");
        Thread b = new MyThread3("B");
        Thread c = new MyThread3("C");
        a.start();
        b.start();
        c.start();
    }
}

class MyThread3 extends Thread {
    private int count = 5;
    public MyThread3(String name){
        super();
        this.setName(name); //设置线程名称
    }

    @Override
    public void run() {
        super.run();
        while (count > 0){
            count--;
            System.out.println(Thread.currentThread().getName() + "计算出的数据：count = "+ count);
        }
    }
}
