import java.util.List;


public abstract class Strategy {

	/**
	 * maximum range upto which prime numbers can be
	 * generated. This application is tested for
	 * MAX_RANGE = 100001
	 */
	final static int MAX_RANGE = 100001;
	
	/**
	 * Every generator should implement it's own prime
	 * number generating strategy
	 * @param a
	 * @param b
	 */
	public abstract List<Integer> generatePrimeNumbers(int a, int b); 
	
	/**
	 * This method checks whether a natural number is prime
	 * or not.
	 * 
	 * @param n
	 * @return
	 */
	public Boolean isPrime(int n) {
		int nSquareRoot = (int)Math.sqrt((double)n);
		if(n==2){
			return true;
		}else if(n==1){
			return false;
		}else if(n%2==0){
			return false;
		}
		for(int i=3;i<=nSquareRoot;i=i+2){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}
}
