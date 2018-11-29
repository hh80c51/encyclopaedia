package interview.thread;

/**
 * @program: demo
 * @description: 停止线程
 * @author: hehang
 * @create: 2018-11-02 10:52
 **/
public class Demo6 {
    public static void main(String[] args){
        try{
            MyThread6 thread = new MyThread6();
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
            System.out.println("是否停止1？ = " + thread.isInterrupted());
            System.out.println("是否停止1？ = " + thread.isInterrupted());
            System.out.println("是否停止1？ = " + thread.interrupted());
            System.out.println("是否停止2？ = " + thread.interrupted());
        }catch (InterruptedException e){
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");

    }
}

class MyThread6 extends Thread{
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5000; i++) {
            System.out.println("i=" + (i + 1));
        }
    }
}
