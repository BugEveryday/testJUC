package com.testJUC;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CommuLockCondition {
	public static void main(String[] args) {
		Demo1 d=new Demo1();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					d.incr();
				}
			}
		}).start();
		new Thread(new FutureTask( new Callable() {
			@Override
			public Object call() throws Exception {
				for(int i=0;i<10;i++) {
					d.decr();
				}
				return null;
			}
		})).start();
	}
}
class Demo1{
	private Lock lock=new ReentrantLock();
	private Condition c=lock.newCondition();
	private int num=0;
	
	public void incr() {
		lock.lock();
		try {
			while(num!=0) {
					c.await();
			}
			num++;
			System.out.println(Thread.currentThread().getName()+"incr"+num);
			c.signalAll();
		}catch(Exception e) {
			
		}finally {
			lock.unlock();
		}
	}
	public void decr() {
		lock.lock();
		try {
			while(num!=1) {
					c.await();
			}
			num--;
			System.out.println(Thread.currentThread().getName()+"decr"+num);
			c.signalAll();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
}