package sumit;

public class LongestPalindomicSubstring {

	
	public static void main(String[] args) {
		String str = "forgeeksskeegfor";
		//String str = "aaaa";
		int n = str.length();
		boolean isPalindrome[][]= new boolean[n+1][n+1];
		int maxLength = 1;
		int beg = 0;
		
		/*
		 * all substrings of length 1 are palindromes
		 * substrings of length 2 are palindromes if both chars are same
		 */
		for(int i=0;i<n;i++){
			isPalindrome[i][i]=true;
			if(i<n-1){
				if(str.charAt(i)==str.charAt(i+1)){
					maxLength=2;
					beg=i;
					isPalindrome[i][i+1]=true;
				}
			}
		}
				
		for(int len=3;len<=n;len++){
			for(int start=0;start<=n-len;start++){
				int end = start+len-1;
				
				if(isPalindrome[start+1][end-1] && str.charAt(start)==str.charAt(end)){
					isPalindrome[start][end]=true;
					if(len>maxLength){
						maxLength=len;
						beg=start;
					}
				}
			}
		}
		System.out.println(str.substring(beg, beg+maxLength));
	}

}
