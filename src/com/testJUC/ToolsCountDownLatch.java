package com.testJUC;

import java.util.concurrent.CountDownLatch;

public class ToolsCountDownLatch {
	public static void main(String[] args) {
		CountDownLatch cdl=new CountDownLatch(6);
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=6;i>0;i--) {
					System.out.println(Thread.currentThread().getName()+"過了"+(6-i)+"关，斩了"+i+"个将");
					cdl.countDown();
				}
			}
		},"关羽").start();
		try {
			Thread.sleep(3000);
			cdl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("你可以走了");
	}
}
