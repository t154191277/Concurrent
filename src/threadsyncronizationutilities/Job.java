package threadsyncronizationutilities;


public class Job implements Runnable{

	private PrintQueue printQueue;
	private PrintQueue2 printQueue2;
	
	public Job(PrintQueue printQueue){
	    this.printQueue=printQueue;
	}
	
	public Job(PrintQueue2 printQueue){
	    this.printQueue2=printQueue;
	}

	@Override
	public void run() {
		System.out.printf("%s: Going to print a job\n", Thread.currentThread()
				.getName());
//		printQueue2.printJob(new Object());
		printQueue.printJob(new Object());
		System.out.printf("%s: The document has been printed\n", Thread
				.currentThread().getName());
	}


}
