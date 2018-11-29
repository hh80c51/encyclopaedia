package interview.thread;

/**
 * @program: demo
 * @description: 守护进程
 * 当进程中不存在非守护进程时，守护进程自动销毁。
 * 如：main线程结束，守护线程也随之结束。
 * @author: hehang
 * @create: 2018-11-06 11:49
 **/
public class Demo8 {
    public static void main(String[] args){
        try {
            MyThread8 thread = new MyThread8();
            thread.setDaemon(true);
            thread.start();
            Thread.sleep(5000);
            System.out.println("我离开thread对象也不再打印了，也就是停止了！");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class MyThread8 extends Thread {
    private int i = 0;

    @Override
    public void run() {
        try {
            while (true) {
                i++;
                System.out.println("i=" + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}