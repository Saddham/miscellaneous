package com.timepass.careercup;

import java.util.Arrays;

public class RatInMaze {
	
	public static boolean solve(int [][] maze){
		int [][] solution = new int[maze.length][maze[0].length];
		for (int i = 0; i < solution.length; i++) {
			Arrays.fill(solution[i], 0);
		}
		if(_solve(maze, solution, 0, 0)){
			System.out.println(Arrays.deepToString(solution));
			return true;
		}
		
		return false;	
	}
	
	private static boolean _solve(int [][] maze, int [][] sol, int x, int y){
		
		if(x==maze.length-1 && y==maze[0].length-1){
			sol[x][y] = 1;
			return true;
		}
		
		if(isSafe(maze, x, y)){
			
			sol[x][y] = 1;
			
			if(_solve(maze, sol, x+1, y)){				
				return true;
			}			
			if(_solve(maze, sol, x, y+1)){			
				return true;
			}								
			
			sol[x][y] = 0;
		}
		
		return false;
	}
	
	private static boolean isSafe(int [][] maze, int x, int y){
		if(x>=0 && x<maze.length && y>=0 && y<maze[0].length && maze[x][y]==1){
			return true;
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		int maze[][] = { {1, 0, 0, 0},
		        		 {1, 1, 0, 1},
		        		 {0, 1, 0, 0},
		        		 {1, 1, 1, 1}
		    			};
		
		solve(maze);
	}
}
