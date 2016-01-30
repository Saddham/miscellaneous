package com.hackerearth.easy.panda;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
class TestClass { 
	public static long [] array;
	public static StringBuilder sb = new StringBuilder();
	public static int n;
	public static long k;
	public static boolean flag;
	public static long ans;
	
	public static void solve(){
		long prev;
		long temp, first;
		int i, j;
		int count1 = 0;
		int count2 = 0;
		if(k>n/2){
			ans = array[0];
			for(i=1; i<n; i++){
				ans &= array[i];
			}
			
			flag = true;
		} else{
			for(j=0; j<32; j++){
				if(count1==n || count2==n)
					break;
				prev = array[n-1];
				first = array[0];
				count1 = 0;
				count2 = 0;
				for(i=0; i<n; i++){				
					if(i==n-1){
						array[i] = (prev & array[i] & first);
					} else{					
						temp = (prev & array[i] & array[i+1]);
						prev = array[i];
						array[i] = temp;
					}
					
					if(array[i]==0){
						count1++;
					}
					if(array[i]==1){
						count2++;
					}
				}	
			}
		}
	}
	
	public static void print(){
		if(flag){
			for(int i=0; i<n; i++){
				sb.append(ans);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("\n");
			flag = false;
			ans = 0L;
		} else{			
			for(int i=0; i<n; i++){
				sb.append(array[i]);
				sb.append(" ");
			}
			sb.deleteCharAt(sb.length()-1);
			sb.append("\n");
		}
	}
	
    public static void main(String args[] ) throws Exception {
		final InputReader ir = new InputReader(System.in);
		int tc;
		tc = ir.nextInt();		

		int i, t;
		for(t=0; t<tc; t++){
			n = ir.nextInt();
			k = ir.nextLong();
			array = new long[n];	
			for(i=0; i<n; i++){
				array[i] = ir.nextLong();
			}
			solve();
			print();			
		}
		
		System.out.println(sb.toString());
    }
    
    private static class InputReader {
		private BufferedReader br;
		private StringTokenizer tokens;
		
		
		public InputReader(InputStream ins){
			br = new BufferedReader(new InputStreamReader(ins));
		}
		
		public String next(){
			String curToken = null;
			if(tokens==null || !tokens.hasMoreTokens()){
	           try{
				tokens = new StringTokenizer(br.readLine(), " ");			
	           } catch(IOException ioException){
	        	  ioException.printStackTrace();
	           }
			}
			
			if(tokens.hasMoreTokens()){
				curToken = tokens.nextToken();
			}
	        
			return curToken;
		}
		
	    public int nextInt(){
			int nextInt = Integer.MIN_VALUE;
			try{
				nextInt = Integer.parseInt(next());
			} catch(NumberFormatException nfException){
				nfException.printStackTrace();
			}
			 
			return nextInt;
		}
	    
		public long nextLong(){
			long nextLong = Integer.MIN_VALUE;
			try{
				nextLong = Long.parseLong(next());
			} catch(NumberFormatException nfException){
				nfException.printStackTrace();
			}
			 
			return nextLong;
		}	
	}
}