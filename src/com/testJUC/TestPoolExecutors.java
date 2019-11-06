package com.testJUC;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestPoolExecutors {
	public static void main(String[] args) {
		ExecutorService es=Executors.newFixedThreadPool(2);
		es.submit(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {
					System.out.println(Thread.currentThread().getName()+"Runnable"+i);
				}
			}
		});
		Future<String> submit2 = es.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				for (int i = 3; i < 6; i++) {
					System.out.println(Thread.currentThread().getName()+"Callable"+i);
				}
				return "Callable 牛逼";
			}
		});
		try {
			System.out.println(submit2.get());
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}
		Future<String> submit = es.submit(new Runnable() {

			@Override
			public void run() {
				System.out.println("i'm runnable and it's result");
			}
		}, "runnable's result");
		try {
			System.out.println(submit.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		es.shutdown();
		
	}
}
