package com.timepass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

class Graph{
	private int V;
	private int E;
	private Map<Integer, List<Integer>> adj;
	
	public Graph(int V){
		this.V = V;
		adj = new HashMap<Integer, List<Integer>>();		
	}
	
	public void addEdge(int v, int w){
		if(adj.get(v) != null){
			adj.get(v).add(w);
		} else{
			adj.put(v, new ArrayList<Integer>());
			adj.get(v).add(w);
		}
		
		if(adj.get(w) == null){
			adj.put(w, new ArrayList<Integer>());
		}
		
		E++;
	}
	
	public void solve(int k){				
		Set<Integer> keySet = adj.keySet();
		for (Integer integer : keySet) {		
			Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
			
			for (Integer integer2 : keySet) {
				visited.put(integer2, false);
			}
			visited.put(integer, true);
			
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(integer);
			while(!stack.isEmpty()){
				int v = stack.pop();
				for (int w : adj.get(v)) {
					if(!visited.get(w) && (v*w <= k)){
						System.out.println(v +" "+w);
					}
					
					visited.put(w, true);
					stack.push(w);
				}
			}
		}

	}
}

public class TreeProblem {

	public static void main(String[] args){
        Graph graph = new Graph(10);
        
        graph.addEdge(0, 9);
        graph.addEdge(7, 3);
        graph.addEdge(4, 6);
        graph.addEdge(1, 2);
        
        graph.solve(21);
	}

}
