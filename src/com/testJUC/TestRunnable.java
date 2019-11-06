package com.testJUC;

public class TestRunnable {
	public static void main(String[] args) {
		TestR test=new TestR();
		Thread t=new Thread(test,"Thread的name");
		t.start();
	}
}
class TestR implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"实现Runnable的线程");
	}
	
}