package interview.thread;

/**
 * @program: demo
 * @description: 继承Thread类
 * @author: hehang
 * @create: 2018-11-01 13:49
 **/
public class Demo1 {

    public static void main(String[] args){
        new MyThread().start();
        System.out.println("main");
    }
}

class MyThread extends Thread {

    @Override
    public void run() {
        super.run();
        System.out.println("Mythread");
    }
}