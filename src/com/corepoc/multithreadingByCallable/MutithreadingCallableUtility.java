package com.corepoc.multithreadingByCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class MutithreadingCallableUtility {

    public static void main(String[] args) {

        //create a pool - the thread pool uses a blocking queue, that is a threadsafe operation
        ExecutorService service = Executors.newFixedThreadPool(10);

        List<Future> allFutures = new ArrayList<>();
        for(int i=0;i<100;i++){
            //submit the task for execution
            Future<Integer> future= service.submit(new ExecutorServiceTask());
            allFutures.add(future);
        }
        //100 futures with 100 placeholders

        //perform some unrelated operations - say for 1 sec

        for(int i=0;i<100;i++){
            Future<Integer> future= allFutures.get(i);
            Integer result = null;
            try {
                //result = future.get();//blocking call since get might not have the value after 1 sec
                //to make sure that this does not throw ExecutionException we can implement the below
                result = future.get(1,TimeUnit.SECONDS);//adds the TimeoutException
                /*
                //other ways for bypassing the ExecutionException
                //cancel the task
                future.cancel(false);//if true, the thread is attempted to be interrupted
                future.isCancelled();
                future.isDone();

                 */
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {//this is for result = future.get(1,TimeUnit.SECONDS);
                e.printStackTrace();
            }
            System.out.println("Result of Future Number :"+i+ " result : "+ result);
        }

        System.out.println("Thread Name :"+ Thread.currentThread().getName());
    }

}

class ExecutorServiceTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {

        Thread.sleep(3000);
        Integer newInt =new Random().nextInt();
        System.out.println("Thread Task :"+ Thread.currentThread().getName()+ " generated Integer : "+newInt);
        return newInt;
    }
}
