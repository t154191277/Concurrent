package threadsyncronizationutilities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Phaser;

import org.apache.log4j.chainsaw.Main;
import org.junit.Test;

import threadsyncronizationutilities.MyPhaser.Student;

public class RTest {

	// @Test
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
		
	}

	// @Test
	public void testCountDownLatch() {
		Videoconference conference = new Videoconference(10);
		Thread threadConference = new Thread(conference);
		threadConference.start();
		for (int i = 0; i < 10; i++) {
			Participant p = new Participant(conference, "Participant " + i);
			Thread t = new Thread(p);
			t.start();
		}
	}

	@Test
	public void testCyclicBarrier() {
		final int ROWS = 10000;
		final int NUMBERS = 1000;
		final int SEARCH = 5;
		final int PARTICIPANTS = 5;
		final int LINES_PARTICIPANT = 2000;
		MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);
		Results results = new Results(ROWS);
		Grouper grouper = new Grouper(results);
		// 创建 CyclicBarrier 对象，名为 barrier。此对象会等待5个线程。当此线程结束后，它会执行前面创建的 Grouper
		// 对象。
		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper);
		Searcher searchers[] = new Searcher[PARTICIPANTS];
		for (int i = 0; i < PARTICIPANTS; i++) {
			searchers[i] = new Searcher(i * LINES_PARTICIPANT,
					(i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock, results,
					5, barrier);
			Thread thread = new Thread(searchers[i]);
			thread.start();
		}
		System.out.printf("Main: The main thread has finished.\n");
	}

	@Test
	public void testPhaser() {
		Phaser phaser = new Phaser(3);
		FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
		FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
		FileSearch documents = new FileSearch("C:\\Documents And Settings",
				"log", phaser);
		Thread systemThread = new Thread(system, "System");
		systemThread.start();
		Thread appsThread = new Thread(apps, "Apps");
		appsThread.start();
		Thread documentsThread = new Thread(documents, "Documents");
		documentsThread.start();
		try {
			systemThread.join();
			appsThread.join();
			documentsThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Terminated: " + phaser.isTerminated());
	}

	@Test
	public void testMyPhaser() {
		MyPhaser phaser = new MyPhaser();
		MyPhaser.Student students[] = new Student[5];
		for (int i = 0; i < students.length; i++) {
			students[i] = phaser.new Student(phaser);
			phaser.register();
		}
		Thread threads[] = new Thread[students.length];
		for (int i = 0; i < students.length; i++) {
			threads[i] = new Thread(students[i], "Student " + i);
			threads[i].start();
		}
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("Main: The phaser has finished: %s.\n",
				phaser.isTerminated());
	}

	@Test
	public void testExchanger() {
		List<String> buffer1 = new ArrayList<String>();
		List<String> buffer2 = new ArrayList<String>();
		Exchanger<List<String>> exchanger = new Exchanger<List<String>>();
		Producer producer = new Producer(buffer1, exchanger);
		Consumer consumer = new Consumer(buffer2, exchanger);
		Thread threadProducer = new Thread(producer);
		Thread threadConsumer = new Thread(consumer);
		threadProducer.start();
		threadConsumer.start();
	}
}
