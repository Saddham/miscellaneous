package com.timepass;

import java.util.HashMap;
import java.util.Map;

public class Histogram {
	
	public static void solve(String str){
		Map<Character, Integer> histogram = new HashMap<Character, Integer>();
		boolean flag = false;
		
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(histogram.get(ch) != null){
				histogram.put(ch, histogram.get(ch)+1);
			} else{
				histogram.put(ch, 1);
			}
		}
		
		for (int i = str.length(); i > 0 ; i--) {
			for (int j = 0; j < str.length(); j++) {
				char ch = str.charAt(j);
				if(histogram.get(ch) == i){
					System.out.print("*");
					histogram.put(ch, histogram.get(ch)-1);
					flag=true;
				}
			}
			if(flag==true){
				System.out.println();
				flag=false;
			}
		}
	}
	
	public static void main(String[] args) {
		String str = "1117567663595489785";
		solve(str);

	}

}
