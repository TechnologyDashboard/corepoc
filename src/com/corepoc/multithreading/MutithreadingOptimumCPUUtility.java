package com.corepoc.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MutithreadingOptimumCPUUtility {

    public static void main(String[] args) {

        //get count of available cores - say 8 core processor
        //for IO / HTTP operations the thread count should be higher - say make it 100
        int coreCount = Runtime.getRuntime().availableProcessors();
        System.out.println("coreCount :"+ coreCount);

        //create a pool - the thread pool uses a blocking queue, that is a threadsafe operation
        ExecutorService service = Executors.newFixedThreadPool(coreCount);

        //submit the task for execution
        for (int i=0 ;i< 100; i++) {
            //Thread t1 = new Thread(new ExecutorServiceTask());
            //t1.start();
            service.execute(new OptimumCPUTask());
        }
        System.out.println("Thread Name :"+ Thread.currentThread().getName());
    }

}

class OptimumCPUTask implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread Task :"+ Thread.currentThread().getName());
    }
}
