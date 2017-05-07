import java.util.ArrayList;
import java.util.List;


public class SimpleGenerator extends Strategy{

	/**
	 *  1 : prime
	 *  0 : not checked yet
	 * -1 : composite
	 * 	This is used for creating a bit map. Each index bit 
	 * 	represents a natural number. Each index can have one
	 * 	of the three values {-1,0,1}
	 */
	private static int primeBits[] = new int[MAX_RANGE];
	
	
	/**
	 * Algorithm
	 * 1)	For each number between a and b firstly check in
	 * 		primeBits whether it is prime or not.
	 * 2)	If the number is never been checked, perform a
	 * 		isPrime check and update primeBits accordingly.
	 * 3)	If the number is prime then update primeNumbers
	 * 		list otherwise do nothing.
	 * 		
	 */
	public List<Integer> generatePrimeNumbers(int a, int b) {
		List<Integer> primeNumbers = new ArrayList<Integer>();
		for(int i=a;i<=b;i++){
			if(primeBits[i]==1){
				primeNumbers.add(i);
			}else if(primeBits[i]==0){
				if(isPrime(i)){
					primeNumbers.add(i);
					primeBits[i]=1;
				}else{
					primeBits[i]=-1;
				}
			}
		}
		return primeNumbers;
	}

}
