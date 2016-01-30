package com.timepass.geeksforgeeks;

public class MinimumCost {

 	public static int solve(int [][] a, int i, int j){
		if(i<0 || j<0){
			return Integer.MAX_VALUE;
		} else if(i==0 && j==0){
			return a[i][j];
		} else{
			return Math.min(solve(a, i-1, j), Math.min(solve(a, i-1, j-1), solve(a, i, j-1)))+a[i][j];
		}					
	}
	
	public static int solve_dp(int [][] a, int m, int n){
		assert m<a.length;
		assert n<a[0].length;
		
		int [][]tc = new int[a.length][a[0].length];
		tc[0][0] = a[0][0];
		for (int i = 1; i < a[0].length; i++) {
			tc[0][i] = a[0][i]+tc[0][i-1];
		}
		
		for (int j = 1; j < a.length; j++) {
			tc[j][0] = a[j][0]+tc[j-1][0];
		}
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				tc[i][j] = Math.min(tc[i-1][j-1], Math.min(tc[i][j-1], tc[i-1][j]))+a[i][j];
			}
		}
		
		return tc[m][n];
	}
	
	public static void main(String[] args) {
		int [][] a = {{1,2,3},
					  {4,8,2},
					  {1,5,3}};
		
		System.out.println(solve_dp(a, 2, 2));

	}

}
