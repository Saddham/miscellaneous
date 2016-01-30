package com.timepass;

public class QuickSort {
	public static void main(String[] args) {
		int [] array = {645,65,65,4,5,7,5,2,3,767,43,34,8};
		
		quickSort(array);
		for (int i : array) {
			System.out.print(i+" ");
		}
	}

	private static void quickSort(int[] array) {		
		reQuickSort(array, 0, array.length-1);
	}

	private static void reQuickSort(int[] array, int left, int right) {
		if((right-left) <= 0){
			return;
		} else{
			int pivot = partition(array, left, right);
			reQuickSort(array, left, pivot-1);
			reQuickSort(array, pivot+1, right);
		}
		
	}

	private static int partition(int[] array, int left, int right) {
		int pivot = array[right];
		int leftPtr = left-1; 
		int rightPtr = right;
		while(true){
			
			while(array[++leftPtr] < pivot);
			while(rightPtr > 0 && array[--rightPtr] > pivot);
			
			if(leftPtr >=rightPtr){
				break;
			} else{
				int temp = array[rightPtr];
				array[rightPtr] = array[leftPtr];
				array[leftPtr] = temp;
			}
		}
		
		int temp = array[right];
		array[right] = array[leftPtr];
		array[leftPtr] = temp;
		
		return leftPtr;
	}
}
