package threadmanagemt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

import org.junit.Test;

public class Testz {
//	@Test
	public void test() throws IOException {
		Thread threads[] = new Thread[10];
		Thread.State status[] = new Thread.State[10];
		for (int i=0; i<10; i++){
			   threads[i]=new Thread(new Calculator(i));
			   if ((i%2)==0){
			      threads[i].setPriority(Thread.MAX_PRIORITY);
			   } else {
			      threads[i].setPriority(Thread.MIN_PRIORITY);
			   }
			   threads[i].setName("Thread "+i);
		}
		
		//主目录，不是src下。
		FileWriter file = new FileWriter("log.txt");
		PrintWriter pw = new PrintWriter(file);
		for(int i = 0; i < 10; i++){
			pw.println("Main : Status of Thread " + i + " : " + threads[i].getState());
			status[i] = threads[i].getState();
		}
		
		for( int i = 0; i < 10 ;i++){
			threads[i].start();
		}
		
		boolean finish=false;
		while (!finish) {
		   for (int i=0; i<10; i++){
		      if (threads[i].getState()!=status[i]) {
		          writeThreadInfo(pw, threads[i],status[i]);
		          status[i]=threads[i].getState();
		      }
		   }
		   finish=true;
		   for (int i=0; i<10; i++){
		      finish=finish && (threads[i].getState()==State.TERMINATED);
		   }
		}
		pw.flush();
		pw.close();
		file.close();
	}

	private void writeThreadInfo(PrintWriter pw, Thread thread , State state) {
		 	pw.printf("Main : Id %d - %s\r\n",thread.getId(),thread.getName());
		    pw.printf("Main : Priority: %d\r\n",thread.getPriority());
		    pw.printf("Main : Old State: %s\r\n",state);
		    pw.printf("Main : New State: %s\r\n",thread.getState());
		    pw.printf("Main : ************************************\r\n");

	}
	
//	@Test
	public void testPrime() throws InterruptedException{
		Thread task = new PrimeGenerator();
		task.start();
		Thread.sleep(50);
		//线程可以忽略中断命令
		task.interrupt();
	}
	
	@Test
	public void testSearchFile() throws InterruptedException{
		FileSearch task = new FileSearch("log.txt","D:/GitWorkSpace/Concurrent/");
		task.start();
		task.sleep(4);
		task.interrupt();
	}
}
