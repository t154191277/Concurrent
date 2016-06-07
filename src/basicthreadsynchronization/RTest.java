package basicthreadsynchronization;

import org.junit.Test;

public class RTest {

//	@Test
	public void testSynchronizedFunction() throws InterruptedException{
		Account account = new Account();
		account.setBalance(1000);
		Company company = new Company(account);
		Thread t = new Thread(company);
		Bank bank = new Bank(account);
		Thread t1 = new Thread(bank);
		System.out.printf("Account : Initial Balance: %f\n",account.getBalance());
		t.start();
		t1.start();
		t.join();
		System.out.printf("Account : Final Balance: %f\n",account.getBalance());
	}
	
//	@Test 
	public void testSynchronizedObject(){
		Cinema cinema = new Cinema();
		TicketOffice1 ticketOffice1=new TicketOffice1(cinema);
		TicketOffice2 ticketOffice2=new TicketOffice2(cinema);
		Thread thread2=new Thread(ticketOffice2,"TicketOffice2");
		Thread thread1=new Thread(ticketOffice1,"TicketOffice1");
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Room 1 Vacancies: %d\n",cinema.getVacanciesCinema1());
		System.out.printf("Room 2 Vacancies: %d\n",cinema.getVacanciesCinema2());
	}
	
	@Test
	public void testWaitAndNotifyAll(){
		EventStorage storage=new EventStorage();
		Producer producer=new Producer(storage);
		Thread thread1=new Thread(producer);
		Consumer consumer=new Consumer(storage);
		Thread thread2=new Thread(consumer);
		thread2.start();
		thread1.start();
	}
}
