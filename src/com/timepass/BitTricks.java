package com.timepass;

public class BitTricks {
	
	public long add(long a, long b){
		while((b | 0) > 0){
			a = -~a; // -~a = a+1
			b = ~-b; // ~-b = b-1
		}
		return a;
	}
	
	public long addV2(long a, long b){
		long carry;
		while(b > 0){
			carry = a&b;
			a = a^b;
			b = carry<<1;
		}
		return a;
	}
	
	
	public long subtract(long a, long b){
		while((b | 0) > 0){
			a = ~-a;
			b = ~-b;
		}
		return a;
	}
	
	public long nextHigher(long a){
		long ripple = a & (-a);
		long higher = a + ripple;
		long ones = a ^ higher;
		ones = (ones>>2)/ripple;
		
		return ones | higher;
	}
	
	public long abs(long a){
		long b = a>>63;
		long ab = (a^b) + 1;
		
		return ab;
	}
	
	public long average(long a, long b){
		long avg = (a&b)+((a^b) >> 1);
		return avg+((avg>>>31) & (a^b));
	}
	
	public long rightSignedShift(long a){
		long t = -(a>>>63);
		return ((a^t)>>>63)^t;
	}
	
	public long sigNum(long a){		
		return (a >> 63)^((-a) >>> 63);
	}
	
	public long greater(long a, long b){		
		return (b-((b-a)*((b-a)>>>63)));
	}

	public long transferOfSign(long a, long b){		
		long t = b >> 63;
		return ((abs(a)^t)-t);
	}
	
	public long decodeZeroMeans2nField(long a){		
		return ((a-1)&63)+1;
	}
	
	public long isEqual(long a, long b){		
		return (0-(a-b));
	}
	
	public long isNotEqual(long a, long b){		
		return (a-b) | (b-a);
	}
	
	//checking upper bound for unsigned numbers
	public boolean checkBound(long x, long a, long b){		
		return (x-a)<(b-a);
	}
	
	//count number of set bits in a non-negative number
	public long countSetBits(long x){		
		x = (x & 0x5555555555555555L)+((x>>>1) & 0x5555555555555555L);
		x = (x & 0x3333333333333333L)+((x>>>2) & 0x3333333333333333L);
		x = (x & 0x0F0F0F0F0F0F0F0FL)+((x>>>4) & 0x0F0F0F0F0F0F0F0FL);
		x = (x & 0x00FF00FF00FF00FFL)+((x>>>8) & 0x00FF00FF00FF00FFL);
		x = (x & 0x0000FFFF0000FFFFL)+((x>>>16) & 0x0000FFFF0000FFFFL);
		
		return x;
	}
	
	//count number of set bits in a non-negative number
	public long countSetBitsV2(long x){
		x = x - ((x >> 1) & 0x55555555);
		x = (x & 0x33333333) + ((x >> 2) & 0x33333333);
		x = (x + (x >> 4)) & 0x0F0F0F0F;
		x = x + (x >> 8);
		x = x + (x >> 16);
		return x & 0x0000003F;
	}
	
	//count number of set bits in a non-negative number
	public int countSetBitsV3(int x){		
		int n;
		n = (x >> 1) & 033333333333; // Count bits in
		x = x - n; // each 3-bit
		n = (n >> 1) & 033333333333; // field.
		x = x - n;
		x = (x + (x >> 3)) & 030707070707; // 6-bit sums.
		return ((x * 0404040404) >> 26) + // Add 6-bit sums.
				(x >> 30);
	}
	
	//count number of set bits in a non-negative number
	public int countSetBitsV4(int x){		
		int n;
		n = (x >> 1) & 033333333333; // Count bits in
		x = x - n; // each 3-bit
		n = (n >> 1) & 033333333333; // field.
		x = x - n;
		x = (x + (x >> 3)) & 030707070707; // 6-bit sums.
		return ((x * 0404040404) >> 26) + // Add 6-bit sums.
				(x >> 30);
	}
	
	//count number of set bits in a non-negative number
	public int setBitsDiff(int x,  int y){	
		x = x - ((x >> 1) & 0x55555555);
		x = (x & 0x33333333) + ((x >> 2) & 0x33333333);
		y = ~y;
		y = y - ((y >> 1) & 0x55555555);
		y = (y & 0x33333333) + ((y >> 2) & 0x33333333);
		x = x + y;
		x = (x & 0x0F0F0F0F) + ((x >> 4) & 0x0F0F0F0F);
		x = x + (x >> 8);
		x = x + (x >> 16);
		return (x & 0x0000007F) - 32;
	}
	
/*	//count number of set bits in a non-negative number
	public long countSetBitsV5(int x){	
		long y;
		y = x * 0x0002000400080010L;
		y = y & 0x1111111111111111L;
		y = y * 0x1111111111111111L;
		y = y >> 60;
		return y;
	}*/
	/*static char table[] = {
		0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4,
		...
		4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8};
	
	//count number of set bits in a non-negative number
	public int countSetBitsV5(int x){	
		return table[x & 0xFF] +
		table[(x >> 8) & 0xFF] +
		table[(x >> 16) & 0xFF]
	}*/
	
	//count number of set bits in a non-negative number
	public long countSetBitsInArray(int [] inputArray){	
		int eights=0, fours=0, fourA=0, fourB=0, twos=0, twoA=0, twoB=0, ones=0;
		long tot = 0;
		int i=0;
		
		for(;i<inputArray.length-8; i=i+8){
			twoA = inputArray[i]^inputArray[i+1];
			twoA = twoA^ones;			 			
			ones = (inputArray[i]&inputArray[i+1]) | (ones&twoA);
			
			twoB = inputArray[i+2]^inputArray[i+3];
			twoB = twoB^ones;			 			
			ones = (inputArray[i]&inputArray[i+1]) | (ones&twoB);
			
			fourA = twoA^twoB;
			fourA = fourA^twos;			 			
			twos = (twoA&twoB) | (twos&fourA);						
			
			twoA = inputArray[i+4]^inputArray[i+5];
			twoA = twoA^ones;			 			
			ones = (inputArray[i+4]&inputArray[i+5]) | (ones&twoA);
			
			twoB = inputArray[i+6]^inputArray[i+7];
			twoB = twoB^ones;			 			
			ones = (inputArray[i+6]&inputArray[i+7]) | (ones&twoB);
			
			fourB = twoA^twoB;
			fourB = fourB^twos;			 			
			twos = (twoA&twoB) | (twos&fourB);
			
			eights = fourA^fourB;
			eights = eights^fours;			 			
			fours = (fourA&fourB) | (fours&eights);
			
			tot = tot + countSetBits(eights);
		}
		
		tot = 8*eights + 4*fours + 2*twos + 1*ones;
		
		for(int j=i; j<inputArray.length; j++){
			tot = tot+countSetBits(inputArray[j]);
		}
		
		return tot;
	}
	
	//Parity Check: Returns 0-for even parity, 1-for odd parity
	public int checkParity(int x){
		int y = x ^ (x>>1);
		y = y ^ (y>>2);
		y = y ^ (y>>4);
		y = y ^ (y>>8);
		y = y ^ (y>>16);
		
		return y & 1;
	}
		
	/*Parity Check: Returns 0-for even parity, 1-for odd parity
	  This is an “in-register table lookup” operation. On the basic RISC it saves one
	  instruction, or two if the load of the constant is not counted. The low-order bit of y has
	  the original word’s parity, but the other bits of y do not contain anything useful.
	  */
	public int checkParityV2(int x){
		int y = x ^ (x>>4);		
		y = y ^ (y>>8);
		y = y ^ (y>>16);
		y = 0x6996 >> (y & 0xF);
		
		return y & 1;
	}
	
	//Parity Check: Returns 0-for even parity, 1-for odd parity
	public int checkParity3(int x){
		x = x ^ (x >> 1);
		x = (x ^ (x >> 2)) & 0x11111111;
		//x = x*0x11111111;       //Or
		int y = (x >> 28) & 1;	// x=x%16
		
		return y & 1;
	}

	//Count number of leading zeroes
	public int countLeadingZeroes(int x){
		if(x== 0) return 32;
		
		int n = 1;
		if((x>>>16) == 0) {n = n+16; x = x<<16;}
		if((x>>>24) == 0) {n = n+8; x = x<<8;}
		if((x>>>28) == 0) {n = n+4; x = x<<4;}
		if((x>>>30) == 0) {n = n+2; x = x<<2;}
		n = n - (x>>>31);
		
		return n;
	} 
	
	//Count number of leading zeroes
	public int countLeadingZeroesV2(int x){
		int y;
		int n;
		n = 32;
		y = x >>16; if (y != 0) {n = n - 16; x = y;}
		y = x >> 8; if (y != 0) {n = n - 8;  x = y;}
		y = x >> 4; if (y != 0) {n = n - 4;  x = y;}
		y = x >> 2; if (y != 0) {n = n - 2;  x = y;}
		y = x >> 1; if (y != 0) return n - 2;
		return n - x;
	}
	
	//Count number of leading zeroes
	public int countLeadingZeroesV3(int x){
		int y;
		int n, c;
		n = 32;
		c = 16;
		do {
		y = x >> c; if (y != 0) {n = n - c; x = y;}
		c = c >> 1;
		} while (c != 0);
		return n - x;
	}
	
	
	//Count number of leading zeroes
	public int countLeadingZeroesV4(int x){
		int y, n;
		n = 0;
		y = x;
		
		while(true){
			if (x < 0) return n;
			if (y == 0) return 32 - n;
			n = n + 1;
			x = x << 1;
			y = y >> 1;
		}
	}
	
	//Count number of leading zeroes
	public int countLeadingZeroesV5(int x){
		int y, m, n;
		y = -(x >> 16); // If left half of x is 0,
		m = (y >> 16) & 16; // set n = 16. If left half
		n = 16 - m; // is nonzero, set n = 0 and
		x = x >> m; // shift x right 16.
		// Now x is of the form 0000xxxx.
		y = x - 0x100; // If positions 8–15 are 0,
		m = (y >> 16) & 8; // add 8 to n and shift x left 8.
		n = n + m;
		x = x << m;
		y = x - 0x1000; // If positions 12–15 are 0,
		m = (y >> 16) & 4; // add 4 to n and shift x left 4.
		n = n + m;
		x = x << m;
		y = x - 0x4000; // If positions 14–15 are 0,
		m = (y >> 16) & 2; // add 2 to n and shift x left 2.
		n = n + m;
		x = x << m;
		y = x >> 14; // Set y = 0, 1, 2, or 3.
		m = y & ~(y >> 1); // Set m = 0, 1, 2, or 2 resp.
		return n + 2 - m;
	}
	
	//Compare number of leading zeroes
	public int compareLeadingZeroes(long x, long y){
		int cmp;
		if((x^y) <= (x&y)) cmp = 0;
		else if((x & ~y) > y) cmp = -1;
		else cmp = 1;
		return cmp;
	}
	
	//Count number of trailing zeroes
	public int countTrailingZeroes(int x){
		return (32-countLeadingZeroes(~x & (x-1)));
	}
	
	//Count number of trailing zeroes
	public int countTrailingZeroesV2(int x){
		int n;
		if (x == 0) return(32);
		n = 1;
		if ((x & 0x0000FFFF) == 0) {n = 17; x = x >> 16;}
		if ((x & 0x000000FF) == 0) {n = n + 8; x = x >> 8;}
		if ((x & 0x0000000F) == 0) {n = n + 4; x = x >> 4;}
		if ((x & 0x00000003) == 0) {n = n + 2; x = x >> 2;}
		return n - (x & 1);
	}
	
	//Count number of trailing zeroes
	public int countTrailingZeroesV3(int x){
		int y;
		int n;
		if (x == 0) return 32;
		n = 31;
		y = x <<16; if (y != 0) {n = n -16; x = y;}
		y = x << 8; if (y != 0) {n = n - 8; x = y;}
		y = x << 4; if (y != 0) {n = n - 4; x = y;}
		y = x << 2; if (y != 0) {n = n - 2; x = y;}
		y = x << 1; if (y != 0) {n = n - 1;}
		return n;
	}
	

	//Count number of trailing zeroes
	public int countTrailingZeroesV4(int x){
		if ((x & 15) > 0) {
			if ((x & 3) > 0) {
			if ((x & 1) > 0) return 0;
			else return 1;
			}
			else if ((x & 4) > 0) return 2;
			else return 3;
			}
			else if ((x & 0x30) > 0) {
			if ((x & 0x10) > 0) return 4;
			else return 5;
			}
			else if ((x & 0x40) > 0) return 6;
			else if (x > 0) return 7;
			else return 8;
	}
	
	//find first left most zero byte - Big Endian
	public int findFirstLeftMostZeroByte(int x) {
		if ((x >> 24) == 0) return 0;
		else if ((x & 0x00FF0000) == 0) return 1;
		else if ((x & 0x0000FF00) == 0) return 2;
		else if ((x & 0x000000FF) == 0) return 3;
		else return 4;
	}
	
	//find first left most zero byte - Big Endian
	int findFirstLeftMostZeroByteV2(int x) {
		int y;
		int n;
											// Original byte: 00 80 other
		y = (x & 0x7F7F7F7F)+ 0x7F7F7F7F; 	// 7F 7F 1xxxxxxx
		y = ~(y | x | 0x7F7F7F7F); 			// 80 00 00000000
		n = countLeadingZeroesV5(y) >> 3; 	// n = 0 ... 4, 4 if x
		return n; 							// has no 0-byte.
	}
	
	//find first left most zero byte - Big Endian
	int findFirstLeftMostZeroByteV3(int x) {
		int y;
		int n;
											
		y = (x - 0x01010101) & ~x & 0x80808080;
		n = countTrailingZeroesV4(y) >> 3;
		return n; 							
	}
	
	//find first left most zero byte - Big Endian
	int findBlank(int x){
		int y;
		int n;
		x = x ^ 0x20202020;									
		// Original byte: 00 80 other
		y = (x & 0x7F7F7F7F)+ 0x7F7F7F7F; 	// 7F 7F 1xxxxxxx
		y = ~(y | x | 0x7F7F7F7F); 			// 80 00 00000000
		n = countLeadingZeroesV5(y) >> 3; 	// n = 0 ... 4, 4 if x
		return n; 							// has no 0-byte.							
	}
	
	//find first left most zero byte - Big Endian
	int findLowercase(int x){
		int d;
		int y;
			
		d = (x | 0x80808080) - 0x61616161;
		d = ~((x | 0x7F7F7F7F) ^ d);
		y = (d & 0x7F7F7F7F) + 0x66666666;
		y = y | d;
		y = y | 0x7F7F7F7F; // Bytes not from 41–5A are FF.
		y = ~y; // Bytes not from 41–5A are 00,
		// bytes from 41–5A are 80.
		return (countLeadingZeroes(y) >> 3);
	}
	
	//find first left most zero byte - Big Endian
	int findUppercase(int x){
		int d;
		int y;
			
		d = (x | 0x80808080) - 0x41414141;
		d = ~((x | 0x7F7F7F7F) ^ d);
		y = (d & 0x7F7F7F7F) + 0x66666666;
		y = y | d;
		y = y | 0x7F7F7F7F; // Bytes not from 41–5A are FF.
		y = ~y; // Bytes not from 41–5A are 00,
		// bytes from 41–5A are 80.
		return (countLeadingZeroes(y) >> 3);
	}
	
	
	//find first left most zero byte - Big Endian
	int findDigits(int x){
		int d;
		int y;
			
		d = (x | 0x80808080) - 0x30303030;
		d = ~((x | 0x7F7F7F7F) ^ d);
		y = (d & 0x7F7F7F7F) + 0x66666666;
		y = y | d;
		y = y | 0x7F7F7F7F; // Bytes not from 41–5A are FF.
		y = ~y; // Bytes not from 41–5A are 00,
		// bytes from 41–5A are 80.
		return (countLeadingZeroes(y) >> 3);
	}
	
	public int findFirstNSetBitsString(int x, int n) {
		int k, p;
		p = 0; // Initialize position to return.
		while (x != 0) {
		k = countLeadingZeroes(x); // Skip over initial 0's
		x = x << k; // (if any).
		p = p + k;
		k = countLeadingZeroes(~x); // Count first/next group of 1's.
		if (k >= n) // If enough,
		return p; // return.
		x = x << k; // Not enough 1's, skip over
		p = p + k; // them.
		}
		return 32;
	}
	
	public int findFirstNSetBitsStringV2(int x, int n) {
		int s;
		while (n > 1) {
		s = n >>> 1;
		x = x & (x >>> s);
		n = n-s;
		}
		return countLeadingZeroes(x);
	}
	
	public int findLongestStringOfSetBits(int x){
		int k;
		for (k = 0; x != 0; k++) x = x & 2*x;
		return k;
	}
	
	public int findSmallestStringOfSetBits(int x) {
		int k;
		int b, e; // Beginnings, ends.
		if (x == 0) return 0;
		b = ~(x >> 1) & x; // 0–1 transitions.
		e = x & ~(x << 1); // 1–0 transitions.
		for (k = 1; (b & e) == 0; k++)
		e = e << 1;
		
		return k;
	}
	
	public int reverseBits(int x){
		x = (x & 0x55555555) << 1 | (x & 0xAAAAAAAA) >>> 1;
		x = (x & 0x33333333) << 2 | (x & 0xCCCCCCCC) >>> 2;
		x = (x & 0x0F0F0F0F) << 4 | (x & 0xF0F0F0F0) >>> 4;
		x = (x & 0x00FF00FF) << 8 | (x & 0xFF00FF00) >>> 8;
		x = (x & 0x0000FFFF) << 16 | (x & 0xFFFF0000) >>> 16;
		
		return x;
	}
	
	public int outerPerfectShuffle(int x){
		int t;
		t = (x ^ (x >>> 8)) & 0x0000FF00; x = x ^ t ^ (t << 8);
		t = (x ^ (x >>> 4)) & 0x00F000F0; x = x ^ t ^ (t << 4);
		t = (x ^ (x >>> 2)) & 0x0C0C0C0C; x = x ^ t ^ (t << 2);
		t = (x ^ (x >>> 1)) & 0x22222222; x = x ^ t ^ (t << 1);
		return x;
	}
	
	public int outerPerfectShuffleV2(int x){
			x = (x & 0x0000FF00) << 8 | (x >> 8) & 0x0000FF00 | x &
			0xFF0000FF;
			x = (x & 0x00F000F0) << 4 | (x >> 4) & 0x00F000F0 | x &
			0xF00FF00F;
			x = (x & 0x0C0C0C0C) << 2 | (x >> 2) & 0x0C0C0C0C | x &
			0xC3C3C3C3;
			x = (x & 0x22222222) << 1 | (x >> 1) & 0x22222222 | x &
			0x99999999;
			
			return x;
	}
	
	public int outerPerfectUnShuffle(int x){
		int t;
		t = (x ^ (x >>> 1)) & 0x22222222; x = x ^ t ^ (t << 1);		
		t = (x ^ (x >>> 2)) & 0x0C0C0C0C; x = x ^ t ^ (t << 2);		
		t = (x ^ (x >>> 4)) & 0x00F000F0; x = x ^ t ^ (t << 4);
		t = (x ^ (x >>> 8)) & 0x0000FF00; x = x ^ t ^ (t << 8);
		return x;
	}
	
	public int innerPerfectShuffle(int x){
		int t;
		t = (x ^ (x >>> 16)) & 0x0000FFFF; x = x ^ t ^ (t << 16);
		t = (x ^ (x >>> 8)) & 0x0000FF00; x = x ^ t ^ (t << 8);
		t = (x ^ (x >>> 4)) & 0x00F000F0; x = x ^ t ^ (t << 4);
		t = (x ^ (x >>> 2)) & 0x0C0C0C0C; x = x ^ t ^ (t << 2);
		t = (x ^ (x >>> 1)) & 0x22222222; x = x ^ t ^ (t << 1);
		return x;
	}
	
	public int innerPerfectShuffleV2(int x){
			x = (x & 0x0000FFFF) << 16 | (x >> 16) & 0x0000FFFF | x &
			0x00000000;
			x = (x & 0x0000FF00) << 8 | (x >> 8) & 0x0000FF00 | x &
			0xFF0000FF;
			x = (x & 0x00F000F0) << 4 | (x >> 4) & 0x00F000F0 | x &
			0xF00FF00F;
			x = (x & 0x0C0C0C0C) << 2 | (x >> 2) & 0x0C0C0C0C | x &
			0xC3C3C3C3;
			x = (x & 0x22222222) << 1 | (x >> 1) & 0x22222222 | x &
			0x99999999;
			
			return x;
	}
	
	public int innerPerfectUnShuffle(int x){
		int t;
		t = (x ^ (x >>> 1)) & 0x22222222; x = x ^ t ^ (t << 1);		
		t = (x ^ (x >>> 2)) & 0x0C0C0C0C; x = x ^ t ^ (t << 2);		
		t = (x ^ (x >>> 4)) & 0x00F000F0; x = x ^ t ^ (t << 4);
		t = (x ^ (x >>> 8)) & 0x0000FF00; x = x ^ t ^ (t << 8);
		t = (x ^ (x >>> 16)) & 0x0000FFFF; x = x ^ t ^ (t << 16);
		return x;
	}
	
	public int compress(int x, int m) {
		int r, s, b; // Result, shift, mask bit.
		r = 0;
		s = 0;
		
		do {
			b = m & 1;
			r = r | ((x & b) << s);
			s = s + (x & b);
			x = x >>> 1;
			m = m >>> 1;
		} while (m != 0);
		
		return r;
	}
	
	public int extract(int x, int m) {
		int r, s, b; // Result, shift, mask bit.
		r = 0;
		s = 0;
		
		do {
			b = m & 1;
			r = r | ((x & b) << s);
			s = s + 1;
			x = b!=0 ? x >>> 1 : x;
			m = m >>> 1;
		} while (x!=0 || m!=0);
		
		return r;
	}
	
	public int squareRoot(int x) {
		int x1;
		int s, g0, g1;
		if (x <= 1) return x;
		s = 1;
		x1 = x - 1;
		if (x1 > 65535) {s = s + 8; x1 = x1 >> 16;}
		if (x1 > 255) {s = s + 4; x1 = x1 >> 8;}
		if (x1 > 15) {s = s + 2; x1 = x1 >> 4;}
		if (x1 > 3) {s = s + 1;}
		g0 = 1 << s; // g0 = 2**s.
		
		g1 = (g0 + (x >> s)) >> 1; // g1 = (g0 + x/g0)/2.
		while (g1 < g0) { // Do while approximations
			g0 = g1; // strictly decrease.
			g1 = (g0 + (x/g0)) >> 1;
		}
		return g0;
	}
	
	public int cubeRoot(int x) {
		int s;
		int y, y2, b;
		y = 0;
		y2 = 0;
		for (s = 30; s >= 0; s = s - 3) {
			y = 2*y;
			y2 = 4*y2;
			
			b = ((3*y2+3*y) | 1) << s;
			if (x >= b) {
				x = x - b;
				y2 = (y2+2*y) | 1;
				y = y | 1;
			}
		}
		return y;
	}
	
	public int cubeRootV2(int x) {
		int s;
		int y, b;
		y = 0;
		
		for (s = 30; s >= 0; s = s - 3) {
			y = 2*y;
			b = (3*y*(y + 1) + 1) << s;
			if (x >= b) {
				x = x - b;
				y = y + 1;
			}
		}
		return y;
	}
	
	public int exp(int x, int n) {
		int p, y;
		y = 1; // Initialize result
		p = x; // and p.
		
		while(true) {
			if ((n&1) > 0) y = p*y; // If n is odd, mult by p.
			n = n >>> 1; // Position next bit of n.
			if (n == 0) return y; // If no more bits in n.
			p = p*p; // Power for next bit of n.
		}
	}
	
	public static void main(String[] args) {
		BitTricks bitTricks = new BitTricks();
		System.out.println("Add: "+bitTricks.add(10, 50));
		System.out.println("Add V2: "+bitTricks.addV2(10, 50));
		System.out.println("Subtract: "+bitTricks.subtract(10, 50));
		System.out.println("Next higher: "+bitTricks.nextHigher(216));
		System.out.println("Immediate Smaller: "+bitTricks.abs(-3));
		System.out.println("Average: "+bitTricks.average(-3,-4));
		System.out.println("Right Signed Shift: "+Long.toBinaryString(bitTricks.rightSignedShift(-1)));
		System.out.println("Greater of two: "+bitTricks.greater(-6,9));
		System.out.println("Transfer sign: "+bitTricks.transferOfSign(-6,-9));
		System.out.println("Transfer sign: "+bitTricks.decodeZeroMeans2nField(-10));
		System.out.println("Is equal: "+bitTricks.isEqual(-8756, -8756));
		System.out.println("Is not equal: "+bitTricks.isNotEqual(86, 8756));
		System.out.println("Check bound: "+bitTricks.checkBound(0, 0, 48));
		System.out.println("Set bit count: "+bitTricks.countSetBits(0x000000000FFFFFFFL));
		System.out.println("Set bit count: "+bitTricks.countSetBitsV2(0x000000000FFFFFFFL));
		System.out.println("Set bit count: "+bitTricks.countSetBitsV3(0x0000FFFF));
		System.out.println("Set bit count: "+bitTricks.countSetBitsV4(0x0000FFFF));
		System.out.println("Set bit count: "+bitTricks.setBitsDiff(0x0000FFFF, 0x000000FF));
		
		int [] inputArray = {0x000000FF, 0x000000FF, 0x000000FF, 0x000000FF};
		System.out.println("Set bit count in given array: "+bitTricks.countSetBitsInArray(inputArray));
		
		System.out.println("Parity: "+bitTricks.checkParity(5));
		System.out.println("Parity: "+bitTricks.checkParityV2(32));
		System.out.println("Parity: "+bitTricks.checkParityV2(10));
		System.out.println("Number of leading zeroes: "+bitTricks.countLeadingZeroes(0x0000FFFF));
		System.out.println("Number of leading zeroes: "+bitTricks.countLeadingZeroesV2(0x0000FFFF));
		System.out.println("Number of leading zeroes: "+bitTricks.countLeadingZeroesV3(0x0000FFFF));
		System.out.println("Number of leading zeroes: "+bitTricks.countLeadingZeroesV4(0x0000FFFF));
		System.out.println("Number of leading zeroes: "+bitTricks.countLeadingZeroesV5(0x0000FFFF));
		System.out.println("Leading zeroes: "+bitTricks.compareLeadingZeroes(0x00FFFFFF, 0xFFFFFFFF));
		System.out.println("Trailing zeroes: "+bitTricks.countTrailingZeroes(0xFFFFFF00));
		System.out.println("Trailing zeroes: "+bitTricks.countTrailingZeroesV2(0xFFFFFF00));
		System.out.println("Trailing zeroes: "+bitTricks.countTrailingZeroesV3(0xFFFFFF00));
		System.out.println("Trailing zeroes: "+bitTricks.countTrailingZeroesV4(0xFFFFFF00));
		System.out.println("Trailing zero bytes: "+bitTricks.findFirstLeftMostZeroByte(0xFFFFFF00));
		System.out.println("Trailing zero bytes: "+bitTricks.findFirstLeftMostZeroByteV2(0xFFFFFF00));
		System.out.println("Trailing zero bytes: "+bitTricks.findFirstLeftMostZeroByteV3(0xFFFF0000));
		System.out.println("Blanks: "+bitTricks.findBlank(0x00200000));
		System.out.println("LowerCase: "+bitTricks.findLowercase(0x00200070));
		System.out.println("Uppercase: "+bitTricks.findUppercase(0x00200047));
		System.out.println("Digit: "+bitTricks.findDigits(0x00200031));
		System.out.println("Fist 1-bit String of given length: "+bitTricks.findFirstNSetBitsString(0x00200F31, 8));
		System.out.println("Fist 1-bit String of given length: "+bitTricks.findFirstNSetBitsStringV2(0x0020FF31, 8));
		System.out.println("Longest String of set bits: "+bitTricks.findLongestStringOfSetBits(0x0020FF31));
		System.out.println("Bits Reversed: "+bitTricks.reverseBits(0xFF000000));
		System.out.println(Integer.toBinaryString(0xFF325531));
		System.out.println("Outer Perfect Shuffle: "+Integer.toBinaryString(bitTricks.outerPerfectShuffle(0xFF325531)));
		System.out.println("Outer Perfect Shuffle: "+Integer.toBinaryString(bitTricks.outerPerfectShuffleV2(0xFF325531)));
		System.out.println("Outer Perfect Unshuffle: "+Integer.toBinaryString(bitTricks.outerPerfectUnShuffle(bitTricks.outerPerfectShuffle(0xFF325531))));
		System.out.println("Inner Perfect Shuffle: "+Integer.toBinaryString(bitTricks.innerPerfectShuffle(0xFF325531)));
		System.out.println("Inner Perfect Shuffle: "+Integer.toBinaryString(bitTricks.innerPerfectShuffleV2(0xFF325531)));
		System.out.println("Inner Perfect Unshuffle: "+Integer.toBinaryString(bitTricks.innerPerfectUnShuffle(bitTricks.innerPerfectShuffle(0xFF325531))));
		System.out.println("Compress: "+Integer.toHexString(bitTricks.compress(0xCC004400, 0xCC044000)));
		System.out.println("Extract: "+Integer.toHexString(bitTricks.extract(bitTricks.compress(0xCC004400, 0xCC044000), 0xCC044000)));
		System.out.println("Square Root: "+bitTricks.squareRoot(4));
		System.out.println("Cube Root: "+bitTricks.cubeRoot(729));
		System.out.println("Cube Root: "+bitTricks.cubeRootV2(729));
		System.out.println("Exponential: "+bitTricks.exp(2, 10));
	}

}