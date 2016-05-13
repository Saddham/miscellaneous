import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 
class TestClass { 
    public static void main(String args[] ) throws Exception {
		final InputReader ir = new InputReader(System.in);
		int tc = ir.nextInt();
		StringBuilder sb = new StringBuilder();
		
		long key, prevCount = 0L;
		BSTWithArray bst = new BSTWithArray();
		for(int t=tc; t>0;t--){
			key = ir.nextLong();
			prevCount += bst.insert(key);
			
			sb.append(prevCount);
			sb.append("\n");			
		}
		
		System.out.println(sb.toString());
    }
    
	public class SegmentTree {
		public int [] array;
		private long [] segTree;
		private long [] lazy;
		private int size;
		private int mid;
		
		public SegmentTree(int size){
			this.size = size;
			segTree = new long[size+1];
			lazy = new long[size+1];
		}
		
		public void updateRange(int l, int r, int val){
			updateRange(1, 1, size, l, r, val);
		}
		
		private void updateRange(int node, int start, int end, int l, int r, int val) {
			//completely outside the range represented by current node
			if(start > end || start<l || end>r){
				return ;
			}
			
			if(lazy[node] != 0){
				segTree[node] += (end-start+1)*lazy[node];
				if(start != end){
					segTree[2*node] += lazy[node];
					segTree[2*node+1] += lazy[node];
				}
				lazy[node] = 0;
			}
			
			//completely inside the range represented by current node
			if(l<=start && end<=r){
				segTree[node] += (end-start+1)*val;
				if(start != end){
					segTree[2*node] += val;
					segTree[2*node+1] += val;
				}
				
				return;
			}
			
			//partially inside the range represented by current node
			mid = (start+end)/2;
			updateRange(2*node, start, mid, l, r, val);
			updateRange(2*node+1, mid+1, end, l, r, val);
			segTree[node] = segTree[2*node] + segTree[2*node+1];
		}
		
		public long queryRange(int l, int r){
			return queryRange(1, 1, size, l, r);
		}

		private long queryRange(int node, int start, int end, int l, int r) {
			if(start > end || start<l || end>r){
				return 0;
			}
			
			if(lazy[node] != 0){
				segTree[node] += (end-start+1)*lazy[node];
				if(start != end){
					segTree[2*node] += lazy[node];
					segTree[2*node+1] += lazy[node];
				}
				lazy[node] = 0;
			}
			
			if(l<=start && end<=r){
				return segTree[node];
			}
			
			mid = (start+end)/2;
			
			return (queryRange(2*node, start, mid, l, r)+
					queryRange(2*node+1, mid+1, end, l, r)); 
		}
	}
	
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
   		
   		public long insert(long key){
   			Node newNode = new Node(key);
   			long count = 0L;
   			if(root==null){
   				root = newNode;
   				return count;
   			}
   			
   			Node prev = root;
   			Node node = root;
   			
   			while(node != null){
				count++;
   				prev = node;
   				if(key<node.key){
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
   			
			return count;
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
	
	private static class BSTWithArray {
		ArrayList<Node> bst = new ArrayList<Node>();
		Node root;		
		
		private static class Node{
			long key;
			int left;
			int right;		
			
			public Node(long key, int left, int right){
				this.key = key;
				this.left = left;
				this.right = right;
			}
		}
		
		private void initialize(long key){
			root = new Node(key, -1, -1);
			bst.add(root);
		}
		
		private void setLeft(long key, int curIdx){
			bst.get(curIdx).left = bst.size();
			bst.add(new Node(key, -1, -1));
		}
		
		private void setRight(long key, int curIdx){
			bst.get(curIdx).right = bst.size();
			bst.add(new Node(key, -1, -1));
		}
		
		public long insert(long key){
			long count = 0L;
			if(bst.size() == 0){
				initialize(key);
				return count;
			}
					
			int currentIdx = 0;
			while (currentIdx < bst.size() ){
				count++;
				if(key <= bst.get(currentIdx).key){
					if(bst.get(currentIdx).left == -1){
						setLeft(key, currentIdx);
						break;
					} else{
						currentIdx = bst.get(currentIdx).left;
					}
				} else{
					if(bst.get(currentIdx).right == -1){
						setRight(key, currentIdx);
						break;
					} else{
						currentIdx = bst.get(currentIdx).right;
					}
				}
			}
			
			return count-1;
		}

		/*public static void main(String[] args) {
			BSTWithArray bst = new BSTWithArray();
			System.out.println(bst.insert(8));
			System.out.println(bst.insert(3));
			System.out.println(bst.insert(5));
			System.out.println(bst.insert(1));
			System.out.println(bst.insert(6));
			System.out.println(bst.insert(8));
			System.out.println(bst.insert(7));
			System.out.println(bst.insert(2));
			System.out.println(bst.insert(4));
			System.out.println(bst.insert(15));
		}*/

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