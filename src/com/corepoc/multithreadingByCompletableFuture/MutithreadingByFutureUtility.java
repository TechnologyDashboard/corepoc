package com.corepoc.multithreadingByCompletableFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class MutithreadingByFutureUtility {

    public static void main(String[] args) {

        //create a pool - the thread pool uses a blocking queue, that is a threadsafe operation
        ExecutorService service = Executors.newFixedThreadPool(10);

        try{
            Future<Integer> orderFuture= service.submit(new OrderTask());
            Integer order=orderFuture.get(); //blocking

            Future<Integer> enrichFuture= service.submit(new EnrichTask(order));
            order=enrichFuture.get(); //blocking

            Future<Integer> performPayFuture= service.submit(new PerformPayTask(order));
            order=performPayFuture.get(); //blocking

            Future<Integer> dispatchFuture= service.submit(new DispatchTask(order));
            order=dispatchFuture.get(); //blocking

            Future<Integer> sendEmailFuture= service.submit(new SendEmailTask(order));
            order=sendEmailFuture.get(); //blocking


        }catch(InterruptedException | ExecutionException ex){
            ex.printStackTrace();
        }

        System.out.println("Thread Name :"+ Thread.currentThread().getName());
    }

}

class OrderTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {

        Thread.sleep(3000);
        Integer newInt =new Random().nextInt();
        System.out.println("OrderTask Task :"+ Thread.currentThread().getName()+ " generated Integer : "+newInt);
        return newInt;
    }
}
class EnrichTask implements Callable<Integer> {

    private int order1;

    public EnrichTask(Integer order) {
        try {
            this.order1=order;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        System.out.println("EnrichTask Task :"+ Thread.currentThread().getName()+ " return Same Order : "+this.order1);
        return this.order1;
    }
}
class PerformPayTask implements Callable<Integer> {

    private int order1;

    public PerformPayTask(Integer order) {
        try {
            this.order1=order;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        System.out.println("PerformPayTask Task :"+ Thread.currentThread().getName()+ " return Same Order : "+this.order1);
        return this.order1;
    }
}
class DispatchTask implements Callable<Integer> {

    private int order1;

    public DispatchTask(Integer order) {
        try {
            this.order1=order;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        System.out.println("DispatchTask Task :"+ Thread.currentThread().getName()+ " return Same Order : "+this.order1);
        return this.order1;
    }
}
class SendEmailTask implements Callable<Integer> {

    private int order1;

    public SendEmailTask(Integer order) {
        try {
            this.order1=order;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(3000);
        System.out.println("SendEmailTask Task :"+ Thread.currentThread().getName()+ " return Same Order : "+this.order1);
        return this.order1;
    }
}

