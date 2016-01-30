package com.timepass.careercup;

import java.util.Arrays;

public class NQueen {
	private static boolean isSafe(int [][] board, int row, int col){
		int i, j;
		 
	    /* Check this row on left side */
	    for (i = 0; i < col; i++)
	    {
	        if (board[row][i]==1)
	            return false;
	    }
	 
	    /* Check upper diagonal on left side */
	    for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
	    {
	        if (board[i][j]==1)
	            return false;
	    }
	 
	    /* Check lower diagonal on left side */
	    for (i = row, j = col; j >= 0 && i < board.length; i++, j--)
	    {
	        if (board[i][j]==1)
	            return false;
	    }
	 
	    return true;
	}
	
	
	
	public static boolean solve(int N){
		int [][] board = new int[N][N];
		for (int i = 0; i < board.length; i++) {
			Arrays.fill(board[i], 0);
		}
		if(_solve(board, 0)){
			for (int i = 0; i < N; i++)
		    {
		        for (int j = 0; j < N; j++)
		            System.out.print(board[i][j]+" ");
		       System.out.println();
		    }
			return true;
		}
		
		return false;	
	}
	
	private static boolean _solve(int [][] board, int col){
		
		 /* base case: If all queens are placed then return true */
	    if (col >= board.length)
	        return true;
	 
	    /* Consider this column and try placing this queen in all rows
	       one by one */
	    for (int i = 0; i < board.length; i++)
	    {
	        /* Check if queen can be placed on board[i][col] */
	        if ( isSafe(board, i, col) )
	        {
	            /* Place this queen in board[i][col] */
	            board[i][col] = 1;
	 
	            /* recur to place rest of the queens */
	            if ( _solve(board, col + 1) == true )
	                return true;
	 
	            /* If placing queen in board[i][col] doesn't lead to a solution
	               then remove queen from board[i][col] */
	            board[i][col] = 0; // BACKTRACK
	        }
	    }
	 
	     /* If queen can not be place in any row in this colum col
	        then return false */
	    return false;
	}
	public static void main(String[] args) {		
		solve(4);
	}

}
