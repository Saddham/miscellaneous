package com.timepass.geeksforgeeks;

public class CoinChange {
	public static int noOfWays(int [] coins, int cents){
		return _noOfWays(coins, coins.length-1, 10);
	}
	
	private static int _noOfWays(int [] coins, int m, int cents){

		if(cents==0){
			return 1;
		}
		
		if(cents < 0){
			return 0;
		}		
		
		if(m<0 && cents>0){
			return 0;
		}
		
		return _noOfWays(coins, m-1, cents)+_noOfWays(coins, m, cents-coins[m]);
	}
	
	public static int noOfWays_dp(int [] coins, int cents){		
		
		int [][] table = new int[cents+1][coins.length];
		
		for (int i = 0; i < coins.length; i++) {
			table[0][i] = 1;
		}
		
		for (int i = 1; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				int x = (i-coins[j])>=0 ? table[i-coins[j]][j] : 0; 
				
				int y = (j>=1) ? table[i][j-1] : 0;
				
				table[i][j] = x+y;
			}
		}
		
		return table[cents][coins.length-1];
	}
	
	
	public static void main(String[] args) {
		int [] coins = {2, 5, 3, 6};
		System.out.println(noOfWays(coins, 10));
		System.out.println(noOfWays_dp(coins, 10));

	}

}
