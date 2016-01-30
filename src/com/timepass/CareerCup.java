package com.timepass;

public class CareerCup {

	public static void main(String[] args) {
		long [] arr = {21, 3, 2, 10, 5, 20, 4};
		long diff = maxDiff(arr);
		
		if (diff == -1) {
			System.out.println("Not Found...");
		} else {
			System.out.println("Max Difference: " + diff);
		}
		
		long [] arr1 = {126, 110, 130};
		long [] arr2 = {125, 127};
		long [] arr3 = {105, 115};
		
		diff = maxEle(arr1, arr2, arr3);
		if (diff == -1) {
			System.out.println("Not Found...");
		} else {
			System.out.println("Min Ele: " + diff);
		}
		
	}

	public static long maxDiff(long [] arr){
		long maxDif = -1;
		long min = arr[0];
		
		for (long ele : arr) {
			min = Math.min(min, ele);
			maxDif = Math.max(maxDif, ele - min);
		}
		
		return maxDif;
	}
	
	
	public static long maxEle(long arr1[], long arr2[], long arr3[]){
		long min1 = arr1[0];
		long max2 = arr2[0] < arr3[0] ? arr3[0] : arr2[0];
		
		for (int ind = 0; ind < arr1.length; ind++) {
			if(ind < arr2.length){
				max2 = Math.max(max2,  arr2[ind]);
			}
			if(ind < arr3.length){
				max2 = Math.max(max2,  arr3[ind]);
			}
			
			if(arr1[ind] >= max2){
				min1 = Math.min(min1, arr1[ind]);
			}
		}
		
		if(max2 > min1){
			min1 = -1;
		}
		
		
		return min1;
	}
	
}


/*private TreeNode[] res = new TreeNode[2];
public void bstTwoSum(TreeNode root, int target) {
        // assume root.val is one of the 2 numbers:
	if (root.val < target) {
		int tar = target - root.val;
		if (tar == root.val) return;  // if root.val = target/2 we won't find the other node
		TreeNode theOther = search(tar, root);
		if (theOther != null) {
			res[0] = root.val > theOther.val ? theOther : root;
			res[1] = res[0] == root ? theOther : root;
		} else {
			// root is not one of the numbers, try it's both subtrees
			bstTwoSum(root.left, target);
			bstTwoSum(root.right, target);
		}
	} else {
		// if current value not less then target value, two numbers must all be in left sub tree.
		bstTwoSum(root.left, target);
	}*/