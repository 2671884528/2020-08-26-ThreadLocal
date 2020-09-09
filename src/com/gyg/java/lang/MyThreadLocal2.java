package com.gyg.java.lang;

import java.util.concurrent.TimeUnit;

/**
 * @auther 郭永钢
 * @data 2020/8/27 0:37
 * @desc:
 */
class Person2 {
    String name = "zs";
}

public class MyThreadLocal2 {
//    volatile static Person2 person = new Person2();
    //属于线程的属性
    static ThreadLocal<Person2> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                Person2 person2 = new Person2();
                threadLocal.set(person2);
                System.out.println(Thread.currentThread().getName()+person2.name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        //使用完成，一定要remove，防止内存泄漏
        threadLocal.remove();
    }
}
