package com.timepass;

public class TowerOfHonoi {

	public static void move(int n, boolean left){
		if(n == 0) return;
		move(n-1, !left);
		
		if(left) System.out.println("Move "+n+": left");
		else System.out.println("Move "+n+": right");
		
		move(n-1, !left);
	}
	public static void main(String[] args) {
		move(3, true);

	}

}
