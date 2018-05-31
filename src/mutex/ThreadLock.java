package mutex;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadLock {

	static int num = 0;

	public static void main(String[] args) throws InterruptedException {
		ThreadOrigin a = new ThreadOrigin("Thread 1");
		ThreadOrigin b = new ThreadOrigin("Thread 2");
		a.start();
		b.start();
//
//		while (num < 30) {
//			System.out.println("Main num = " + num);
//			num++;
//        	Thread.sleep(10);
//		}
		ThreadOrigin.lock.lock();
		ThreadOrigin.printnum("Main");
		ThreadOrigin.lock.unlock();
		
		
		a.join();
		b.join();

	}

}

class ThreadOrigin extends Thread {
	static ReentrantLock lock = new ReentrantLock();
	String name;

	ThreadOrigin(String name) {
		this.name = name;
	}

	public void run() {
		lock.lock();
		printnum(this.name);
		lock.unlock();
	}
	
	static void printnum(String name){
		int num=0;
		while (num < 30) {
			System.out.println(name + " num = " + num);
			num++;
		}
	}
}

