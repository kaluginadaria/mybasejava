package ru.javawebinar.mybase;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();
    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();
    private static final Lock READ_LOCK = reentrantReadWriteLock.readLock();
    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + "," + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
//                    counter++;
                }
            }

        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


        for (int i = 0; i < THREADS_NUMBER; i++) {

            Future<Integer> future = executorService.submit(() ->

            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });

        }

        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(mainConcurrency.counter);

        final String a = "a";
        final String b = "b";
        deadlock(a,b);
        deadlock(b,a);
    }

    private static void deadlock(Object a, Object b){
        new Thread(()->{
            System.out.println("waiting "+a);
            synchronized (a){
                System.out.println("holding "+ a);
                try{
                    Thread.sleep(100);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("Waiting "+b);
                synchronized (b){

                    System.out.println(" reached "+b);
                }
            }
        }).start();
    }

    private void inc() {

        atomicCounter.incrementAndGet();

    }
}