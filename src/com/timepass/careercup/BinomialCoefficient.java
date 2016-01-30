package com.timepass.careercup;

public class BinomialCoefficient {
	public static int bc(int n, int r){		
		return fact(n)/(fact(r)*fact(n-r));
	}
	
	private static int fact(int n){				
		int res = Integer.MIN_VALUE;

		if (n == 0) res = 1;
		
		while(n>0){
			int [] resa = new int[n];
			resa[0] = 1;
			for (int i = 1; i < resa.length; i++) {
				resa[i] = (i+1)*resa[i-1];
			}
			
			return resa[n-1];			
		}

		return res;
	}

	public static int bc_v2(int n, int r){
		if(n==r) return 1;
		if(r==0) return 1;
		
		return bc_v2(n-1, r)+bc_v2(n-1, r-1);
	}
	
	public static int bc_v2_dp(int n, int r){
		
		int [][] res = new int[n+1][r+1];
		
		for (int i = 0; i <= n; i++) {
			for(int j = 0; j <= Math.min(i, r); j++){
				if(i==j || j==0){
					res[i][j] = 1;
				} else{				
					res[i][j] = res[i-1][j] + res[i-1][j-1];
				}
			}
		}
		
		
		return res[n][r];
	}
	
	public static void main(String[] args) {		
		System.out.println(bc(4, 2));
		System.out.println(bc_v2(4, 2));
		System.out.println(bc_v2_dp(4, 2));
	}

}
