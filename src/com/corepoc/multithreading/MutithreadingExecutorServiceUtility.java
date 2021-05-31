package com.corepoc.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutithreadingExecutorServiceUtility {

    public static void main(String[] args) {

        //create a pool - the thread pool uses a blocking queue, that is a threadsafe operation
        ExecutorService service = Executors.newFixedThreadPool(10);

        //submit the task for execution
        for (int i=0 ;i< 100; i++) {
            //Thread t1 = new Thread(new ExecutorServiceTask());
            //t1.start();
            service.execute(new ExecutorServiceTask());
        }
        System.out.println("Thread Name :"+ Thread.currentThread().getName());
    }

}

class ExecutorServiceTask implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread Task :"+ Thread.currentThread().getName());
    }
}
