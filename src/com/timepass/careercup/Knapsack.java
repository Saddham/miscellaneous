package com.timepass.careercup;

public class Knapsack {
	public static int solve(int [] vals, int [] wghs, int cap, int n){		
		if(n==0 || cap==0) return 0;
		
		if(cap<wghs[n-1]){
			return solve(vals, wghs, cap, n-1);
		} 
				
		return Math.max(vals[n-1]+solve(vals, wghs, cap-wghs[n-1], n-1), solve(vals, wghs, cap, n-1));
	}
	
	public static int solve_dp(int [] vals, int [] wghs, int cap){		
		int [][] res = new int[vals.length+1][cap+1];
		
		for (int i = 0; i <= vals.length; i++) {
			for (int w = 0; w <= cap; w++) {
				if(i==0 || w==0) res[i][w] = 0;
				else if(wghs[i-1] > w) {
					res[i][w] = res[i-1][w]; 
				} else {
					res[i][w] = Math.max(vals[i-1]+res[i-1][w-wghs[i-1]], res[i-1][w]);
				}
			}
		}
		
		return res[vals.length][cap];
	}
	
	
	public static void main(String[] args) {
		int val[] = {60, 100, 120};
	    int wt[] = {10, 20, 30};
	    int  W = 50;
		System.out.println(solve(val, wt, W, val.length));
		System.out.println(solve_dp(val, wt, W));
	}

}
