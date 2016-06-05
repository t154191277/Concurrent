package threadmanagemt;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
		PrintWriter pw = null;
		try {
			FileWriter fw = new FileWriter("log.txt");
			pw = new PrintWriter(fw);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i =0;i<100;i++){
			pw.print(i);
			pw.flush();
		}
		pw.close();
	}
}
