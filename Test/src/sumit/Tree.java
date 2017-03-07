package sumit;

public class Tree {
	private int data;
	private Tree left;
	private Tree right;
	private int height;
	
	Tree(int data){
		this.data=data;
		this.left=null;
		this.right=null;
		this.height=1;
	}
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Tree getLeft() {
		return left;
	}
	public void setLeft(Tree left) {
		this.left = left;
	}
	public Tree getRight() {
		return right;
	}
	public void setRight(Tree right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	

}
