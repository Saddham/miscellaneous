package com.timepass;

class TrieST<Value>{
	private static final int R = 256;
	private Node root;
	private int N;
	
	private static class Node{
		private Object value;
		private Node [] nextNode = new Node[R];		
	}
	
	public Value get(String key){
		Node x = get(root, key, 0);
		if(x==null) return null;
		return (Value) x.value;
	}
	
	private Node get(Node x, String key, int d) {
		if(x==null) return null;
		if(d==key.length()) return x;
		
		int ch = key.charAt(d);				
		return get(x.nextNode[ch], key, d+1);
	}

	public void put(String key, Value value){
		root = put(root, key, value, 0);
	}

	private Node put(Node x, String key, Value value, int d) {
		if(x==null) x = new Node();
		
		if(d==key.length()){
			if(x.value == null) N++;
			x.value = value;
			return x;
		}
		
		char ch =  key.charAt(d);
		x.nextNode[ch] = put(x.nextNode[ch], key, value, d+1);
		return x;
	}
}


public class TrieApp {

	public static void main(String[] args) {
		String [] strs = {"gdfg", "gdg", "gdfg", "jk", "dfsf", "jh"};
		TrieST<Integer> st = new TrieST<Integer>();
        for (int i = 0; i<strs.length; i++) {
            String key = strs[i];
            st.put(key, i);
        }

        System.out.println("keys(\"\"):");
        for (String key : strs) {
        	System.out.println(key + " " + st.get(key));
        }       
	}
}
