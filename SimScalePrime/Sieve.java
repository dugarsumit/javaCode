import java.util.ArrayList;
import java.util.List;


public class Sieve extends Strategy{
	
	/**
	 * It's like a boolean map where each index represents
	 * a natural number. It's values can be interpreted as 
	 * follows
	 * True : composite number
	 * False : prime number
	 */
	private static boolean isComposite[] = new boolean[MAX_RANGE];
	
	/**
	 * It's a switch that controls whether we should run this
	 * algorithm or not.
	 */
	private static boolean isSieve = true;
	
	/**
	 * For every number less than MAX_RANGE, it checks if the
	 * number is prime and lies between a and b
	 */
	@Override
	public List<Integer> generatePrimeNumbers(int a, int b) {
		List<Integer> primeNumbers = new ArrayList<Integer>();
		for(int i=2;i<MAX_RANGE;i++){
			if(isComposite[i]==false && i>=a && i<=b){
				primeNumbers.add(i);
			}
		}
		return primeNumbers;
	}

	/**
	 * It implements this algorithm
	 * https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
	 * @param n
	 */
	public void sieve(int n) {
		int nSquareRoot = (int)Math.sqrt((double)n);
		for(int i=2;i<=nSquareRoot;i++){
			if(isComposite[i]==false){
				int squareI = i*i;
				for(int j=squareI;j<n;j=j+i){
					isComposite[j]=true;
				}
			}
		}
		setSieve(false);
	}

	public static boolean isSieve() {
		return isSieve;
	}

	public static void setSieve(boolean isSieve) {
		Sieve.isSieve = isSieve;
	}
}
