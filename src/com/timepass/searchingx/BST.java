package com.timepass.searchingx;

import java.util.LinkedList;
import java.util.Queue;


public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
    
    public void put(Key key, Value value){
    	root = put(root, key, value);
    }
    private Node put(Node x, Key key, Value value) {
		if(x == null) return new Node(key, value, 1);
		
		int cmp = x.key.compareTo(key); 
		if(cmp > 0) x.left = put(x.left, key, value);
		else if(cmp < 0) x.right = put(x.right, key, value);
		else x.val = value;
		
		x.N = size(x.left)+size(x.right)+1;
		
		return x;
	}
    
    public Value get(Key key){
    	if(key == null) return null;
    	return get(root, key);
    }
    
    private Value get(Node x, Key key) {
    	if(x == null) return null;
    	
    	int cmp = x.key.compareTo(key);
    	if(cmp > 0) return get(x.left, key);
    	else if(cmp < 0) return get(x.right, key);
    	else return x.val;
	}
	public int size(){
    	return size(root);
    }
    
	private int size(Node x) {
		if(x == null) return 0;
		
		return x.N;		
	}
	
	public Iterable<Key> keys(){
		Queue<Key> queue = new LinkedList<Key>();
		keys(root, queue);
		return queue;
	}
	
	private void keys(Node x, Queue<Key> queue) {		
		if(x == null) return;
		queue.add(x.key);
		
		keys(x.left, queue);
		keys(x.right, queue);			
	}
	
	public static void main(String[] args) { 
    	    BST<String, Integer> st = new BST<String, Integer>();
    	    st.put("gfdgdfg", 6456);
    	    st.put("jiuyj", 56);
    	    st.put("ogdf", 66);
    	    st.put("qmph", 53);
            for (String s : st.keys())
                System.out.println(s + " " + st.get(s));
    }
}