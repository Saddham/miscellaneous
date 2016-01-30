package com.timepass.ds;

public class LinkedListStack<ItemType> {
	 Node top;
	 
	  ItemType pop() {
		 if (top != null) {
			 ItemType item = top.getData();
			 top = top.getNext();
			 return item;
		 }
		 return null;
	 }
	 
	 void push(ItemType item) {
		 Node t = new Node(item);
		 t.setNext(top);
		 top = t;
	 }
	 
	 private class Node{
		 private ItemType data;
		 private Node next;
		 
		 public Node(ItemType item) {
			data = item;
		}

		public ItemType getData() {
			return data;
		}

		public void setData(ItemType data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
		 
		 
	 }
 }