package threadsyncronizationutilities;

import org.apache.log4j.chainsaw.Main;
import org.junit.Test;


public class RTest {
	
	@Test
	public void testBinarySemaphores() {
		PrintQueue printQueue = new PrintQueue();
		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread" + i);
		}
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}
	
	public static void main(String[] args) {
		PrintQueue2 printQueue = new PrintQueue2();
		Thread thread[] = new Thread[10];
		for (int i = 0; i < 10; i++) {
			thread[i] = new Thread(new Job(printQueue), "Thread" + i);
		}
		for (int i = 0; i < 10; i++) {
			thread[i].start();
		}
	}

}
