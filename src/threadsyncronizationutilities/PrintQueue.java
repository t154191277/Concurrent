package threadsyncronizationutilities;

import java.util.concurrent.Semaphore;

public class PrintQueue {

	private final Semaphore semaphore;

	public PrintQueue() {
		semaphore = new Semaphore(1);
	}

	/**
	 * 1. ���ȣ� ��Ҫ����acquire()�������semaphore��
	    2. Ȼ�� �Թ�����Դ������Ҫ�Ĳ�����
		3. ��� ����release()�������ͷ�semaphore��
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
