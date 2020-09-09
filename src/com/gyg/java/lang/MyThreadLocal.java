package com.gyg.java.lang;

import java.util.concurrent.TimeUnit;

/**
 * @auther 郭永钢
 * @data 2020/8/27 0:37
 * @desc:
 */
class Person {
    String name = "zs";
}

public class MyThreadLocal {
    volatile static Person person = new Person();

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName()+person.name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                person.name = "ls";
                System.out.println(Thread.currentThread().getName()+person.name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
    }
}
