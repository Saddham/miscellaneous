package com.timepass.producerconsumer;

public class Producer implements Runnable {
	Biskett biskett;
	
	public Producer(Biskett biskett) {
		this.biskett = biskett;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			biskett.put(i);	
		}
		
		try {
			Thread.sleep((int)(Math.random() * 1000));
		} catch (InterruptedException e) {
		}
	}

}
