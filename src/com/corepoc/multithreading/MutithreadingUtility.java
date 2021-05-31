package com.corepoc.multithreading;

import java.util.logging.Logger;

public class MutithreadingUtility {

    public static void main(String[] args) {
        for (int i=0 ;i< 10; i++) {
            Thread t1 = new Thread(new Task());
            t1.start();
        }
        System.out.println("Thread Name :"+ Thread.currentThread().getName());
    }

}

class Task implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread Task :"+ Thread.currentThread().getName());
    }
}
