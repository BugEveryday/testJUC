package com.testJUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ToolsCyclicBarrier {
	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(5, new Runnable() {
			@Override
			public void run() {
				System.out.println("你可以走了");
			}
		});
		for (int i = 1; i <= 5; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() );
				try {
					cb.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}, "关羽过了"+i+"关了").start();
		}
	}
}
