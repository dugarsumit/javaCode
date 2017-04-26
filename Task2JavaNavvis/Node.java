

public class Node implements Comparable<Node> {
	private String word;
	private Integer occurence;
	private Node leftNode;
	private Node rightNode;

	public Node(){
		
	}
	
	public Node(Node node){
		this.word = node.getWord();
		this.occurence = node.getOccurence();
		this.leftNode = node.getLeftNode();
		this.rightNode = node.getRightNode();
	}
	
	public Node(String word,Integer occurance){
		this.word = word;
		this.occurence = occurance;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getOccurence() {
		return occurence;
	}

	public void setOccurence(Integer occurence) {
		this.occurence = occurence;
	}

	public Node getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public Node getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

	public int compareTo(Node n) {
		if (occurence == n.occurence)
			return 0;
		else if (occurence > n.occurence)
			return 1;
		else
			return -1;
	}

	@Override
	public String toString() {
		return "Node [word=" + word + ", occurence=" + occurence
				+ ", leftNode=" + leftNode + ", rightNode=" + rightNode + "]";
	}

//	@Override
//	public String toString() {
//		return "["+occurence+","+leftNode+","+rightNode+"]";
//	}

	
}
