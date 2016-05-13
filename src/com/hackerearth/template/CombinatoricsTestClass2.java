import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
class TestClass { 
	public static final int MAX_VAL = 100000;
	public static long [] fact = new long[MAX_VAL+1];
	public static long [] invfact = new long[MAX_VAL+1];
	public static final long MOD = 1000000007L;
	
		
	public static long inverse(long base, long expo){
		if(expo==0)
			return 1;
		else if((expo&1)>0)
			return base*inverse(base, expo-1)%MOD;
		else{
			long root=inverse(base, expo>>>1);
			return root*root%MOD;
		}
	}
 
	
	public static void preCompute(){
		int i;
		fact[0] = 1;
		for(i=1; i<=MAX_VAL; i++){
			fact[i] = (i*fact[i-1])%MOD;
		}
				
		invfact[MAX_VAL] = inverse(fact[MAX_VAL], MOD-2);
		for(i=MAX_VAL; i>0; i--){
			invfact[i-1] = (i*invfact[i])%MOD;
		}		
	}
		
    public static void main(String args[] ) throws Exception {
		final InputReader ir = new InputReader(System.in);
		int tc = ir.nextInt();
		StringBuilder sb = new StringBuilder();
		
		preCompute();
		int n, k, i;
		long [] strengths;
		long ans;
		long ta;
		
		for(int t=tc; t>0;t--){
			n = ir.nextInt();
			k = ir.nextInt();					
			ans = 0;
			strengths = new long[n];
			for(i=0; i<n; i++){
				strengths[i] = ir.nextLong();
			}
			
			Arrays.sort(strengths);
			
			for(i=0; i<(n-k+1); i++){
				
				ta = fact[n-i-1]%MOD;
				ta = (ta * invfact[k-1])%MOD;
				ta = (ta * invfact[n-i-k])%MOD;
				ta = (ta * strengths[i])%MOD;
				
				ans = (ans+ta)%MOD;
			}
			
			sb.append(ans);
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

//http://en.wikipedia.org/wiki/Sprague%E2%80%93Grundy_theorem#Proof