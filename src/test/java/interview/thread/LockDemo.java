package interview.thread;

import com.athongkun.utils.SleepUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @program: demo
 * @description: 自定义同步组件
 * @author: hehang
 * @create: 2019-03-26 11:06
 **/
public class LockDemo {
    @Test
    public void test() {
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            @Override
            public void run() {
                while(true){
                    lock.lock();
                    try{
                        SleepUtils.second(1);
                        System.out.println(Thread.currentThread().getName());
                        SleepUtils.second(1);
                    }finally {
                        lock.unlock();
                    }
                }
            }
        }
        //启动10个线程
        for (int i = 0; i < 10; i++){
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        //每隔一秒换行
        for(int i = 0; i < 10; i++){
            SleepUtils.second(1);
            System.out.println();
        }

    }
}

class TwinsLock implements Lock {
    private final Sync sync = new Sync(2);
    private static final class Sync extends AbstractQueuedSynchronizer {
        Sync(int count) {
            if(count <= 0){
                throw new IllegalArgumentException("count must large than zero.");
            }
            setState(count);
        }
        public int tryAcquireShared(int reduceCount) {
            for (;;){
                int current = getState();
                int newCount = current - reduceCount;
                if(newCount < 0 || compareAndSetState(current,newCount)) {
                    return newCount;
                }
            }
        }
        public boolean tryReleaseShared(int returnCount){
            for(;;){
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }
    public void lock(){
        sync.acquireShared(1);
    }
    public void unlock(){
        sync.releaseShared(1);
    }

    @Override
    public boolean tryLock() {      //尝试非阻塞的获取锁，调用该方法后立刻返回，如果能够后去返回true，否则返回false
        return false;
    }

    /**
     * 超时的获取锁，当前线程在以下3种情况会返回：
     * ①当前线程在超时时间内获得了锁
     * ②当前线程在超时时间内被中断
     * ③超时时间结束，返回false
     **/
    @Override
    public boolean tryLock(long time, TimeUnit unit) {
        return false;
    }

    @Override
    public void lockInterruptibly() {

    }

    @Override
    public Condition newCondition() {       //获取等待通知组件
        return null;
    }
}
