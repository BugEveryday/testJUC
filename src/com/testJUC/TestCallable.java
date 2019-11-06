package com.testJUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		TestC tc=new TestC();
		FutureTask<String> ft=new FutureTask<>(tc);
		Thread t=new Thread(ft);
		t.start();
		String string = ft.get();//get()方法会阻塞当前线程，当前线程是ft里传的tc
		System.out.println(string);
		
		
	}
}
class TestC implements Callable<String>{

	@Override
	public String call() throws Exception {
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName()+i);
		}
		return "Callable 牛逼";
	}
	
}