package com.chen.example.thread;

import java.util.concurrent.Callable;

/**
 * @author chenlong
 * @date 2024/12/6
 */
public class MyCallable implements Callable {


    public Object call() throws Exception {
        Thread.sleep(1000);
        return "hello";
    }
}
