package threadmanagemt;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

import org.junit.Test;

public class Calculator implements Runnable{
	private int number;

	public Calculator(int number) {
		super();
		this.number = number;
	}

	@Override
	public void run() {
		for(int i = 1; i <= 10; i++){
			System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(), number, i ,i * number);
		}
		
	}
}	
