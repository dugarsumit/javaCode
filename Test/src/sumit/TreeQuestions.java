package sumit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TreeQuestions {

	public static void main(String[] args) {
		Tree root = initialize();
		zigzag(root);
	}
	
	private static Tree initialize(){
		Tree root = new Tree(1);
		root.setLeft(new Tree(2));
		root.setRight(new Tree(3));
		root.getLeft().setLeft(new Tree(4));
		root.getLeft().setRight(new Tree(5));
		root.getRight().setLeft(new Tree(6));
		root.getRight().setRight(new Tree(7));
		return root;
	}
	
	private static void zigzag(Tree root){
		if(root==null){
			return;
		}
		Tree delimeterNode = new Tree(Integer.MIN_VALUE);
		Deque<Tree> queue = new ArrayDeque<Tree>();
		List<Integer> out = new ArrayList<Integer>();
		queue.offer(root);
		queue.offer(delimeterNode);
		while(!queue.isEmpty()){
			Tree current = queue.poll();
			if (current != null && current.getData() != Integer.MIN_VALUE) {
				out.add(current.getData());
				if (current.getLeft() != null) {
					queue.offer(current.getLeft());
				}
				if (current.getRight() != null) {
					queue.offer(current.getRight());
				}
			} else{
				if(!queue.isEmpty()){
					out.add(null);
					queue.offer(delimeterNode);
				}
			}
		}
		
		Deque<Integer> stack = new ArrayDeque<Integer>();
		boolean flag= true;
		for(Integer i : out){
			if(i==null){
				flag=!flag;
				continue;
			}
			if(flag){
				while(!stack.isEmpty()){
					System.out.print(stack.pop()+"-");
				}
				System.out.print(i+"-");
			}else{
				stack.push(i);
			}
		}
		
	}
	
	/**
	 * Least Common Ancestor
	 * @param root
	 * @param a
	 * @param b
	 * @return
	 */
	private static Tree LCA(Tree root,int a,int b){
		if(root==null){
			return root;
		}
		if(root.getData()==a||root.getData()==b){
			return root;
		}
		Tree left = LCA(root.getLeft(),a,b);
		Tree right = LCA(root.getRight(),a,b);
		if(left!=null && right!=null){
			return root;
		}
		return (left!=null?left:right);
	}
	
	private static void printAncestorsOfNode(Tree root,Deque<Integer> stack,int data){
		if(root==null){
			return;
		}
		stack.push(root.getData());
		if (root.getData()==data) {
			System.out.println(stack.toString());
		}else{
			printAncestorsOfNode(root.getLeft(), stack,data);
			printAncestorsOfNode(root.getRight(), stack,data);
		}
		stack.pop();
	}
	
	private static Tree mirror(Tree root){
		if(root!=null){
			Tree tmp = root.getLeft();
			root.setLeft(root.getRight());
			root.setRight(tmp);
			mirror(root.getLeft());
			mirror(root.getRight());
		}
		return root;
	}
	
	private static void printRootToLeafPaths(Tree root,Deque<Integer> stack){
		if(root==null){
			return;
		}
		stack.push(root.getData());
		if (root.getLeft() == null && root.getRight()==null) {
			System.out.println(stack.toString());
		}else{
			printRootToLeafPaths(root.getLeft(), stack);
			printRootToLeafPaths(root.getRight(), stack);
		}
		stack.pop();
	}
	
	/**
	 * My version of finding max width of a tree using level order traversal
	 * @param root
	 * @return
	 */
	private static int getMaxWidth(Tree root){
		Tree delimeterNode = new Tree(Integer.MIN_VALUE);
		if(root==null){
			return 0;
		}
		Deque<Tree> queue = new ArrayDeque<Tree>();
		queue.offer(root);
		queue.offer(delimeterNode);
		int width = 0;
		int maxWidth = 0;
		while(!queue.isEmpty()){
			Tree current = queue.poll();
			if(current!=null && current.getData()!=Integer.MIN_VALUE){
				width++;
				if(current.getLeft()!=null){
					queue.offer(current.getLeft());
				}
				if(current.getRight()!=null){
					queue.offer(current.getRight());
				}
			}else{
				if(width>maxWidth){
					maxWidth = width;
				}
				width=0;
				if(!queue.isEmpty()){
					queue.offer(delimeterNode);
				}
			}
		}
		return maxWidth;
	}
	
	
	private static int maxWidth(Tree root){
		int height = height(root);
		int maxWidth = 0;
		for(int i=0;i<height;i++){
			maxWidth = Math.max(maxWidth, getWidth(root, i));
		}
		return maxWidth;
	}
	
	private static int getWidth(Tree root,int depth){
		if(root==null){
			return 0;
		}else{
			if(depth==0)
				return 1;
			else
				return getWidth(root.getLeft(), depth-1)+getWidth(root.getRight(), depth-1);
		}
	}
	
	private static int diameter(Tree root){
		if(root==null){
			return 0;
		}
		int diameter1 = height(root.getLeft())+height(root.getRight())+1;
		int diameter2 = Math.max(diameter(root.getLeft()), diameter(root.getRight()));
		return Math.max(diameter1, diameter2);
	}
	
	/**
	 * DFS search in a binary tree has three sub-types
	 * 1) PreOrder
	 * 2) InOrder
	 * 3) PostOredr
	 * @param root
	 */
	private static void printDFS(Tree root, int type) {
		switch (type) {
			case 1:
				printPreOrder(root);
				break;
			case 2:
				printInOrder(root);
				break;
			case 3:
				printPostOrder(root);
				break;
		}
	}
	
	private static int height(Tree root){
		int height =  0;
		if(root==null){
			return height;
		}
		int heightLeftTree = height(root.getLeft());
		int heightRightTree = height(root.getRight());
		if(heightLeftTree>heightRightTree){
			height = heightLeftTree;
		}else{
			height = heightRightTree;
		}
		return height+1;
	}
	
	private static void printBSFInReverse(Tree root){
		if(root==null){
			System.out.println("Empty tree!");
		}
		Deque<Tree> queue = new ArrayDeque<Tree>();
		Deque<Integer> stack = new ArrayDeque<Integer>();
		queue.addLast(root);
		while(!queue.isEmpty()){
			Tree current = queue.poll();
			if(current!=null){
				stack.push(current.getData());
				if(current.getRight()!=null){
					queue.offer(current.getRight());
				}
				if(current.getLeft()!=null){
					queue.offer(current.getLeft());
				}
			}
		}
		while(!stack.isEmpty()){
			System.out.print(stack.pop()+"-");
		}
		
	}
	
	private static int size(Tree root){
		if(root==null){
			return 0;
		}
		int leftTreeSize = size(root.getLeft());
		int rightTreeSize = size(root.getRight());
		return leftTreeSize+rightTreeSize+1;
	}
	
	private static void  printPreOrder(Tree root){
		if(root!=null){
			System.out.print(root.getData()+"-");
			printPreOrder(root.getLeft());
			printPreOrder(root.getRight());
		}
	}
	
	private static void  printPostOrder(Tree root){
		if(root!=null){
			printPostOrder(root.getLeft());
			printPostOrder(root.getRight());
			System.out.print(root.getData()+"-");
		}
	}
	
	private static void  printInOrder(Tree root){
		if(root!=null){
			printInOrder(root.getLeft());
			System.out.print(root.getData()+"-");
			printInOrder(root.getRight());
		}
	}
	
	/**
	 * BFS in a binary tree is Level Order
	 * @param root
	 */
	private static void printBFS(Tree root){
		if(root==null){
			System.out.println("Empty Tree!");
		}
		Deque<Tree> queue = new ArrayDeque<Tree>();
		queue.addLast(root);
		while(!queue.isEmpty()){
			Tree current = queue.poll();
			if(current!=null){
				System.out.print(current.getData()+"-");
				if(current.getLeft()!=null){
					queue.offer(current.getLeft());
				}
				if(current.getRight()!=null){
					queue.offer(current.getRight());
				}
			}
		}
	}
}
