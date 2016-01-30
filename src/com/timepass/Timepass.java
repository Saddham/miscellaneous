package com.timepass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.lang.reflect.Array;

class Timepass {
	static long goodMask; // 0xC840C04048404040 computed below
	public static void main(String args[] ) throws Exception {
		genericArrayTest();
		/*int initialCapacity = 8;
		 initialCapacity |= (initialCapacity >>>  1);
		 System.out.println( initialCapacity);
		 initialCapacity |= (initialCapacity >>>  2);
		 initialCapacity |= (initialCapacity >>>  4);
		 initialCapacity |= (initialCapacity >>>  8);
		 initialCapacity |= (initialCapacity >>> 16);
		 initialCapacity++;
		 
		 System.out.println( initialCapacity);
		
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        String specialString;
			try {
				specialString = br.readLine().intern();
				if(specialString.length() <= (500000)){	
				int N = Integer.parseInt(br.readLine());
				if(N>=1 && N <= 500000){
				for (int i = 0; i < N; i++) {
					String [] query = br.readLine().split(" ");
					int  s = Integer.parseInt(query[0]);
					int e = Integer.parseInt(query[1]);
					if((s>=1 && s<=e) && (e>=s && e<=specialString.length())){	        	
					Pattern pattern = Pattern.compile("[HackerEarth][HackerEarth][HackerEarth][HackerEarth][HackerEarth][HackerEarth][HackerEarth][HackerEarth][HackerEarth][HackerEarth][HackerEarth]", Pattern.CASE_INSENSITIVE);
					
					Matcher matcher = null;
				
					int count = 0;
					if(0 == (e-11)){
						matcher = pattern.matcher(specialString.substring(s-1, e));
		
							if(matcher.find()){
								count = 1;
							}
							
					} else{
						int j = 0;
						while(j < (e-11)){
							matcher = pattern.matcher(specialString.substring(s-1+j, e));
		
							while(matcher.find()){
								count++;
							}
							j++;
						}
					}
					System.out.println(count);
				}	
				}
				
				}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}	*/
		
	/*	double a=8;
		double b=64;
		
		double n = Math.log(b)/Math.log(a);
		double e = Math.ceil(n);
		double epsilon = 1.86786786788887454564657e-31;
		System.out.println(epsilon);
		if((n/e) == 1){
			System.out.println("true");
		}*/
		
		
/*		String str = "54_hfghfgh_";
		Pattern ptr = Pattern.compile("\\w+(?=\\w{1})");
		Matcher mtr = ptr.matcher(str);
		if(mtr.find())
			System.out.println("true");*/	
		
		/*
		for (int i=0; i<64; ++i) goodMask |= Long.MIN_VALUE >>> (i*i);
		
		long num = 64;
		if(isSquare(num)){
			System.out.println("Yes");
		} else{
			System.out.println("No");
		}*/
		
/*		int n=10;
		intpart("", n, n);*/
		
		/*int val[] = {60, 100, 120};
	    int wt[] = {10, 20, 30};
	    int  W = 50;
	    int n = val.length;
	    System.out.println(knapSack(W, wt, val, n));
		
		System.out.println(replaceWhiteSpaces("rthty ryt bg y"));*/
  }

	public static boolean isSquare(long x) {
		
	    // This tests if the 6 least significant bits are right.
	    // Moving the to be tested bit to the highest position saves us masking.
	    if (goodMask << x >= 0) return false;
	    final int numberOfTrailingZeros = Long.numberOfTrailingZeros(x);
	    // Each square ends with an even number of zeros.
	    if ((numberOfTrailingZeros & 1) != 0) return false;
	    x >>= numberOfTrailingZeros;
	    // Now x is either 0 or odd.
	    // In binary each odd square ends with 001.
	    // Postpone the sign test until now; handle zero in the branch.
	    if ((x&7) != 1 | x <= 0) return x == 0;
	    // Do it in the classical way.
	    // The correctness is not trivial as the conversion from long to double is lossy!
	    final long tst = (long) Math.sqrt(x);
	    return tst * tst == x;
	}
	
	
	public static void intpart(String pref, int n, int maxi){
		if(n==0){
			System.out.println(pref);
			return;
		}
		for(int i=1; i<=maxi && i<=n; i++)
			intpart(pref+(pref==""?"":"+")+i, n-i,i);
	}
	
	public static int max(int a, int b) { return (a > b)? a : b; }
	
	// Returns the maximum value that can be put in a knapsack of capacity W
	public static int knapSack(int W, int wt[], int val[], int n)
	{
	   int i, w;
	   int K[][] = new int[n+1][W+1];
	 
	   // Build table K[][] in bottom up manner
	   for (i = 0; i <= n; i++)
	   {
	       for (w = 0; w <= W; w++)
	       {
	           if (i==0 || w==0)
	               K[i][w] = 0;
	           else if (wt[i-1] <= w)
	                 K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]);
	           else
	                 K[i][w] = K[i-1][w];
	       }
	   }
	 
	   return K[n][W];
	}
	
	
	public static String replaceWhiteSpaces(String str){
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(sb, "%20");
		}
		
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	public static void genericArrayTest(){
		Object [] test = new Object[10];
		test[0] = new ArrayList<Integer>();
		((ArrayList<Integer>)test[0]).add(10);
		System.out.println(((ArrayList<Integer>)test[0]).get(0));
		
		ArrayList<Integer> [] test2 = (ArrayList<Integer> []) Array.newInstance(ArrayList.class, 58);
		test2[0] = new ArrayList<Integer>();
		test2[0].add(20);
		System.out.println(test2[0].get(0));
	}
	
}
