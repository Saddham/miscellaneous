import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.Array;

class TestClass { 
	private static ArrayList<Edge> [] edges;
	private static boolean [] visited;
	private static PriorityQueue<Edge> pq;
	
	public static long prim(){
		pq = new PriorityQueue<Edge>();
		pq.add(new Edge(0, 0, 1L));
		int v, w;
		long ans = 1;
		Edge tmp;
		
		while(!pq.isEmpty()){
			tmp = pq.remove();
			v = tmp.either();
			v = tmp.other(v);
			if(visited[v]) continue;
			ans = (ans*tmp.weight())%1000000007L;
			//System.out.println(tmp.weight());
			if(edges[v].size()>0){
				for(Edge edge : edges[v]){
					w = edge.other(v);					
					if(!visited[w]){
						pq.add(edge);
					//	System.out.println(pq.peek().weight());
					}
				}			
			}
			
			visited[v] = true;
		}
		
		return ans;		
		
	}
	
    public static void main(String args[] ) throws Exception {
		final InputReader ir = new InputReader(System.in);
		int tc = ir.nextInt();
		StringBuilder sb = new StringBuilder();
		
		int n, m, v, w, e, i;		
		long weight;
		for(int t=tc; t>0;t--){
			n = ir.nextInt();
			m = ir.nextInt();
			visited = new boolean[n];
			edges = (ArrayList<Edge> []) Array.newInstance(ArrayList.class, m);
			for(i=0; i<n; i++){
				edges[i] = new ArrayList<Edge>();
			}
			for(i=0; i<m; i++){
				v = ir.nextInt();
				w = ir.nextInt();
				weight = ir.nextLong();
				edges[v-1].add(new Edge(v-1, w-1, weight));
				edges[w-1].add(new Edge(w-1, v-1, weight));
			}
			
			long ans = prim();
			sb.append(ans);
			sb.append("\n");			
		}
		
		System.out.println(sb.toString());
    }
    
    private static class Edge implements Comparable<Edge>{
    	private int v;
    	private int w;
    	private long weight;
    	
    	public Edge(int v, int w, long weight){
    		this.v = v;
    		this.w = w;
    		this.weight = weight;
    	} 
    	
	    public long weight(){
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