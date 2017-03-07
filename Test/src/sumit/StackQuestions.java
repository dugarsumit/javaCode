package sumit;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackQuestions {

	public static void main(String[] args) {
		int a[] = { 1, 5, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5, 5};
		int b[] = {1, 9, 6, 8,8,8,0,1,1,0,6,5};
		recursivelyRemoveAdjacentDuplicates(b);
	}

	private static int[] span(int[] a) {
		int[] span = new int[a.length];
		span[0] = 1;
		for (int i = 1; i < a.length; i++) {
			if (a[i] >= a[i - 1]) {
				span[i] = span[i - 1] + 1;
			} else {
				span[i] = 1;
			}
		}
		return span;
	}

	/**
	 * Given an array with heights of rectangles (assuming width as 1), we need
	 * to find the largest rectangle possible.
	 * http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
	 * 
	 * @param a
	 * @return
	 */
	private static int largestRectangle(int a[]) {
		int maxArea = 0;
		int i=0;
		Deque<Integer> stack = new ArrayDeque<Integer>();
		while(i<a.length){
			if(stack.isEmpty() || a[i]>=a[stack.peek()]){
				stack.push(i++);
			}else{
				int top = stack.pop();
				maxArea = Math.max(maxArea, a[top] * (stack.isEmpty()?i:i-stack.peek()-1));
			}
		}
		while(!stack.isEmpty()){
			int top = stack.pop();
			maxArea = Math.max(maxArea, a[top] * (stack.isEmpty()?i:i-stack.peek()-1));
		}
		
		return maxArea;
	}
	
	private static void sort(int a[]){
		Deque<Integer> stack = new ArrayDeque<Integer>();
		for(int i=0;i<a.length;i++){
			stack.push(a[i]);
		}
		System.out.println(stack.toString());
		Deque<Integer> sortedStack = new ArrayDeque<Integer>();
		while(!stack.isEmpty()){
			int tmp = stack.pop();
			while(!sortedStack.isEmpty() && tmp<sortedStack.peek()){
				stack.push(sortedStack.pop());
			}
			sortedStack.push(tmp);
		}
		System.out.println(sortedStack.toString());
	}

	private static void recursivelyRemoveAdjacentDuplicates(int a[]) {
		Deque<Integer> stack = new ArrayDeque<Integer>();
		for (int i = 0; i < a.length;) {
			if (stack.isEmpty() || (i<a.length-1 && a[i] != a[i + 1])) {
				if (!stack.isEmpty()) {
					if (a[i] == stack.peek()) {
						stack.pop();
					} else {
						stack.push(a[i]);
					}
				} else {
					stack.push(a[i]);
				}
				i++;
			} else {
				int ptr = i;
				while (ptr < a.length && a[i] == a[ptr]) {
					ptr++;
				}
				if (a[i] == stack.peek()) {
					stack.pop();
				}
				if(i==a.length-1 && ptr==a.length){
					stack.push(a[i]);
				}
				i = ptr;
			}
		}
		System.out.println(stack.toString());
	}
	
	private static void print(int a[]) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + "-");
		}
		System.out.println("");
	}
}
