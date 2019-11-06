package com.testJUC;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/*
 * 两个线程，同时操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 *  实现两个线程的交替操作，即加 减 加 减 加 减…… 交替10轮
 */
public class CommuRunnable {
	public static void main(String[] args) throws InterruptedException {
		Demo demo=new Demo();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=1;i<=10;i++) {
					demo.decr();
				}
			}
			
		}).start();
		Thread.sleep(1000);
		new Thread(new FutureTask(new Callable<String>() {
			@Override
			public String call() throws Exception {
				for(int i=0;i<10;i++) {
					demo.incr();
				}
				return "all has done";
			}
		})).start();
	}
}
class Demo {
	private  int num=0;
	
	public  synchronized void incr() {
		while(num !=0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		num++;
		System.out.println(Thread.currentThread().getName()+"FutureTask(Callable)"+num);
		this.notifyAll();
	}
	public synchronized void decr() {
		while(num!=1) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		num--;
		System.out.println(Thread.currentThread().getName()+"Runnable"+num);
		this.notifyAll();
	}
	
}
