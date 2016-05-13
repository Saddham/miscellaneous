import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
class TestClass { 
	private static Subset [] subsets; 
	private static int m;
	private static Edge [] edges;
	
	private static int CUT_OFF = 10;
	
	public static void sort(Comparable dest[]){
		Comparable[] aux = dest.clone();
		sort(aux, dest, 0, dest.length-1);
	}
	
	private static void sort(Comparable[] src, Comparable[] dest, int lo, int hi) {
		if((hi - lo) <= 0) return;
		
		if(hi <= lo+CUT_OFF){
			insertionSort(dest, lo, hi);
			return;
		}
		
		int mid = (hi+lo)/2;
		sort(dest, src, lo, mid);
		sort(dest, src, mid+1, hi);
		
		if(!less(src[mid+1], src[mid])){
			System.arraycopy(src, lo, dest, lo, hi-lo+1);
			return;
		}
		
		merge(src, dest, lo, mid, hi);
	}

	private static void merge(Comparable[] src, Comparable[] dest, int lo, int mid, int hi) {
		int i = lo, j = mid+1;
		for (int k = lo; k <= hi; k++) {
			if(i > mid) dest[k] = src[j++];
			else if(j > hi) dest[k] = src[i++];
			else if(less(src[j], src[i])) dest[k] = src[j++];
			else dest[k] = src[i++];
		}
	}

	private static void insertionSort(Comparable[] dest, int lo, int hi) {
		for (int i = lo; i <= hi; i++) {
			for (int j = i; (j > lo) && less(dest[j], dest[j-1]); j--) {
				exchange(dest, j, j-1);
			}
		}
		
	}

	private static void exchange(Comparable[] dest, int i, int j) {
		Comparable temp = dest[i];
		dest[i] = dest[j];
		dest[j] = temp;		
	}

	private static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	
	private static int find(int x){
		if(subsets[x].parent != x){
			subsets[x].parent = find(subsets[x].parent);
		}
		
		return subsets[x].parent;
	}
	
	public static void union(int i, int j){
		i = find(i);
		j = find(j);
		
		if(subsets[i].rank < subsets[j].rank){
			subsets[i].parent = j;
		} else if(subsets[i].rank > subsets[j].rank){
			subsets[j].parent = i;
		} else{
			subsets[j].parent = i;
			subsets[i].rank = subsets[i].rank+1;
		}
	}
	
    public static void main(String args[] ) throws Exception {
		final InputReader ir = new InputReader(System.in);
		int tc = ir.nextInt();
		StringBuilder sb = new StringBuilder();
		
		int n, v, w, e;
		long weight;
		//edges = new Edge [100005];		
		//parent = new int[5005];
		for(int t=tc; t>0;t--){
			n = ir.nextInt();
			m = ir.nextInt();
			edges = new Edge[m];
			subsets = new Subset[n];
			for(int i=0; i<n; i++){
				subsets[i] = new Subset(i, 0);
			}
			//Arrays.fill(parent, -1);
			for(int i=0; i<m; i++){
				v = ir.nextInt();
				w = ir.nextInt();
				weight = ir.nextLong();
				edges[i] = new Edge(v-1, w-1, weight);
			}
						
			sort(edges);
						
			long ans = 0;
			for(int i=0; i<m; i++){
				v = edges[i].either();
				w = edges[i].other(v);
				if(find(v) != find(w)){
					ans += edges[i].weight();
					union(v, w);
				}
			}
			
			sb.append(ans);
			sb.append("\n");			
		}
		
		System.out.println(sb.toString());
    }
    
    private static class Subset{
    	int parent;
    	int rank;
    	
    	public Subset(int parent, int rank){
    		this.parent = parent;
    		this.rank = rank;
    	}
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
    		return (other.weight>=this.weight?(other.weight>this.weight?1:0):-1);
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