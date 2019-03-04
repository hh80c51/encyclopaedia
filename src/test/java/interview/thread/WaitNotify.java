package interview.thread;

import com.athongkun.utils.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description: 等待/通知机制
    1）使用wait()、notify()和notifyAll()时需要先对调用对象加锁。即synchronized (lock)
    2）调用wait()方法后，线程状态由RUNNING变为WAITING，并将当前线程放置到对象的等待队列。此时释放了锁
    3）notify()或notifyAll()方法调用后，等待线程依旧不会从wait()返回，需要调用notify()或
notifAll()的线程释放锁之后，等待线程才有机会从wait()返回。
    4）notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而notifyAll()
方法则是将等待队列中所有的线程全部移到同步队列，被移动的线程状态由WAITING变为
BLOCKED。
    5）从wait()方法返回的前提是获得了调用对象的锁。

等待/通知经典范式
等待方：
synchronized(对象) {
    while(条件不满足){

    }
    对应的处理逻辑
}
通知方：
synchronized(对象) {
    改变条件
    对象.notifyAll();
}

 * @author: hehang
 * @create: 2019-02-14 10:03
 **/
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws Exception {
        Thread waitThread = new Thread(new Wait(),"WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(),"NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock) {
                //当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. waiting @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        //wait会释放锁
                        lock.wait();
                    } catch (InterruptedException e) {

                    }
                }

                //条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false.running @ "
                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        public void run() {
            //加锁，拥有lock的Monitor
            synchronized (lock) {
                //获取lock的锁，然后进行通知，通知时不会释放lock的锁
                //直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock.notify @ "
                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                //notify并不释放锁，而是通知wait的线程可以抢锁了
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            //再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ "
                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }
}
