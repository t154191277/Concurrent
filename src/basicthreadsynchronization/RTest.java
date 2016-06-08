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
	
//	@Test
	public void testWaitAndNotifyAll(){
		EventStorage storage=new EventStorage();
		Producer producer=new Producer(storage);
		Thread thread1=new Thread(producer);
		Consumer consumer=new Consumer(storage);
		Thread thread2=new Thread(consumer);
		thread2.start();
		thread1.start();
	}
	
//	@Test
	public void testLock(){
		PrintQueue printQueue=new PrintQueue();
		Thread thread[]=new Thread[10];
		for (int i=0; i<10; i++){
			thread[i]=new Thread(new Job(printQueue),"Thread "+ i);
		}
		for (int i=0; i<10; i++){
			thread[i].start();
		}
	}
	
	/**
	 * junit 不支持多線程測試，因為最後調用的是system.exit(0),jvm結束
	 * 添加GroboUtils-5-core.jar包之後解決
	 */
//	@Test 
	public void testLock2(){
		PricesInfo pricesInfo=new PricesInfo();
		Reader readers[]=new Reader[5];
		Thread threadsReader[]=new Thread[5];
		for (int i=0; i<5; i++){
			readers[i]=new Reader(pricesInfo);
			threadsReader[i]=new Thread(readers[i]);
		}
		Writer writer=new Writer(pricesInfo);
		Thread threadWriter=new Thread(writer);
		for (int i=0; i<5; i++){
			threadsReader[i].start();
		}
		threadWriter.start();
	}
	
//	@Test
	public void testLock3() {
		PrintQueue1 printQueue=new PrintQueue1();
		Thread thread[]=new Thread[10];
		for (int i=0; i<10; i++){
			thread[i]=new Thread(new Job1(printQueue),"Thread "+ i);
		}
		for (int i = 0; i < 10; i++) {
			thread[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testLock4() {
		FileMock mock = new FileMock(100, 10);
		Buffer buffer = new Buffer(20);
		Producer1 producer = new Producer1(mock, buffer);
		Thread threadProducer = new Thread(producer, "Producer");
		Consumer1 consumers[] = new Consumer1[3];
		Thread threadConsumers[] = new Thread[3];
		for (int i = 0; i < 3; i++) {
			consumers[i] = new Consumer1(buffer);
			threadConsumers[i] = new Thread(consumers[i], "Consumer " + i);
		}

		threadProducer.start();
		for (int i = 0; i < 3; i++) {
			threadConsumers[i].start();
		}
	}
	
	//问题很大！？ GroboUtils出问题？ test3
	//GroboUtiles不是这样测试的
	public static void main(String[] args) {
		PrintQueue1 printQueue=new PrintQueue1();
		Thread thread[]=new Thread[10];
		for (int i=0; i<10; i++){
			thread[i]=new Thread(new Job1(printQueue),"Thread "+ i);
		}
		for (int i = 0; i < 10; i++) {
			thread[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
