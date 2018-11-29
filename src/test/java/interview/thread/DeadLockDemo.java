package interview.thread;

/**
 * @program: demo
 * @description: 死锁: A线程和B线程互相等待对方释放锁
 * 场景：
 * 1.t1拿到锁之后，因为一些异常情况没有释放锁（死循环）。
 * 2.又或者t1拿到一个数据库锁，释放锁的时候抛出了异常，没释放掉。
 * 避免死锁的常用方法：
 * 1.避免一个线程同时获取多个锁
 * 2.避免一个线程在锁内同时占用多个资源，尽量每个锁只占用一个资源。
 * 3.尝试使用定时锁，使用lock.tryLock(timeout)来替代使用内部锁机制。
 * 4.对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况。
 *
 * @author: hehang
 * @create: 2018-11-07 14:25
 **/
public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";
    public static void main(String[] args) {
        new DeadLockDemo().deadLock();
    }
    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
