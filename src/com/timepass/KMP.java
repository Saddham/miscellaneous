package com.timepass;

import java.util.ArrayList;
import java.util.List;

public class KMP {

	private static void computeLPS(int [] lps, String pattern){
		int m = lps.length;
		int i = 1;
		int len = 0;
		lps[0] = 0;		
		
		while(i<m){
			if(pattern.charAt(i) == pattern.charAt(len)){
				len++;
				lps[i] = len;
				i++;
			} else{
				if(len != 0){
					len = lps[len-1];
				} else{
					lps[i] = 0;
					i++;
				}
			}
		}
	}
	
	public static List<Integer> searchPattern(String pattern, String input){
		int [] lps = new int[pattern.length()];
		computeLPS(lps, pattern);		
		int i = 0;
		int j = 0;
		int M = pattern.length();
		int N = input.length();		
		List<Integer> pos = new ArrayList<Integer>();
		
		while(i<N){
			if(pattern.charAt(j) == input.charAt(i)){
				i++;
				j++;
			}
			
			if(j == M){
				pos.add(i-j);
				j = lps[j-1];
			} else if(i<N && pattern.charAt(j)!=input.charAt(i)){
				if(j != 0){
					j = lps[j-1];
				} else{
					i++;
				}
			}
		}
		
		
		return pos;
	}
	
	public static void main(String[] args) {
		String input = "ABABDABABCABABABACDABABCABAB";
		String pattern = "ABABCABAB";
		int m = pattern.length();
		for (int pos : searchPattern(pattern, input)) {
			System.out.println("Pattern found at "+ pos+" "+input.substring(pos, pos+m));
		}
	}

}
