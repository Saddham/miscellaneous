package com.timepass.geeksforgeeks;

import java.util.Arrays;

public class MatrixProblems {
	public static int numberOfPaths(int m, int n){
		int [][] res = new int [m+2][n+2];
		for (int i = 0; i < res.length; i++) {
			Arrays.fill(res[i], 0);
		}
		
		res[m][n+1] = 1;
		for (int i = m; i >= 1; i--) {
			for (int j = n; j >= 1; j--) {
				res[i][j] = res[i+1][j] + res[i][j+1];
			}
		}
		
		return res[1][1];
	}
	
	public static int numberOfPaths_v2(int m, int n) {
		 return numberOfPaths_v2(0, 0, m-1, n-1);
	}
	
	public static int numberOfPaths_v2(int r, int c, int m, int n) {
	  if (r == m && c == n)
	    return 1;
	  if (r > m || c > n)
	    return 0;
	 
	  return numberOfPaths_v2(r+1, c, m, n) + numberOfPaths_v2(r, c+1, m, n);
	}
	
	private static int fact(int n){
		int f = 1;
		for(int i=2; i<=n; i++){
			f = f*i;
		}
		return f;
	}
	
	public static void main(String[] args) {
		System.out.println(numberOfPaths(10, 10));
		System.out.println(numberOfPaths_v2(10, 10));
		System.out.println(fact(10+10)/(fact(10)*fact(10)));
	}

}
