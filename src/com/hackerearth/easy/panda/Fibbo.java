package com.hackerearth.easy.panda;

public class Fibbo {
	public static void fibbo(int n){
		fibbo(0, 1, n);		
	}
	
	public static void fibbo(int f1, int f2, int n){
		if(n==0){
			return;
			//System.out.println(f1+f2);
		} else{
			System.out.println(f2);
			fibbo(f2, f1+f2, n-1);
			
		}
				
	}
	
	
	public static void main(String[] args) {		
		fibbo(5);
	}

}
