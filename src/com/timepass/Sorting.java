package com.timepass;

import java.util.Random;

public class Sorting {
	private static int MAX = 1000;

	public static void main(String[] args) {
		int a[] = new int[1000];
		
		Random randomNmGn = new Random();
		for (int i = 0; i < MAX; i++) {
			a[i] = randomNmGn.nextInt(MAX);
			
		}
				
		//System.out.print("fdsgdgd ");
		/*mergeSort(a, 0, MAX-1);
		bubbleSort(a);*/
		bucketSort(a, MAX);
		for (int ele : a) {
			System.out.print("fdsgdgd ");
			System.out.print(ele+" ");
		}

	}

	private static void bucketSort(int[] a, int range) {
		int [] b = new int[range];
		//a and b should be of same length
		for (int i = a.length-1; i >= 0; i--) {
			b[a[i]] += 1;
		}
		int k=0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 1; j <= b[i]; j++) {
				a[k++] = i;
			}
		}
	}

	private static void bubbleSort(int[] a) {
		for(int j = a.length-1; j > 0 ; j-- ){
			for (int i = a.length-1; i > 0; i--) {
				if((a[i]-a[i-1])>0){
					a[i] ^= a[i-1];
					a[i-1] ^= a[i];
					a[i] ^= a[i-1];
				}
				
			}
		}			
		
	}

	private static void mergeSort(int a[], int low, int high){
		if(low < high){
			int mid = (low+high)/2;
			mergeSort(a, low, mid);
			mergeSort(a, mid+1, high);
			merge(a, low, mid, high);
		}
	}

	private static void merge(int[] a, int low, int mid, int high) {
		int temp[] = new int[MAX];
		int i = low;
		int l = low;
		int j = mid+1;
		
		while(i<=mid && j<=high){
			if (a[i] <= a[j] ) {
				temp[l] = a[i];
				i++;
			} else{
				temp[l] = a[j];
				j++;
			}			
			l++;
		}
		
		while(i<=mid){
			temp[l] = a[i];
			l++;
			i++;
		}
		
		while(j<=high){
			temp[l] = a[j];
			l++;
			j++;
		}
		
		for(int k=low; k<=high; k++){
			a[k] = temp[k]; 
		}
		
	}
}
