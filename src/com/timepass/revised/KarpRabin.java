package com.timepass.revised;

public class KarpRabin {
	private int R = 256;
	private int rp = 0;
	private int rs = 0;
	private int h = 1;
	private int q = 101;
	private int patternSize;
	
	public KarpRabin(int patternSize){
		this.patternSize = patternSize; 
	}
	
	public int issub(String str, String pattern){
		calH();
		calRp(pattern);
		calRs(str);
		int index = -1;
			
		for (int i = 0; i < str.length()-patternSize+1; i++) {
			
			if(rs==rp){
				index = isEqual(str, pattern, i);
				if(index>0){
					return index; 
				}
			}
			
			if(i<str.length()-patternSize){
				replace(str.charAt(i), str.charAt(i+patternSize));
			}
		}
						
		return index;
	}

	private int isEqual(String str, String pattern, int i) {
		int flag = i;
		for (int j=0; j < patternSize; j++) {
			if(str.charAt(i+j) != pattern.charAt(j)){
				flag = -1;
			}
		}
		
		return flag;
	}

	private void replace(char old, char cur) {
		rs = ((rs -  old*h)*R+ cur)%q;
		if(rs<0) rs += q;
	}

	private void calRp(String pattern) {
		for (int i = 0; i < pattern.length(); i++) {
			rp = (rp*R + pattern.charAt(i))%q;
		}
	}

	private void calRs(String str) {
		for (int i = 0; i < patternSize; i++) {
			rs = (rs*R + str.charAt(i))%q;
		}
	}
	
	private void calH() {
		int temp=256;
		int length=patternSize-1;
		while(true){
			if((length & 1) > 0) h = (h*temp)%q;
			if(length<=0) return;
			length = length>>>1;
			temp = temp*temp;
		}		
	}

	public static void main(String[] args) {
		String str = "Saddam";
		String pattern = "ddam";
		KarpRabin kr = new KarpRabin(pattern.length());
		System.out.println(kr.issub(str, pattern));
	}

}
