package com.segmenttree;

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
		if(start > end || start<l || end>r){
			return ;
		}
		
		if(lazy[node] != 0){
			segTree[node] += (end-start+1)*lazy[node];
			if(start != end){
				lazy[2*node] += lazy[node];
				lazy[2*node+1] += lazy[node];
			}
			lazy[node] = 0;
		}
		
		if(l<=start && end<=r){
			segTree[node] += (end-start+1)*val;
			if(start != end){
				lazy[2*node] += val;
				lazy[2*node+1] += val;
			}
			
			return;
		}
		
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
				lazy[2*node] += lazy[node];
				lazy[2*node+1] += lazy[node];
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
