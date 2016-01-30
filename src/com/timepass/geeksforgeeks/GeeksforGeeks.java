package com.timepass.geeksforgeeks;

import java.util.Arrays;

public class GeeksforGeeks {
	
	public static int ceilIndex(int [] a, int l, int r, int key){
		int m;
		
		while((r-l) > 1){
			m = (l+(r-l)/2);
			if(a[m] >= key){
				r = m;
			} else{
				l = m;
			}
		}
		
		return r;
	}
	
	public static int longestIncreasingSubsequenceLength(int [] a){
		int [] tailIndex = new int[a.length];
		int length;
		Arrays.fill(tailIndex, 1);
		tailIndex[0] = a[0];
		length = 1;
		
		for (int i = 1; i < a.length; i++) {
			if(a[i] < tailIndex[0]){
				tailIndex[0] = a[i];
			} else if(a[i] > tailIndex[length-1]){
				tailIndex[length++] = a[i];
			} else{
				tailIndex[ceilIndex(a, 0, length-1, a[i])] = a[i];
			}
			
			System.out.print(tailIndex[length-1]+" ");
		}
		
		return length;			
	}
	
	public static int longestCommonSubsequenceLength(String a, String b){
		int [] tailIndex = new int[257];
		StringBuilder subsequence = new StringBuilder();
		
		for (int i = 0; i < a.length(); i++) {
			tailIndex[(int)a.charAt(i)] += 1;
			tailIndex[(int)b.charAt(i)] += 1;
		}
		
		for (int i = 0; i < tailIndex.length; i++) {
			if((tailIndex[i] >>> 1) == 1){
				subsequence.append((char)i);
			}
		 }
		
		return subsequence.length();			
	}
	
	public static int longestCommonSubsequenceLengthV2(String a, String b, int m, int n){
		if(m==0 || n==0){
			return 1;
		}
		if(a.charAt(m) == b.charAt(n)){
			return 1+longestCommonSubsequenceLengthV2(a, b, m-1, n-1);			
		} 
		
		return Math.max(longestCommonSubsequenceLengthV2(a, b, m, n-1), longestCommonSubsequenceLengthV2(a, b, m-1, n));					
	}
	
	public static int findEditDistance(String a, String b, int m, int n){
		if(m==0 || n==0){
			return 0;
		} 
		if(m==0){
			return n;			
		} 
		if(n==0){
			return m;			
		} 
		
		int right = findEditDistance(a, b, m, n-1)+1;
		int left = findEditDistance(a, b, m-1, n)+1;
		int corner = findEditDistance(a, b, m-1, n-1)+(a.charAt(m-1) != b.charAt(n-1)?2:0);
		
		return Math.min(Math.min(right, left), corner);					
	}
	
	private static boolean isPalindrome(String str){
		if(str == null){
			return false;
		} else if(str.length()==2){
			return str.charAt(0)==str.charAt(1);
		} else if(str.length()>2){		
			int len = str.length()-1;
			for (int i = 0; i < len/2; i++) {
				if(str.charAt(i) != str.charAt(len-i))
					return false;
			}
		}
		return true;
	}
	
	public static int numberOfPalindromes(String str){
		int result = 0;
		for(int i=0; i<str.length(); i++){
			String sb = null;
			for(int j=0; (i+j)<str.length();j++){
				sb = str.substring(j, i+j+1);
				result += isPalindrome(sb)?1:0;
			}						 
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		int a[] = {2, 5, 3, 7, 11, 8, 10, 13, 6 };	    
	 
	    System.out.println("Length of Longest Increasing Subsequence is "+
	    		longestIncreasingSubsequenceLength(a));
	    
	    
	    String str1 = "ABKCDSJKGH";
	    String str2 = "AEKDLSFAHR";
	    
	    System.out.println("Length of Longest Common Subsequence is "+
	    		longestCommonSubsequenceLength(str1, str2));
	    
	    System.out.println("Length of Longest Common Subsequence is "+
	    		longestCommonSubsequenceLengthV2(str1, str2, str1.length()-1, str2.length()-1));	    
	    
	    str1 = "INTENTION";
	    str2 = "EXECUTION";
	    System.out.println("Minimum edit distance "+
	    		findEditDistance(str1, str2, str1.length()-1, str2.length()-1));
	    
	    System.out.println("Number of palindrome sub strings in given string: "+ numberOfPalindromes("aba"));
	}

}
