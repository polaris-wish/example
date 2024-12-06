package com.chen.example;

import com.chen.example.thread.MyRunableTask;
import com.chen.example.thread.MyCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("Hello world!");

        Thread thread = new Thread(new MyRunableTask());
        thread.start();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        MyCallable myCallable = new MyCallable();
        Future future = executorService.submit(myCallable);
        System.out.println(System.currentTimeMillis()+"-------");
        Object resutl = future.get();
        System.out.println(resutl);
        System.out.println(System.currentTimeMillis()+"-------");

        Thread.yield();
    }


}