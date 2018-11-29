package interview.thread;

/**
 * @program: demo
 * @description: 脏读
 * 当A线程调用加synchronized的X方法时，A线程就获得了X方法的锁，准确的说，获得了对象的锁，
 * B线程必须等A执行完毕才能执行X方法，但此时B可以调用其他非synchronized方法。
 * 而B线程如果调用了其他synchronized方法时，必须等A线程将X方法执行完，也就是释放对象锁后才能使用。
 * @author: hehang
 * @create: 2018-11-06 15:49
 **/
public class Demo9 {
    public static void main(String[] args) {
        try {
            PublicVar publicVar = new PublicVar();
            ThreadA threadA = new ThreadA(publicVar);
            threadA.start();
            ThreadA.sleep(200); //打印结果受此值大小影响
            publicVar.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PublicVar {
    public String username = "A";
    public String password = "AA";
    synchronized public void setValue(String username, String password) {
        try {
            this.username =  username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method thread name="
                    + Thread.currentThread().getName() + " username="
                    + username + " password=" + password);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getValue(){
        System.out.println("getValue method thread name="
            + Thread.currentThread().getName() + " username=" + username
            + " password=" + password);
    }
}

class ThreadA extends Thread {
    private PublicVar publicVar;

    public ThreadA(PublicVar publicVar){
        super();
        this.publicVar = publicVar;
    }

    @Override
    public void run() {
        super.run();
        publicVar.setValue("B", "BB");
    }
}