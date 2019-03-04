package com.athongkun.utils;

import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description: 休眠工具
 * @author: hehang
 * @create: 2019-02-14 10:28
 **/
public class SleepUtils {
    public static final void second(long sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (Exception e) {

        }
    }
}
