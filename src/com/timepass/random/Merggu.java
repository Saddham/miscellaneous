package com.timepass.random;

public class Merggu {
	public static void sort(int [] arr){
		_sort(arr, 0, arr.length-1);
	}

	private static void _sort(int[] arr, int lo, int hi) {		
		if(hi-lo>0){
			int mid = lo+(hi-lo)/2;
			_sort(arr, lo, mid);
			_sort(arr, mid+1, hi);
			merge(arr, lo, mid, hi);
			if(arr[mid+1] >= arr[mid]){
				return;
			}
		}
	}

	private static void merge(int[] arr, int lo, int mid, int hi) {
		int N = hi-lo+1;
		int [] temp = new int[N];
		int i = lo;
		int j = mid+1;
		
		for(int k=0; k<N; k++){
			if(i>mid) temp[k] = arr[j++];
			else if(j>hi) temp[k] = arr[i++];
			else if(arr[i] < arr[j]) temp[k] = arr[i++];
			else temp[k] = arr[j++];
		}
		
		System.arraycopy(temp, 0, arr, lo, N);
	}
	
	
	public static void main(String[] args) {
		int [] arr = {54,545,545,5,5345,55,5};
		sort(arr);
		for (int i : arr) {
			System.out.print(i+" ");
		}
	}
}
