package com.eurochemix.webapp.Lesson07;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ilya on 10.03.2016.
 */
public class MainConcurrency {
    static int syncSum;
    static  final AtomicInteger ATOMIC_SUM = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                   synchronized(this){
                       syncSum++;
                   }
                    ATOMIC_SUM.incrementAndGet();
                }
            }).start();

        }
        Thread.sleep(1000);
        System.out.println(syncSum);
        System.out.println(ATOMIC_SUM);


//        new Thread() {
//            @Override
//            public void run() {
//                System.out.println(this.getName());
//            }
//        }.start();


    }


}
