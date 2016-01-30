/**
 * 
 */
package com.timepass.timer;

import java.util.LinkedList;
import java.util.Queue;



/**
 * @author Saddham
 *
 */
public class MyTimer extends System{
	Queue<Task> queue = new LinkedList<Task>();
	Boolean flag; 
	@Override
	public void schedule(Runnable runnable, int seconds) {
		Task newTask = new Task();
		newTask.setRunnable(runnable);
		newTask.setSeconds(seconds);
		queue.add(newTask);
		
		Task task = queue.remove();
		super.schedule(task.getRunnable(), task.getSeconds());
	}	

}
