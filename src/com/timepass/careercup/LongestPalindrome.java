package com.timepass.careercup;

public class LongestPalindrome {
	
	public static int longestPalSubstr(String str){
		int n = str.length();
		boolean [][] table = new boolean[n][n];
		
		for(int i=0; i<n; i++){
			table[i][i] = true;
		}				
		
		int start = 0;
		int maxLength = 1;
		
	    for (int i = 0; i < n-1; ++i)
	    {
	        if (str.charAt(i) == str.charAt(i+1))
	        {
	            table[i][i+1] = true;
	            start = i;
	            maxLength = 2;
	        }
	    }
		
		for(int l=3; l<=n ; l++){			
			for(int i=0; i<n-l+1; i++){				
				int j = i+l-1;
				if(table[i+1][j-1] && str.charAt(i)==str.charAt(j)){
					table[i][j] = true;
					if(l>maxLength){
						start = i;
						maxLength = l;
					}
				}
			}			
		}
			
		System.out.println(str.substring(start, start+maxLength));
		return maxLength;
	}
	
	
	public static int longestPalSubstr_v2(String str){
		int maxLength = 1;
		int start = 0;
		int n = str.length();
		for(int l=1; l<n; l++){
			int low = l-1;
			int high = l;
			while(low>=0 && high<n && (str.charAt(low)==str.charAt(high))){
				if((high-low+1) > maxLength){
					start = low;
					maxLength = high-low+1; 
				}
				
				low--;
				high++;
			}
			
			low = l-1;
			high = l+1;
			while(low>=0 && high<n && (str.charAt(low)==str.charAt(high))){
				if((high-low+1) > maxLength){
					start = low;
					maxLength = high-low+1; 
				}
				
				low--;
				high++;
			}			
		}
		
		System.out.println(str.substring(start, start+maxLength));
		return maxLength;
	}
	
	
	public static void main(String[] args) {
		System.out.println(longestPalSubstr("abrba"));
		System.out.println(longestPalSubstr_v2("abrba"));
	}

}
