package com.timepass;

import java.util.ArrayList;
import java.util.List;

public class RabinKarp {
	private static final int d = 256;
	private static final int q = 101;
	private static int h;
	
	private static void computeH(int m){
		 h = 1;
		 int _d = d;
		 while(m>0){
			 if((m&1) > 0) h = (_d*h)%q;
			 m=m>>>1;
			 _d=(_d*_d)%q;			 
		 }		 
	}
	
	private static int hash(String str, int len){
		int hash=0; 
		for (int i=0; i<len; i++) {
			hash = (hash*d+str.charAt(i))%q;
		}
		
		return hash;
	}
	
	public static List<Integer> searchPattern(String pattern, String input){
		List<Integer> pos = new ArrayList<Integer>();
		int m = pattern.length();
		int n = input.length();
		computeH(m-1);
		int pHash = hash(pattern, m);
		int iHash = hash(input, m);
		
		for(int i=0; i < n-m; i++){
			if(pHash == iHash){
				int j;
				for(j=0; j<m; j++){
					if(input.charAt(i+j) != pattern.charAt(j)) break;
				}
				if(j==m){
					pos.add(i);
				}
			}
			
			if(i < (n-m)){
				iHash = (d*(iHash-input.charAt(i)*h)+input.charAt(i+m))%q;
				
				if(iHash < 0){
					iHash = iHash+q;
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
