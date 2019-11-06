package com.testJUC;

public class TestThread {
	public static void main(String[] args) {
		new TestT().start();
	}
}
class TestT extends Thread{
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"继承Thread的线程");
	}
}