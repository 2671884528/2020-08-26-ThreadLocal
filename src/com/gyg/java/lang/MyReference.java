package com.gyg.java.lang;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther 郭永钢
 * @data 2020/8/26 23:01
 * @desc: 这个类提供线程局部变量。 这些变量与其正常的对应方式不同，
 * 因为访问一个的每个线程（通过其get或set方法）都有自己独立初始化的变量副本。
 * ThreadLocal实例通常是希望将状态与线程关联的类中的私有静态字段（例如，用户ID或事务ID）。
 */

public class MyReference {
    private  static List LIST= new ArrayList();
    private static ReferenceQueue<Object> QUEUE = new ReferenceQueue<>();
    public static void main(String[] args) throws IOException, InterruptedException {

        /*1.引用的种类-强引用（不会被回收，内存不够会抛出OOM）*/
//        Object o = new Object();
//        o = null;
//
//        System.gc();
//        System.in.read();
        /*####################*/

        /*2.软引用(会被回收，当内存不够的时候)*/
//        SoftReference<byte[]> reference = new SoftReference<>(new byte[1024 * 1024 * 10]);
//        System.out.println(reference.get());
//        System.gc();
//        Thread.sleep(500);
//        System.out.println(reference.get());
//        byte[] bytes = new byte[1024 * 1024 * 15];
//        System.out.println(reference.get());
        /*########################*/

        /*3:弱引用(只要遇见GC就会被回收)*/
//        WeakReference<byte[]> weakReference = new WeakReference<>(new byte[1024 * 1024 * 10]);
//        System.out.println(weakReference.get());
//        System.gc();
//        System.out.println(weakReference.get());
//        Thread.sleep(500);
//        System.out.println(weakReference.get());
        /*###############################*/

        /*4.虚引用（对象就不存在）*/
        PhantomReference<byte[]> phantomReference =
                new PhantomReference<>(new byte[1024 * 1024 * 10], QUEUE);
        new Thread(() -> {
            while (true) {
                if (QUEUE.poll() != null) {
                    System.out.println("虚拟对象被JVM回收");
                }
            }
        }, "listenRefObj").start();

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024*1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());

            }
        }).start();
        Thread.sleep(500);
    }
}
