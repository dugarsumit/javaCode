package sumit;

public class ArrayQuestions {
	
	public static void main(String[] args) {
		/*int a[] = {1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0};
		print(a);
		System.out.println();
		int b [] = partition(a);
		print(b);*/
		
		int a [] = {-2, -5, 6, -2, -3, 1, 5, -6};
		int sum = maximumSumSubArrayDNC(a,0,a.length-1);
		System.out.println(sum);
	}
	
	/**
	 * maximumSumSubArray Divide and Conquer O(NlogN)
	 * @param a
	 */
	public static int maximumSumSubArrayDNC(int a [],int start,int end){
		if(start>end){
			return 0;
		}
		
		if(start==end){
			return Math.max(0,a[start]);
		}
		
		int mid = (start+end)/2;
		int leftMidSumMax = 0;
		int sum = 0;
		for(int i=mid-1;i>=start;i--){
			sum+=a[i];
			leftMidSumMax = Math.max(sum, leftMidSumMax);
		}
		
		int rightMidSumMax = 0;
		sum = 0;
		for(int i=mid;i<end;i++){
			sum+=a[i];
			rightMidSumMax = Math.max(sum, rightMidSumMax);
		}
		int midSum = leftMidSumMax+rightMidSumMax;
		int leftSum = maximumSumSubArrayDNC(a, start, mid);
		int rightSum = maximumSumSubArrayDNC(a, mid+1, end);
		return Math.max(Math.max(leftSum, rightSum),midSum);
	}
	
	
	/**
	 * maximumSumSubArray Dynamic Programming O(N2)
	 * @param a
	 */
	public static void maximumSumSubArrayDP(int a[]){
		int[] sumUpTo = new int[a.length];
		int partialSum = 0;
		for(int i=0;i<a.length;i++){
			partialSum+=a[i];
			sumUpTo[i]=partialSum;
		}
		
		int max= 0;
		for(int subArraySize = 1;subArraySize<=a.length;subArraySize++){
			for(int start=0;start<=a.length-subArraySize;start++){
				int end = start+subArraySize-1;
				int sum = sumUpTo[end]-sumUpTo[start];
				if(sum>max) max=sum;
			}
		}
		System.out.println(max);
	}
	
	/**
	 * maximumSumSubArray Brute Force O(N3)
	 * @param a
	 */
	public static void maximumSumSubArrayBF(int a[]){
		int max= 0;
		for(int subArraySize = 1;subArraySize<=a.length;subArraySize++){
			for(int start=0;start<=a.length-subArraySize;start++){
				int sum=0;
				int end = start+subArraySize;
				for(int i=start;i<end;i++){
					sum+=a[i];
				}
				if(sum>max) max=sum;
			}
		}
		System.out.println(max);
	}

	/**
	 * Move all the zeros to the end of an array.
	 * @param a
	 * @return
	 */
	public static int [] moveZerosToEnd(int [] a){
		int countOfNonZero = 0;
		for(int i=0;i<a.length;i++){
			if(a[i]!=0){
				a[countOfNonZero++]=a[i];
			}
		}
		while(countOfNonZero<a.length){
			a[countOfNonZero++]=0;
		}
		return a;
	}
	
	public static void print(int [] a){
		for(int i :a){
			System.out.print(i+" ");
		}
	}
	
	/**
	 * It is almost similar to quick sort partition. It can be used to move all the zeros to end in an array.
	 * @param a
	 * @return
	 */
	public static int [] partition(int a[]){
		int mid=0;
		for(int i=0;i<a.length;i++){
			if(a[i]!=0){
				int temp = a[mid];
				a[mid] = a[i];
				a[i] = temp;
				mid++;
			}
		}
		return a;
	}
	
}

