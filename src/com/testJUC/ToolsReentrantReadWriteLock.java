package com.testJUC;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ToolsReentrantReadWriteLock {
	public static void main(String[] args) {
		ReadWrite rw=new ReadWrite();
		new Thread(()->{
			for(int i=0;i<100;i++) {
				rw.read();
			}
		}).start();
		new Thread(()->{
			rw.write("i'm writing");
		}).start();
	}
}
class ReadWrite{
	private Object obj;
	private ReadWriteLock rwLock=new ReentrantReadWriteLock();
	public void read() {
		rwLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"we can read"+obj);
		}finally {
			rwLock.readLock().unlock();
		}
	}
	public void write(Object obj) {
		rwLock.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"i will write  "+obj+",  you can't read or write");
		}finally {
			rwLock.writeLock().unlock();
		}
	}
}