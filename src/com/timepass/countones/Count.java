package com.timepass.countones;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Count {

	public static void main(String[] args) {
		countOnes(1000);
	}

	public static int countOnes(int power){
		int count = 0;
		
		if(power < 2){
			count = power + 1; 
		} else{
			StringBuilder number = new StringBuilder("11");			
			while((--power) > 0){
				char [] charArray = number.toString().toCharArray();
				int carry = 0;
				number = new StringBuilder(""+charArray[charArray.length-1]);
				
				for (int i = charArray.length-1; i > 0 ; i--) {
					int temp = Integer.parseInt(""+charArray[i]) + Integer.parseInt(""+charArray[i-1]) + carry;

					carry = temp/10;
					temp = temp%10;
				
					number.append(temp);				
				}
			
				number.append(Integer.parseInt(""+charArray[0])+carry);
				number.reverse();
				
			}
			
			Pattern pattern = Pattern.compile("1");
			Matcher matcher = pattern.matcher(number);
			
			while(matcher.find()){
				count++;
			}
		}
		
		return count;
	}
}
