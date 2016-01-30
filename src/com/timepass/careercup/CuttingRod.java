package com.timepass.careercup;

public class CuttingRod {
	
	public static int maxPrice(int [] prices, int rodLength){
		return maxPrice(prices, rodLength, 0);
	}
	
	private static int maxPrice(int [] prices, int rodLength, int price){
		if(rodLength < 0){
			return Integer.MIN_VALUE;
		}
		
		if(rodLength == 0){
			return price;
		}
		
		int max = Integer.MIN_VALUE;
		int res;
		for (int i = 0; i < rodLength; i++) {
			res = maxPrice(prices, rodLength-i-1, price+prices[i]);
			if(res > max){
				max = res;
			}
		}
		
		return max;
	}
	
	public static int maxPrice_dp(int [] prices, int rodLength){
		int [] sol = new int[rodLength+1];			
		sol[0] = 0;
		
		for(int i=1; i<rodLength+1; i++){
			int max = Integer.MIN_VALUE;
			for (int j=0; j<i; j++) {
				max = Math.max(max, prices[j]+sol[i-j-1]);
			}
			sol[i] = max;
		}
		
		return sol[rodLength];
	}
	
	public static void main(String[] args) {
		int [] prices = {3, 5, 8, 9, 10, 17, 17, 20};
		int  rodLength = 8;
		System.out.println(maxPrice(prices, rodLength));
		System.out.println(maxPrice_dp(prices, rodLength));
		
	}

}
