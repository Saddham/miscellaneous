import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class TestClass { 
	private static char [] s;
	private static int [] z;
	private static int n;
	private static int [] lps;
	private static int k;
	public static void z_function(){
		int L = 0, R = 0;
		//int [] z = new int[n];
		for (int i = 1; i < n; i++) {
			if (i > R) {
				L = R = i;
				while (R < n && s[R-L] == s[R]) R++;
				z[i] = R-L; R--;
			} else {
				int k = i-L;
				if (z[k] < R-i+1) z[i] = z[k];
				else {
					L = i;
					while (R < n && s[R-L] == s[R]) R++;
					z[i] = R-L; R--;
				}
			}
		}
	}
	public static void computeLPS(){
		int len = 0;
		int i = 1;
		lps[0] = 0;
		while(i<n){
			if(s[i] == s[len]){
				len++;
				lps[i] = len;
				i++;
			} else{
				if(len != 0){
					len = lps[len-1];
				} else{
					lps[i] = 0;
					i++;
				}
			}
		}
	}

	public static void main(String args[] ) throws Exception {
		final InputReader ir = new InputReader(System.in);
		int tc = ir.nextInt();
		StringBuilder sb = new StringBuilder();
		TrieST<String> trie = new TrieST<String>();
		String str;
		for(int t=tc; t>0;t--){
			str = ir.next();
			trie.put(str, str);
		}
		System.out.println(trie.nodes());
	}

	private static class TrieST<Value> {
		private static final int R = 26; 
		private Node root;
		private int N;
		private String key;
		private Value value;
		private Queue<String> results;
		private String pattern;
		private String query;
		private int length;
		private int nodes;
		private static class Node{
			int words;
			int prefixes;
			Object value;
			Node [] next = new Node[R]; 
		}
		public TrieST(){
		}
		public int nodes(){
			return nodes;
		}
		public Value get(String key){
			this.key = key; 
			Node node = get(root, 0);
			if(node == null) return null;
			return (Value) node.value;

		}
		private Node get(Node node, int d) {
			if(node == null) return null;
			if(d == key.length()) return node;
			int ch = key.charAt(d)-97;
			return get(node.next[ch], d+1);
		}
		public void put(String key, Value value){
			this.key = key;
			this.value = value;
			root = put(root, 0);
		}
		private Node put(Node node, int d) {
			if(node == null){ 
				node = new Node();
				nodes++;
			}
			if(d == key.length()){
				if(node.value==null) N++;
				node.value = value;
				node.words = node.words+1;
				return node;
			}
			node.prefixes = node.prefixes+1;
			int ch = key.charAt(d)-97;
			node.next[ch] = put(node.next[ch], d+1);
			return node;
		}
		public int size(){
			return N;
		}
		public boolean isEmpty(){
			return size()==0;
		}
		public Iterable<String> keys() {
			return keysWithPrefix("");
		}
		public Iterable<String> keysWithPrefix(String prefix){
			results = new LinkedList<String>();
			this.key = prefix;
			Node node = get(root, 0);
			collect(node, new StringBuilder(prefix));
			return results;
		}
		private void collect(Node node, StringBuilder prefix) {
			if(node==null) return;
			if(node.value!=null) results.add(prefix.toString());
			for(int i=0; i<R; i++){
				prefix.append((char)(97+i));
				collect(node.next[i], prefix);
				prefix.deleteCharAt(prefix.length()-1);
			}
		}
		public Iterable<String> keysThatMatch(String pattern) {
			results = new LinkedList<String>();
			this.pattern = pattern;
			collectKeysThatMatch(root, new StringBuilder());
			return results;
		}
		private void collectKeysThatMatch(Node node, StringBuilder prefix) {
			if (node == null) return;
			int d = prefix.length();
			if (d == pattern.length() && node.value != null)
				results.add(prefix.toString());
			if (d == pattern.length())
				return;
			char c = pattern.charAt(d);
			if (c == '.') {
				for (int ch = 0; ch < R; ch++) {
					prefix.append((char)(97+ch));
					collectKeysThatMatch(node.next[ch], prefix);
					prefix.deleteCharAt(prefix.length() - 1);
				}
			}
			else {
				prefix.append(c);
				collectKeysThatMatch(node.next[c-97], prefix);
				prefix.deleteCharAt(prefix.length() - 1);
			}
		}

		public String longestPrefixOf(String query) {
			length = -1;
			this.query = query; 

			longestPrefixOf(root, 0);
			if (length == -1) return null;
			else return query.substring(0, length);
		}
		private void longestPrefixOf(Node x, int d) {
			if (x == null) return;
			if (x.value != null) length = d;
			if (d == query.length()) return;
			int c = query.charAt(d)-97;
			longestPrefixOf(x.next[c], d+1);
		}

		public void delete(String key) {
			root = delete(root, key, 0);
		}
		private Node delete(Node x, String key, int d) {
			if (x == null) return null;
			if (d == key.length()) {
				if (x.value != null) N--;
				x.value = null;
			}
			else {
				int c = key.charAt(d)-97;
				x.next[c] = delete(x.next[c], key, d+1);
			}
			// remove subtrie rooted at x if it is completely empty
			if (x.value != null) return x;
			for (int c = 0; c < R; c++)
				if (x.next[c] != null)
					return x;
			return null;
		}
	}

	private static class BinaryTrie {
		private BinaryNode root = new BinaryNode();	
		private static final int R = 31; 
		
		private static class BinaryNode{
			int value;
			BinaryNode [] arr = new BinaryNode[2];
		}
		
		private void insert(int pre_xor) {
			BinaryNode temp = root;
			 
		    // Start from the msb, insert all bits of
		    // pre_xor into Trie
		    for (int i=R-1; i>=0; i--){
		        // Find current bit in given prefix
		        int val = 0;
		        if((pre_xor & (1<<i))>0){
		        	val=1;
		        }
		 
		        // Create a new node if needed
		        if (temp.arr[val] == null){
		            temp.arr[val] = new BinaryNode();
		        }
		        temp = temp.arr[val];
		    }
		 
		    // Store value at leaf node
		    temp.value = pre_xor;	    
		}
	
		public int query(int pre_xor){
			BinaryNode temp = root;
			 
		    // Start from the msb, insert all bits of
		    // pre_xor into Trie
		    for (int i=R-1; i>=0; i--)
		    {
		        // Find current bit in given prefix
		        int val = 0;
		        if((pre_xor & (1<<i))>0){
		        	val=1;
		        }
		        
		        // Traverse Trie, first look for a
		        // prefix that has opposite bit
		        if (temp.arr[1-val] != null)
		            temp = temp.arr[1-val];
		 
		        // If there is no prefix with opposite
		        // bit, then look for same bit.
		        else if (temp.arr[val] != null)
		            temp = temp.arr[val];
		    }
		 
		    return pre_xor^(temp.value);	    
		}
		
		public static void test(){
			int k = 4;
			int t = k&(1<<2);
			System.out.println(t);
		}
		
		/*public static void main(String[] args) {
			//test();
			int [] a = {3, 8,  2,  6,  4};	
			BinaryTrie bTrie = new BinaryTrie();
			int ans=0;
			int pre=0;		
			bTrie.insert(0);
			for(int i=0; i<a.length; i++){
			    pre = pre ^ a[i];
			    bTrie.insert(pre);
			    ans=(int)Math.max(ans, bTrie.query(pre));
			}
			System.out.println(ans);
			
			bTrie.insert(2);		
			System.out.println(bTrie.temp.toString());
			bTrie.insert(7);
			System.out.println(bTrie.temp.toString());
			int ans = bTrie.query(4);
			System.out.println(ans);
		}*/
	}
	
	private static class SuffixTree {
	  static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789\1\2";
	  static int lcsLength;
	  static int lcsBeginIndex;
	  
	  public static class Node {
		int begin;
		int end;
		int depth; // distance in characters from root to this node
		Node parent;
		Node[] children;
		Node suffixLink;

		Node(int begin, int end, int depth, Node parent) {
		  this.begin = begin;
		  this.end = end;
		  this.parent = parent;
		  this.depth = depth;
		  children = new Node[ALPHABET.length()];
		}
	  }

	  public static Node buildSuffixTree(CharSequence s) {
		int n = s.length();
		byte[] a = new byte[n];
		for (int i = 0; i < n; i++) a[i] = (byte) ALPHABET.indexOf(s.charAt(i));
		Node root = new Node(0, 0, 0, null);
		Node node = root;
		for (int i = 0, tail = 0; i < n; i++, tail++) {
		  Node last = null;
		  while (tail >= 0) {
			Node ch = node.children[a[i - tail]];
			while (ch != null && tail >= ch.end - ch.begin) {
			  tail -= ch.end - ch.begin;
			  node = ch;
			  ch = ch.children[a[i - tail]];
			}
			if (ch == null) {
			  node.children[a[i]] = new Node(i, n, node.depth + node.end - node.begin, node);
			  if (last != null) last.suffixLink = node;
			  last = null;
			} else {
			  byte t = a[ch.begin + tail];
			  if (t == a[i]) {
				if (last != null) last.suffixLink = node;
				break;
			  } else {
				Node splitNode = new Node(ch.begin, ch.begin + tail, node.depth + node.end - node.begin, node);
				splitNode.children[a[i]] = new Node(i, n, ch.depth + tail, splitNode);
				splitNode.children[t] = ch;
				ch.begin += tail;
				ch.depth += tail;
				ch.parent = splitNode;
				node.children[a[i - tail]] = splitNode;
				if (last != null) last.suffixLink = splitNode;
				last = splitNode;
			  }
			}
			if (node == root) {
			  --tail;
			} else {
			  node = node.suffixLink;
			}
		  }
		}
		return root;
	  }

	/*  // random test
	  public static void main(String[] args) {
		  String s1 = "saddamsidpathan";
		  String s2 = "sid";
		  String s = s1 + '\1' + s2 + '\2';
		  Node tree = buildSuffixTree(s);
		  lcsLength = 0;
		  lcsBeginIndex = 0;  
		  // find longest common substring
		  lcs(tree, s1.length(), s1.length() + s2.length() + 1);
		  System.out.println("lcsLength "+ lcsLength+"\n"+"lcsBeginIndex"+lcsBeginIndex);
	  }*/

	  // traverse suffix tree to find longest common substring
	  public static int lcs(Node node, int i1, int i2) {		
		  
		if (node.begin <= i1 && i1 < node.end) {
		  return 1;
		}
		if (node.begin <= i2 && i2 < node.end) {
		  return 2;
		}
		int mask = 0;
		for (char f = 0; f < ALPHABET.length(); f++) {
		  if (node.children[f] != null) {
			mask |= lcs(node.children[f], i1, i2);
		  }
		}
		if (mask == 3) {
		  int curLength = node.depth + node.end - node.begin;
		  if (lcsLength < curLength) {
			lcsLength = curLength;
			lcsBeginIndex = node.begin;
		  }
		}
		return mask;
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