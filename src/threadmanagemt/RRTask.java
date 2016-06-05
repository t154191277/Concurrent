package threadmanagemt;

import java.util.concurrent.TimeUnit;

public class RRTask implements Runnable{

	@Override
	public void run() {
		try { TimeUnit.SECONDS.sleep(1);
		}catch (InterruptedException e) {}
	}

}
