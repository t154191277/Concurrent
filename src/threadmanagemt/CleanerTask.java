package threadmanagemt;

import java.util.Date;
import java.util.Deque;

public class CleanerTask extends Thread{
	
	private Deque<Integer> deque;
	
	public CleanerTask(Deque<Integer> deque) {
		super();
		this.deque = deque;
		setDaemon(true);
	}

	@Override
	public void run() {
		while(true){
			Date date = new Date();
			clean(date);
		}
	}

	private void clean(Date date) {
		long second = date.getSeconds();
		for(Integer i : deque){
			if( (i + 10) < second){
				deque.remove(i);
				System.out.println("remove one");
			}
		}
	}
}
