package com.timepass;

/**
 * @author Saddam
 *
 */
public class OneMoreQuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String [] input = {"a", "b", "c", "d"};
		sort(input);
		for(String str : input){
			System.out.println(str);
		}
	}
	
	public static void sort(Comparable input[]){
		quickSort(input, 0, input.length-1);
	}

	private static void quickSort(Comparable[] input, int lo, int hi) {
		if(hi <= lo){
			return;
		}
		
		int pivot = partition(input, lo, hi);
		quickSort(input, lo, pivot-1);
		quickSort(input, pivot+1, hi);
		
	}

	private static int partition(Comparable[] input, int lo, int hi) {
		int i = lo;
		int j = hi+1;
		Comparable pivot = input[lo];
		
		while(true){
			
			while(input[++i].compareTo(pivot) < 0){
				if(i == hi) break;
			}
			
			while(pivot.compareTo(input[--j]) < 0){}
			
			if(i >= j) break;
			
			exchange(input, i, j);
		}
		
		exchange(input, lo, j);
		
		return j;
	}

	private static void exchange(Comparable[] input, int i, int j) {
		Comparable temp;
		temp = input[j];
		input[j] = input[i];
		input[i] = temp;
	}

}
