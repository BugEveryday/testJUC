package com.testJUC;

public class TestNiming {
	public static void main(String[] args) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					System.out.println(Thread.currentThread().getName()+i);
				}
			}
			
		}).start();
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName()+i);
		}
	}
}
