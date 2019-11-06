package com.testJUC;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ToolsSemaphore {
	public static void main(String[] args) {
		Semaphore s=new Semaphore(3);
		for(int i=0;i<6;i++) {
			new Thread(new Runnable() {
				public void run() {
					try {
						s.acquire();
						System.out.println("共3个车位，"+Thread.currentThread().getName()+"进入");
						TimeUnit.SECONDS.sleep(2);
						System.out.println("共3个车位，"+Thread.currentThread().getName()+"退出");
						s.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			},i+"号车").start();
		}
	}
}
