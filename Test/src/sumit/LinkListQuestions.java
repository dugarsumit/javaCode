package sumit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class LinkListQuestions {

	private static void printList(ListNode list){
		while(list!=null){
			System.out.print(list.getData()+"-");
			list = list.getNext();
		}
	}
	
	/**
	 * Calculates length of both circular and single linked list
	 * @param head
	 * @return
	 */
	private static int length(ListNode head){
		if(head==null){
			return 0;
		}
		int l = 1;
		ListNode current = head.getNext();
		while(current!=null && current!=head){
			l++;
			current = current.getNext();
		}
		return l;
	}
	
	private static ListNode createList(){
		ListNode head = new ListNode(1);
		head.setNext(new ListNode(2));
		head.getNext().setNext(new ListNode(3));
		head.getNext().getNext().setNext(new ListNode(4));
		head.getNext().getNext().getNext().setNext(new ListNode(5));
		head.getNext().getNext().getNext().getNext().setNext(new ListNode(6));
		head.getNext().getNext().getNext().getNext().getNext().setNext(new ListNode(7));
		//head.getNext().getNext().getNext().getNext().getNext().getNext().setNext(head);
		return head;
	}
	
	public static void main(String[] args) {
		ListNode head = createList();
		printList(head);
		ListNode newHead = rightShift(head, 3);
		System.out.println("------------");
		printList(newHead);
	}
	
	/**
	 * Reverse Link List in Pairs
	 * QUE : 1-2-3-4-x
	 * ANS : 2-1-4-3-x
	 * 
	 * @param head
	 * @return
	 */
	private static ListNode reverseLinkListInPair(ListNode head){
		ListNode prev = null;
		ListNode newHead = null;
		ListNode current = head;
		while(current!=null && current.getNext()!=null){
			ListNode next = current.getNext();
			ListNode temp = next.getNext();
			current.setNext(temp);
			next.setNext(current);
			if(prev==null){
				newHead = next;
			}else{
				prev.setNext(next);
			}
			prev = current;
			current = temp;
		}
		return newHead; 
	}
	
	/**
	 * QUE : 1-2-3-4
	 * ANS : 4-2-3-1
	 * @param head
	 * @return
	 */
	private static ListNode reverseLinkList(ListNode head){
		ListNode prev = null;
		ListNode current = head;
		while(current!=null){
			ListNode next = current.getNext();
			current.setNext(prev);
			prev =current;
			current = next;
		}
		return prev;
	}
	
	/**
	 * Reverse link list in blocks of k
	 * Let k = 3
	 * Que : 1-2-3-4-5-6-7-8
	 * Ans : 3-2-1-6-5-4-8-7
	 * 
	 * @param head
	 * @return
	 */
	private static ListNode reverseLinkListInBlocks(ListNode head,int k){
		ListNode prev = null;
		ListNode current = head;
		ListNode next = null;
		int count = k;
		while(current!=null && count>0){
			next = current.getNext();
			current.setNext(prev);
			prev =current;
			current = next;
			count--;
		}
		if(next!=null){
			head.setNext(reverseLinkListInBlocks(next, k));
		}
		return prev;
	}
	
	private static ListNode josephusProblem(ListNode head,int m){
		ListNode current = head;
		int n = length(head);
		for(int i=n;n>1;n--){
			ListNode prev = null;
			for(int j=0;j<m-1;j++){
				prev = current;
				current = current.getNext();
			}
			prev.setNext(current.getNext());
			current = current.getNext();
		}
		return current;
	}
	
	private static ListNode rightShift(ListNode head,int k){
		ListNode newHead = null;
		int n = length(head)-k;
		ListNode current = head;
		ListNode firstHalfLastNode = null;
		while(current!=null && --n>=0){
			firstHalfLastNode = current;
			current = current.getNext();
		}
		firstHalfLastNode.setNext(null);
		newHead = current;
		while(current.getNext()!=null){
			current = current.getNext();
		}
		current.setNext(head);
		return newHead;
	}
}
