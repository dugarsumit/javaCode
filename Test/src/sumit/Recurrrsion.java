package sumit;


public class Recurrrsion {
	
	public static void printBinary(String str,int n){
		if(str.length()==n){
			System.out.println(str);
		}else{
			printBinary(str+"0", n);
			printBinary(str+"1", n);
		}
	}
	
	public static void towerOfHanoi(int n, String fromPeg, String toPeg,String auxPeg){
		if(n==1){
			System.out.println("Move from "+fromPeg+" to "+toPeg);
		}else{
			towerOfHanoi(n-1, fromPeg, auxPeg, toPeg);
			System.out.println("Move from "+ fromPeg + " to "+toPeg);
			towerOfHanoi(n-1, auxPeg, toPeg, fromPeg);
		}
	}
	
	
	public static void main(String[] args) {
		//printBinary("", 4);
		towerOfHanoi(4, "A", "B", "C");
	}
}
