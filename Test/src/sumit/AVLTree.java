package sumit;

public class AVLTree {

	public static int balance(Tree root){
		if(root==null){
			return 0;
		}
		return height(root.getLeft())-height(root.getLeft());
	}
	
	public static int height(Tree root){
		if(root==null){
			return 0;
		}
		return root.getHeight();
	}
	
	public static Tree leftRotate(Tree pivot){
		if(pivot==null){
			return pivot;
		}
		Tree rightSubTreeOfPivot = pivot.getRight();
		Tree leftSubTree_Of_rightSubTreeOfPivot = rightSubTreeOfPivot.getLeft();
		rightSubTreeOfPivot.setLeft(pivot);
		pivot.setRight(leftSubTree_Of_rightSubTreeOfPivot);
		rightSubTreeOfPivot.setHeight(Math.max(height(rightSubTreeOfPivot.getLeft()), height(rightSubTreeOfPivot.getRight()))+1);
		pivot.setHeight(Math.max(height(pivot.getLeft()), height(pivot.getRight()))+1);
		return rightSubTreeOfPivot;
	}
	
	public static Tree rightRotate(Tree pivot){
		if(pivot==null){
			return pivot;
		}
		Tree leftSubTreeOfPivot = pivot.getLeft();
		Tree rightSubTree_Of_leftSubTreeOfPivot = leftSubTreeOfPivot.getRight(); 
		leftSubTreeOfPivot.setRight(pivot);
		pivot.setLeft(rightSubTree_Of_leftSubTreeOfPivot);
		leftSubTreeOfPivot.setHeight(Math.max(height(leftSubTreeOfPivot.getLeft()), height(leftSubTreeOfPivot.getRight()))+1);
		pivot.setHeight(Math.max(height(pivot.getLeft()), height(pivot.getRight()))+1);
		return leftSubTreeOfPivot;
	}
	
	public static Tree insert(Tree root,int data){
		if(root==null){
			return new Tree(data);
		}
		
		/**
		 * Insert node into correct position
		 */
		if(data<root.getData()){
			root.setLeft(insert(root.getLeft(), data));
		}else{
			root.setRight(insert(root.getRight(), data));
		}
		
		/**
		 * Update heights of ancestors
		 */
		root.setHeight(Math.max(height(root.getLeft()), height(root.getRight()))+1);
		
		/**
		 * Get Balance and perform rotations if the tree is unbalanced
		 */
		int balance = balance(root);
		
		/**
		 * Left Left Case : Solved By Single Right rotation
		 */
		if(balance>1 && data<root.getLeft().getData()){
			return rightRotate(root);
		}
		
		/**
		 * Right Right Case : Solved By Single Left Rotation
		 */
		if(balance<-1 && data > root.getRight().getData()){
			return leftRotate(root);
		}
		
		/**
		 * Left Right Case : Solved By Left-then-Right Rotation
		 */
		if(balance>1 && data>root.getLeft().getData()){
			root.setLeft(leftRotate(root.getLeft()));
			return rightRotate(root);
		}
		
		/**
		 * Right Left Case : Solved By Right-then-Left Rotation
		 */
		if(balance<-1 && data<root.getRight().getData()){
			root.setRight(rightRotate(root.getRight()));
			return leftRotate(root);
		}
		
		return root;
	}
}
