package interview.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: demo
 * @description: 公平锁实例
 * 公平锁的执行顺序跟线程的启动顺序保持一致。非公平锁会抢占前面线程的锁，使线程执行顺序和启动顺序不一致.
 * 公平锁和非公平锁释放时，最后都要写一个volatile变量state。
·* 公平锁获取时，首先会去读volatile变量。
·* 非公平锁获取时，首先会用CAS更新volatile变量，这个操作同时具有volatile读和volatile写的内存语义。
 * @author: hehang
 * @create: 2019-01-11 10:15
 **/
public class MyFairLock {
    /**
     * true表示 ReentrantLock 的公平锁
     */
    ReentrantLock lock = new ReentrantLock(false);

    public void testFail(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"获得了锁");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        MyFairLock fairLock = new MyFairLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName()+"启动");
            fairLock.testFail();
        };

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(runnable));
            threadList.get(i).start();
        }
    }
}
