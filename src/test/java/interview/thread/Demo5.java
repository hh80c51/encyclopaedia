package interview.thread;

/**
 * @program: demo
 * @description: 非线程安全，加synchronized后线程安全
 * println是线程不安全的
 * @author: hehang
 * @create: 2018-11-01 16:05
 **/
public class Demo5 {
    public static void main(String[] args){
        ALogin a = new ALogin();
        a.start();
        BLogin b = new BLogin();
        b.start();
    }
}

class LoginServlet{
    private static String usernameRef;
    private static String passwdRef;

    synchronized protected static void doPost(String username, String passwd) {
        try {
            usernameRef = username;
            if(username.equals("a")){
                Thread.sleep(5000);
            }
            passwdRef = passwd;
            System.out.println("username = "+usernameRef + " passwod = " + passwd);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class ALogin extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("a","aa");
    }
}

class BLogin extends Thread {
    @Override
    public void run() {
        LoginServlet.doPost("b","bb");
    }
}