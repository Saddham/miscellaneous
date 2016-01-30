package com.timepass.careercup;

public class EggDrop {

	public static int eggDrop(int eggs, int floors){
		if(floors==0 || floors==1){
			return floors;
		}
		
		if(eggs==1)
			return floors;
		
		int min = Integer.MAX_VALUE;
		int res;
				
		for (int i = 1; i <= floors; i++) {
			res = 1+Math.max(eggDrop(eggs-1, i-1), eggDrop(eggs, floors-i));
			if(res < min)
				min = res;
		}
		
		return min;
	}
	
	public static int eggDrop_dp(int eggs, int floors){
		int [][] eggsFloors = new int[eggs+1][floors+1];
		
		for (int i = 1; i <= eggs; i++) {
			eggsFloors[i][0] = 0;
			eggsFloors[i][1] = 1;
		}
		
		for (int i = 1; i <= floors; i++) {
			eggsFloors[1][i] = i;		
		}
		
		for (int i = 2; i <= eggs; i++) {			
			for (int j = 2; j <= floors; j++) {
				eggsFloors[i][j] = Integer.MAX_VALUE;
				for (int k = 1; k <= j; k++) {
					int res = 1+Math.max(eggsFloors[i-1][k-1], eggsFloors[i][j-k]);
					if(res < eggsFloors[i][j]) 
						eggsFloors[i][j] = res;
				}
				
			}			
		}
		
		return eggsFloors[eggs][floors];
	}	

	public static void main(String[] args) {
		System.out.println(eggDrop(2, 10));
		System.out.println(eggDrop_dp(2, 36));

	}

}
