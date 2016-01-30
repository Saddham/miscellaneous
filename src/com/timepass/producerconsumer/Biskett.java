package com.timepass.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

public class Biskett{
	private Queue<Integer> queue = new LinkedList<Integer>();
	private final int SIZE = 2;
	
	public synchronized Integer get(){
		if(queue.isEmpty()){
			try {
				wait();
				System.out.println("Waiting for producer to add new elements"); 
			} catch (InterruptedException e) {
			}
		}
		
		Integer element = queue.remove();
		System.out.println("Element removed: "+element);
		notifyAll();
		return element;			
	}
	
	public synchronized void put(Integer element){
		if(queue.size() == SIZE){
			try {
				wait();
				System.out.println("Waiting for consumer to remove existing elements");
			} catch (InterruptedException e) {
			}
		}
		
		queue.add(element);
		System.out.println("Element added: "+element);
		notifyAll();
	}
}
