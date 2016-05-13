import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
class TestClass { 
	public static long [] arr;
	public static int n;		
	public static final int MAX = 10000005;
	public static boolean [] prime = new boolean[MAX];
	public static int [] in;
	
	public static void sieve()
	{
		int i, j;
		prime[1] = true;
		for(i = 2;i < 10005;++i)
		{
			if(!prime[i])
				for(j = i*i;j < MAX;j += i)
					prime[j] = true;
		}
	}
	
	public static void update(int x, int val){
		for(; x<=n; x += (x&-x)){
			arr[x] += val;
		}
	}
	
	public static long query(int x){
		long sum = 0;
		for(; x>0; x -= (x&-x)){
			sum += arr[x];
		}
		
		return sum;
	}
	
    public static void main(String args[] ) throws Exception {
		final InputReader ir = new InputReader(System.in);
		int tc = ir.nextInt();
		StringBuilder sb = new StringBuilder();
		sieve();
		int i, t, spot, op, x, y, q;
		for(t=tc; t>0;t--){
			n = ir.nextInt();
			arr = new long[n+1];
			in = new int[n+1]; 
			for(i=1; i<=n; i++){
				spot = ir.nextInt();
				in[i] = spot;
				if(!prime[spot]){
					update(i, 1);
				}
			}
			
			q = ir.nextInt();
			for(i=1; i<=q; i++){				
				op = ir.nextChar();
				x = ir.nextInt();
				y = ir.nextInt();
				if(op=='A'){
					sb.append(query(y)-query(x-1));
					sb.append("\n");
				} else{
					if(!prime[y]){
						if(prime[in[x]])
							update(x, 1);
					} else{
						if(!prime[in[x]])
							update(x, -1);
					}
					in[x] = y;
				}												
			}			
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
	    
	    public int nextChar(){
			int nextInt = Integer.MIN_VALUE;
			try{
				nextInt = next().charAt(0);
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