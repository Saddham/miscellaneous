import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
class TestClass { 
	private static int n, m, s, a, h;
	private static int [][] graph;

	// Solves the all-pairs shortest path problem using Floyd Warshall algorithm
	public static long floydWarshell(){

	    long [][] dist = new long[n][n];
	    int i, j, k;
	 
	    for (i = 0; i < n; i++){
	        for (j = 0; j < n; j++){
	        	if(i==j){
	        		dist[i][j] = 0L;
	        	} else if(graph[i][j]==0){
	        		dist[i][j] = (long) Integer.MAX_VALUE;
	        	} else{
	        		dist[i][j] = (long) graph[i][j];
	        	}
	        }
		}
	    		
	    for (k = 0; k < n; k++) {
	        for (i = 0; i < n; i++){
	            for (j = 0; j < n; j++){
	                if (dist[i][k] + dist[k][j] < dist[i][j])
	                    dist[i][j] = dist[i][k] + dist[k][j];
	            }
	        }
	    }
	    
	    long ans = Integer.MIN_VALUE;
	    long local;
	    for (k = 0; k < n; k++) {
	    	if(!(k==s || k==a || k==h)){
	    		local = dist[s][k]+2*dist[k][a]+dist[k][h];
	    		if(local>ans){
	    			ans = local;
	    		}
	    	}
	    }
	    /*
	    for (i = 0; i < n; i++){
	        for (j = 0; j < n; j++){
	        		System.out.print(dist[i][j]+" ");
	        }	 
	       	System.out.println();
       }
	    */
	    
	   return ans;	    
	}

    public static void main(String args[] ) throws Exception {
		final InputReader ir = new InputReader(System.in);
		int tc = ir.nextInt();
		StringBuilder sb = new StringBuilder();
		
		int v, w, wt;
		for(int t=tc; t>0;t--){
			n = ir.nextInt();
			m = ir.nextInt();
			graph = new int[n][n];			
			
			for(int i=0; i<m; i++){
				v = ir.nextInt();
				w = ir.nextInt();
				wt = ir.nextInt();
				if(graph[v-1][w-1]>0 && graph[v-1][w-1]<wt) continue;
				graph[v-1][w-1] = wt;
				graph[w-1][v-1] = wt;
			}
			
			s = ir.nextInt();
			a = ir.nextInt();
			h = ir.nextInt();
			s--;
			a--;
			h--;
			
			sb.append(floydWarshell());
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