public class Node implements Comparable<Node> {
	private String word;
	private Integer occurrence;
	private Node leftNode;
	private Node rightNode;

	public Node(Node node) {
		this.word = node.getWord();
		this.occurrence = node.getOccurrence();
		this.leftNode = node.getLeftNode();
		this.rightNode = node.getRightNode();
	}

	public Node(String word, Integer occurance) {
		this.word = word;
		this.occurrence = occurance;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Integer getOccurrence() {
		return occurrence;
	}

	public void setOccurrence(Integer occurence) {
		this.occurrence = occurence;
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
		if (occurrence == n.getOccurrence())
			return 0;
		else if (occurrence > n.getOccurrence())
			return 1;
		else
			return -1;
	}

	@Override
	public String toString() {
		String leftNodeStr = "NULL";
		String rightNodeStr = "NULL";
		if (leftNode != null) {
			leftNodeStr = "{word=" + leftNode.getWord() + " occurrence="
					+ leftNode.getOccurrence() + "}";
		}
		if (rightNode != null) {
			rightNodeStr = "{word=" + rightNode.getWord() + " occurrence="
					+ rightNode.getOccurrence() + "}";
		}
		return "[word=" + word + ", occurrence=" + occurrence + ", leftNode="
				+ leftNodeStr + ", rightNode=" + rightNodeStr + "]";
	}
}
