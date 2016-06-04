package threadmanagemt;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetWorkConnectionLoader extends Thread{
	@Override
	public void run() {
		System.out.println("network start at: " + new Date());
		try {
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("network end at:" + new Date());
	}
}
