package com.corepoc.multithreadingByCompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class MutithreadingByCompletableFutureUtility {

    private static ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        //create a pool - the thread pool uses a blocking queue, that is a threadsafe operation
        //ExecutorService service = Executors.newFixedThreadPool(10);

        for(int i=0;i<10;i++){
            //submit the task for execution
           CompletableFuture.supplyAsync(() -> getOrder())
                   .thenApply(order -> enrichOrder(order))
                   .thenApply(order -> performPayment(order))
                   .thenApply(order -> dispatchOrder(order))
                   .thenAccept(order -> sendEmail(order));

        }

        ExecutorService cpuBound = Executors.newFixedThreadPool(8);
        ExecutorService ioBound = Executors.newCachedThreadPool();

        //main thread will run for 20 times and will never be blocked
        for(int i=0;i<20;i++){
            //submit the task for execution
            //same thread to run 2 separate executorService operation thenApplyAsync is used as overloaded method
            CompletableFuture.supplyAsync(() -> getOrder(),ioBound)
                    .thenApplyAsync(order -> enrichOrder(order),cpuBound)//same thread to run 2 separate executorService operations
                    .thenApply(order -> performPayment(order))
                    .thenApply(order -> dispatchOrder(order))
                    .thenAccept(order -> sendEmail(order));

        }

        //internally it uses the ForkJoinPool which is an AsyncPool default pool
        //how to handle exception in
        //if it a failed order then handle in performPayment, if normal order then proceed normally in performPayment,dispatchOrder,sendEmail
        for(int i=0;i<30;i++){
            //submit the task for execution
            CompletableFuture.supplyAsync(() -> getOrder())
                    .thenApplyAsync(order -> enrichOrder(order))
                    //.exceptionally(e -> new FailedOrder(e)) //if it a failed order then handle in performPayment, if normal order then performPayment,dispatchOrder,sendEmail
                    .thenApply(order -> performPayment(order))
                    .thenApply(order -> dispatchOrder(order))
                    .thenAccept(order -> sendEmail(order));

        }



        System.out.println("Thread Name :"+ Thread.currentThread().getName());
    }


    public static Integer getOrder(){
        //System.out.println("getOrder Task :"+ Thread.currentThread().getName()+ " method entry");
        Future<Integer> orderFuture=service.submit(new CompletableOrderTask());
        Integer newInt=0;
        try {
            newInt =orderFuture.get();
            Thread.sleep(3000);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        //Integer newInt =new Random().nextInt();
        //System.out.println("getOrder Task :"+ Thread.currentThread().getName()+ " generated Integer : "+newInt);
        return newInt;
    }
    public static Integer enrichOrder(Integer order){
        service.submit(new CompletableEnrichTask(order));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Integer newInt =new Random().nextInt();
        System.out.println("enrichOrder Task :"+ Thread.currentThread().getName()+ " generated Integer : "+order);
        return order;
    }
    public static Integer performPayment(Integer order){
        service.submit(new CompletablePerformPayTask(order));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Integer newInt =new Random().nextInt();
        System.out.println("performPayment Task :"+ Thread.currentThread().getName()+ " generated Integer : "+order);
        return order;
    }
    public static Integer dispatchOrder(Integer order){
        service.submit(new CompletableDispatchTask(order));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Integer newInt =new Random().nextInt();
        System.out.println("dispatchOrder Task :"+ Thread.currentThread().getName()+ " generated Integer : "+order);
        return order;
    }
    public static Integer sendEmail(Integer order){
        service.submit(new CompletableSendEmailTask(order));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Integer newInt =new Random().nextInt();
        System.out.println("sendEmail Task :"+ Thread.currentThread().getName()+ " generated Integer : "+order);
        return order;
    }
}
class CompletableOrderTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {

        Thread.sleep(3000);
        Integer newInt =new Random().nextInt();
        System.out.println("CompletableOrderTask Task :"+ Thread.currentThread().getName()+ " generated Integer : "+newInt);
        return newInt;
    }
}
class CompletableEnrichTask implements Callable<Integer> {

    private int order1;

    public CompletableEnrichTask(Integer order) {
        try {
            this.order1=order;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        System.out.println("CompletableEnrichTask Task :"+ Thread.currentThread().getName()+ " return Same Order : "+this.order1);
        return this.order1;
    }
}
class CompletablePerformPayTask implements Callable<Integer> {

    private int order1;

    public CompletablePerformPayTask(Integer order) {
        try {
            this.order1=order;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        System.out.println("CompletablePerformPayTask Task :"+ Thread.currentThread().getName()+ " return Same Order : "+this.order1);
        return this.order1;
    }
}
class CompletableDispatchTask implements Callable<Integer> {

    private int order1;

    public CompletableDispatchTask(Integer order) {
        try {
            this.order1=order;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        System.out.println("CompletableDispatchTask Task :"+ Thread.currentThread().getName()+ " return Same Order : "+this.order1);
        return this.order1;
    }
}
class CompletableSendEmailTask implements Callable<Integer> {

    private int order1;

    public CompletableSendEmailTask(Integer order) {
        try {
            this.order1=order;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        System.out.println("CompletableSendEmailTask Task :"+ Thread.currentThread().getName()+ " return Same Order : "+this.order1);
        return this.order1;
    }
}