package com.timepass;

public class QuickSortApp {

	public static void main(String[] args) {
		Integer [] input = {66456,665,6456,6546,645,654,6,4234,3424};
		
		sort(input);
		for (Integer integer : input) {
			System.out.print(integer+" ");
		}
		

	}
	
	 public static <T extends Comparable<? super T>>  void  sort(T [] input) {
		 quickSort(input, 0, input.length-1);
	}
	 
	 public static <T extends Comparable<? super T>>  void  quickSort(T [] input, int start, int end) { 
		 
		 if(start < end){
			 int newLength = partitian(input, start, end);
			 quickSort(input, start, newLength);
			 quickSort(input, newLength+1, end);
		 }
	 }
	 
	 public static <T extends Comparable<? super T>>  int  partitian(T [] input, int start, int end) { 
		 
		 int left = start, right = end;
		 	 
		 while(input[left].compareTo(input[start]) < 0){			
			 left += 1;			 			 		
		 }
		
		 while(input[right].compareTo(input[start]) > 0){
			 right -= 1;		 			
		 }
		 
		 if(left < right){
			 T temp;
			 temp = input[right];
			 input[right] = input[left];
			 input[left] = temp;
		 }
		 
		 return right-1;
	 }
}
