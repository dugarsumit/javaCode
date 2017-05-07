import java.util.List;
import java.util.Scanner;

public class PrimeGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Do you want to run test cases (1/0) ?");
		int ans = scanner.nextInt();
		if(ans==1){
			runTestCases();
		}else{
			System.out.println("No tests cases were run!");
		}
		System.out.println("Number of tries? :");
		int t = scanner.nextInt();
		int a,b,c;
		while(t>0){
			System.out.println("Enter first number :");
			a = scanner.nextInt();
			while(a==0){
				System.out.println("Zero is an invalid Number! Please enter again : ");
				a = scanner.nextInt();
			}
			System.out.println("Enter second number :");
			b = scanner.nextInt();
			while(b>=Strategy.MAX_RANGE){
				System.out.println(b +" is an invalid Number! Please enter again : ");
				b = scanner.nextInt();
			}
			System.out.println("---------------------------- :");
			System.out.println("Available algorithms for generating primes between "
							+ a + " and " + b);
			System.out.println("1) Simple algorithm but slow");
			System.out.println("2) Moderately complex algorithm and medium fast");
			System.out.println("3) Complex algorithm and fast");
			System.out.println("Your Choice :");
			c = scanner.nextInt();
			while(c>3 || c<1){
				System.out.println("Invalid strategy! Please choose a correct strategy");
				c = scanner.nextInt();
			}
			switch (c) {
			case 1:
				runStrategy1(a,b);
				break;
			case 2:
				runStrategy2(a,b);
				break;
			case 3:
				runStrategy3(a,b);
				break;
			default:
				break;
			}
			t--;
		}
		scanner.close();
	}
	
	private static void runStrategy1(int a,int b){
		Strategy strategy = new SimpleGenerator();
		List<Integer> primeNumbers = strategy.generatePrimeNumbers(a, b);
		System.out.println(primeNumbers);
	}
	
	private static void runStrategy2(int a,int b){
		Strategy strategy = new Sieve();
		if(Sieve.isSieve()){
			((Sieve)strategy).sieve(Strategy.MAX_RANGE);
		}
		List<Integer> primeNumbers = strategy.generatePrimeNumbers(a, b);
		System.out.println(primeNumbers);
	}
	
	private static void runStrategy3(int a,int b){
		Strategy strategy = new OptimizedSieve();
		if(OptimizedSieve.isOptimizedSieve()){
			((OptimizedSieve)strategy).optimizedSieve(Strategy.MAX_RANGE);
		}
		List<Integer> primeNumbers = strategy.generatePrimeNumbers(a, b);
		System.out.println(primeNumbers);
	}
	
	public static void runTestCases(){
		int a[] = {1,10,20};
		int b[] = {10,100,35};
		for(int i=0;i<a.length;i++){
			System.out.println("Test Case-"+i+1+" : [a="+a[i]+",b="+b[i]+"]");
			runStrategy1(a[i], b[i]);
			runStrategy2(a[i], b[i]);
			runStrategy3(a[i], b[i]);
			System.out.println("--------------------------------");
		}
	}
}
