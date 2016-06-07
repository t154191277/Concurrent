package basicthreadsynchronization;

public class Company implements Runnable{
	
	private Account account;

	public Company(Account account) {
		super();
		this.account = account;
	}

	@Override
	public void run() {
		for (int i=0; i<100; i++){
			account.addAmount(1000);
		}
	}


}
