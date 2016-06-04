package threadmanagemt;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataSourcesLoader extends Thread{
	@Override
	public void run() {
		System.out.println("data start at: " + new Date());
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("data end at:" + new Date());
	}
}
