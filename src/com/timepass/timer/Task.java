package com.timepass.timer;

public class Task {
	private Runnable runnable;
	private int seconds;
	public Runnable getRunnable() {
		return runnable;
	}
	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
}
