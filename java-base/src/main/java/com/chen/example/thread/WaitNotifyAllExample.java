package com.chen.example.thread;

class SharedObject {
    private boolean flag = false;
    public synchronized void waitMethod() throws InterruptedException {
        System.out.println("线程 " + Thread.currentThread().getName() + " 进入等待方法，获取到锁");
        while (!flag) {
            // 调用wait方法，释放锁并等待
            this.wait();
        }
        System.out.println("线程 " + Thread.currentThread().getName() + " 被唤醒后重新获取到锁，继续执行");
    }
    public synchronized void notifyAllMethod() {
        System.out.println("线程 " + Thread.currentThread().getName() + " 进入唤醒方法，获取到锁");
        flag = true;
        // 调用notifyAll方法唤醒所有等待的线程
        this.notifyAll();
        System.out.println("线程 " + Thread.currentThread().getName() + " 唤醒其他线程后，释放锁");
    }
}
public class WaitNotifyAllExample {
    public static void main(String[] args) {
        final SharedObject sharedObject = new SharedObject();
        // 创建等待线程
        Thread waitingThread = new Thread(() -> {
            try {
                sharedObject.waitMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "等待线程");
        waitingThread.start();
        // 主线程休眠一段时间，让等待线程获取锁并进入等待状态
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 创建唤醒线程
        Thread notifyingThread = new Thread(() -> {
            sharedObject.notifyAllMethod();
        }, "唤醒线程");
        notifyingThread.start();
    }
}