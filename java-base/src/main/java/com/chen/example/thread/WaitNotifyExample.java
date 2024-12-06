package com.chen.example.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class SharedResource {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void method() {
        lock.lock();
        try {
            System.out.println("线程 " + Thread.currentThread().getName() + " 获取到锁");
            // 调用wait方法，释放锁并等待
            condition.await();
            System.out.println("线程 " + Thread.currentThread().getName() + " 被唤醒后重新获取到锁，继续执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void notifyAllThreads() {
        lock.lock();
        try {
            condition.signalAll();
            System.out.println("唤醒所有等待的线程");
        } finally {
            lock.unlock();
        }
    }
}
public class WaitNotifyExample {
    public static void main(String[] args) {
        final SharedResource resource = new SharedResource();
        // 创建并启动等待线程
        Thread waitingThread1 = new Thread(() -> resource.method(), "等待线程1");
        Thread waitingThread2 = new Thread(() -> resource.method(), "等待线程2");
        waitingThread1.start();
        waitingThread2.start();
        // 主线程休眠一段时间，让等待线程获取锁并进入等待状态
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 唤醒所有等待线程
        resource.notifyAllThreads();
    }
}