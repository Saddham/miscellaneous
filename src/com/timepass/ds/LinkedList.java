package com.timepass.ds;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.naming.OperationNotSupportedException;

public class LinkedList<Value> implements Iterable<Value>{
	private Node head;
	private int size;	
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public int size(){
		return size;
	}
	
	public void add(Value value){
		if(value == null) throw new NullPointerException("Value cannot be null...");
		head = add(head, value);
		size++;
	}
	
	private Node add(Node head2, Value value) {
		if(head2 == null) return new Node(value, null);
		
		head2.nextNode = add(head2.nextNode, value);
		
		return head2;		
	}
	
	public ListIterator getListIterator(){
		return new ListIterator();
	}

	public Value get(){
		return (Value)head.value;
	}
	
	public Value get(int i){
		if(i<0 || i>size-1) throw new IndexOutOfBoundsException();
		
		Node x = head;
		int j=0;
		while(x!=null){
			if(j==i)
				return (Value)x.value;
			x = x.nextNode;
			j++;
		}
		
		return null;
	}
	public void addBeforeV2(Value value, Value value2){
		head = addBefore(head, head, value, value2);
	}
	
	private Node addBefore(Node prev, Node cur, Value value, Value value2) {
		if(cur == null) throw new NoSuchElementException();
		
		if(cur.value == value){
			Node newNode = new Node(value2, cur);
			size++;
			return newNode;
		} else{
			cur.nextNode = addBefore(cur, cur.nextNode, value, value2);
		}
		
		return cur;
	}

	/*public void addBefore(Value value, Value value2){
		Node cur = head;
		Node prev = head;
		while(cur!=null){
			if(cur.value == value){
				Node newNode = new Node(value2, cur);
				prev.nextNode = newNode;
				size++;
				return;
			}
			prev = cur;
			cur = cur.nextNode;
		}
		
		throw new NoSuchElementException();
	}*/

	@Override
	public ListIterator iterator() {
		return new ListIterator();
	}
	
	@Override
	public int hashCode() {
		int hashCode = 1;
		for (Value value : this) {
			hashCode = 31*hashCode + (value==null?0:value.hashCode());
		}
		
		return hashCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		
		if(!(obj instanceof LinkedList))
			return false;
		
		ListIterator itr1 = iterator();
		ListIterator itr2 = ((LinkedList) obj).iterator();
		
		while(itr1.hasNext() && itr2.hasNext()){
			Value obj1 = itr1.next();
			Object obj2 = itr2.next();
			
			if(!(obj2==null?obj1==null:obj2.equals(obj1)))
				return false;
		}
		
		return !(itr1.hasNext() || itr2.hasNext());		
	}
	
	private class ListIterator implements Iterator<Value>{
		private Node itrHead = head;
		
		@Override
		public boolean hasNext() {			
			return itrHead!=null ? true : false;
		}

		@Override
		public Value next() {			
			Value value = (Value)itrHead.value;
			itrHead = itrHead.nextNode;
			
			return value;
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
			
		}
	}  

	private class Node{
		private Object value;
		private Node nextNode;
		
		public Node(Object value, Node node) {
			this.value = value;
			nextNode = node; 
		}
		
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
		
		public Node getNextNode() {
			return nextNode;
		}
		public void setNextNode(Node nextNode) {
			this.nextNode = nextNode;
		}				
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> list1 = new LinkedList<Integer>();
		LinkedList<Integer> list2 = new LinkedList<Integer>();
		for (int i = 0; i < 1; i++) {
			list1.add(i);
		}
		
		for (int i = 0; i < 100; i++) {
			list2.add(i);
		}
		
		for (Integer integer : list1) {
			System.out.print(integer+" ");
		}
		
		System.out.println();
		System.out.println(list1.equals(list2));
		list2.add(100);
		System.out.println(list1.equals(list2));
		System.out.println(list1.equals(list1));
		System.out.println(list1.equals(new java.util.LinkedList<Integer>()));
		System.out.println(list1.get());
		//System.out.println(list1.get(99));
		list1.addBeforeV2(0, 201);
		System.out.println(list1.get(0));
		for (Integer integer : list1) {
			System.out.print(integer+" ");
		}
		System.out.println(list1.size());
	}
}
