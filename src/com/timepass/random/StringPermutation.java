package com.timepass.random;

import com.sun.org.apache.bcel.internal.generic.SWAP;

public class StringPermutation {
	
	public static void permute(String str){
		permute(new StringBuilder(str), 0, str.length()-1);
	}
	
	private static void permute(StringBuilder str, int l, int r){
		if(l==r){
			System.out.println(str.toString());
		} else{
			for(int i=l; i<=r; i++){
				swap(str, l, i);
				permute(str, l+1, r);
				swap(str, l, i);
			}		
		}
	}
	
	private static void swap(StringBuilder str, int i, int j){
		char ch = str.charAt(j);
		str.setCharAt(j, str.charAt(i));
		str.setCharAt(i, ch);
	}
	
	
	
	public static void main(String[] args) {
		String str = "ABC";
		permute(str);
	}

}
