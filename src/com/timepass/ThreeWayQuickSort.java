/**
 * 
 */
package com.timepass;

/**
 * @author Saddam
 *
 */
public class ThreeWayQuickSort {

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
		
		int lt = lo;
		int gt = hi;
		
		Comparable v = input[lo];
		int i = lo;;
		while(i <= gt){
		
			if(input[i].compareTo(v) < 0){
				exchange(input, i++, lt++);
			} else if(input[i].compareTo(v) > 0){
				exchange(input, i, gt--);
			} else{
				i++;
			}
		}
		
		quickSort(input, lo, lt-1);
		quickSort(input, gt+1, hi);
	}
	
	private static void exchange(Comparable[] input, int i, int j) {
		Comparable temp;
		temp = input[j];
		input[j] = input[i];
		input[i] = temp;
	}

}
