import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
class TestClass { 
    public static void main(String args[] ) throws Exception {
		final InputReader ir = new InputReader(System.in);
		int tc = ir.nextInt();
		StringBuilder sb = new StringBuilder();
		
		int n, m, v, w, e;
		Edge [] edges = new Edge [40005];
		Set<Integer> set;
		for(int t=tc; t>0;t--){
			n = ir.nextInt();
			m = ir.nextInt();
			for(int i=0; i<m; i++){
				v = ir.nextInt();
				w = ir.nextInt();
				edges[i] = new Edge(v, w, 0F);
			}
			
			set = new HashSet<Integer>();
			for(int j=0; j<n-1;j++){
				e = ir.nextInt();
				Edge edge = edges[e-1];
				v = edge.either();
				set.add(v);
				set.add(edge.other(v));
			}
			if(set.size()==n){
				sb.append("YES");
			} else{
				sb.append("NO");
			}
			
			sb.append("\n");			
		}
		
		System.out.println(sb.toString());
    }
    
    private static class Edge implements Comparable<Edge>{
    	private int v;
    	private int w;
    	private double weight;
    	
    	public Edge(int v, int w, double weight){
    		this.v = v;
    		this.w = w;
    		this.weight = weight;
    	} 
    	
	    public double weight(){
			return weight;
		}
		
		public int either(){
			return v;
		}
		
		public int other(int x){
			if(x == v) return w;
			else if(x == w) return v;
			else throw new IllegalArgumentException();
		}
		    	
    	public int compareTo(Edge other){
    		return (this.weight>=other.weight?(this.weight>other.weight?1:0):-1);
    	}
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