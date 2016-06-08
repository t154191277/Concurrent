package threadsyncronizationutilities;

import java.util.concurrent.Semaphore;

public class PrintQueue {

	private final Semaphore semaphore;

	public PrintQueue() {
		semaphore = new Semaphore(1);
	}

	/**
	 * 1. 首先， 你要调用acquire()方法获得semaphore。
	    2. 然后， 对共享资源做出必要的操作。
		3. 最后， 调用release()方法来释放semaphore。
	 * @param document
	 */
	public void printJob(Object document) {
		try {
			semaphore.acquire();
			long duration = (long) (Math.random() * 10);
			System.out.printf(
					"%s: PrintQueue: Printing a Job during %d seconds\n",
					Thread.currentThread().getName(), duration);
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}
}
