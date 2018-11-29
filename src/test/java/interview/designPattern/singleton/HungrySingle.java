package interview.designPattern.singleton;

/**
 * @program: demo
 * @description: 饿汉模式
 * @author: hehang
 * @create: 2018-11-05 16:41
 **/
public class HungrySingle {
    private static final HungrySingle newInstance = new HungrySingle();
    private HungrySingle(){

    }
    public static HungrySingle getNewInstance() {
        return newInstance;
    }
}
