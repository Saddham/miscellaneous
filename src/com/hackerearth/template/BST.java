private static class BST{
   		Node root;
   		private class Node{
   			long key;
   			Node left;
   			Node right;
   			
   			public Node(long key){
   				this.key = key;
   			}
   		}
   		
   		public void insert(long key){
   			Node newNode = new Node(key);
   			
   			if(root==null){
   				root = newNode;
   				return;
   			}
   			
   			Node prev = root;
   			Node node = root;
   			
   			while(node != null){
   				prev = node;
   				if(key == node.key){
   					return;
   				}else if(key<node.key){
   					node = node.left;
   				} else{
   					node = node.right;
   				}   				
   			}
   			
   			if(key<=prev.key){
   				prev.left = newNode;
   			} else{
   				prev.right = newNode;
   			}
   			
   		}
   		
   		public String search(long key){   			   			
   			Node node = root;
   			
   			while(node != null){
   				if(key==node.key){
   					return "YES";
   				} else if(key<node.key){
   					node = node.left;
   				} else{
   					node = node.right;
   				}   				
   			}
   			
   			return "NO";   		
   		}
   		
   	}
