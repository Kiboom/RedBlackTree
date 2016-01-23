package test;

import tree.Node;
import tree.Tree;

public class deletionTest {
	static Node deleteNode;

	public static void main(String[] args) {
		Tree tree = new Tree();
		buildTree(tree, 10, "ASC");
		tree.delete(deleteNode);
		tree.print();
	}

	private static void buildTree(Tree tree, int nodeNum, String insertOrder) {
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
			deleteNode = newNode;
		}
		tree.print();
		System.out.println("\n\n");
	}
	
}
