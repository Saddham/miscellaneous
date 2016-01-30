package com.timepass.producerconsumer;

public class Consumer implements Runnable{
	Biskett biskett;
	
	public Consumer(Biskett biskett) {
		this.biskett = biskett;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			biskett.get();			
		}
	}

}
