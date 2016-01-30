package com.timepass;


public class ShellSort {
  public static void main(String args[]){
    String inputStr = " 456 65 34 8 54 95 324 65 876854 5 ";
    
    String [] arrayStr = inputStr.trim().split(" ");
	 int [] array = new int[arrayStr.length];
    
    int h=0;
    for(String str : arrayStr){
      array[h++] = Integer.parseInt(str);
    }
    
    h = 1;
    while(h <= (array.length/3)){
      h = h*3 + 1;
    }
    
    while(h>0){
      
      for(int outr=h; outr<array.length; outr++){
        
        int innr = outr;
 		  while((innr > (h-1)) && (array[innr-h] > array[innr])){
            array[innr] = array[innr] ^ array[innr-h];
            array[innr-h] = array[innr] ^ array[innr-h];
            array[innr] = array[innr] ^ array[innr-h];
            innr -= 1;

        }
      }
      
      h = (h-1)/3;
    }
    
    for(int ele : array){
      System.out.print(ele+" ");
    }
    
  }
}