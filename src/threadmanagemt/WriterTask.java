package threadmanagemt;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class WriterTask extends Thread{
	
	private Deque<Integer> deque;
	
	public WriterTask(Deque<Integer> deque) {
		super();
		this.deque = deque;
	}

	@Override
	public void run() {
		int i = 100;
		while(--i > 0){
			Integer second = new Date().getSeconds();
			deque.addFirst(second);
			System.out.println(i);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
