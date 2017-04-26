
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

public class Task2 {

	private static File dataFile = new File("data2.txt");
	private static Map<String, Integer> wordOccuranceMap = new HashMap<String, Integer>();
	private static List<Node> nodes = new LinkedList<Node>();
	private static Node root = null;
	
	public static void main(String args[]) {
		readFile();
		createNodes();
		generateTree();
		//printTree(nodes);
	}

	public static void printTree(List<Node> nodes){
		System.out.println(root.toString());
	}
	
	public static void generateTree(){
		List<Node> tempNodes = new LinkedList<Node>();
		tempNodes.addAll(nodes);
		while(!tempNodes.isEmpty()){
			Collections.sort(tempNodes);
			Node node1 = new Node(tempNodes.get(0));
			Node node2 = null;
			if(tempNodes.size()>1){
				node2 = new Node(tempNodes.get(1));
			}
			if(node2==null){
				root = node1;
				tempNodes.remove(0);
			}else{
				String word = node1.getWord()+node2.getWord();
				Integer occurrence = node1.getOccurence()+node2.getOccurence();
				Node parent = new Node(word,occurrence);
				parent.setLeftNode(node1);
				parent.setRightNode(node2);
				System.out.println("node1="+node1.toString());
				System.out.println("node2="+node2.toString());
				System.out.println("parent="+parent.toString());
				System.out.println("----------------------------------------");
				tempNodes.remove(0);
				tempNodes.remove(0);
				tempNodes.add(parent);
			}
		}
	}
	
	public static void createNodes(){
		Iterator<Entry<String, Integer>> it = wordOccuranceMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String,Integer> pair = it.next();
			nodes.add(new Node(pair.getKey(),pair.getValue()));
		}
	}
	
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
						Integer ocurrence = wordOccuranceMap.get(word);
						ocurrence = (ocurrence == null) ? 1 : ocurrence + 1;
						wordOccuranceMap.put(word, ocurrence);
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
}
