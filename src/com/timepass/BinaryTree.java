package com.timepass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTree {
	private static int MAX = 10;
	static Node BSTNode = null;
	public static void main(String[] args) {
		
		for (int i = 0; i < MAX; i++) {
			insert(BSTNode, i);
		}
		
		inorderTreeWalk(BSTNode);
		minimum(BSTNode);
		maximum(BSTNode);
		predecessor(BSTNode, 0);
		successor(BSTNode, 568);
		changeValues(BSTNode, 0);
		inorderTreeWalk(BSTNode);
	}
	
	private static int changeValues(Node root, int greater) {
		if(root == null)
			return 0;
		int tmp = root.getKey();
		int right = changeValues(root.getRight(), greater);
		root.setKey(right + greater);
		int left = changeValues(root.getLeft(), root.getKey() + tmp);
		return left + tmp + right;
	}
	
	private static int replaceGhanta(Node root, int sum){
		if(root == null){
			return sum;
		}
		int curValue = root.getKey();
		root.setKey(replaceGhanta(root.getRight(), sum));
		return replaceGhanta(root.getLeft(), curValue+root.getKey());
	}
	
	private static void successor(Node root, int val) {
		Node treeNode = root;
		
		while((treeNode != null) && (treeNode.getKey() != val)){
			if(treeNode.getKey() < val){
				treeNode = treeNode.getRight();
			} else{
				treeNode = treeNode.getLeft();
			}
		}
	
		if(treeNode != null){
			if(treeNode.getRight() != null){
				System.out.println("Successor: "+treeNode.getRight().getKey());
			} else{
				Node parent = treeNode.getParent();
				while((parent != null) && (parent.getRight() == treeNode)){
					treeNode = parent;
					parent = parent.getParent();
				}
				if (parent != null) {
					System.out.println("Successor: "+parent.getKey());
				} else{
					System.out.println("No Successor");
				}
			}
		} else{
			System.out.println("No such val");
		}
	}

	private static void predecessor(Node root, int val) {
		Node treeNode = root;
		
		while((treeNode != null) && (treeNode.getKey() != val)){
			if(treeNode.getKey() < val){
				treeNode = treeNode.getRight();
			} else{
				treeNode = treeNode.getLeft();
			}
		}
	
		if(treeNode != null){
			if(treeNode.getLeft() != null){
				System.out.println("Predecessor: "+treeNode.getLeft().getKey());
			} else{
				Node parent = treeNode.getParent();
				while((parent != null) && (parent.getLeft() == treeNode)){
					treeNode = parent;
					parent = parent.getParent();
				}
				if (parent != null) {
					System.out.println("Predecessor: "+parent.getKey());
				} else{
					System.out.println("No Predecessor");
				}
			}
		} else{
			System.out.println("No such val");
		}		
	}

	private static void maximum(Node root) {
		Node treeNode = root;
		Node  ghanta = null;
		
		while(treeNode != null){
			ghanta = treeNode;
			treeNode = treeNode.getRight();
		}

		System.out.println(ghanta.getKey());
		
	}

	private static void minimum(Node root) {
		Node treeNode = root;
		Node  ghanta = null;
		
		while(treeNode != null){
			ghanta = treeNode;
			treeNode = treeNode.getLeft();
		}
		System.out.println();
		System.out.println(ghanta.getKey());
		
	}

	private static void inorderTreeWalk(Node root) {
		
		if(root != null){
			inorderTreeWalk(root.getLeft());
			System.out.print(root.getKey() + " ");
			inorderTreeWalk(root.getRight());
		}
		
	}

	private static void insert(Node root, int val) {
		
		Node treeNode = root;
		Node  ghanta = null;
		
		while(treeNode != null){
			ghanta = treeNode;
			if(treeNode.getKey() < val){
				treeNode = treeNode.getRight();
			} else{
				treeNode = treeNode.getLeft();
			}
		}
		
		Node newNode = new Node(val);
		if(root == null){
			BSTNode = newNode;
		}else if(ghanta.getKey() < newNode.getKey()){
			ghanta.setRight(newNode);
		} else {
			ghanta.setLeft(newNode);
		}
		
		newNode.setParent(ghanta);
	}
}

class Node{
	private Integer key = null;
	private Node parent = null;
	private Node left = null;
	private Node right = null;
	
	public Node() {
		super();
	}
	
	public Node(int key) {
		super();
		this.key = key;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public Node getRight() {
		return right;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	
	public int getKey() {
		return key;
	}
	
	public void setKey(int key) {
		this.key = key;
	}		
}

