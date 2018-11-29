package interview.thread;

/**
 * @program: demo
 * @description: 实现Runnable接口
 * @author: hehang
 * @create: 2018-11-01 14:10
 **/
public class Demo2 {
    public static void main(String[] args){
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable,"MyRunnable");
        thread.start();
        System.out.println(Thread.currentThread().getName() + " is processing");
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is processing");
    }
}