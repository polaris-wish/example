package com.chen.example.thread;

/**
 * @author chenlong
 * @date 2024/12/6
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("this is my Threa run....");
    }
}
