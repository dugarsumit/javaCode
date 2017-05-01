import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class Task2 {

	private static File dataFile = new File("data2.txt");

	/**
	 * key = word value = number of occurrences of that word in the file.
	 */
	private static Map<String, Integer> wordOccuranceMap = new HashMap<String, Integer>();

	/**
	 * list of nodes formed by words from the input data file.
	 */
	private static List<Node> nodes = new LinkedList<Node>();

	/**
	 * reference to the root node of the tree
	 */
	private static Node root = null;

	public static void main(String args[]) {
		readFile();
		createNodes();
		generateTree();
		BFS();
	}

	/**
	 * This is just for printing the final tree.
	 * "-" represents an empty node.
	 */
	public static void BFS() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (queue.size() > 0) {
			int levelNodes = queue.size();
			while (levelNodes > 0) {
				Node node = queue.remove();
				if (node != null) {
					System.out.print(node.getOccurrence() + " ");
					queue.add(node.getLeftNode());
					queue.add(node.getRightNode());
				} else {
					System.out.print("- ");
				}
				levelNodes--;
			}
			System.out.println();
		}
	}

	public static void printTree(Node leftNode, Node rightNode, Node parent) {
		System.out.println("parent=" + parent.toString());
		System.out.println("----------------------------------------");
	}

	/**
	 * Algorithm : 
	 * 1) keep reading from the nodes list until there are nodes in it. 
	 * 2) sort the list in ascending order. 
	 * 3) pick two nodes with the least occurrence value. If only one node left then make this node as the root
	 * 	  of the tree, otherwise combine the two nodes and generate a parent. 
	 * 4) remove the above two nodes from the nodes list and add the parent node to the list. 
	 * 5) go to step 1
	 */
	public static void generateTree() {
		while (!nodes.isEmpty()) {
			Collections.sort(nodes);
			Node leftNode = new Node(nodes.get(0));
			Node rightNode = null;
			if (nodes.size() > 1) {
				rightNode = new Node(nodes.get(1));
			}
			if (rightNode == null) {
				root = leftNode;
				nodes.remove(0);
			} else {
				String word = leftNode.getWord() + rightNode.getWord();
				Integer occurrence = leftNode.getOccurrence()
						+ rightNode.getOccurrence();
				Node parent = new Node(word, occurrence);
				parent.setLeftNode(leftNode);
				parent.setRightNode(rightNode);
				printTree(leftNode, rightNode, parent);
				nodes.remove(0);
				nodes.remove(0);
				nodes.add(parent);
			}
		}
	}

	/**
	 * creates an initial node from each word
	 */
	public static void createNodes() {
		Iterator<Entry<String, Integer>> it = wordOccuranceMap.entrySet()
				.iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> pair = it.next();
			nodes.add(new Node(pair.getKey(), pair.getValue()));
		}
	}

	/**
	 * 1) reads the input data file line by line 
	 * 2) extracts words from each line 
	 * 3) creates a map of words and their occurrences
	 */
	public static void readFile() {
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;

		try {
			fileReader = new FileReader(dataFile);
			bufferedReader = new BufferedReader(fileReader);
			try {
				String currentLine = null;
				while ((currentLine = bufferedReader.readLine()) != null) {
					String words[] = currentLine.split(" ");
					for (String word : words) {
						word = purifyWord(word);
						if (word != null) {
							Integer ocurrence = wordOccuranceMap.get(word);
							ocurrence = (ocurrence == null) ? 1 : ocurrence + 1;
							wordOccuranceMap.put(word, ocurrence);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * filters word by removing extra spaces around it
	 * @param word
	 * @return
	 */
	public static String purifyWord(String word) {
		String purifiedWord = null;
		if (!word.isEmpty() && word != " " && word != "\n") {
			purifiedWord = word.trim();
			purifiedWord = purifiedWord.toLowerCase();
		}
		return purifiedWord;
	}
}
