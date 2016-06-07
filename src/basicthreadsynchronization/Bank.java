package basicthreadsynchronization;

public class Bank implements Runnable{

	private Account accout;
	
	public Bank(Account accout) {
		super();
		this.accout = accout;
	}

	@Override
	public void run() {
		for(int i = 0 ; i < 100 ; i++){
			accout.subAmount(1000);
		}
	}
	
}
