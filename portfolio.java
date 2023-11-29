package portfolio;

import java.util.concurrent.locks.ReentrantLock;

class Counter {
	private int count = 0;
	private final ReentrantLock lock = new ReentrantLock();
	
	public void increment() {
		lock.lock();
		try {
			while (count < 20) {
				count++;
				System.out.println(Thread.currentThread().getName() + " incremented count to " + count);
				Thread.sleep(100);
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void decrement() {
		lock.lock();
		try {
			while (count > 0) {
				count--;
				System.out.println(Thread.currentThread().getName() + " decremented count to " + count);
				Thread.sleep(100);
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

public class Main {
	public static void main(String[] args) {
		Counter counter = new Counter();
		
		Thread t1 = new Thread(() -> {
			counter.increment();
		}, "t1");
		
		Thread t2 = new Thread(() -> {
			counter.decrement();
		}, "t2");
		
		t1.start();
		t2.start();
	}
}
