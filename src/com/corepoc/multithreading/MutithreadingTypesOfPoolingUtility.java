package com.corepoc.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MutithreadingTypesOfPoolingUtility {

    public static void main(String[] args) throws InterruptedException {

        //get count of available cores - say 8 core processor
        //for IO / HTTP operations the thread count should be higher - say make it 100
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("coreCount :"+ coreCount);

        //create a pool - the thread pool uses a blocking queue, that is a threadsafe operation
        //fixed Thread Pool
        //ExecutorService service = Executors.newFixedThreadPool(coreCount);

        //cached Thread Pool , if all threads are busy, then create a new thread for the task and place in the pool
        //Life Cycle : If thread is idle for 60 seconds (no task to execute) then kill the thread
        //ExecutorService service = Executors.newCachedThreadPool();

        /*
        //schedule Thread Pool , run after 10 secs
        //for scheduling of task
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        //task to run after 10 seconds delay
        service.schedule(new ThreadPoolingTask(),10, TimeUnit.SECONDS);
        //task to run repeatedly after 10 seconds
        service.scheduleAtFixedRate(new FixedRateSchedulePoolingTask(),30,10, TimeUnit.SECONDS);
        //task to run repeatedly after 10 seconds
        service.scheduleWithFixedDelay(new FixedDelaySchedulePoolingTask(),45,10, TimeUnit.SECONDS);

         */

        //single ThreadPool Executor - recreates the thread if killed because of the task
        //tasks will run sequentially, since there is only one thread
        ExecutorService service = Executors.newSingleThreadExecutor();


        //submit the task for execution
        for (int i=0 ;i< 100; i++) {
            //Thread t1 = new Thread(new ExecutorServiceTask());
            //t1.start();
            service.execute(new SinglePoolingTask());
        }
        System.out.println("Thread Name :"+ Thread.currentThread().getName());
        service.shutdown();//initiate shutdown
        service.isShutdown();//returns true if all tasks are completed
        service.awaitTermination(10,TimeUnit.SECONDS);//block until all tasks are completed
    }

}

class ThreadPoolingTask implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread Task :"+ Thread.currentThread().getName());
    }
}
class FixedRateSchedulePoolingTask implements Runnable{
    @Override
    public void run() {
        System.out.println("FixedRateSchedulePoolingTask Task :"+ Thread.currentThread().getName());
    }
}
class FixedDelaySchedulePoolingTask implements Runnable{
    @Override
    public void run() {
        System.out.println("FixedDelaySchedulePoolingTask Task :"+ Thread.currentThread().getName());
    }
}
class SinglePoolingTask implements Runnable{
    @Override
    public void run() {
        System.out.println("SinglePoolingTask Task :"+ Thread.currentThread().getName());
    }
}
