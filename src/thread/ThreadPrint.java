package thread;

public class ThreadPrint {
	public static void main(String[] args) throws InterruptedException {
		ThreadA a = new ThreadA("threaA Start : ");
		ThreadB b = new ThreadB("threaB Start : ");
		a.start();
		b.start();

		int num = 0;
		while (num < 10) {
			System.out.println("Main num = " + num);
			num++;
		}
		
		a.join();
		b.join();

	}

}

class ThreadA extends Thread {
	ThreadA(String name) {
		this.name = name;
	}

	private int num;
	private String name;

	public void run() {
		while (num < 10) {
			System.out.println( this.name + " num = " + num);
			num++;
		}
	}
}

class ThreadB extends Thread {
	ThreadB(String name) {
		this.name = name;
	}

	private int num;
	private String name;

	public void run() {
		while (num < 10) {
			System.out.println( this.name + " num = " + num);
			num++;
		}
	}
}
