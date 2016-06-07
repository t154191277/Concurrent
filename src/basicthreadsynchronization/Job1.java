package basicthreadsynchronization;

public class Job1 implements Runnable{

	private PrintQueue1 printQueue;
	
	public Job1(PrintQueue1 printQueue){
		this.printQueue=printQueue;
	}

	
	@Override
	public void run() {
		System.out.printf("%s: Going to print a document\n", Thread
				.currentThread().getName());
		printQueue.printJob(new Object());
		System.out.printf("%s: The document has been printed\n", Thread
				.currentThread().getName());
	}

}
