package com.timepass;

public class StackLinkApp {

	public static void main(String[] args) {
		State st = State.Unvisited;
		if(State.Unvisited.equals(st)){
			for (State st2 : State.values()) {
				System.out.println(st2);
			}
		}
		

	}

}

enum State{
	Unvisited, Visiting, Visited;
}; 

class Stack<T>{
	class Node{
		Object data;
		Node next = null;
		
		public Node(T data) {
			super();
			this.data = data;
		}
	}	
}

