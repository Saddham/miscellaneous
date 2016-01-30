package com.timepass;

public class GeekForGeeks {

	public static int search(int [] a, int value){
		return binarySearch(a, value, 0, a.length-1);
	}
	
	public static int searchV2(int [] a, int value){
		return binarySearchItr(a, value);
	}
	
	private static int binarySearch(int [] a, int value, int lo, int hi){		
		
		if(hi>=lo){
			int mid = lo+(hi-lo)/2;
			if(a[mid] == value) return mid;
			if(a[mid] > value) return binarySearch(a, value, lo, mid-1);
			return binarySearch(a, value, mid+1, hi);
		}
		
		return -1;		
	}

	private static int binarySearchItr(int [] a, int value){		
		int hi = a.length-1;
		int lo = 0;		
		int mid=0;
		
		while((hi-lo)>1){
			mid = lo+(hi-lo)/2;
			
			if(a[mid] > value){ 
				hi = mid;
			} else{
				lo = mid;
			}
		}
		
		if(lo==mid) return mid;
		return -1;		
	}	
	
	private static int floor(int [] a, int value){		
		int hi = a.length-1;
		int lo = 0;		
		int mid=0;
		
		while((hi-lo)>1){
			mid = lo+(hi-lo)/2;
			
			if(a[mid] < value){ 
				lo = mid;
			} else{
				hi=mid;
			}
		}

		return lo;		
	}
	
	private static int ceil(int [] a, int value){		
		int hi = a.length-1;
		int lo = 0;		
		int mid=0;
		
		while((hi-lo)>1){
			mid = lo+(hi-lo)/2;
			
			if(a[mid] <= value){ 
				lo = mid;
			} else{
				hi=mid;
			}
		}

		return hi;		
	}
	
	private static int occurrences(int [] a, int value){		
		return ceil(a, value)-floor(a, value)-1;		
	}
	
	public static int min(int [] a){		
		int hi = a.length-1;
		int lo = 0;		
		
		if(a[hi] >= a[lo]) return a[lo];
		
		int mid=0;
		
		while(lo<=hi){						
			if(hi==lo) return a[lo];
			mid = lo+(hi-lo)/2;
			
			if(a[mid] < a[hi]){ 
				hi=mid;
			} else{
				lo=mid+1;
			}
		}

		
		return -1;		
	}
	
	
	public static void main(String[] args) {		
		int a[] = {32,65,70,77,83,86,103,103,103,103,546,787,877};
		System.out.println(search(a, 100));
		System.out.println(searchV2(a, 86));
		System.out.println(floor(a, 103));
		System.out.println(ceil(a, 103));
		System.out.println(occurrences(a, 103));
		int a1[] = {32,65,70,77,83,86,90,99,99,2,3,4,5,7,8,9,15,20};
		System.out.println(min(a));
	}

}
