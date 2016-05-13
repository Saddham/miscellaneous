import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
class TestClass { 
	public static int MAX_VAL = 1001;
	public static long [][] ncr = new long[MAX_VAL][MAX_VAL];
	
	public static void preCompute(){
		int i, j;
		ncr[0][0] = 1;
		for(i=1; i<MAX_VAL; i++){
			ncr[i][0] = 1;
			ncr[0][i] = 0;
		}
		
		for(i=1; i<MAX_VAL; i++){
			for(j=1; j<MAX_VAL; j++){
				ncr[i][j] = ncr[i-1][j-1] + ncr[i-1][j];
			}
		}
		
	}
	
	
    public static void main(String args[] ) throws Exception {
		final InputReader ir = new InputReader(System.in);
		int tc = ir.nextInt();
		StringBuilder sb = new StringBuilder();
		
		preCompute();
		int n, r;
		for(int t=tc; t>0;t--){
			n = ir.nextInt();
			r = ir.nextInt();					
			
			sb.append(ncr[n][r]);
			sb.append("\n");			
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