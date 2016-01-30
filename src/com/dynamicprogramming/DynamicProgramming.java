package com.dynamicprogramming;

public class DynamicProgramming {

	public static int matrixChainOrder(int [] pi, int s, int e){
		if(s==e)
			return 0;
				
		int count;
		int min=Integer.MAX_VALUE;
		
		for(int k=s; k<e; k++){
			count = matrixChainOrder(pi, s, k)+
					matrixChainOrder(pi, k+1, e)+
					pi[s-1]*pi[k]*pi[e];
			
			if(count<min){
				min = count;
			}
		}
		
		return min;
	}
	
	public static void booleanTest(){
		boolean [] bools = new boolean[1];
		System.out.println(bools[0]);
	}
	
	public static void stringTest(){
		String str1 = new String("different");
		String str2 = new String("different");
		
		System.out.println(str1==str2);
		System.out.println(str1.equals(str2));
		str1 = "same";
		str2 = "same";
		System.out.println(str1==str2);
	}
	
	public static void main(String[] args) {
		int pi[] = {40, 20, 30, 10, 30};
		System.out.println(matrixChainOrder(pi, 1, pi.length-1));
		booleanTest();
		stringTest();
	}

}
