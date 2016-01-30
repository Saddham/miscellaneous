package com.timepass.string.substring.matching;

public class KarpRabin {
	private long rt = 0;
	private long rs = 0;
	private long prime = 5;
	private long alphabet = 256;
	private int patternSize;
	
	public KarpRabin(int size){
		patternSize = size;
	}
	
	public void append(int c){
		rt = ((rt * alphabet % prime) + c) % prime;
	}
	
	public void appendPattern(int c){
		rs = ((rs * alphabet % prime) + c) % prime;
	}
	
	public void skip(int c){
		rt = (rt+prime- c * (long)Math.pow(alphabet, patternSize-1) % prime) % prime; 
	}
	
	public boolean contains(String pattern, String inputString){
		for (int i = 0; i < pattern.length(); i++) {
			appendPattern(pattern.charAt(i));
		}
		
		for (int i = 0; i < pattern.length(); i++) {
			append(inputString.charAt(i));
		}

		if (rs == rt) {
			int c=0;
			for (int i = 0; i < pattern.length(); i++) {
				if(inputString.charAt(i) != pattern.charAt(i)){
					c=1;
					break;
					}
			}		
			if(c==0){
				return true;
			}
		} 
			for (int i = 0; i < (inputString.length()-patternSize); i++) {
				skip(inputString.charAt(i));
				append(inputString.charAt(i+patternSize));
				if (rs == rt) {
					boolean found = true;
					for (int j = 0; j < patternSize; j++) {
						if(inputString.charAt(i+j+1 ) != pattern.charAt(j)){
							found = false;
							break;
						}
					}
					if(found)
						return true;
				}
			}
				
		
		return false;
	}
	
	public static void main(String[] args) {
		String pattern = "tykada";
		String inputString = "fhftykada";
		KarpRabin karpRabin = new KarpRabin(pattern.length());
		System.out.println(karpRabin.contains(pattern, inputString));
	}
}
