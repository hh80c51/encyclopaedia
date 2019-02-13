package interview.thread;

/**
 * @program: demo
 * @description: 停止线程
 * 通过结果可以看出interrupted()方法具有清除中断状态的功能，
 * @author: hehang
 * @create: 2018-11-02 11:36
 **/
public class Demo7 {
    public static void main(String[] args){
        Thread.currentThread().interrupt();
        System.out.println("是否停止1？ = " + Thread.interrupted());
        System.out.println("是否停止1？ = " + Thread.interrupted());
        System.out.println("是否停止1？ = " + Thread.interrupted());
        System.out.println("是否停止1？ = " + Thread.interrupted());
        System.out.println("end!");
    }
}
