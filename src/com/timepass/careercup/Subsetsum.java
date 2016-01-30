package com.timepass.careercup;

import java.util.Arrays;

public class Subsetsum {

	private static void subsetSum(int [] s, int t[], int tSize, int itr, int sum, int targetSum){
		if(sum == targetSum){
			printSubset(t, tSize);
			if(itr + 1 < s.length && sum - s[itr] + s[itr+1] <= targetSum ){
				subsetSum(s, t, tSize-1, itr+1, sum-s[itr], targetSum);
			}
			return;
		} else{
			if(itr < s.length && sum + s[itr] <= targetSum ){
				for (int i = itr; i < s.length; i++) {
					t[tSize] = s[i];
					subsetSum(s, t, tSize+1, i+1, sum+s[i], targetSum);				
				}
			}
		}
	}
	
	private static void printSubset(int[] t, int tSize) {		
		System.out.print("{ ");
		for (int i = 0; i < tSize; i++) {
			System.out.print(t[i]+" ");
		}
		System.out.print("}");
	}

	public static void generateSubsets(int [] s, int targetSum){
		int [] t = new int[s.length];
		Arrays.sort(s);
		for (int i : s) {
			System.out.print(i+" ");
		}
		System.out.println();
		int total = 0;
	    for( int i = 0; i < s.length; i++ ){
	        total += s[i];
	    }
	    if( s[0] <= targetSum && total >= targetSum ){
	    	subsetSum(s, t, 0, 0, 0, targetSum);
	    }
	}
	
	public static void main(String[] args) {
		int weights[] = {10, 7, 5, 18, 12, 20, 15};
	 
	    generateSubsets(weights, 35);

	}

}
