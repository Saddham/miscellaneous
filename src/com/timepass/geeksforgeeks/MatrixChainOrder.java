package com.timepass.geeksforgeeks;

public class MatrixChainOrder {
	
	public static int minOrder(int [] p){
		return _minOrder(p, 1, p.length-1);
	}
	
	public static int _minOrder(int [] p, int i, int j){
		if(i==j)
			return 0;
		
		int count;
		int min =Integer.MAX_VALUE;
	
		for (int k = i; k < j; k++) {
			count = _minOrder(p, i, k)+
					_minOrder(p, k+1, j)+
					p[i-1]*p[k]*p[j];
			if(count<min){
				min = count;
			}
		}	
		
		return min;
	}
	
	public static int minOrder_dp(int p[]){
		return _minOrder_dp(p, p.length);
	}
	
	// Matrix Ai has dimension p[i-1] x p[i] for i = 1..n
	private static int _minOrder_dp(int p[], int n){
	 
	    /* For simplicity of the program, one extra row and one extra column are
	       allocated in m[][].  0th row and 0th column of m[][] are not used */
	    int m[][] = new int[n][n];
	 
	    int i, j, k, L, q;
	 
	    /* m[i,j] = Minimum number of scalar multiplications needed to compute
	       the matrix A[i]A[i+1]...A[j] = A[i..j] where dimention of A[i] is
	       p[i-1] x p[i] */
	 
	    // cost is zero when multiplying one matrix.
	    for (i = 1; i < n; i++)
	        m[i][i] = 0;
	 
	    // L is chain length.  
	    for (L=2; L<n; L++)   
	    {
	        for (i=1; i<=n-L; i++)
	        {
	            j = i+L-1;
	            m[i][j] = Integer.MAX_VALUE;
	            for (k=i; k<=j-1; k++)
	            {
	                // q = cost/scalar multiplications
	                q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
	                if (q < m[i][j])
	                    m[i][j] = q;
	            }
	        }
	    }
	 
	    return m[1][n-1];
	}
	
	public static void main(String[] args) {
		int [] p = {10, 20, 30};
		
		System.out.println(minOrder(p));
		System.out.println(minOrder_dp(p));
	}
	
}
