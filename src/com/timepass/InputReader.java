package com.timepass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputReader {
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
        	   System.err.println(ioException.getMessage());
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
			System.err.println(nfException.getMessage());
		}
		
		return nextInt;
	}
	
	public static void main(String[] args) {
		InputReader ir = new InputReader(System.in);
		int tc = ir.nextInt();
		for(int i=0; i<tc; i++){
			System.out.println("a: "+ir.nextInt()+" b: "+ir.nextInt());
		}

	}
	
	
}
