package sumit;


/**
 * Prob : 24 
 * Page : 121s
 * @author sumit
 *
 */
public class LargestRectangle {

	public static int longestRectangle(int a[]){
		int x=1;
		for(int i=0;i<a.length;i++){
			if(a[i]>x){
				x=a[i];
			}
		}
		return x;
	}
	
	public static void main(String[] args) {
		int a[] = {3,2,5,6,1,4,4};
		int longestRectangle = longestRectangle(a);
	}
}
