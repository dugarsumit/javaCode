package com.sumit;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


public class OptimizedSieve extends Strategy{

	/**
	 * It's like a boolean map where each bit represents
	 * a natural number. It's values can be interpreted as 
	 * follows
	 * 1 : composite number
	 * 0 : prime number
	 * It is more memory efficient than boolean[]
	 */
	private static BitSet isCompos = new BitSet(MAX_RANGE);
	
	/**
	 * It's a switch that controls whether we should run this
	 * algorithm or not.
	 */
	private static boolean isOptimizedSieve = true;
	
	/**
	 * These are number of primes less than 100000
	 */
	private static int NUMBER_OF_PRIMES = 9592;
	
	/**
	 * Stores prime numbers generated from sieves
	 */
	private static int primes[] = new int[NUMBER_OF_PRIMES];
	//private static List<Integer> primes = new ArrayList<Integer>();
	
	
	/**
	 * Optimized Sieve of Eratosthenes
	 * 1)	instead of traversing through the entire n numbers we will,
	 * 		traverse only the prime numbers.
	 * 2)	we will use a binary search variant for finding prime numbers
	 * 		within the range of a and b.
	 * 3) 	We can make it more memory efficient by using BitSet instead of
	 * 		boolean[]
	 * @param a
	 * @param b
	 */
	@Override
	public List<Integer> generatePrimeNumbers(int a, int b) {
		List<Integer> primeNumbers = new ArrayList<Integer>();
		int start = binarySearch(a, 0, primes.length-1);
		for(int i=start;i<primes.length;i++){
			if(primes[i]>=a && primes[i]<=b){
				primeNumbers.add(primes[i]);
			}
		}
		return primeNumbers;
	}

	
	/**
	 * It is similar to originl Sieve except that it also stores
	 * prime numbers in the array primes[]
	 * 
	 * @param n
	 */
	public void optimizedSieve(int n){
		int nSquareRoot = (int)Math.sqrt((double)n);
		for(int i=2;i<=nSquareRoot;i++){
			if(isCompos.get(i)==false){
				int squareI = i*i;
				for(int j=squareI;j<n;j=j+i){
					isCompos.set(j);
				}
			}
		}
		// -2 for handling case of 0 and 1 being prime
		NUMBER_OF_PRIMES = MAX_RANGE-isCompos.cardinality()-2;
		primes = new int[NUMBER_OF_PRIMES];
		int index = 0;
		for (int k = 2; k < MAX_RANGE; k++) {
			if (isCompos.get(k) == false) {
				primes[index] = k;
				index++;
			}
		}
		setOptimizedSieve(false);
	}
	
	
	/**
	 * binary search for closest element greater than or equal to a
	 * 
	 * @param a
	 * @param first
	 * @param last
	 * @return
	 */
	public static int binarySearch(int a, int first, int last) {
		if (first > last) {
			return -1;
		}
		int mid = (last + first) / 2;
		if (a == primes[mid]) {
			return mid;
		} else if (a < primes[mid]) {
			if (a >= primes[first] && a <= primes[mid - 1]) {
				return binarySearch(a, first, mid-1);
			} else if (a < primes[first]) {
				return first;
			} else {
				return mid;
			}
		} else {
			if (a <= primes[last] && a >= primes[mid + 1]) {
				return binarySearch(a, mid + 1, last);
			} else if (a > primes[last]) {
				return last;
			} else {
				return mid;
			}
		}
	}

	public static boolean isOptimizedSieve() {
		return isOptimizedSieve;
	}

	public static void setOptimizedSieve(boolean isOptimizedSieve) {
		OptimizedSieve.isOptimizedSieve = isOptimizedSieve;
	}
}
