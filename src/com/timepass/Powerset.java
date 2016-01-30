package com.timepass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Powerset {
	private static int [] setElements;
	
	public static List<ArrayList<Integer>> powerSet(int [] elements){
		List<ArrayList<Integer>> setList = new ArrayList<ArrayList<Integer>>();
		
		setElements = elements;		
		int numberOfSets = (int) Math.pow(2, setElements.length);
		for(int i=numberOfSets;i>0;i--){
			setList.add(new ArrayList<Integer>());
		}
		
		powerSet(setList, numberOfSets-1);
		return setList;
	}
	
	private static void powerSet(List<ArrayList<Integer>> setList, int set) {
		if(set < 0) return;
		powerSet(setList, set-1);
		for(int i=0; i<setElements.length;i++){
			if((set & setElements[i]) > 0){
				setList.get(set).add(setElements[i]);
			}
		}
		
	}
	
	public static void main(String[] args) {
		int [] elements = {1, 65, 34, 23, 43};
		List<ArrayList<Integer>> setList = powerSet(elements);
		
		Iterator<ArrayList<Integer>> setIterator= setList.iterator();
		int i = 0;
		while(setIterator.hasNext()){
			System.out.println("Set "+i+": ");
			ArrayList<Integer> set = setIterator.next();
			Iterator<Integer> elementsIterator = set.iterator();
			while(elementsIterator.hasNext()){
				System.out.print(" "+elementsIterator.next());
			}
			
			i++;
			System.out.println();
		}
	}

}
