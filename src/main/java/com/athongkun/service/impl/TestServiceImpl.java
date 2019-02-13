package com.athongkun.service.impl;

import com.athongkun.dao.TestDao;
import com.athongkun.service.TestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @program: demo
 * @description: 测试接口实现类
 * @author: hehang
 * @create: 2018-07-10 14:30
 **/
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    public int selectCount(){
        int num = testDao.select_test();
        System.out.println(num);
        return num;
    }

    @Test
    public void testSelectCount(){
        int num  = this.selectCount();
        System.out.println(num);
    }

    @Test
    public void testCAS(){
        AtomicReferenceFieldUpdater updater=AtomicReferenceFieldUpdater.newUpdater(Dog.class,String.class,"name");
        Dog dog1=new Dog();
        updater.compareAndSet(dog1,dog1.name,"test") ;
        System.out.println(dog1.name);
    }
}

class Dog
{
    volatile  String name="dog1";

}
