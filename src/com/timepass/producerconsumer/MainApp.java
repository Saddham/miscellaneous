package com.timepass.producerconsumer;

public class MainApp {

	public static void main(String[] args) {
		Biskett biskett = new Biskett();
		Thread producer = new Thread(new Producer(biskett));
		Thread consumer = new Thread(new Consumer(biskett));
		
		producer.start();
		consumer.start();

	}

}
