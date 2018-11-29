package interview.thread;

/**
 * @program: demo
 * @description: 出现异常，锁自动释放
 * @author: hehang
 * @create: 2018-11-07 10:16
 **/
public class Demo10 {
    public static void main(String[] args){
        try {
            Service service = new Service();
            ThreadAA threadAA = new ThreadAA(service);
            threadAA.setName("a");
            threadAA.start();
            Thread.sleep(500);
            ThreadBB threadBB = new ThreadBB(service);
            threadBB.setName("b");
            threadBB.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Service {
    synchronized public void testMethod() {
        if(Thread.currentThread().getName().equals("a")) {
            System.out.println("ThreadName=" + Thread.currentThread().getName()
                + " run beginTime=" + System.currentTimeMillis());
            int i = 1;
            while (i == 1) {
                if(("" + Math.random()).substring(0, 8).equals("0.123456")) {
                    System.out.println("ThreadName="
                        + Thread.currentThread().getName()
                        + " run exceptionTime="
                        + System.currentTimeMillis());
                    Integer.parseInt("a");
                }
            }
        }else {
            System.out.println("Thread B run Time="
                    + System.currentTimeMillis());
        }
    }
}

class ThreadAA extends Thread {
    private Service service;

    public ThreadAA(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.testMethod();
    }
}

class ThreadBB extends Thread {
    private Service service;

    public ThreadBB(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.testMethod();
    }
}
