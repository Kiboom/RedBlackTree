package test;

import tree.Node;
import tree.Tree;

public class insertionTest {

	public static void main(String[] args) {
		littleDataTest(5, "ASC");
	}
	
	
	public static void littleDataTest(int nodeNum, String insertOrder) {
		Tree tree = new Tree();
		Node newNode;
		
		System.out.println("[" + nodeNum + "개의 노드를 입력하는 경우]\n");
		
		for(int i=0 ; i<nodeNum ; i++){
			switch (insertOrder) {
			case "ASC" : newNode = new Node(i); 
						 break;
			case "DSC" : newNode = new Node(nodeNum-i); 
						 break;
			case "RANDOM" : newNode = new Node((int)(Math.random()*nodeNum)); 
						 	break;
			case "EQUAL" : newNode = new Node(1); 
							break;
			default : System.out.println("ASC, DSC, RANDOM, EQUAL 중 하나를 입력하세요.");
					  return;
			}
			tree.insert(newNode);
		}
		tree.print();
		System.out.println("\n\n");
	}

}
